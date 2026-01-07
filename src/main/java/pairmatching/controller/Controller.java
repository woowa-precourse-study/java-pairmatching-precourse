package pairmatching.controller;

import pairmatching.command.*;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.CrewGroup;
import pairmatching.domain.Machine;
import pairmatching.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {
    private Map<String, Command> commands = new HashMap<>();
    private final Machine machine;
    private final InputView inputView;
    private final Service service;
    public static final int MAX_RETRY = 10;

    public Controller(Service service) {
        this.inputView = new InputView();
        this.service = service;
        this.machine = new Machine();
    }

    public void run() {
        initCommands();
        initSetting();
        while(true){
            String function = doRetry(inputView::readFunction);

            if (function.equals("Q")){
                return;
            }

            Command command = commands.get(function);
            command.execute();
        }

    }

    private void initCommands() {
        commands.put("1", new PairMatching(service,machine));
        commands.put("2", new FindPair(service,machine));
        commands.put("3", new PairReset(service,machine));
        commands.put("Q", new Quit());
    }

    private void initSetting(){
        machine.add(Course.BACKEND,readBackendFile());
        machine.add(Course.FRONTEND,readFrontendFile());
    }

    public CrewGroup readBackendFile() {
        CrewGroup crewGroup=new CrewGroup();
        try{
            BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/backend-crew.md"));
            br.readLine(); // header skip

            String line;
            while((line=br.readLine())!=null){
                crewGroup.add(new Crew(line, Course.BACKEND));
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽는데 오류가 발생했습니다.");
        }
        return crewGroup;
    }

    public CrewGroup readFrontendFile() {
        CrewGroup crewGroup=new CrewGroup();
        try{
            BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/frontend-crew.md"));
            br.readLine(); // header skip

            String line;
            while((line=br.readLine())!=null){
                crewGroup.add(new Crew(line, Course.FRONTEND));
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽는데 오류가 발생했습니다.");
        }
        return crewGroup;
    }


    private <T> T doRetry(Supplier<T> action) {
        int retry = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retry++;
                System.out.println(e.getMessage());

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }


}

