node(){
	
	//Set Jenkins run parameters
	properties([
		parameters([
			string(defaultValue: 'mrchecker-framework-modules/', description: 'Execute job for given Module. Example mrchecker-framework-modules/ ', name: 'APP_WORKSPACE'),
			string(defaultValue: 'develop', description: 'Execute job on given branch', name: 'WORKING_BRANCH'), 
			booleanParam(defaultValue: false, description: '''Should given job be deployed to Remote Nexus repository ? 
#1. Go to Nexus staging repo 
https://oss.sonatype.org/#stagingRepositories  and release    WAIT ~15min to update Maven Central Repo
#2. Verify release OR snapshot repo
Release
https://oss.sonatype.org/content/repositories/releases/com/capgemini/ntc/
https://oss.sonatype.org/content/groups/public/com/capgemini/ntc/
https://repo.maven.apache.org/maven2/com/capgemini/ntc/
Snapshot
https://oss.sonatype.org/content/repositories/snapshots/com/capgemini/ntc/''', name: 'IS_TO_DEPLOY_REMOTE_NEXUS'), 
			string(defaultValue: '', description: 'Application version number. If empty, pom.xml version will be taken', name: 'VERSION'), 
			string(defaultValue: 'origin/develop', description: 'Optional variable. What is your "master" branch', name: 'MAIN_BRANCH'), 
			string(defaultValue: 'https://github.com/devonfw/devonfw-testing.git', description: 'Optional variable. Which repo to run', name: 'GIT_REPO'), 
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
    
            docker.image('lucst/devonfwe2e:v2-0.4').inside(){
                    stageDeploy(env.VERSION);
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
void stageDeploy(String version){
	echo("stageDeploy");
	echo("version: -${version}-");
    
    //Load Deploy process and run call() method
	def module = load "${env.SUBMODULES_DIR}/Deploy.groovy";
	
    module.deployToLocalRepo(version);
    if ( env.IS_TO_DEPLOY_REMOTE_NEXUS.toBoolean() ){
        module.deployToRemoteRepo(version);
    }
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
