def call(){
	
}

public void sendMail(String message) {
	echo 'Sending e-mail: ' + env.GIT_AUTHOR_EMAIL;
	mail bcc: '', body: generateMailBody(message), cc: '', charset: 'UTF-8', from: '', mimeType: 'text/plain', replyTo: '', subject: 'Build failed!', to: env.GIT_AUTHOR_EMAIL
}


private String generateMailBody(String message){
	withCredentials([[$class: 'UsernamePasswordBinding', credentialsId: "${env.JENKINS_CREDENTIALS}", variable: 'USER_NOTIFICATION']]) {
			sh "echo BUILD_URL: ${env.BUILD_URL}";
			sh "curl -u ${env.USER_NOTIFICATION} ${env.BUILD_URL}consoleText > console.log";
			sh "grep -e WARNING console.log > warning.log || exit 0";
			sh "grep -e ERROR console.log > error.log || exit 0";
		}
	
	def utils = load "${env.SUBMODULES_DIR}/Utils.groovy";
	
	def String text = \
	"See <${env.BUILD_URL}>\n" + \
	"" + message + "\n" + \
	"------------------------------------------------\n" + \
	"ERROR: \n" + utils.loadFile("error.log") + "\n" + \
	"------------------------------------------------\n" + \
	"WARNING: \n" + utils.loadFile("warning.log") + "\n" + \
	"------------------------------------------------\n" + \
	"CHANGE_LOG: \n" + currentBuild.changeSets + "\n" + \
	"\n\n ---------------------------------------------- \n"
	
	echo("Text to send: \n " + text)
	return text;
	}


def private getChangeLogSets() {
	
	def changeLogSets = currentBuild.changeSets
	for (int i = 0; i < changeLogSets.size(); i++) {
		def entries = changeLogSets[i].items
		for (int j = 0; j < entries.length; j++) {
			def entry = entries[j]
			echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
			def files = new ArrayList(entry.affectedFiles)
			for (int k = 0; k < files.size(); k++) {
				def file = files[k]
				echo "  ${file.editType.name} ${file.path}"
			}
		}
	}
}
	
return this