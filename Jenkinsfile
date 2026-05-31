pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // 프로젝트 폴더 내에 있는 jar 파일을 직접 경로로 지정 (절대경로 사용)
                sh 'javac -encoding UTF-8 -cp .:/Users/kimminji/git/SWprac/junit-platform-console-standalone-5.10.2.jar -d classes studentManager/src/student/*.java'
            }
        }
        stage('Test') {
            steps {
                // 테스트 실행 시에도 동일한 jar 경로를 사용
                sh 'java -cp classes:/Users/kimminji/git/SWprac/junit-platform-console-standalone-5.10.2.jar org.junit.platform.console.ConsoleLauncher --scan-classpath > test_results.txt'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'test_results.txt', fingerprint: true
        }
    }
}