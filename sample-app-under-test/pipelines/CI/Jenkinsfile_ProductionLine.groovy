node(){
	
	//Set Jenkins run parameters
	properties([
		parameters([
			string(defaultValue: 'develop', description: 'Execute job on given branch', name: 'WORKING_BRANCH'),  
			string(defaultValue: '*', description: '''What tests to run
HelloWorld - run test class -HelloWorld-
Hel*orld - run all test classes start with -Hel- and end with -orld-  
TestSquare,TestCi*le -  run test class TestSquare  and  all test classes start with -Hel- and end with -orld-
TestCircle#mytest  - run test class TestCircle and only test method -mytest-
TestCircle#testOne+testTwo - run test class TestCircle and test method -testOne-  and -testTwo-
TestCircle#test* - run test class TestCircle and all test methods start with -test-
MORE information here http://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html''', name: 'TEST_NAME'), 
			string(defaultValue: 'DEV', description: 'Value taken from   environment.csv  file', name: 'ENVIRONMENT'), 
			string(defaultValue: '5', description: 'Number of concurrent test execution', name: 'THREAD_COUNT'), 
			string(defaultValue: 'http://selenium-hub-core:4444/wd/hub', description: 'Optional variable. Used only in Selenium execution, for Selenium Grid', name: 'SELENIUM_HUBURL'), 
			string(defaultValue: '', description: 'Browser options. Possible values = "headless;param2=value2;testEquals=FirstEquals=SecondEquals;--testMe" ', name: 'SELENIUM_BROWSER_OPTIONS'),
			choice(choices: 'chrome\nfirefox\nie', description: 'Optional variable. Used only in Selenium execution, for Browser type', name: 'SELENIUM_BROWSER'),  
			string(defaultValue: 'mrchecker-app-under-test/', description: 'Execute job for given Module. Example mrchecker-app-under-test/ ', name: 'APP_WORKSPACE'),
			string(defaultValue: 'origin/develop', description: 'Optional variable. What is your "master" branch', name: 'MAIN_BRANCH'), 
			string(defaultValue: 'http://gitlab-core:80/gitlab/devon/mrchecker.git', description: 'Optional variable. Which repo to run', name: 'GIT_REPO'), 
			string(defaultValue: '', description: 'Optional list of mvn parameters, example -DskipTests=true -Dtest=*', name: 'MVN_PARAMETERS')
			]), 
			pipelineTriggers([])
		]);
	

	timestamps {		
            try{
		    stagePrepareEnv(params);
		    stageGitPull();
		    setJenkinsJobDescription();
		    boolean isWorkingBranchMaster = isWorkingBranchMaster();
    
            def mvn_version = 'Maven3'
            withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {
                    stageBuildCompile();
                    stageIntegrationTests();
                }
            currentBuild.result = 'SUCCESS';
        } catch (Exception e) {
            sendMail(e);
            error 'Error: ' + e
            currentBuild.result = 'FAILURE';
            
        }
    }
}

//==================================== END OF PIPELINE ===================================

def private void overrideProperties(params){
	for (param in params){
		if (env.(param.key) == null ){
			echo "Adding parameter '${param.key}' with default value: '${param.value}' "
			env.(param.key) = param.value;
		} else {
			echo "Parameter '${param.key}' has overriden value: '${env.(param.key)}' "
		}
	}
	echo sh(script: "env | sort", returnStdout: true)
}


def private void stagePrepareEnv(properties){
    stage('Prepare environment'){
        overrideProperties(properties)
        setJenkinsJobVariables();
        setWorkspace();
        echo sh(script: "env | sort", returnStdout: true)
    }
}

def private void setJenkinsJobVariables(){
    env.JOB_NAME_UPSTREAM="Mr Checker"
	env.BUILD_DISPLAY_NAME_UPSTREAM = env.BUILD_TAG
	env.BUILD_URL_UPSTREAM = env.BUILD_URL  + 'console'
	env.GIT_CREDENTIALS = ""
	env.JENKINS_CREDENTIALS = "" //jenkins_slave user;
    

} 



/**
Creates an environment variable with path to local workspace. This is required because when using pipeline plugin 
Jenkins does not create the WORKSPACE env var
**/
void setWorkspace(){ 
    env.WORKSPACE_LOCAL = sh(returnStdout: true, script: 'pwd').trim();
    echo("Variable WORKSPACE LOCAL: " + env.WORKSPACE_LOCAL);
    env.PROJECT_HOME = "${env.WORKSPACE_LOCAL}/${env.APP_WORKSPACE}/";
    echo("Variable Project home: " + env.PROJECT_HOME);
	env.SUBMODULES_DIR = "${env.PROJECT_HOME}/pipelines/CI/submodules";
    echo("Variable submodules: " + env.SUBMODULES_DIR);
	env.COMMONS_DIR = "${env.PROJECT_HOME}/pipelines/commons";
    echo("Variable commons: " + env.COMMONS_DIR);

    try{
        env.APP_WORKSPACE = APP_WORKSPACE;
        echo("env.APP_WORKSPACE=${env.APP_WORKSPACE}");
    } catch (Exception e){
        error("Setup application folder used for CI execution.\nExample APP_WORKSPACE=allure-core-module/")
    }    
    echo("After env variables setter");
}

def private void setJenkinsJobDescription(){
    def utils = load "${env.COMMONS_DIR}/Utils.groovy";
    utils.setJenkinsDescription("${env.WORKING_BRANCH}");
}

def private String isWorkingBranchMaster(){
    def utils = load "${env.COMMONS_DIR}/Utils.groovy";
    isWorkingBranchMaster = utils.isBranchType("${env.MAIN_BRANCH}")
    echo("isWorkingBranchMaster: " + isWorkingBranchMaster);
    return isWorkingBranchMaster;
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

void stageIntegrationTests(){
	echo("stageIntegrationTests");
	//Load IntegrationTests file and run call() method
	def module = load "${env.SUBMODULES_DIR}/IntegrationTests.groovy";
	module();
}

void sendMail(Exception e){
	echo("sendMail")
	//Load Mail file and run call() method
	def module = load "${env.COMMONS_DIR}/MailSender.groovy";
	module.sendMail(e.toString());
}

/**
Pull sources from repository
**/
void stageGitPull(){
    stage('Git Pull'){
        //Clone jenkins files	
        git branch: "${env.WORKING_BRANCH}", credentialsId: "${env.GIT_CREDENTIALS}", url: "${env.GIT_REPO}"
 
        //Load GitPull file
        def module = load "${env.SUBMODULES_DIR}/GitPull.groovy";
        module.setGitAuthor();
        module.tryMergeWithBranch(env.MAIN_BRANCH);
    }       
}

return this
