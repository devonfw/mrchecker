rem  if you have issues to run this script from IntelliJ, please run it from windows console.
set PLAYWRIGHT_BROWSERS_PATH=C:\Proj\playwright
call mvn -f ..\pom.xml exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
