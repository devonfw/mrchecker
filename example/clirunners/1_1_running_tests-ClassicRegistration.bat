echo "Running tests..."
cd ..
mvn clean verify -Dgroups=RegisterOKTest -Dbrowser=firefox