# Install #

1. Download package [TestEnvironment](https://github.com/devonfw/devonfw-testing/releases/download/1.0.0/testing-selenium-java-applications.7z)
2. Unzip with [7z](http://www.7-zip.org/download.html)
3. In unzipped folder run _start-eclipse.bat_

Full information about install you could find here [GitHub Wiki](https://github.com/devonfw/devonfw-testing/wiki/How-to-install)

# Setup environment #

### Eclipse ###

- Style formating : 
    + import [eclipse_format_code_standards.xml](README/eclipse_format_code_standards.xml)
    + how to use:  Ctrl + Shift + F

	![Eclipse formatting style](https://github.com/devonfw/devonfw-testing.wiki.git/images/2986566461-Import_Format.png)
    
# Run test case #

*Junit - classic*
> mvn clean test-compile test site -Dtest=RegisterOKTest

