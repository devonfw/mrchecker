public void publishAllureReport(String resultsPath = "target/allure-results"){
	allure includeProperties: false, jdk: '', report: "allure-report", results: [[path: "${resultsPath}"]]
}