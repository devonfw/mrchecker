def call(){
	sh "java -version"

	stage('SonarQube'){
		withCredentials([[$class: 'StringBinding', credentialsId: "${env.SONAR_CREDENTIALS_ID}", variable: 'SONAR_TOKEN']]) {	
			sh"set +x"	
			sh"""
			sonar-scanner \
			-Dsonar.projectKey=\"${env.SONAR_RESOURCE_ID}\" \
			-Dsonar.projectName=\"${env.SONAR_PROJECT_NAME}\" \
			-Dsonar.projectVersion=\"${env.BUILD_VERSION_BATCH}\" \
			-Dsonar.sources=\"${env.PROJECT_HOME}/src/\" \
			-Dsonar.projectBaseDir=\"${env.PROJECT_HOME}\" \
			-Dsonar.junit.reportsPath=\"target/\" \
			-Dsonar.host.url=\"${env.SONAR_URL}\" \
			-Dsonar.login=\"${env.SONAR_TOKEN}\" \
			-Dsonar.java.source=\"1.8\" \
			-Dsonar.java.target=\"1.8\" \
			-Dsonar.profile=\"PWI_Java\" \
			-Dsonar.language=\"java\" \
			-Dsonar.analysis.mode=\"${env.SONAR_ANALYSIS_MODE}\" \
			-Dsonar.sourceEncoding=\"UTF-8\" \
			-Dsonar.java.binaries=\"target/classes\" \
		"""

		//stageQualityGateSonar();
		stageBlockerIssuesVerification();
		}
	}
}	

private String stageQualityGateSonar(){
	
    def result = getSonarQualityGateResult()
    
    if(result.contains("ERROR")){
        echo " The project FAILED the sonar quality gate"
    	echo " For reason go to: ${env.SONAR_URL}/dashboard/index?id=${env.SONAR_RESOURCE_ID} "
    	currentBuild.result = 'FAILURE'
    }
    else if (result.contains("WARN")){
        echo "The project has passed the quality gate but has WARNINGS on the quality gate conditions, ${env.SONAR_URL}/dashboard/index?id=${env.SONAR_RESOURCE_ID} "
    }
    else{
        echo "The project has PASSED the quality gate, ${env.SONAR_URL}/dashboard/index?id=${env.SONAR_RESOURCE_ID} "
    }
}

private void stageBlockerIssuesVerification(){ 
	
	try{
	    blockerIssues = getSonarBlockerViolations().toInteger();
	} catch (Exception e) {	
		echo "ERROR: Unable to read Sonar 'blockerIssues'. Not a integer value. Setting default value blockerIssues = 0"
		blockerIssues = 0;
	}
	def informationMessage = "Number of blocker issues:" + blockerIssues + ". Acceptable number of blockers: ${env.SONAR_ACCEPTABLE_NUMBER_OF_BLOCKER_ISSUES}."

	echo "Sonar analyze. " + informationMessage
    if(blockerIssues>env.SONAR_ACCEPTABLE_NUMBER_OF_BLOCKER_ISSUES.toInteger()){
    	error("Sonar Analyze error." + informationMessage+" Build failed due to high number of blocker issues. Please check report  ${env.SONAR_REPORT_URL}")
    }
}
   
private String getSonarQualityGateResult(){

   def String sonar_quality_gate_req = """
			{ \"format\": \"xml\",
			\"resource\": \"${env.SONAR_RESOURCE_ID}\", 
			\"metrics\": \"alert_status\"}  
		"""
   echo('sonar_quality_gate_req: ' +  sonar_quality_gate_req);
   writeFile file: 'sonar_quality_gate_req.json', text: sonar_quality_gate_req
 
   sh "curl -H 'Content-Type: application/json' -X POST ${env.SONAR_URL}/api/resources/index -d @sonar_quality_gate_req.json > sonar_quality_gate_res.xml"
   
   sh " cat sonar_quality_gate_res.xml | grep -oP '(?<=data>)[^<]+' > sonar_quality_gate_result.txt"
   
   def sonar_quality_gate_result= readFile('sonar_quality_gate_result.txt')
   
   //State of the Quality Gate associated to your Project. Possible values are : ERROR, WARN, OK
   return sonar_quality_gate_result;
}

private String getSonarBlockerViolations(){ 

   def String blocker_violations_req = """
			{ \"format\": \"xml\",
			\"resource\": \"${env.SONAR_RESOURCE_ID}\", 
			\"metrics\": \"blocker_violations\"}  
		"""
   echo('sonar_quality_gate_req: ' +  blocker_violations_req);
   writeFile file: 'blocker_violations_req.json', text: blocker_violations_req
 
   sh "curl -H 'Content-Type: application/json' -X POST ${env.SONAR_URL}/api/resources/index -d @blocker_violations_req.json > blocker_violations_res.xml"
   
   sh " cat blocker_violations_res.xml | grep -oP '(?<=frmt_val>)[^<]+' > blocker_violations_result.txt"
   
   def blocker_violations_result= readFile('blocker_violations_result.txt')
   
   return blocker_violations_result;
}	

return this