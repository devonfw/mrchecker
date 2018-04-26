def call(){
		
//# Unit test run
	stage('Unit test'){
		
		try{

			sh"""
            	cd ${env.WORKSPACE_LOCAL}/${env.ANALIZEDMODULE}
            	mvn -q surefire:test -Dtest=${env.TEST_NAME} -DseleniumGrid=${env.HUBURL} -Dos=LINUX -Denv=${env.ENVIRONMENT} -Dbrowser=${env.SELENIUM_BROWSER} -DbrowserVersion=64.0.3282.186
            	mvn -q site
            """

			echo "Surefire report"
			junit "**/${env.WORKSPACE_LOCAL}/${env.ANALIZEDMODULE}target/surefire-reports/TEST-*.xml"


			if (fileExists("${env.WORKSPACE_LOCAL}/${env.ANALIZEDMODULE}target/site/allure-report/index.html")) {
				echo("Before publish allure");
				publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "${env.WORKSPACE_LOCAL}/${env.ANALIZEDMODULE}target/site/allure-report", reportFiles: 'index.html', reportName: "allure"]);
				echo("After publish allure");
			} else {
				echo("Any HTML report found!");
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
