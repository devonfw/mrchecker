/**
Pull sources from repository
**/
def call(){
	stage('Git Pull'){
		setGitAuthor();
	}
}

def void setGitAuthor() {
	//Save build properties into file. Generated variables will be used in all Downstream jobs
	echo "setGitAuthor"

	sh "ls;";
	sh'''
		GIT_C="$(git rev-parse HEAD)"
		GIT_COMMIT="$GIT_C";
		echo GIT_COMMIT=$GIT_COMMIT >> build.properties
		GIT_AUTHOR=$(git --no-pager show -s --format='%an' $GIT_COMMIT)
		GIT_AUTHOR_EMAIL=$(git --no-pager show -s --format='%ae' $GIT_COMMIT)
		git config --global user.email $GIT_AUTHOR_EMAIL
		git config --global user.name $GIT_AUTHOR
		echo GIT_AUTHOR=$GIT_AUTHOR >> build.properties
		echo GIT_AUTHOR_EMAIL=$GIT_AUTHOR_EMAIL >> build.properties
	'''
	def utils = load "${env.SUBMODULES_DIR}/Utils.groovy";
	utils.loadProperties('build.properties');
}
	
def void tryMergeWithBranch(String targetBranch){ 	

	def WORKING_BRANCH  = env.WORKING_BRANCH;
	def utils = load "${env.SUBMODULES_DIR}/Utils.groovy";
    try{
		echo ("Try merge command");
        sh"git merge --no-commit --no-ff ${targetBranch} > git_merge_result.txt"
    }catch (Exception e){
		echo ("Merge exception");
        String message = utils.loadFile("git_merge_result.txt") + "\n" +e
        //SendMail with e
//        mailSender = load "${env.COMMONS_DIR}/MailSender.groovy";
//        mailSender.sendMail(message);
        sh"git request-pull ${targetBranch} origin ${WORKING_BRANCH}"    
        sh"git merge --abort" 
        error(message);
    }

}

def void writeChangedTestsFileNamesToFile(String currentBranch){ 
			
	
    echo("currentBranch: ${currentBranch}");
    try{
       String filenamepath = "${env.WORKSPACE}" +"/" + "gitdiff.log";  
       echo ("Git diff: ${filenamepath}");
        sh"""	    
            git diff --name-only --diff-filter=ACMR origin/develop...${currentBranch} | grep -E '.*.feature\$' > ${filenamepath}	
        """;
       writeTagNamesToFile(filenamepath);
	}catch(e){
		echo "no test changed $e" 
	}
}

def void writeTagNamesToFile(String filenamepath){			
	def file = readFile (filenamepath)
	
	tagsList = [];
	def index = file.indexOf('.feature');
	while(index>0) {
		def path = file.substring(0, index + 8).trim();
		String filePath = "./" + path;
		def file2 = readFile (filePath);
		
		def lines = file2.split(/\n/) as String[]
		for(line in lines) {
			if(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")) continue;
			if(line.contains('Feature:')) break;
			if(line.contains('@')) {				
				tagsList<<line.replace(',', '').trim();
				break;
			}
		}		
		file = file.substring(index + 8, file.size()).trim();
		index = file.indexOf('.feature');
	}
	
	def content;
	if(tagsList) {
		content = tagsList.join(',');		
	} else {
		content = '';
	}
	env.TAGNAME = content;
	
	sh'''
		echo $TAGNAME > tagsList.txt
	''';	
}

return this
