echo "running tests"
cd ..
mvn clean verify -Dgroups=ResolutionTest -Dbrowser=chrome