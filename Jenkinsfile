pipeline {
    agent any 
    stages {
        stage('checkout') {
            steps {
               checkout scm 
            }
        }
        stage('build'){
            steps{
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('deploy'){
            steps{
                sh '''
                
                docker rm categoryproductmachinecode -f || true 
                docker rmi categoryproductmachinecode || true

                docker build -t categoryproductmachinecode .
                docker run -d -p 8081:8080 categoryproductmachinecode
                 '''
            }
        }
    }
}
