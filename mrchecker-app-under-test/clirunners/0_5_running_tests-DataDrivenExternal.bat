echo "running tests"
cd ..
mvn test surefire:test -Dtest=RegisterOKTestDDExternalDataTest -Dbrowser=chrome