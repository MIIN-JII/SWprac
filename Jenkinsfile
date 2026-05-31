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
                // 맥용 컴파일 명령어 (classes 폴더에 자바 컴파일 결과 저장)
                sh 'javac -encoding UTF-8 -d classes studentManager/src/student/*.java'
            }
        }
        stage('Test') {
    	    steps {
        	sh 'java -cp .:junit-platform-console-standalone-5.10.2.jar org.junit.platform.console.ConsoleLauncher --scan-classpath > test_results.txt'
    	    }
	}
    }
    post {
        always {
            // 테스트 결과 파일을 젠킨스에서 바로 볼 수 있게 저장
            archiveArtifacts artifacts: 'test_results.txt', fingerprint: true
        }
        failure {
            echo 'Build or test failed'
        }
        success {
            echo 'Build and test succeeded'
        }
    }
}