def call(){

//# Unit test run
    stage('Deploy'){
        sh"""
            mkdir -p ${env.WORKSPACE_LOCAL}/m2/repository
            set BASE_PATH=${env.WORKSPACE_LOCAL}
            cd ${env.WORKSPACE_LOCAL}/${env.ANALIZEDMODULE}
            mvn -q deploy -DskipTests=true
            """
        archive "${env.WORKSPACE_LOCAL}/${env.ANALIZEDMODULE}target/*.jar"
    }
}

return this