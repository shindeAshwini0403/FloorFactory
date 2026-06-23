pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/shindeAshwini0403/FloorFactory.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
         stage(' Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        success {
            echo 'Build Successful'
        }
        failure {
            echo 'Build Failed'
        }
      
}