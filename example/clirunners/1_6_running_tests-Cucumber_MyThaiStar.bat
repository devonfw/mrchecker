echo "Running MyThaiStar Cucumber tests..."
mvn -f ../pom.xml clean verify -Dcucumber.class=RunMts* -P cucumber -Dbrowser=firefox