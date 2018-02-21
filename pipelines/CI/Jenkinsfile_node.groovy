node(){
	
    setJobNameVariables();
    stagePrepareEnv();
    stageGitPull();
	echo("After git pull");
   // setCurrentBranchName();
    
    def utils = load "${env.SUBMODULES_DIR}/Utils.groovy";	
    try{
    	echo("Jenkins node script run")
	//	stashNotification("INPROGRESS");
		utils.generateUserIDVariable(); //Generate USER_ID and USER_GROUP
        docker.image('docker.com/devonfwe2e:v1-0.0').inside("-u ${env.USER_ID}:${env.USER_GROUP}"){
			//withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "${env.ARTIFACTORY_USER}", passwordVariable: 'ARTIFACTORY_PASSWORD', usernameVariable: 'ARTIFACTORY_USERNAME']]) {
                //stageBuildCompile();
                 //stageUnitTestsAndStaticAnalyze();
                 stageIntegrationTests();
           // }
        }
		
		//stashNotification("SUCCESSFUL");
        currentBuild.result = 'SUCCESS';
	} catch (Exception e) {
		//stashNotification("FAILED");
		
		sendMail(e);
		error 'Error: ' + e
        currentBuild.result = 'FAILURE';
	}
    
}

private void setCurrentBranchName(){
	echo "echo get current branch"
	echo "env.STASH_CREDENTIALS=${env.STASH_CREDENTIALS}";
	echo "env.WORKING_BRANCH=${env.WORKING_BRANCH}"
	
	if(env.WORKING_BRANCH && env.WORKING_BRANCH != "null"){
		
		def BRANCH_NAME=env.WORKING_BRANCH;
		echo "branch is present $BRANCH_NAME"
		withCredentials([[$class: 'UsernamePasswordBinding', credentialsId: "${env.STASH_CREDENTIALS}", variable: 'USER_NOTIFICATION']]) {
			SOURCE_BUILD = env.BUILD_URL;
			echo SOURCE_BUILD
			env.SOURCE_BUILD = "${SOURCE_BUILD}"
			def revision = "${BRANCH_NAME}"
			sh '''
			echo "$WORKING_BRANCH"
			curl -s -u $USER_NOTIFICATION "$SOURCE_BUILD"consoleText | grep "Checking out Revision" | sed -nr \'s/.*[^ ]origin\\/([^ ,\\)]+).*/\\1/p\'
            curl -s -u $USER_NOTIFICATION --data-urlencode "description=$WORKING_BRANCH" "$SOURCE_BUILD"submitDescription
			'''
		}
	}else{
		echo "branch is not present"
		withCredentials([[$class: 'UsernamePasswordBinding', credentialsId: "${env.STASH_CREDENTIALS}", variable: 'USER_NOTIFICATION']]) {
			SOURCE_BUILD = env.BUILD_URL;
			echo SOURCE_BUILD
			env.SOURCE_BUILD = "${SOURCE_BUILD}"
			sh '''
                revision=$(curl -s -u $USER_NOTIFICATION "$SOURCE_BUILD"consoleText | grep "Checking out Revision" | sed -nr \'s/.*[^ ]origin\\/([^ ,\\)]+).*/\\1/p\')
                curl -s -u $USER_NOTIFICATION --data-urlencode "description=$revision" "$SOURCE_BUILD"submitDescription
                '''
		}
		branchName = currentBuild.description;
		echo("branchName:" + branchName)
		env.WORKING_BRANCH = branchName;
	}
}

