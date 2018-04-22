/**
Verify that the code builds without errors
**/    
def call(){
	stage('Build Compile'){
		//# compile
		if(env.TESTMODULE.equals("allure-app-under-test")) {
			sh """
                cd ${env.WORKSPACE_LOCAL}/allure-framework-modules/allure-core-module
                mvn -q clean
                mvn -q deploy
                mvn -q compile -DskipTests=true

                cd ${env.WORKSPACE_LOCAL}/allure-framework-modules
                mvn -q compile -DskipTests=true

				cd ${env.WORKSPACE_LOCAL}/allure-app-under-test
				mvn -q compile -DskipTests=true
			"""
		}
		else {
			sh """
				cd ${env.WORKSPACE_LOCAL}/allure-framework-modules/${env.TESTMODULE}
				mvn -q clean
				mvn -q compile -DskipTests=true
				mvn -q test-compile
			"""
		}
	}
}

return this
