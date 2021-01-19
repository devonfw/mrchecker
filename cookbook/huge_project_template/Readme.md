MrChecker template
---------------
This scenario targets bigger project (everything above 3 different multibranch job I would call big). However you can also utilize this techniquest in smaller projects.

We will try to focus on unification. We assume that you already have the necessary infrastructure, either from your support team or from small project template (any way it might be good idea to familarize with that scenario). If you create your own infra from small project template you should also consider seting up nexus repository manager, as a part of unification proccess we will externalize a lot of common project parts, to mange them easly.

In case of big project it would be impossible to create step by step guide how to make it right, because I dont believe there is a right way. I'm still learning and discovering things I could do better, moreover every project is different. So I will try to create a guide consisting a question you need to answer yourself.

Good read to start is <https://semver.org>. To prepare some sane versioning scheme. It will help you later.

### What could we externalize?
Think of the project? What are the main parts of it? Which of them are common among subprojects?
<details>
	<summary>Main parts of my projects</summary>

	* test code - definitelly subproject specific
	* maven - it need to have some project specific parts (artifactID), but there are some common parts(dependencies, profile configs)
	* jenkins jobs and CI/CD setup - generally there are a lot of common, with a slight differences
	* hooks - same in each project

</details>

### What parts and how we will externalise in my imaginary project?

##### Maven
We can create maven parent-pom which will contain base dependencies. So we are making sure all of our subproject use the same version of basic dependencies. Every subproject will inherit set of profiles so transition from one subproject to another will be easy.

##### Jenkins
We can create base building blocks which can be later use to create Jenkins Jobs. We will put the blocks into jenkins shared library.

##### Hooks and common code
We will create a java dependency which will provide common code such as hooks, maybe basic java steps like for login (if all subprojects have the same procedure for login).


### How we can handle that?

We need to handle our common parts in the most seemless and transparent way possible.

##### Maven
parent-pom can be just treated as normal maven dependency - more on that in `./parent-pom` folder - which can be deployed to nexus repository.

##### Jenkins
Jenkins shared libraries - does not need to be deployed in any special way, we will discuss that more in `./jenkins-shared-lib`, but in a nutshell, just create a repo.

##### Hooks and common code
Another maven dependency - more on it in `./shared-code`.