void getCurrentBranchName(){
    withCredentials([[$class: 'UsernamePasswordBinding', credentialsId: "${env.STASH_CREDENTIALS}", variable: 'USER_NOTIFICATION']]) {
               SOURCE_BUILD = env.BUILD_URL; 
               env.SOURCE_BUILD = "${SOURCE_BUILD}"
               sh '''
                revision=$(curl -s -u $USER_NOTIFICATION "$SOURCE_BUILD"consoleText | grep "Checking out Revision" | sed -nr \'s/.*[^ ]origin\\/([^ ,\\)]+).*/\\1/p\')
                echo "Found commit ID $revision"
                curl -s -u $USER_NOTIFICATION --data-urlencode "description=$revision" "$SOURCE_BUILD"submitDescription
                '''



            SOURCE_BUILD = env.BUILD_URL;
			echo SOURCE_BUILD
			env.SOURCE_BUILD = "${SOURCE_BUILD}"
			def revision = "${BRANCH_NAME}"
			sh '''
			echo "$WORKING_BRANCH"
			curl -s -u $USER_NOTIFICATION "$SOURCE_BUILD"consoleText | grep "Checking out Revision" | sed -nr \'s/.*[^ ]origin\\/([^ ,\\)]+).*/\\1/p\'
            echo "Found commit ID $WORKING_BRANCH"
             curl -s -u $USER_NOTIFICATION --data-urlencode "description=$WORKING_BRANCH" "$SOURCE_BUILD"submitDescription
			'''


            }    
    branchName = currentBuild.description;
    echo("branchName:" + branchName)
    return branchName;
}

void stagePrepareEnv(){
    stage('Prepare environment'){
        cleanWorkspace();
        setWorkspace();
	}
}

void setJobNameVariables(){
    echo("BeforeSetJobNameVariables");
    env.JOB_NAME_UPSTREAM="Training project"
	env.BUILD_DISPLAY_NAME_UPSTREAM = env.BUILD_TAG
	env.BUILD_URL_UPSTREAM = env.BUILD_URL  + 'console'
	//env.GIT_CREDENTIALS=""
	//env.STASH_CREDENTIALS=""
	//env.ARTIFACTORY_CREDENTIALS=""
	//env.SONAR_CREDENTIALS_ID=''
	//env.USER_CREDENTIALS=env.STASH_CREDENTIALS
    env.ARTIFACTORY_USER = ''
    echo("AfterSetJobNameVariables");
}

def private void cleanWorkspace(){
	env.checkDir = sh(returnStdout: true, script: 'pwd').trim();
	echo("Data removed from: " + env.checkDir);
	sh "rm -rf *";
}

