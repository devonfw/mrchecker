echo "Running tests..."
cd ..
mvn clean verify -Dgroups=ResolutionTest -Dbrowser=firefox