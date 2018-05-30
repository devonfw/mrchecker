def call(String STATE){
	withCredentials([[$class: 'UsernamePasswordBinding', credentialsId: "${env.STASH_CREDENTIALS}", variable: 'USER_NOTIFICATION']]) {
			def String message = """{ \"state\": \"${STATE}\",
								\"key\": \"${env.JOB_NAME_UPSTREAM}\", 
								\"name\": \"${env.BUILD_DISPLAY_NAME_UPSTREAM}\",  
								\"url\": \"${env.BUILD_URL_UPSTREAM}\",  
								\"description\": \"Changes by ${env.GIT_AUTHOR} \"}\"
								"""
		
		
		echo('message: ' +  message);
		writeFile file: 'stashdata.json', text: message
		sh"curl -u ${env.USER_NOTIFICATION} -H 'Content-Type: application/json' -X POST https://bitbucket.org/rest/build-status/1.0/commits/${env.GIT_COMMIT} -d @stashdata.json"
	}	
}

return this