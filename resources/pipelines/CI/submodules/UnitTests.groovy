def call(){
		
//# Unit test run
	stage('Unit test'){
		
		try{
		sh """
			cd ${env.PROJECT_HOME}
			mvn test
		"""
		} catch (Exception e) {
			error ( e.toString() );
		} finally{
			echo ("Publish junit report")
			junit allowEmptyResults: true, testResults: "**/target/surefire-reports/*.xml"
		}
	}
}

return this
