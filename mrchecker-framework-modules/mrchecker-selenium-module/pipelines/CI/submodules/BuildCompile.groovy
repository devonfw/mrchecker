/**
Verify that the code builds without errors
**/    
def call(){
	stage('Build Compile'){
		sh"""
            cd ${env.APP_WORKSPACE}
            mvn -q clean install -DskipTests=true ${env.MVN_PARAMETERS}
            mvn -q compile ${env.MVN_PARAMETERS}
            mvn -q test-compile ${env.MVN_PARAMETERS}
        """
    }
}

return this
