package pairmatching.controller;

import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Options;
import pairmatching.service.MatchingService;
import pairmatching.utils.Parser;
import pairmatching.utils.RandomGenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MatchingController {

    private Map<String, Runnable> commands = new HashMap<>();
    private static Map<Options, List<List<String>>> crewGroup = new HashMap<>();
    private final MatchingService matchingService;
    private static final InputView inputView= new InputView();
    private static final OutputView outputView= new OutputView();
    private static String PREFIX_ERROR="[ERROR] ";

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    public void run() {
        initCommands();
        while(true){
            try {
                String choice=inputView.readChoice();
                if (choice.equals("Q")){
                    break;
                }
                Runnable command = commands.get(choice);
                command.run();

            } catch (IllegalArgumentException | NoSuchElementException e) { // 입력안함은 여기서 자동 제거
                System.out.println(PREFIX_ERROR+e.getMessage());
            }

        }

    }

    public static Options getOptions(){
        String input = inputView.readCourseAndMission();
        List<String> inputs = Parser.splitBy(input, ",");
        Course course = Course.fromName(inputs.get(0));
        Level level = Level.fromLevel(inputs.get(1));
        String mission=inputs.get(2);
        return new Options(level,course,mission);
    }

    public void initCommands() {
        commands.put("1", MatchingController::pairMatching);
        commands.put("2", MatchingController::pairCheck);
        commands.put("3", MatchingController::pairReset);
        commands.put("Q", MatchingController::quit);
    }

    public static void pairMatching() {
        outputView.printCourseInfo();
        while(true){
            Options options=getOptions();
            if (crewGroup.containsKey(options)){
                String response=inputView.readRetry();
                if (response.equals("아니오")){
                    continue;
                }

            }

            String content = readFile(options.getCourse().getFileName());
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

            crewGroup.put(options,crews);

            System.out.println("페어 매칭 결과입니다.");
            for (List<String> crew: crews){
                System.out.println(String.join(" : ",crew));
            }
            break;
        }

    }

    public static void pairCheck() {
        outputView.printCourseInfo();
        Options options=getOptions();

        if (crewGroup.containsKey(options)){
            System.out.println("페어 매칭 결과입니다.");
            for (List<String> crew: crewGroup.get(options)){
                System.out.println(String.join(" : ",crew));
            }
            return;
        }
        throw new IllegalArgumentException("매칭 이력이 없습니다.");

    }

    public static void pairReset() {
        crewGroup = new HashMap<>();
        System.out.println("\n초기화 되었습니다. \n");
    }

    public static void quit() {
        return;
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

}
