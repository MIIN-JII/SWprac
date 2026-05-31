pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        
        stage('Build') {
            steps {
                // 이클립스에 이미 설치된 플러그인 경로를 직접 참조 (파일을 새로 안 받음)
                sh 'javac -encoding UTF-8 -cp .:/Applications/Eclipse.app/Contents/Eclipse/plugins/junit-jupiter-api_6.0.3.jar:./classes -d classes studentManager/src/student/*.java'
            }
        }
        
        stage('Test') {
            steps {
                // ConsoleLauncher 대신, 클래스패스에 라이브러리를 포함해 실행
                sh 'java -cp .:classes:/Applications/Eclipse.app/Contents/Eclipse/plugins/junit-jupiter-api_6.0.3.jar org.junit.platform.console.ConsoleLauncher --scan-classpath > test_results.txt'
            }
        }
    }
    
    post {
        always {
            archiveArtifacts artifacts: 'test_results.txt'
            // JUnit 플러그인이 테스트 결과를 보기 좋게 리포트로 만들어줍니다.
            junit 'test_results.txt' 
        }
    }
}