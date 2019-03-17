echo "running tests"
cd ..
mvn clean test surefire:test -Dtest=RegisterOKTest -Dbrowser=chrome