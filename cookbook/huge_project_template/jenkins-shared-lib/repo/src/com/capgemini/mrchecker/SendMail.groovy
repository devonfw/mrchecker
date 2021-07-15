/**
Module for sending an email message with test results
it needs to have env.mailReceipients defined
*/
public void sendMail(){
	emailext  mimeType: 'text/html', body: this.generateMailBody(), recipientProviders: [[$class: 'RequesterRecipientProvider']], subject: this.getSubjectText(), to: ${env.mailReceipients}
}
private String generateMailBody(){
	def passed = build.testResultAction.getPassCount()
	def total = build.testResultAction.getTotalCount()
	return "</br><h1><center>The ${passed} of ${total} Total Test Cases have PASSED.</center></h1></br>"
}
private String getSubjectText(){
	return "Build ${env.BUILD_DISPLAY_NAME_UPSTREAM}"
}