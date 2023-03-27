public void runSeleniumTestsFromGroups(String groups = ${env.SELENIUM_GROUPS}){
	withMaven {
    	this.runTests("-Dbrowser=${env.BROWSER} -DseleniumGrid=${env.SELENIUM_GRID_URL} -Dgroups=${groups}")
	}
}
public void runJunitTestsFromGroups(String groups = ${env.JUNIT_GROUPS}){
	withMaven {
    	this.runTests("-Dgroups=${groups}")
	}
}
public void runTests(String additionalParams){
	withMaven{
		sh "mvn -q test ${additionalParams}"
	}
}
public void runCleanJob(){
	withMaven {
    	sh "mvn -q clean"
	}
}