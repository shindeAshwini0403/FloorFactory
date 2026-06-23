pipeline {
    agent any
      tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code'
            }
        }

        stage('Build') {
            steps {
                echo 'Build application'
            }
        }

        stage('Test') {
            steps {
                echo' test application'
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
}