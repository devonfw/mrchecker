def call(){

//# Build deployable app
}



def private void deployToLocalRepo(String version){
    stage('Deploy - local repo') {
        if (version == null || version.isEmpty()){
            sh"""
                export BASE_PATH=.
                cd ${env.APP_WORKSPACE}
                mvn -q install -DskipTests=true ${env.MVN_PARAMETERS}
            """
        } else {
            sh"""
                export BASE_PATH=.
                cd ${env.APP_WORKSPACE}
                mvn -q install -Dversion=${version} -DskipTests=true ${env.MVN_PARAMETERS}
            """
        }
        archiveArtifacts allowEmptyArchive: true, artifacts: "${env.APP_WORKSPACE}target/*.jar";
    }
    
}

def private void deployToRemoteRepo(String version) {	
    stage('Deploy - nexus repo') {
        withCredentials([
            usernamePassword(credentialsId: 'ossrh', passwordVariable: 'MAVEN_PASSWORD', usernameVariable: 'MAVEN_USER'), 
            string(credentialsId: 'GPG_PASSWORD', variable: 'GPG_PASSWORD'), zip(credentialsId: 'gpg_sign_mrchecker', variable: 'GPG_HOMEDIR')
            ]){ 
                if (version == null || version.isEmpty()){
                    sh"""
                        cd ${env.APP_WORKSPACE}
                        mvn -q -DskipTests=true -P release deploy ${env.MVN_PARAMETERS}
                    """
                } else{
                    sh"""
                        cd ${env.APP_WORKSPACE}
                        mvn -q -Dversion=${version} -DskipTests=true -P release deploy ${env.MVN_PARAMETERS}
                    """
                }
            }
	}
}

return this