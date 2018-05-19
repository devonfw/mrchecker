
def void loadProperties(String filename){
				def map = [:]
				def file = loadFile(filename);
				def lines = file.split(/\n/) as String[]
				for(line in lines){
					echo line
					keyValuePair = line.split( '=' )
					map.put(keyValuePair[0], keyValuePair[1])
					env."${keyValuePair[0]}"="${keyValuePair[1]}"
				}
}


def String loadFile(String filename){
	def file = readFile filename;
	return file;
}

def public boolean isBranchType(String branchType){
  sh "git branch -a --contains HEAD | grep -v detached | grep -v \"no branch\" | sort | head -1 > branchname.txt";
  branchname = readFile('branchname.txt').trim();
  echo "Branchname = "  + branchname;
  status = branchname.toLowerCase().matches("(.*)${branchType}(.*)");
  return status;
}

def public void setJenkinsDescription(String text){
    //Description visible under Job Executor Number 
    currentBuild.setDescription(text)  
}

def public generateUserIDVariable(){
	//Generate USER_ID and USER_GROUP and store values into env.USER_ID, env.USER_GROUP.
	sh '''
		USER_ID="$(grep $USER /etc/passwd | cut -d: -f3)"; 
		USER_GROUP="$(grep $USER /etc/passwd | cut -d: -f4)";
		echo USER_ID=$USER_ID > usernameID.txt;
		echo USER_GROUP=$USER_GROUP >> usernameID.txt;
		cat usernameID.txt;
		'''
		
	loadProperties("usernameID.txt");
	echo("User  ID=${env.USER_ID},  user group id=${env.USER_GROUP}");  
	return [env.USER_ID, env.USER_GROUP];
}

return this