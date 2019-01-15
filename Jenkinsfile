pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''chmod +x gradlew

./gradlew build
'''
      }
    }
    stage('Test') {
      steps {
        sh '''chmod +x gradlew

./gradlew test
'''
      }
    }
  }
}