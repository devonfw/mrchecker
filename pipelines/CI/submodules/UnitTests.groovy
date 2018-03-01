def call(){
		
//# Unit test run
	stage('Unit test'){
		
		try{
			if(!env.TESTMODULE.equals("allure-app-under-test")) {
				sh """
				cd ${env.WORKSPACE_LOCAL}/allure-framework-modules/${env.TESTMODULE}
				mvn test
				"""
			}
		} catch (Exception e) {
			error ( e.toString() );
		} finally{
			echo ("Publish junit report")
			junit allowEmptyResults: true, testResults: "**/target/surefire-reports/*.xml"
		}
	}
}

return this
