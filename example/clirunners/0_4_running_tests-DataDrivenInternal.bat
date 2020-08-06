echo "running tests"
cd ..
mvn clean verify -Dgroups=RegisterOKTestDDInternalDataTest -Dbrowser=chrome