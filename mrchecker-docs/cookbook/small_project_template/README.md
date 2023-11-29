MrChecker template
-------------------
This branch features a template for small (less than 3-5 jobs) project.

## Setup manual
1. Prerequisites
	* Aquire linux machine with 6vCPU and 8GB of RAM (rough estimates - but changing this values will affect your performance, more on it in next section). 320GB of HDD should be enought.
	* Install docker on it.
	* Setup docker-compose.
1. Config
	* Put a docker folder from this project to your machine.
	* Go to `docker/docker-compose.yml` - make sure you are checked out on a branch 'small_project_template'
	* Adjust replicas numbers for selenium agents.
		* in `docker/docker-compose.yml` there are services named in form of `selenium-\<browser name\>` each of them has replicas parameter
		* you should adjust it to match your need
		* e.g. If your requirement is to test on firefox you will need only firefox slots so set replica parameter for `selenium-firefox` to 4 and for every other `selenium-\<browser name\>` to 0
		* e.g. If your requirement is to test on firefox, opera, and chrome you want to set replicas parameter for 2 for all this browsers so you can decide from MrChecker framework which slot to take.
	* Setup PostgreSQL password via `POSTGRES_PASSWORD` variable.
	* If you're using windows then the postgres service will fail. https://forums.docker.com/t/not-able-to-change-permissions-for-files-and-folders-created-on-mounted-volumes/45769 so my workaround is to remove mounting point on the host system side and create a docker volume without link to host file system `- /var/lib/postgresql/data` instead of `- ./data/db/:/var/lib/postgresql/data`
	* Go there and run `docker-compose up` now the magic happens.
1. Jenkins initial setup
	* Wait until jenkins image is downloaded and built, wait for selenium and gitea images.
	* Go to `./jenkins/secrets/initialAdminOassword`
	* Now when the stack is up and runing, from your PC go to `host:8888` which is default jenkins port and fill the form in with the value of of initial admin pass aquired in the previous step.
	* Select to install sugested plugins.
	* Jenkins agents will fail to boot and keep restarting.
1. Jenkins agent config
	* Connect agents
		* Go to jenkins and on the left pane click on the `build executor status`
		* Then click on the `New Node`
		* Assign it a name (e.g. `agent1` ) and check `Permament Agent`
		* Assign a lable (e.g. `agent`)
		* Set remote root directory to `/var/jenkins`
		* Then click on the node name you've created
		* To get the secret go to `Nodes` and click on underlined node name. The secret will be long hexadecimal string after `-secret` or after `echo`
		* Copy the secret and put it in `docker/docker-compose.yml` with the name
		* Restart container to apply the changes for example with `docker-compose up -d --scale jenkins_agents_1=0` and then `docker-compose up -d --scale jenkins_agents_1=1`.
		* If you need more space either you can expand numbers of executors in Node config tab or copy paste the chunk with agent service in docker compose and create new one with distinctive name and secret(jenkins would not allow you to connect more than one agent with same secret and name)
1. Jenkins Allure tool configuration
	* Go to `http://localhost:8888/configureTools/`
	* Last item is Allure Commandline
	* Select a name e.g. `allure-cmd`
	* Select `Install Automatically`
	* Select version
	* Click save
1. Gitea initial setup
	* From your PC access `host:3000` which is default Gitea port.
	* Select register. You should be prompted with `Initial Configuration` form.
	* Select database to PostgreSQL adress `gitea-db:5432`
	* Provide it with PostgreSQL password from Config step.
	* Gitea will restart.
	* Register an user who will become an admin.
1. Setting up a project (gitea part)
	* Create empty new repository from Gitea UI
	* On your PC copy `../../template` content from this project
	* Go to newly created project and run `git init`, then `git add -A`, next `git commit -m "initial commit"`.
	* Now push this to gitea with `git remote add origin http://<host>:3000/<user>/<yourprojectname>.git`, `git push -u origin master` (the commands are listed in Gitea UI)
	* Create technical user for jenkins
1. Setting up a project (jenkins part)
	* From jenkins UI Create Item, multibranch pipeline
	* In section Branch Sources select git.
	* As project repository add address to your project (it might be necessary to replace `<host>` with `gitea-web`)
	* Create new credentials and set them to you tech user
	* Save
1. Finishing actions
	* Configure jenkins security <https://www.jenkins.io/doc/book/managing/security/>
	* Adjust Jenkinsfile
	

### Hardware resources
Below I just wanted to show you how I calculated the values, feel free to mix match and scale it.
#### Jenkins
<https://www.jenkins.io/doc/book/scaling/hardware-recommendations/>
There are no examples here. But they state the minimum amount of ram needed is 200MB (I consider this bare minimum), so 1 GB would be resonable amount. CPU is not mentioned at all so we can asume it's more Memory bound, thus 1vCPU.

#### Selenium
<https://github.com/SeleniumHQ/docker-selenium/issues/549>
<https://github.com/SeleniumHQ/docker-selenium>
No clear answer but we can assume one selenium agent can consume from 0.5GB to 2GB of RAM memory, and some CPU. So for basic grid with 1 Firefox and 1 Chrome agent we will need 3GB of ram and 2vCPU. We also need something for hub, 1GB and 0.5vCPU should be enought. Total 4GB and 2.5vCPU.

#### Gitea
<https://docs.gitea.io/en-us/#system-requirements>
2vCPUs and 1GB of RAM.

#### System and docker
For sure there is system and docker overhead so until this point we have 6GB of ram and 5.5vCPU. To give it some room lets round that up to 8GB and 6vCPU.

#### Jenkins agents
Its best to have jenkins agents (and selenium agents) on separate VM. For jenkins agent I can sugest 1vCPU and 1GB of RAM rule.