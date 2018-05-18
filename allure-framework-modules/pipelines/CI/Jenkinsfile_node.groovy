
def properties = {
        TEST_NAME : '*',
        THREAD_COUNT : '8',
        MVN_PARAMETERS : '',
        ENVIRONMENT : 'DEV',
        SELENIUM_HUBURL : 'http://10.40.234.103:4444/wd/hub',
        SELENIUM_BROWSER : 'chrome',
        GIT_REPO : 'https://github.com/devonfw/devonfw-testing.git',
        MAIN_BRANCH : 'develop',
        WORKING_BRANCH = 'develop'
}

node(){
	
    stagePrepareEnv(properties);
    stageGitPull();
    
    def utils = load "${env.SUBMODULES_DIR}/Utils.groovy";
    timestamps {     
        try{
            docker.image('lucst/devonfwe2e:v2-0.3').inside(){
                    stageBuildCompile();
                    stageUnitTests();
                    stageDeploy();
           
                    //Disabled tryMerge(). Finally enable it          
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

def private void overrideProperties(properties){
	for (param in properties){
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
	env.GIT_CREDENTIALS = "gitchudzik"
    env.JENKINS_CREDENTIALS = "0c089c76-f103-4f97-8d2d-c31830d2c21d" //jenkins_slave user;
    

} 



/**
Creates an environment variable with path to local workspace. This is required because when using pipeline plugin 
Jenkins does not create the WORKSPACE env var
**/
void setWorkspace(){ 
    env.WORKSPACE_LOCAL = sh(returnStdout: true, script: 'pwd').trim();
    echo("Variable WORKSPACE LOCAL: " + env.WORKSPACE_LOCAL);
    env.PROJECT_HOME = "${env.WORKSPACE_LOCAL}/allure-framework-modules/";
    echo("Variable Project home: " + env.PROJECT_HOME);
	env.SUBMODULES_DIR = "${env.PROJECT_HOME}/pipelines/CI/submodules";
    echo("Variable submodules: " + env.SUBMODULES_DIR);
	env.COMMONS_DIR = "${env.PROJECT_HOME}/pipelines/commons";
    echo("Variable commons: " + env.COMMONS_DIR);
    
   
    
    env.NON_DEVELOP_BRANCH = currentBuild.description != null && !currentBuild.description.isEmpty() && !currentBuild.description.equals("$env.MAIN_BRANCH");
    echo("Variable NON_DEVELOP_BRANCH: " + env.NON_DEVELOP_BRANCH);

    try{
            env.APP_WORKSPACE = APP_WORKSPACE;
            echo("env.APP_WORKSPACE=${env.APP_WORKSPACE}");
        } catch (Exception e){
            error("Setup application folder used for CI execution.\nExample APP_WORKSPACE=allure-core-module/")
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
	//Load Deploy process and run call() method
	def module = load "${env.SUBMODULES_DIR}/Deploy.groovy";
	module();
}

void sendMail(Exception e){
	echo("sendMail")
	//Load Mail file and run call() method
	def module = load "${env.COMMONS_DIR}/MailSender.groovy";
	module(e);
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
        /* module.tryMerge(); */
    }       
}

return this
