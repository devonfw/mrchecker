def call(){
		
//# Unit test run
	stage('Unit test'){
        sh"""
            cd ${env.APP_WORKSPACE}
            mvn -q surefire:test -Dtest=${env.TEST_NAME} -DseleniumGrid=${env.SELENIUM_HUBURL} -Denv=${env.ENVIRONMENT} -Dbrowser=${env.SELENIUM_BROWSER}
            mvn -q site
        """
        junit "**/${env.APP_WORKSPACE}target/surefire-reports/TEST-*.xml"
        if (fileExists("${env.APP_WORKSPACE}target/site/allure-report/index.html")) {
            echo("Before publish allure");
            publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "${env.APP_WORKSPACE}target/site/allure-report", reportFiles: 'index.html', reportName: "allure"]);
            echo("After publish allure");
        } else {
            echo("Any HTML report found!");
        }
    }
}
return this
