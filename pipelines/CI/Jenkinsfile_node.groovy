node(){
	
    setJobNameVariables();
    stagePrepareEnv();
    stageGitPull();
    
    //def utils = load "${env.SUBMODULES_DIR}/Utils.groovy";
    try{
	//	utils.generateUserIDVariable(); //Generate USER_ID and USER_GROUP
        docker.image('lucst/devonfwe2e:v2-0.0').inside("-u root:root"){
				stageBuildCompile();
				stageUnitTests();
				stageDeploy();
        }

        currentBuild.result = 'SUCCESS';
	} catch (Exception e) {
		//sendMail(e);
		error 'Error: ' + e
        currentBuild.result = 'FAILURE';
	}
    
}


void stagePrepareEnv(){
    stage('Prepare environment'){
        //cleanWorkspace();
        setWorkspace();
	}
}

void setJobNameVariables(){
    env.JOB_NAME_UPSTREAM="Mr Checker"
	env.BUILD_DISPLAY_NAME_UPSTREAM = env.BUILD_TAG
	env.BUILD_URL_UPSTREAM = env.BUILD_URL  + 'console'
	env.GIT_CREDENTIALS = "gitchudzik"
	env.STASH_CREDENTIALS="gitchudzik"
	env.USER_CREDENTIALS=env.STASH_CREDENTIALS
}

def private void cleanWorkspace(){
	sh "sudo rm -rf *";
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
    env.PROJECT_HOME = "${env.WORKSPACE_LOCAL}/allure-app-under-test/";
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
		env.HUBURL = HUBURL;
		echo("env.HUBURL=${env.HUBURL}");
	} catch (Exception e){
		echo("HUBURL was not overwritten");
		env.HUBURL = "http://10.40.234.103:4444/wd/hub";
	}

	try{
		env.TESTMODULE = TESTMODULE;
		echo("env.TESTMODULE=${env.TESTMODULE}");
	} catch (Exception e){
		echo("TESTMODULE was not overwritten");
		env.TESTMODULE = "allure-app-under-test";
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

void stageUnitTests(){
	echo("stageUnitTests");
	//Load UnitTests file and run call() method
	def module = load "${env.SUBMODULES_DIR}/UnitTests.groovy";
	module();
}

void stageDeploy(){
	echo("stageDeploy");
	//Load UnitTests file and run call() method
	def module = load "${env.SUBMODULES_DIR}/Deploy.groovy";
	module();
}

//void sendMail(Exception e){
//	echo("sendMail")
//	//Load Mail file and run call() method
//	def module = load "${env.COMMONS_DIR}/MailSender.groovy";
//	module(e);
//}

/**
Pull sources from repository
**/
void stageGitPull(){
	
    //Set branch name   
    
    //Clone jenkins files	
	git branch: "${env.WORKING_BRANCH}", credentialsId: "${env.GIT_CREDENTIALS}", url: "${env.GIT_REPO}"

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
