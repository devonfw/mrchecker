def call(){

//# Build deployable app
    stage('Deploy'){
        sh"""
            mkdir -p ${env.WORKSPACE}/m2/repository
            set BASE_PATH=${env.WORKSPACE}
            cd ${env.APP_WORKSPACE}
            mvn -q deploy -DskipTests=true
        """
        archive "${env.APP_WORKSPACE}target/*.jar"
    }
}
return this