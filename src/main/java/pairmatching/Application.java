package pairmatching;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.exception.Validator;
import pairmatching.utils.Parser;
import pairmatching.utils.RandomGenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Application {

    // ===== 상수 =====

    static Map<String, Runnable> commands = new HashMap<>();
    static String PREFIX_ERROR = "[ERROR] ";
    static final int MAX_RETRY = 10;
    static final String BACKEND_FILE_NAME = "backend-crew2.md";
    static final String FRONT_FILE_NAME = "frontend-crew2.md";


    // ===== main / run =====

    public static void main(String[] args) {
        try {
            initCommands();
            run();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    static void run() {
        try {
            System.out.println("기능을 선택하세요.\n 1. 페어 매칭\n 2. 페어 조회\n 3. 페어 초기화\n Q. 종료");
            String choice = readInputWithRetry(List.of(
                    Validator::validateNotBlank,
                    Validator::validateInputFormat
            ));

            Runnable command = commands.get(choice);
            command.run();


        } catch (IllegalArgumentException | NoSuchElementException e) { // 입력안함은 여기서 자동 제거
            System.out.println(PREFIX_ERROR + e.getMessage());
        }

    }

    static void initCommands() {
        commands.put("1", Application::pairMatching);
        commands.put("2", Application::pairCheck);
        commands.put("3", Application::pairReset);
        commands.put("Q", Application::quit);
    }

    static void pairMatching() {
        System.out.println("""
                                
                #############################################
                과정: 백엔드 | 프론트엔드
                미션:
                  - 레벨1: 자동차경주 | 로또 | 숫자야구게임
                  - 레벨2: 장바구니 | 결제 | 지하철노선도
                  - 레벨3:\s
                  - 레벨4: 성능개선 | 배포
                  - 레벨5:\s
                ############################################
                과정, 레벨, 미션을 선택하세요.
                ex) 백엔드, 레벨1, 자동차경주
                """);
        String option = readInputWithRetry(List.of(
                Validator::validateNotBlank
        ));
        List<String> options = Parser.splitBy(option, ",");

        Course course = Course.fromName(options.get(0));
        Level level = Level.fromLevel(options.get(1));

        // 파일 읽어오기
        String content = readFile(course.getFileName());
        List<String> names = RandomGenerator.getRandomNames(Parser.splitBy(content, "\n"));


        Deque<String> queue = new ArrayDeque<>(names);
        List<List<String>> crews= new ArrayList<>();
        while (!queue.isEmpty()){
            if (queue.size()==3){
                crews.add(List.of(queue.pollFirst(),queue.pollFirst(),queue.pollFirst()));
                break;
            }
            crews.add(List.of(queue.pollFirst(),queue.pollFirst()));
        }

        System.out.println("페어 매칭 결과입니다.");
        for (List<String> crew: crews){
            System.out.println(String.join(" : ",crew));
        }






    }

    static void pairCheck() {
        System.out.println("페어 조회");
    }

    static void pairReset() {
        System.out.println("페어 초기화");
    }

    static void quit() {
        System.out.println("종료");
    }

    public static String readFile(String fileName) {
        String content = "";
        try {
            // 👉 README.md의 전체 내용을 한 줄의 문자열로 읽어옴
            content = Files.readString(Path.of(
                    ClassLoader.getSystemResource(fileName).toURI()
            ));
            content = content.replace("null", "");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return content;
    }

    static String readInput(List<Validator> validators) {
        String input = Console.readLine();
        for (Validator v : validators) {
            v.validate(input);
        }
        return input;
    }


    static String readInputWithRetry(List<Validator> validators) {
        int retry = 0;
        while (true) {
            try {
                return readInput(validators);
            } catch (IllegalArgumentException | NoSuchElementException e) {
                retry++;
                System.out.println(PREFIX_ERROR + e.getMessage());

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }

}
