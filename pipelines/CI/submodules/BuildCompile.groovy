/**
Verify that the code builds without errors
**/    
def call(){
	stage('Build Compile'){
		//# compile
		sh """
			cd ${env.WORKSPACE_LOCAL}/allure-framework-modules
			cd allure-core-module
			mvn -q clean
			mvn -q compile -DskipTests=true
			mvn -q test-compile
			cd ..
			cd allure-security-module
			mvn -q clean
			mvn -q compile -DskipTests=true
			mvn -q test-compile
			cd ..
			cd allure-selenium-module
			mvn -q clean
			mvn -q compile -DskipTests=true
			mvn -q test-compile
			cd ${env.WORKSPACE_LOCAL}/allure-app-under-test
			mvn -q clean
			mvn -q compile -DskipTests=true
			mvn -q test-compile
		"""
	}
}

return this
