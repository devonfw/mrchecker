def call(){

//# Build deployable app
    deployToLocalRepo();
}



def private void deployToLocalRepo(){
    stage('Install to local repo'){
        sh"""
            export BASE_PATH=.
            cd ${env.APP_WORKSPACE}
            mvn install -DskipTests=true ${env.MVN_PARAMETERS}
        """
        archiveArtifacts"${env.APP_WORKSPACE}target/*.jar"
    }
}

return this