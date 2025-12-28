package pairmatching.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import pairmatching.command.Command;
import pairmatching.command.CommandResponse;
import pairmatching.command.RematchOption;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.domain.Crew;
import pairmatching.service.PairMatchingService;
import pairmatching.util.InputParser;
import pairmatching.util.Retry;
import pairmatching.view.InputView;
import pairmatching.view.model.FeatureAModel;

public class FeatureACommand implements Command<FeatureACommand> {

    private final PairMatchingService service;

    public FeatureACommand(PairMatchingService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            List<Enum<? extends Enum<?>>> content = Retry.retryUntilSuccess(() -> {
                String readContents = InputView.readContents();
                List<String> contents = InputParser.parseContents(readContents);

                Course course = Course.from(contents.get(0).trim());
                Level level = Level.from(contents.get(1).trim());
                Mission mission = Mission.from(contents.get(2).trim());

                return Arrays.asList(course, level, mission);
            });

            Course course = (Course) content.get(0);
            Level level = (Level) content.get(1);
            Mission mission = (Mission) content.get(2);

            if (service.MatchingResultIsExist(course, level, mission)) {
                RematchOption rematchOption = Retry.retryUntilSuccess(() -> {
                    String readRematching = InputView.readRematching();
                    return RematchOption.from(readRematching);
                });

                if (rematchOption.equals(RematchOption.YES)) {
                    service.removeRecord(course, level, mission);
                }

                if (rematchOption.equals(RematchOption.NO)) {
                    continue;
                }
            }

            // 매칭 실행
            List<Set<Crew>> matching = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                matching = service.generateMatching(course, level, mission);
                if (!matching.isEmpty()) {
                    break;
                }
            }

            if (matching.isEmpty()) {
                continue;
            }

            return CommandResponse.keepGoing(new FeatureAModel(matching));
        }

    }
}
