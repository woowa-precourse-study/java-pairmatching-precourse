package pairmatching;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.exception.Validator;

import java.util.*;

public class Application {

    // ===== 상수 =====

    static Map<String, Runnable> commands = new HashMap<>();
    static String PREFIX_ERROR="[ERROR] ";
    static final int MAX_RETRY = 10;


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
        try{
            System.out.println("기능을 선택하세요.\n 1. 페어 매칭\n 2. 페어 조회\n 3. 페어 초기화\n Q. 종료");
            String choice = readInputWithRetry(List.of(
                    Validator::validateNotBlank,
                    Validator::validateInputFormat
            ));

            Runnable command = commands.get(choice);
            command.run();



        } catch(IllegalArgumentException | NoSuchElementException e){ // 입력안함은 여기서 자동 제거
            System.out.println(PREFIX_ERROR+e.getMessage());
        }

    }

    static void initCommands() {
        commands.put("1", Application::pairMatching);
        commands.put("2", Application::pairCheck);
        commands.put("3", Application::pairReset);
        commands.put("Q", Application::quit);
    }

    static void pairMatching() {
        System.out.println("페어매칭");
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
