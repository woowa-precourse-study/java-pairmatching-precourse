package pairmatching.controller;

import pairmatching.command.*;
import pairmatching.domain.*;
import pairmatching.service.MatchingService;
import pairmatching.utils.Parser;
import pairmatching.utils.RandomGenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MatchingController {

    private Map<String, Command> commands = new HashMap<>();
    private final MatchingMachine matchingMachine = new MatchingMachine();
    private final MatchingService matchingService;
    private static final InputView inputView = new InputView();

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
        initCommands();
    }

    public void run() {
        while(true){
            try {
                String choice=inputView.readChoice();

                if (choice.equals("Q")){
                    break;
                }

                Command command = commands.get(choice);
                command.execute();

            } catch (IllegalArgumentException | NoSuchElementException e) { // 입력안함은 여기서 자동 제거
                System.out.println("[ERROR] "+e.getMessage());
            }

        }

    }



    private void initCommands() {
        commands.put("1", new PairMatching(this));
        commands.put("2", new PairCheck(this));
        commands.put("3", new PairReset(this));
        commands.put("Q", new Quit(this));
    }


    public void pairMatching() {
        OutputView.printCourseInfo();
        while(true){
            String input = inputView.readCourseAndMission();
            Options options=getOptions(input);
            if (matchingMachine.isAlreadyMatched(options)){
                String response=inputView.readRetry();
                if (response.equals("아니오")){
                    continue;
                }
            }

            String content = readFile(options.course().getFileName());  // TODO : 이거 record 정리하기
            List<String> names = RandomGenerator.getRandomNames(Parser.splitBy(content, "\n"));

            Deque<String> queue = new ArrayDeque<>(names);
            List<Pairs> pairs= new ArrayList<>();

            while (!queue.isEmpty()){
                List<String> crews=new ArrayList<>();
                if (queue.size()==3){
                    crews.add(queue.poll());
                    crews.add(queue.poll());
                    crews.add(queue.poll());
                    pairs.add(new Pairs(crews));
                    break;
                }

                crews.add(queue.poll());
                crews.add(queue.poll());
                pairs.add(new Pairs(crews));
            }
            matchingMachine.addMatch(options, pairs);

            System.out.println("페어 매칭 결과입니다.");
            for (Pairs pair: matchingMachine.getMatch(options)){
                System.out.println(String.join(" : ",pair.getPairs()));
            }
            break;
        }

    }

    public void pairCheck() {
        OutputView.printCourseInfo();
        String input = inputView.readCourseAndMission();
        Options options=getOptions(input);
        if (matchingMachine.isAlreadyMatched(options)){
            OutputView.printMatchingResult(matchingMachine.getMatch(options));
            return;
        }
        throw new IllegalArgumentException("매칭 이력이 없습니다.");
    }

    public void pairReset() {
        matchingMachine.reset();
        System.out.println("\n초기화 되었습니다. \n");
    }

    public void quit() {
        return;
    }

    public static Options getOptions(String input){
        List<String> inputs = Parser.splitBy(input, ",");
        Course course = Course.fromName(inputs.get(0));
        Level level = Level.fromLevel(inputs.get(1));
        String mission=inputs.get(2);
        return new Options(level,course,mission);
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
