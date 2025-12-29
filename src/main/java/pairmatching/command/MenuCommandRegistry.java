package pairmatching.command;

import java.util.EnumMap;
import pairmatching.command.impl.FeatureACommand;
import pairmatching.command.impl.FeatureBCommand;
import pairmatching.command.impl.FeatureCCommand;
import pairmatching.service.PairMatchingService;

public class MenuCommandRegistry {

    private final EnumMap<MenuOption, Command> commands;

    private MenuCommandRegistry(EnumMap<MenuOption, Command> commands) {
        this.commands = commands;
    }

    public static MenuCommandRegistry from(PairMatchingService service) {
        EnumMap<MenuOption, Command> map = new EnumMap<>(MenuOption.class);
        map.put(MenuOption.A, new FeatureACommand(service));
        map.put(MenuOption.B, new FeatureBCommand(service));
        map.put(MenuOption.C, new FeatureCCommand(service));
        return new MenuCommandRegistry(map);
    }

    public void execute(MenuOption option) {
        commands.get(option).execute();
    }
}
