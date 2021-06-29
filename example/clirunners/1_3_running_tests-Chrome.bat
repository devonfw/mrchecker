echo "Running tests..."
cd ..
mvn clean verify -Dgroups=TestsChrome -DexcludeGroups=TestsLocal,TestsNONParallel -Dbrowser=firefox