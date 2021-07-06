echo "Running tests..."
mvn -f ../pom.xml clean verify -Dgroups=RegisterOKTestDDInternalDataTest -Dbrowser=firefox