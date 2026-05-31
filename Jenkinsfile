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
                // 컴파일 (테스트 라이브러리 없이 순수 자바 코드만 빌드)
                // -d classes: 컴파일된 결과물을 classes 폴더에 저장
                sh 'javac -encoding UTF-8 -d classes studentManager/src/student/*.java'
                echo 'Build Success!'
            }
        }
    }
    
    post {
        success {
            echo '빌드가 성공적으로 완료되었습니다.'
        }
        failure {
            echo '빌드 중 오류가 발생했습니다. 로그를 확인하세요.'
        }
    }
}