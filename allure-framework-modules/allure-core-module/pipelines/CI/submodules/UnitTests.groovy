def call(){
		
//# Unit test run
	stage('Unit test'){
        sh"""
            cd ${env.APP_WORKSPACE}
            mvn -q surefire:test -Dtest=${env.TEST_NAME} -Dthread.count=${env.THREAD_COUNT} -Denv=${env.ENVIRONMENT} ${env.MVN_PARAMETERS}
            
        """
        try{
            sh"""
                cd ${env.APP_WORKSPACE}
                mvn -q site:site ${env.MVN_PARAMETERS}
            """
            junit "**/${env.APP_WORKSPACE}target/surefire-reports/TEST-*.xml"
            if (fileExists("${env.APP_WORKSPACE}target/site/allure-report/index.html")) {
                echo("Before publish allure");
                publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "${env.APP_WORKSPACE}target/site/allure-report", reportFiles: 'index.html', reportName: "allure"]);
                echo("After publish allure");
            } else {
                echo("Any HTML report found!");
            }
        } catch (Exception e){
            echo("No report generated. Reason: \n" + e);
        }
    }
}
return this
