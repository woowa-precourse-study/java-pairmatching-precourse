package pairmatching.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pairmatching.controller.Command;
import pairmatching.controller.PairMatchingController;
import pairmatching.controller.command.PairMatchingCommand;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.CrewGroup;
import pairmatching.domain.MatchingRecords;
import pairmatching.domain.PairGenerator;
import pairmatching.domain.PairMatchingProgram;
import pairmatching.domain.crewgroup.BackEndCrewGroup;
import pairmatching.domain.crewgroup.FrontEndCrewGroup;
import pairmatching.domain.pairgenerator.RandomPairGenerator;

public class ApplicationFactory {

    public PairMatchingController controller() {
        return new PairMatchingController(commands(), matchingProgram());
    }

    public Map<String, Command> commands() {
        return Map.of(
                "1", new PairMatchingCommand()
        );
    }

    public PairMatchingProgram matchingProgram() {
        return new PairMatchingProgram(defaultGroup(), generator(), matchingRecords());
    }

    private PairGenerator generator() {
        return new RandomPairGenerator();
    }

    private MatchingRecords matchingRecords() {
        return new MatchingRecords(Map.of());
    }

    private Map<Course, CrewGroup> defaultGroup() {
        return Map.of(
                Course.FRONTEND, frontEndCrew(),
                Course.BACKEND, backEndCrew()
        );
    }

    private CrewGroup frontEndCrew() {
        List<Crew> crews = new ArrayList<>();

        File file = new File("src/main/resources/frontend-crew.md");
        readCrew(file, Course.FRONTEND, crews);

        return new FrontEndCrewGroup(crews);
    }

    private CrewGroup backEndCrew() {
        List<Crew> crews = new ArrayList<>();

        File file = new File("src/main/resources/backend-crew.md");
        readCrew(file, Course.BACKEND, crews);

        return new BackEndCrewGroup(crews);
    }

    private void readCrew(File file, Course course, List<Crew> crews) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String name = reader.readLine();
                crews.add(new Crew(course, name));
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
