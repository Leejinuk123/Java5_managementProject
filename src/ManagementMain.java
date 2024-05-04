import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
    // dev로 가자
/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class ManagementMain {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static final Scanner sc = new Scanner(System.in);




    // 실행부분
    public static void main(String[] args) {
        setInitData();



        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }





    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>(); // 학생 배열 초기화
        // 과목 배열 초기화 , Enum을 활용하여 리펙토링
        subjectStore = List.of(
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.JAVA),
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.OBJECT_ORIENTED),
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.SPRING),
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.JPA),
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.MYSQL),
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.DESIGN_PATTERN),
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.SPRING_SECURITY),
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.REDIS),
                new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectList.MONGODB)
        );
        scoreStore = new ArrayList<>(); // 점수 배열 초기화
    }


    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        StudentMethod studentMethod = new StudentMethod();

        // 기능구현 - by 정근
            // inItMethod 로 INDEX_TYPE_STUDENT 만 넘겨주면 Student 인스턴스를 리턴받음
          Student student = studentMethod.inItMethod(sequence(INDEX_TYPE_STUDENT));

        // 기능 구현 (필수 과목, 선택 과목)
        //필수과목 입력받고 저장하기
        studentMethod.mandatoryMethod(student ,subjectStore);

        //선택과목 입력받고 저장하기
        studentMethod.choiceMethod(student, subjectStore);


        // 리스트 확인
        System.out.println(student.getStudentId() + "   " + student.getStudentName()); // 수강생 인스턴스 생성확인
        ArrayList studentSubjectList = student.getStudentSubjectList();
        for (Object o : studentSubjectList) {
            System.out.println(o);
        }


        System.out.println("수강생 등록 성공!\n");


    }






    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {

        /* 수강생 ID 입력받기 >  등록 or 수정  or 조회 or 메인
         등록: 과목이름 / 회차 / 점수 / 등급 입력 하면 한 번에 처리(저장)
         수정: 과목이름 / 회차 / 점수 / 등급 입력 하면 한 번에 처리(저장)
         조회: 과목 선택 > 회차 선택 > 과목-회차-등급 출력
         */

        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("\n관리할 수강생의 번호를 입력하시오...");
            int inStudentId = sc.nextInt(); //입력값을 받은 inStudentID로 가지고 있기
            sc.nextLine();

            //받은 수강생Id 로 Score 저장소에 등록
            //받은 수강생Id 로 등록된 것 가져와서 수정,
            //받은 수강생Id 로 등록 후 조회할 예정
            //getStudentId에 등록된 id 라면 다음으로 넘어가고, 아니면 없다는 메세지 출력후 다시 입력하게 유도
            //등록된 id가 하나도 없으므로 위 내용은 불가능함

            System.out.println("==================================");
            System.out.println("1. 수강생" + inStudentId + "의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생" + inStudentId + "의 과목별 회차 점수 수정");
            System.out.println("3. 수강생" + inStudentId + "의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        System.out.println("등록할 과목을 입력해 주세요: ");

        String studentId = getStudentId(); // 관리할 수강생 고유 번호
//        등록: 과목이름 / 회차 / 점수 / 등급 입력 하면 한 번에 처리(저장)

        // 기능 구현
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        //수정: 과목이름 / 회차 / 점수 / 등급 입력 하면 한 번에 처리(저장)


        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호


        // 기능 구현 (조회할 특정 과목)

        System.out.println("회차별 등급을 조회합니다...");
        //조회: 과목 선택 > 회차 선택 > 과목-회차-등급 출력
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

}