/**
Creates an environment variable with path to local workspace. This is required because when using pipeline plugin 
Jenkins does not create the WORKSPACE env var
**/
void setWorkspace(){
    // sh 'pwd > pwd.current'
    // env.WORKSPACE_LOCAL = readFile('pwd.current').trim();
    
    env.WORKSPACE_LOCAL = sh(returnStdout: true, script: 'pwd').trim();
    echo("Variable WORKSPACE LOCAL: " + env.WORKSPACE_LOCAL);
    env.PROJECT_HOME = "${env.WORKSPACE_LOCAL}/allure-app-under-test";
    echo("Variable Project home: " + env.PROJECT_HOME);
	env.SUBMODULES_DIR = "${WORKSPACE_LOCAL}/pipelines/CI/submodules";
    echo("Variable submodules: " + env.SUBMODULES_DIR);
	env.COMMONS_DIR = "${WORKSPACE_LOCAL}/pipelines/commons";
    echo("Variable commons: " + env.COMMONS_DIR);
    env.FEATURE_BUILD = currentBuild.description != null && !currentBuild.description.isEmpty() && !currentBuild.description.equals('develop');
    echo("Variable FEATURE_BUILD: " + env.FEATURE_BUILD);

    try{
		env.ENVIRONMENT = ENVIRONMENT;
        echo("env.ENVIRONMENT=${env.ENVIRONMENT}");
    } catch (Exception e){
		echo("ENVIRONMENT was not overwritten");
		env.ENVIRONMENT = "DEV";
    } 

    try{
		env.BRANCH_TYPE_OVERRIDE = BRANCH_TYPE_OVERRIDE;
    } catch (Exception e){
		echo("BRANCH_TYPE_OVERRIDE was not overwritten");
		env.BRANCH_TYPE_OVERRIDE = "feature/";
    } 
    
    try{
		env.WORKING_BRANCH = WORKING_BRANCH.trim().isEmpty() ? env.GIT_BRANCH : WORKING_BRANCH;
        echo("env.WORKING_BRANCH=${env.WORKING_BRANCH}");
    } catch (Exception e){
		echo("WORKING_BRANCH was not overwritten");
		env.WORKING_BRANCH = "develop";
    }

    try{
		env.GIT_REPO = GIT_REPO;
    } catch (Exception e){
		echo("GIT_REPO was not overwritten");
		env.GIT_REPO="https://github.com/devonfw/devonfw-testing.git"
    }
    
    try{
		env.SONAR_RESOURCE_ID = SONAR_RESOURCE_ID;
		echo("env.SONAR_RESOURCE_ID=${env.SONAR_RESOURCE_ID}");
    } catch (Exception e){
		echo("SONAR_RESOURCE_ID was not overwritten");
		env.SONAR_RESOURCE_ID="Maven_Training";
    }
    env.SONAR_PROJECT_NAME=env.SONAR_RESOURCE_ID
    
    try{
		env.SONAR_URL = SONAR_URL;
		echo("env.SONAR_URL=${env.SONAR_URL}");
    } catch (Exception e){
		echo("SONAR_URL was not overwritten");
		env.SONAR_URL="http://\$(boot2docker ip):9000";
    }
    
    try{
		env.BUILD_VERSION_BATCH = BUILD_VERSION_BATCH;
		echo("env.BUILD_VERSION_BATCH=${env.BUILD_VERSION_BATCH}");
    } catch (Exception e){
		echo("BUILD_VERSION_BATCH was not overwritten");
		env.BUILD_VERSION_BATCH="1.0";
    }
    
    /**
    SONAR_ANALYSIS_MODE - paremeter is to define how sonar should generate report
    publish - is to created for the first time report
    issues - is to update report, is a "preview" equivalent intended for use by tools
    preview -  is typically used to determine whether code changes are good enough to move forward 
    **/
    try{
		env.SONAR_ANALYSIS_MODE = SONAR_ANALYSIS_MODE;
		echo("env.SONAR_ANALYSIS_MODE=${env.SONAR_ANALYSIS_MODE}");
    } catch (Exception e){
		echo("SONAR_ANALYSIS_MODE was not overwritten");
		env.SONAR_ANALYSIS_MODE="publish";
    }
    
    try{
		env.SONAR_ACCEPTABLE_NUMBER_OF_BLOCKER_ISSUES = SONAR_ACCEPTABLE_NUMBER_OF_BLOCKER_ISSUES;
		echo("env.SONAR_ACCEPTABLE_NUMBER_OF_BLOCKER_ISSUES=${env.SONAR_ACCEPTABLE_NUMBER_OF_BLOCKER_ISSUES}");
    } catch (Exception e){
		echo("SONAR_SONAR_ACCEPTABLE_NUMBER_OF_BLOCKER_ISSUES was not overwritten");
		env.SONAR_ACCEPTABLE_NUMBER_OF_BLOCKER_ISSUES="0";
    }
    
     env.SONAR_REPORT_URL = "${env.SONAR_URL}/dashboard/index?id=${env.SONAR_RESOURCE_ID}" 

	try{
		env.MINIMUM_LINE_COVERAGE = MINIMUM_LINE_COVERAGE;
		echo("env.MINIMUM_LINE_COVERAGE=${env.MINIMUM_LINE_COVERAGE}");
    } catch (Exception e){
		echo("MINIMUM_LINE_COVERAGE was not overwritten");
		env.MINIMUM_LINE_COVERAGE="60";
    }

	try{
		env.MINIMUM_BRANCH_COVERAGE = MINIMUM_BRANCH_COVERAGE;
		echo("env.MINIMUM_BRANCH_COVERAGE=${env.MINIMUM_BRANCH_COVERAGE}");
    } catch (Exception e){
		echo("MINIMUM_BRANCH_COVERAGE was not overwritten");
		env.MINIMUM_BRANCH_COVERAGE="80";
    }

    echo("After env variables setter");

}

