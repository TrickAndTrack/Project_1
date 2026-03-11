pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-21'
    }

    environment {
        DEV_SERVER = "192.168.1.10"
        QA_SERVER = "192.168.1.11"
        UAT_SERVER = "192.168.1.12"
        PROD_SERVER = "192.168.1.13"

        APP_NAME = "project1"
        DEPLOY_DIR = "/opt/apps"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/TrickAndTrack/Project_1.git'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }

        stage('Deploy DEV') {
            steps {
                echo "Deploying to DEV"
                sh """
                scp target/*.jar user@$DEV_SERVER:$DEPLOY_DIR/$APP_NAME.jar
                ssh user@$DEV_SERVER 'pkill -f project1 || true'
                ssh user@$DEV_SERVER 'nohup java -jar $DEPLOY_DIR/$APP_NAME.jar --spring.profiles.active=dev > app.log 2>&1 &'
                """
            }
        }

        stage('Deploy QA') {
            steps {
                input message: "Approve deployment to QA?"
                sh """
                scp target/*.jar user@$QA_SERVER:$DEPLOY_DIR/$APP_NAME.jar
                ssh user@$QA_SERVER 'pkill -f project1 || true'
                ssh user@$QA_SERVER 'nohup java -jar $DEPLOY_DIR/$APP_NAME.jar --spring.profiles.active=qa > app.log 2>&1 &'
                """
            }
        }

        stage('Deploy UAT') {
            steps {
                input message: "Approve deployment to UAT?"
                sh """
                scp target/*.jar user@$UAT_SERVER:$DEPLOY_DIR/$APP_NAME.jar
                ssh user@$UAT_SERVER 'pkill -f project1 || true'
                ssh user@$UAT_SERVER 'nohup java -jar $DEPLOY_DIR/$APP_NAME.jar --spring.profiles.active=uat > app.log 2>&1 &'
                """
            }
        }

        stage('Deploy PROD') {
            steps {
                input message: "Approve deployment to PROD?"
                sh """
                scp target/*.jar user@$PROD_SERVER:$DEPLOY_DIR/$APP_NAME.jar
                ssh user@$PROD_SERVER 'pkill -f project1 || true'
                ssh user@$PROD_SERVER 'nohup java -jar $DEPLOY_DIR/$APP_NAME.jar --spring.profiles.active=prod > app.log 2>&1 &'
                """
            }
        }

    }

    post {
        success {
            echo 'Deployment completed successfully!'
        }

        failure {
            echo 'Build or deployment failed!'
        }
    }
}