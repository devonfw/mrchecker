# Install #

1. Java 1.8 JDK 64bit
    + download and install 
       http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
![Capture.PNG](https://bitbucket.org/repo/ranep8/images/3137412086-Capture.PNG)
    + Windows Local Environment: 
        - *Variable name*: JAVA_HOME    *Variable value*: c:\Where_You_Installed_Java
        - *Variable name*: PATH    *Variable value*: %JAVA_HOME%\bin; %JAVA_HOME%\lib
    
    ![Java.PNG](https://bitbucket.org/repo/ranep8/images/427137171-Java.PNG)

    + verify in command line:  > java --version

1. Maven 3.5
    + download http://www-eu.apache.org/dist/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.zip
    + unzip, to C:\maven
    + Windows Local Environment
        - *Variable name*: M2_HOME    *Variable value*: c:\maven
        - *Variable name*: PATH    *Variable value*: %M2_HOME%\bin

    ![Maven.PNG](https://bitbucket.org/repo/ranep8/images/4126112216-Maven.PNG)

    + verify in commmand line:  > mvn --version

1. Eclipse IDE
    + download and unzip https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/neon/3/eclipse-java-neon-3-win32-x86_64.zip

1. Browsers:
    + Chrome
    + Firefox
    + Edge

# Setup environment #


### Run test ###

- Run first test: 
    + [0_1_running_tests-ClassicRegistration.bat](https://bitbucket.org/capntc/selenium_workshop/src/b03275461b1451d7b188b937b77cb6bd77bc2e7a/clirunners/0_1_running_tests-ClassicRegistration.bat?at=develop)
- Generate and view report: 
    + [2_generate_report_and_start_jetty.bat](https://bitbucket.org/capntc/selenium_workshop/src/022bb8023f580e6498e580b706526e23a2800cc1/clirunners/2_generate_report_and_start_jetty.bat?at=develop)
    + [3_show_report.bat](https://bitbucket.org/capntc/selenium_workshop/src/cc4f1840dd7b2831907526c47bdf247ab74903a2/clirunners/3_show_report.bat?at=develop)

### Eclipse ###

- Style formating : 
    + import [eclipse_format_code_standards.xml](https://bitbucket.org/capntc/selenium_workshop/src/cc4f1840dd7b2831907526c47bdf247ab74903a2/README/eclipse_format_code_standards.xml?at=develop)
    + how to use:  Ctrl + Shift + F

	![Capture.PNG](https://bitbucket.org/repo/ranep8/images/2986566461-Capture.PNG)
    
# Run test case #

*Junit - classic*
> mvn clean test-compile test site -Dtest=RegisterOKTest

*Cucumber - classis manula runner*
> mvn clean test-compile test site -P cucumber -Dtest=RegistrationTest

*Cucumber - runner autogenerator*
> mvn clean test-compile test site -P cucumber-parallel -Dtest=RunCucumberTestSuite  -Dtags="@Registration"

