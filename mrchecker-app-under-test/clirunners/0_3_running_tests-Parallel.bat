echo "running tests"
cd ..
mvn test surefire:test -Dtest=TS_Tag2 -Dbrowser=chrome