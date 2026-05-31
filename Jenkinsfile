pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // 1. 깃허브에서 소스코드 가져오기
                checkout scm
            }
        }
        
        stage('Setup') {
            steps {
                // 2. JUnit 파일 확인 및 자동 다운로드 (이미 있으면 스킵)
                sh '''
                if [ ! -f junit-platform-console-standalone-5.10.2.jar ]; then
                    echo "JUnit 파일이 없습니다. 다운로드를 시작합니다..."
                    curl -L -O https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/5.10.2/junit-platform-console-standalone-5.10.2.jar
                else
                    echo "JUnit 파일이 이미 존재합니다. 다운로드를 건너뜁니다."
                fi
                '''
            }
        }
        
        stage('Build') {
            steps {
                echo 'Compiling...'
                // 3. 자바 코드 컴파일 (JUnit 경로 포함)
                sh 'javac -encoding UTF-8 -cp .:junit-platform-console-standalone-5.10.2.jar -d classes studentManager/src/student/*.java'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Testing...'
                // 4. JUnit 5로 테스트 실행 및 결과를 파일로 저장
                sh 'java -cp classes:junit-platform-console-standalone-5.10.2.jar org.junit.platform.console.ConsoleLauncher --scan-classpath > test_results.txt'
            }
        }
    }
    
    post {
        always {
            // 5. 테스트 결과 파일을 젠킨스 빌드 페이지에 기록
            archiveArtifacts artifacts: 'test_results.txt', fingerprint: true
        }
        success {
            echo '모든 과정이 성공적으로 완료되었습니다!'
        }
        failure {
            echo '빌드 또는 테스트 중 오류가 발생했습니다. 로그를 확인하세요.'
        }
    }
}