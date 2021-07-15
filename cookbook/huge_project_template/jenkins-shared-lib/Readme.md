MrChecker template - jenkins shared libraries
---------------
This is the shared library for jenkins. It is impossible to target your needs in 100%, so I will do my best to give you good example, but the final polishing is up to you.

The official documentation is here <https://www.jenkins.io/doc/book/pipeline/shared-libraries/> might be worth reading.

Every project needs a basic jenkins file which will reside into project repo (most commonly named `Jenkinsfile` in root project dir).
The example of such file is presented in `huge_project_template/jenkins-shared-lib/Jenkinksfile`

#### How to setup jenkins shared library?
If you have a specified jenkins folder for you test jobs, thats great. Go to it. Next on left pane select `configure`, in tab Pipeline Libraries click `Add`. Chose a name for it and a default branch (strongly sugesting `master`). Makre sure that `Allow default version to be overriden` is checked. Retrival method, probably you should go for `Modern SCM` paste repository and select credentials. Thats it we are set up.

#### How to version it?
In my projects I use `master` branch as a stable shared library which is by default used in all subprojects via `@Library('name')_`.
I also have a template/test project which uses `@Library('name@develop')_`. I will often create test branches in template/test project just to change this one line to `@Library('name@feature')_` and make full real test. (I perform smaller / partial tests via Jenkins replay job feature replacing code there)

#### What should jenkins shared lib include?
In my opinion good shared library include all logic which is needed for your operation, so Jenkinsfile is only invocation of building blocks.