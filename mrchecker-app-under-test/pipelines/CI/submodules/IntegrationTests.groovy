def call(){
		
//# Unit test run
	stage('Integration test'){
        sh"""
            cd ${env.APP_WORKSPACE}
            mvn -q surefire:test -Dtest=${env.TEST_NAME} -Dthread.count=${env.THREAD_COUNT} -DseleniumGrid=${env.SELENIUM_HUBURL} -Denv=${env.ENVIRONMENT} -Dbrowser=${env.SELENIUM_BROWSER} ${env.MVN_PARAMETERS}
            
        """
        try{
            sh"""
                cd ${env.APP_WORKSPACE}
                mvn -q site:site ${env.MVN_PARAMETERS}
            """			
            junit "**/${env.APP_WORKSPACE}target/surefire-reports/TEST-*.xml"		
            allure includeProperties: true, jdk: '', report: "${env.APP_WORKSPACE}target/site/allure-report", results: [[path: "${env.APP_WORKSPACE}target/allure-results"]]
        } catch (Exception e){
            echo("No report generated. Reason: \n" + e);
        }
    }
}
return this
