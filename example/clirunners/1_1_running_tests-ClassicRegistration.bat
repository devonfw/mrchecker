echo "Running tests..."
mvn -f ../pom.xml clean verify -Dgroups=RegisterOKTest -Dbrowser=firefox