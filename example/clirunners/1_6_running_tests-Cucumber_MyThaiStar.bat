
echo "Running MyThaiStar Cucumber tests..."
cd ..
mvn clean verify -Dcucumber.class=RunMts* -P cucumber -Dbrowser=firefox