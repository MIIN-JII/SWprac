pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // 깃허브에서 소스코드 가져오기
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compiling...'
                // 자바 파일들을 컴파일하여 classes 폴더에 저장
                // -cp 옵션으로 JUnit 라이브러리를 경로와 함께 지정
                sh 'javac -encoding UTF-8 -cp .:junit-platform-console-standalone-5.10.2.jar -d classes studentManager/src/student/*.java'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Testing...'
                // JUnit을 사용하여 테스트를 수행하고 결과를 test_results.txt에 저장
                // --scan-classpath: 컴파일된 클래스 내에서 테스트 케이스를 자동으로 찾음
                sh 'java -cp classes:junit-platform-console-standalone-5.10.2.jar org.junit.platform.console.ConsoleLauncher --scan-classpath > test_results.txt'
            }
        }
    }
    
    post {
        always {
            // 테스트 결과 파일을 젠킨스 작업 페이지에 남김
            archiveArtifacts artifacts: 'test_results.txt', fingerprint: true
        }
        success {
            echo 'Build and test succeeded!'
        }
        failure {
            echo 'Build or test failed. Please check the logs.'
        }
    }
}