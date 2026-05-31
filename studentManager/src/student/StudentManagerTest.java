// assertTrue(조건)

// 의미: "괄호 안의 조건이 무조건 참(True)이어야 해!"

// 활용: 데이터가 정상적으로 추가되었는지, 특정 조건을 만족하는지 확인할 때 씁니다.

// assertFalse(조건)

// 의미: "괄호 안의 조건이 무조건 거짓(False)이어야 해!"

// 활용: 데이터가 확실히 지워졌는지, 존재하지 않는지 확인할 때 씁니다.

// assertThrows(예외 타입, 실행할 코드)

// 의미: "이 코드를 실행하면 반드시 지정된 예외(에러)가 처리해야 !"

// 활용: 프로그램이 잘못된 입력(중복 추가, 없는 데이터 삭제 등)을 받았을 때 튕기지 않고 우리가 의도한 대로 '예외'를 잘 뱉어내며 방어하는지 테스트할 때 사용합니다. 


package student;

//테스트에 필요한 JUnit 5 라이브러리
import org.junit.jupiter.api.BeforeAll;
//BeforeEach -> BeforeAll
//최초 한 번만 객체를 생성하고 모든 테스트가 이를 공유하므로, 앞선 테스트의 결과가 뒤의 테스트에 영향을 줌. 
//반드시 static 메서드여야 하며, 다루는 변수도 static 이어야 합니다.
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder; // 순서 지정을 위한 임포트
import org.junit.jupiter.api.MethodOrderer;   // 순서 지정 방식을 위한 임포트
import org.junit.jupiter.api.Order;           // 순서 번호를 위한 임포트
import static org.junit.jupiter.api.Assertions.*;

//테스트 순서를 @Order 어노테이션을 기반으로 실행하겠다고 선언
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentManagerTest {

 // @BeforeAll에서 초기화해야 하므로 static 변수 사용
 private static StudentManager manager;

 @BeforeAll
 static void setUp() {
     // 최초 1회만 객체 생성
     manager = new StudentManager();
 }

 // [1] 학생 추가 테스트 (잘 추가되었는가?) 
 @Test
 @Order(1)
 void testAddStudent() {
     manager.addStudent("김민지");
     assertTrue(manager.hasStudent("김민지"), "학생이 정상적으로 추가되어야 합니다.");
 }

 // [2] 중복 추가 예외 처리 테스트 (중복 추가 시 예외처리를 하는가?) 
 @Test
 @Order(2)
 void testAddDuplicateStudentException() {
     assertThrows(IllegalArgumentException.class, () -> {
         manager.addStudent("김민지");
     }, "중복 추가 시 IllegalArgumentException이 발생해야 합니다.");
 }

 // [3] 학생 제거 테스트 (잘 제거되었는가?) 
 @Test
 @Order(3)
 void testRemoveStudent() {
     // 1번 테스트에서 추가한 "김민지"를 여기서 제거
     manager.removeStudent("김민지");
     assertFalse(manager.hasStudent("김민지"), "학생이 정상적으로 제거되어야 합니다.");
 }

 // [4] 존재하지 않는 학생 제거 예외 처리 테스트 (없는 데이터 접근할 때 예외처리를 하는가?)
 @Test
 @Order(4)
 void testRemoveNonExistentStudentException() {
     // 3번 테스트에서 "김민지"를 지웠기 때문에 현재 목록은 비어있는 상태임
     assertThrows(IllegalArgumentException.class, () -> {
         manager.removeStudent("김민지");
     }, "존재하지 않는 학생 제거 시 IllegalArgumentException이 발생해야 합니다.");
 }
}