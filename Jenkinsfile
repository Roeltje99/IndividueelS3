pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh '''chmod +x /.gradle
/.gradle test
'''
      }
    }
    stage('Build') {
      steps {
        sh '''chmod +x gradlew

./gradlew build
'''
      }
    }
  }
}