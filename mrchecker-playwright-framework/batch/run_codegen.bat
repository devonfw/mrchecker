set PLAYWRIGHT_BROWSERS_PATH=C:\Proj\playwright
rem TODO: Change the link to your app
call mvn -f ..\pom.xml  exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen --ignore-https-errors https://demoqa.com/login"
