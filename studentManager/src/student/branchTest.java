package student;

import java.util.Random;

public class branchTest { // 1. 클래스 이름을 파일 이름과 똑같이 수정!
    public static void main(String[] args) { 
        String[] names = {"김철수", "이영희", "박민지", "최도현"};
        Random r = new Random();

        System.out.println("=== 학생 대충 출력 ===");
        for (int i = 0; i < 3; i++) {
            int id = 20260000 + r.nextInt(1000);
            String name = names[r.nextInt(names.length)];
            double gpa = 2.0 + (r.nextDouble() * 2.5);

            System.out.printf("학번: %d | 이름: %s | 학점: %.2f\n", id, name, gpa);
        }
    }
}

