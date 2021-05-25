echo "Running Cucumber tests..."
cd ..
mvn clean verify -Dcucumber.class=Run* -P cucumber