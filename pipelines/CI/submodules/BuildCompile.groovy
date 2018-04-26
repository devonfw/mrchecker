/**
Verify that the code builds without errors
**/    
def call(){
	stage('Build Compile'){
		//# compile
		if(env.TESTMODULE.equals("allure-app-under-test")) {
			env.ANALIZEDMODULE = env.TESTMODULE
		}
		else {
			env.ANALIZEDMODULE = "allure-framework-modules/" + env.TESTMODULE
		}

			sh'update-ca-certificates -f'
            sh """
                cd ${env.WORKSPACE_LOCAL}/${env.ANALIZEDMODULE}
                mvn -q clean install -U -DskipTests=true
            	mvn -q compile
            	mvn -q test-compile
            """
		}
}

return this
