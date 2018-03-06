/**
Run integration tests suite
**/
def call(){
	echo("env.TESTNAME=${env.TESTNAME}");
    echo("env.TAGNAME=${env.TAGNAME}");
	echo("env.TAGNAME_FOR_ONE_THREAD_RUN=${env.TAGNAME_FOR_ONE_THREAD_RUN}");
	try{

		runFirstTest();

//        if (env.TAGNAME == '' && env.TAGNAME_FOR_ONE_THREAD_RUN == ''){
//            echo "No tags and test names found."
//            runIntegrationTests();
//		} else if (env.TAGNAME != '' && env.TAGNAME_FOR_ONE_THREAD_RUN != '') {
//			echo "Run tests in parallel and sequentially together"
//			runAllTaggedTests();
//		} else if (env.TAGNAME != '') {
//			echo "Run tests only in paralell execution"
//			runTaggedTestsParalelly();
//		} else if (env.TAGNAME_FOR_ONE_THREAD_RUN != '') {
//			echo "Run tests only in sequential execution"
//			runTaggedTestsSequentially();
//		} else if (env.TESTNAME != ''){
//            runConcreteTests();
//        }
	}catch(Exception e){
			echo("Test failed.");
			error(e.toString());
	} finally {
		try{
			generateAllureReport();
		} catch(Exception e){
			echo("Any report files found.");
		}
	}
}

private generateAllureReport(){
	sh """
        cd ${env.PROJECT_HOME}
		if [ -d "target/allure-results" ]; then
			mvn -q site
		else
			echo "No allure results found."
		fi
    """
}

private runFirstTest() {
	sh """
        cd ${env.PROJECT_HOME}
        mvn clean compile test site -Dtest=RegisterOKTest -DcustomHubUrl=${env.HUBURL} -Dlogin.url=${env.LOGINURL} -DtestLocally=false -Dbrowser=chrome
    """
	//clean test-compile test site -Dtest=RegisterOKTest
	//clean compile test site -Dtest=
}

private runAllTaggedTests(){
    echo ("run Tests given by user: ${TAGNAME},${TAGNAME_FOR_ONE_THREAD_RUN}")    
    sh """
        cd ${env.PROJECT_HOME}
        mvn -q --fail-at-end test -P cucumber-parallel -Doracle.jdbc.timezoneAsRegion=false -Dtest=TestSuite -DthreadCount=${env.THREAD_COUNT} -Dtags=\"${env.TAGNAME}\" -Dbrowser=${env.BROWSER} -DtestLocally=${env.TESTLOCALLY} -DcustomHubUrl=${env.HUBURL} -Dlogin.url=${env.LOGINURL} -Dredirect.url=${env.REDIRECTURL} -Denv.app=${env.APP} -Denv.service=${env.SERVICE}
		rm ${env.RUNNERS_DIR}/* -f
		mvn -q --fail-at-end test -P cucumber-parallel-one-thread -Doracle.jdbc.timezoneAsRegion=false -Dtest=TestSuite -DthreadCount=1 -Dtags=\"${env.TAGNAME_FOR_ONE_THREAD_RUN}\" -Dbrowser=${env.BROWSER} -DtestLocally=${env.TESTLOCALLY} -DcustomHubUrl=${env.HUBURL} -Dlogin.url=${env.LOGINURL} -Dredirect.url=${env.REDIRECTURL} -Denv.app=${env.APP} -Denv.service=${env.SERVICE}
    """
}

private runTaggedTestsParalelly(){
    echo ("run Tests given by user: ${TAGNAME}")    
    sh """
        cd ${env.PROJECT_HOME}
        mvn -q --fail-at-end test -P cucumber-parallel -Doracle.jdbc.timezoneAsRegion=false -Dtest=TestSuite -DthreadCount=${env.THREAD_COUNT} -Dtags=\"${env.TAGNAME}\" -Dbrowser=${env.BROWSER} -DtestLocally=${env.TESTLOCALLY} -DcustomHubUrl=${env.HUBURL} -Dlogin.url=${env.LOGINURL} -Dredirect.url=${env.REDIRECTURL} -Denv.app=${env.APP} -Denv.service=${env.SERVICE}
	"""
}

private runTaggedTestsSequentially(){
    echo ("run Tests given by user: ${TAGNAME_FOR_ONE_THREAD_RUN}")    
    sh """
        cd ${env.PROJECT_HOME}
        mvn -q --fail-at-end test -P cucumber-parallel-one-thread -Doracle.jdbc.timezoneAsRegion=false -Dtest=TestSuite -DthreadCount=1 -Dtags=\"${env.TAGNAME_FOR_ONE_THREAD_RUN}\" -Dbrowser=${env.BROWSER} -DtestLocally=${env.TESTLOCALLY} -DcustomHubUrl=${env.HUBURL} -Dlogin.url=${env.LOGINURL} -Dredirect.url=${env.REDIRECTURL} -Denv.app=${env.APP} -Denv.service=${env.SERVICE}
    """
}

private runConcreteTests(){
	echo ("run Tests given by user: ${TESTNAME}")	
    sh """
        cd ${env.PROJECT_HOME}
        mvn -q --fail-at-end surefire:test verify -P cucumber -DfailIfNoTests=false -Dtest=${env.TESTNAME} -Doracle.jdbc.timezoneAsRegion=false -Dbrowser=${env.BROWSER} -DtestLocally=${env.TESTLOCALLY} -DcustomHubUrl=${env.HUBURL} -Dlogin.url=${env.LOGINURL} -Dredirect.url=${env.REDIRECTURL} -Denv.app=${env.APP} -Denv.service=${env.SERVICE}
    """
}

private runIntegrationTests(){
	echo ("run Integration Tests")

	def currentBranch = "${env.WORKING_BRANCH}"
	echo currentBranch
	
	def module = load "${env.SUBMODULES_DIR}/GitPull.groovy";
	module.writeChangedTestsFileNamesToFile(currentBranch);	
	
	echo "env.TAGNAME = ${env.TAGNAME}"
	if (env.TAGNAME.size()>0){
		sh """
            cd ${env.PROJECT_HOME}
            mvn -q --fail-at-end test -P cucumber-parallel -Dtest=TestSuite -DthreadCount=8 -Dtags=\"${env.TAGNAME}\" -Dbrowser=${env.BROWSER} -DtestLocally=${env.TESTLOCALLY} -DcustomHubUrl=${env.HUBURL} -Dlogin.url=${env.LOGINURL} -Dredirect.url=${env.REDIRECTURL} -Denv.app=${env.APP} -Denv.service=${env.SERVICE}
          """    
	}else {	
	    echo "No tests changes found"    
    }
}

private getListOfTestsAsMavenParam(){ 
	def fileName = "testsList.txt"

	def file = readFile (fileName)

    def lines = file.split(/\n/) as String[]

    classesList = [];

 		for(line in lines) { 
 			if(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")) continue;
            def lastIndexOf = line.lastIndexOf('/');
 			def className = line.substring(lastIndexOf+1, line.size()-5)
 			println "className : ${className}"
 			classesList<<className;
 		}
 	
	if(classesList)
	  return  classesList.join(',');
	else 
	  return ""; 
	  
}

return this
