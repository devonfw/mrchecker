/**
Verify that the code builds without errors
**/    
def call(){
	stage('Build Compile'){
		//# compile
		sh """
			cd ${env.WORKSPACE_LOCAL}/allure-framework-modules
			cd allure-core-module
			mvn clean install -DskipTests=true
			cd ..
			cd ${env.WORKSPACE_LOCAL}/allure-app-under-test
			mvn clean install -DskipTests=true
		"""
	}
}

return this
