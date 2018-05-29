
def void loadProperties(String filename){
				def map = [:]
				def file = loadFile(filename);
				def lines = file.split(/\n/) as String[]
				for(line in lines){
					if(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")) continue;
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


def boolean isCurrentBranchRelease(){
  //sh "git branch -a --contains HEAD | grep -v detached | grep -v \"no branch\" | sort | head -1 > branchname.txt";
 // branchname = readFile('branchname.txt').trim();
 
  echo "Branchname = "  + env.JENKINS_PARENT_BRANCHNAME;
  branchname = env.JENKINS_PARENT_BRANCHNAME;
  status = branchname.contains("release/") ? true : false;
  return status;
}

def boolean isCurrentBranchDevelop(){
  //sh "git branch -a --contains HEAD | grep -v detached | grep -v \"no branch\" | sort | head -1 > branchname.txt";
 // branchname = readFile('branchname.txt').trim();
 
  echo "Branchname = "  + env.JENKINS_PARENT_BRANCHNAME;
  branchname = env.JENKINS_PARENT_BRANCHNAME;
  status = branchname.contains("develop") ? true : false;
  return status;
}

def public void generateUserIDVariable(){
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
}

return this