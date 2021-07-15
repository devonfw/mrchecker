echo "Running tests..."
mvn -f ../pom.xml clean verify -Dgroups=ResolutionTest -Dbrowser=firefox