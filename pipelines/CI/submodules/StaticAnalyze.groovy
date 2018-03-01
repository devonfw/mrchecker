def call(){
	stage("Static anaylize"){
		runStyleValidation();
		runStaticAnalyzeTools();
		publishReports();
	}
}

private runStyleValidation(){

	try{
		if(env.TESTMODULE.equals("allure-app-under-test")) {
//			sh """
//				cd ${env.WORKSPACE_LOCAL}/${env.TESTMODULE}
//				mvn -q formatter:validate
//			"""
		}
		else {
//			sh """
//				cd ${env.WORKSPACE_LOCAL}/allure-framework-modules/${env.TESTMODULE}
//				mvn -q formatter:validate
//			"""
		}
	} catch(Exception e){
		
		def message = """File format is not in line with approved standards. \n
			Therefore please: \n
			1. Import format style. ${env.PROJECT_HOME}/resources/style_format \n
			2. In Eclipse run Ctrl+Shift+F \n
			\n
			NOTE: Reformat can be done by  mvn formatter:format
			\n\n ---------------------------------------------- \n
			""";
	
		error ( message + "\n\n" + e.toString() );
		
		}
}

private runStaticAnalyzeTools(){

	if(env.TESTMODULE.equals("allure-app-under-test")) {
		sh """
				cd ${env.WORKSPACE_LOCAL}/${env.TESTMODULE}
				mvn -q site
			"""
	}
	else {
		sh """
				cd ${env.WORKSPACE_LOCAL}/allure-framework-modules/${env.TESTMODULE}
				mvn -q site
			"""
	}
}

private publishReports(){
	step([$class: 'FindBugsPublisher', canComputeNew: false, defaultEncoding: '', excludePattern: '', failedTotalHigh: '1', healthy: '', includePattern: '', isRankActivated: true, pattern: '**/target/findbugsXml.xml', unHealthy: '', unstableTotalHigh: '3'])
	// jacoco skipCopyOfSrcFiles: true, buildOverBuild: true, changeBuildStatus: true, deltaComplexityCoverage: '1', deltaLineCoverage: '5', deltaMethodCoverage: '5', exclusionPattern: '**/*Test*.class', inclusionPattern: '**/*.class', maximumBranchCoverage: '100', maximumClassCoverage: '100', maximumLineCoverage: '80', maximumMethodCoverage: '70', minimumBranchCoverage: '80', minimumClassCoverage: '80', minimumLineCoverage: '50', minimumMethodCoverage: '50'
	jacoco changeBuildStatus: true, exclusionPattern: '**/*Test*.class', inclusionPattern: '**/*.class', maximumBranchCoverage: '100', maximumLineCoverage: '80', minimumBranchCoverage: "${env.MINIMUM_BRANCH_COVERAGE}", minimumLineCoverage: "${env.MINIMUM_LINE_COVERAGE}"

	// publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "${env.PROJECT_HOME}/target/site", reportFiles: 'findbugs.html', reportName: "Training - Findbugs"])
	publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "${env.PROJECT_HOME}/target/site", reportFiles: 'pmd.html', reportName: "Training - PMD"])
	publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "${env.PROJECT_HOME}/target/site", reportFiles: 'cpd.html', reportName: "Training - Copy/Paste Detector"])
	// publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: '${env.PROJECT_HOME}/target/cobertura/reports/cobertura-html', reportFiles: 'index.html', reportName: "Training Cobertura"])
}

return this