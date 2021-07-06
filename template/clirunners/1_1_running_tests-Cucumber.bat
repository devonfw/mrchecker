echo "Running Cucumber tests..."
mvn -f ../pom.xml clean verify -Dcucumber.class=Run* -P cucumber