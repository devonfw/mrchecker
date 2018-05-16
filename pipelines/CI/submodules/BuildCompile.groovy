/**
Verify that the code builds without errors
**/    
def call(){
	stage('Build Compile'){
		sh"""
            cd ${env.APP_WORKSPACE}
            mvn -q clean install -DskipTests=true
            mvn -q compile
            mvn -q test-compile
        """
}

return this
