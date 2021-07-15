echo "Running tests..."
mvn -f ../pom.xml clean verify -Dgroups=TestsChrome -DexcludeGroups=TestsLocal,TestsNONParallel -Dbrowser=firefox