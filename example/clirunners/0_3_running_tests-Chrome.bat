echo "running tests"
cd ..
mvn verify -Dgroups=TestsChrome -DexcludeGroups=TestsLocal,TestsNONParallel -Dbrowser=chrome