/**
Verify that the code builds without errors
**/  
void stageBuildCompile(){
	echo("stageBuildCompile")
	//Load BuildCompile file and run call() method
	def module = load "${env.SUBMODULES_DIR}/BuildCompile.groovy";
	module();	
}

/**
Run unit tests and static analysis in parallel. Publish the results after both finish.
**/	
void stageUnitTestsAndStaticAnalyze(){
	parallel(
        unitTests: {
            stageUnitTests();
        }, 	
        staticAnalysis: {
            stageStaticAnalyze();
        }
    )
	stageStaticAnalyzeSonar();
}	

void stageUnitTests(){
	echo("stageUnitTests")
	//Load UnitTests file and run call() method
	def module = load "${env.SUBMODULES_DIR}/UnitTests.groovy";
	module();
}

void stageStaticAnalyze(){
	echo("stageStaticAnalyze")
	//Load StaticAnalyze file and run call() method
	def module = load "${env.SUBMODULES_DIR}/StaticAnalyze.groovy";
	module();
}	

void stageStaticAnalyzeSonar(){
	echo("stageStaticAnalyzeSonar")
	//Load StaticAnalyzeSonar file and run call() method
	def module = load "${env.SUBMODULES_DIR}/StaticAnalyzeSonar.groovy";
	module();
}	

/**
Run integration tests suite
**/	
void stageIntegrationTests(){
	stage('Integration Tests'){
        //Load IntegrationTests file and run call() method
        echo("IntegrationTests");
        def module = load "${env.SUBMODULES_DIR}/IntegrationTests.groovy";
        try{ 
            module();
        }
        finally{ 
            publishHtml();
        }	
    }
}

void publishHtml(){ 
	if (fileExists('target/site/allure-report/index.html')) {
        publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/site/allure-report', reportFiles: 'index.html', reportName: "allure"]);
    } else {
        echo("Any HTML report found.");
    }
    
   try{     
        step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml']);
   }catch (e){ 
        echo("Any JUnit HTML report found.");
   }
   try{
    step([$class: 'CucumberTestResultArchiver', ignoreBadSteps: true, testResults: '**/target/cucumber-parallel/*.json'])
    step([$class: 'CucumberReportPublisher', fileExcludePattern: '', fileIncludePattern: '*.json', ignoreFailedTests: true, jenkinsBasePath: '', jsonReportDirectory: 'target/cucumber-parallel', missingFails: false, parallelTesting: false, pendingFails: false, skippedFails: false, undefinedFails: false])
   }catch(e){
    echo("Any Cucumber report ")
    }
}

void sendMail(Exception e){
	echo("sendMail")
	//Load Mail file and run call() method
	def module = load "${env.COMMONS_DIR}/MailSender.groovy";
	module(e);
}

/**
Send stash notification with given state 
**/
void stashNotification(String state){
	echo("stashNotification")
	//Load StashNotification file and run call() method
	def module = load "${env.COMMONS_DIR}/StashNotification.groovy";
	module(state);
}

/**
Pull sources from repository
**/
void stageGitPull(){
	
    //Set branch name   
    
    //Clone jenkins files	
	git branch: "${env.WORKING_BRANCH}", credentialsId: "${env.GIT_CREDENTIALS}", url: "${env.GIT_REPO}"
	echo("enter- stageGitPull")

	sh "pwd"
	sh "echo check directory status: "
	sh "ls"

    boolean isCurrentBranchFeature = "feature/".equals(env.BRANCH_TYPE_OVERRIDE) ? true : false;
	echo("isCurrentBranchFeature= ${isCurrentBranchFeature}");
	


    //Load GitPull file and run call() method
	def module = load "${env.SUBMODULES_DIR}/GitPull.groovy";
	module();
    if(isCurrentBranchFeature){ 
		module.tryMerge();
	}   
}

return this
