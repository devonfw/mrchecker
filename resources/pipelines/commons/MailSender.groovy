

def call(Exception e){
	echo 'Sending e-mail: ' + env.GIT_AUTHOR_EMAIL;
	mail bcc: '', body: generateMailBody(e), cc: '', charset: 'UTF-8', from: '', mimeType: 'text/plain', replyTo: '', subject: 'Build failed!', to: env.GIT_AUTHOR_EMAIL
}


private String generateMailBody(Exception e){
	withCredentials([[$class: 'UsernamePasswordBinding', credentialsId: "${env.USER_CREDENTIALS}", variable: 'USER_NOTIFICATION']]) {
			sh "echo BUILD_URL: ${env.BUILD_URL}";
			sh "curl -u ${env.USER_NOTIFICATION} ${env.BUILD_URL}consoleText > console.log";
			sh "grep -e WARNING console.log > warning.log || exit 0";
			sh "grep -e ERROR console.log > error.log || exit 0";
		}
	
	def utils = load "${env.SUBMODULES_DIR}/Utils.groovy";
	
	def String message = \
	"See <${env.BUILD_URL}>\n" + \
	"" + e.toString() + "\n" + \
	"------------------------------------------------\n" + \
	"ERROR: \n" + utils.loadFile("error.log") + "\n" + \
	"------------------------------------------------\n" + \
	"WARNING: \n" + utils.loadFile("warning.log") + "\n" + \
	"\n\n ---------------------------------------------- \n"
	return message;
	}


return this