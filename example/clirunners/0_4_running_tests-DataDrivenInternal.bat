echo "running tests"
cd ..
mvn test surefire:test -Dtest=RegisterOKTestDDInternalDataTest -Dbrowser=chrome