echo "running tests"
cd ..
mvn clean verify -Dgroups=RegisterOKTest -Dbrowser=chrome