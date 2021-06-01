echo "Running tests..."
cd ..
mvn clean verify -Dgroups=RegisterOKTestDDInternalDataTest -Dbrowser=firefox