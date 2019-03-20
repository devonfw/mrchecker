echo "running tests"
cd ..
mvn test surefire:test -Dtest=com.capgemini.mrchecker.selenium.features.samples.resolutions.ResolutionTest -Dbrowser=chrome