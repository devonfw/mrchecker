echo "Running Cucumber tests"
cd ..
mvn test -Dcucumber.class=Run* -P cucumber