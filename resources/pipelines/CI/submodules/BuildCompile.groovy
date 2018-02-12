/**
Verify that the code builds without errors
**/    
def call(){
	stage('Build Compile'){
		//# compile
		sh """
			cd ${env.PROJECT_HOME}
			mvn -q clean
			mvn -q compile -DskipTests=true
			mvn -q test-compile
		"""
	}
}

return this
