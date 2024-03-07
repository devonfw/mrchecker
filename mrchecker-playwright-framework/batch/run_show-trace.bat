rem TODO: Change the link to your trace file
call cd ..
call mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace ./target/traces/trace-com.capgemini.frameworkExamples.LocaleExample#localeEnglish_test.zip"