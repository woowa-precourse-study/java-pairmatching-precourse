package pairmatching.command;

import java.util.EnumMap;
import pairmatching.command.impl.FeatureACommand;
import pairmatching.command.impl.FeatureBCommand;
import pairmatching.command.impl.QuitCommand;
import pairmatching.service.PairMatchingService;

public class MenuCommandRegistry {

    private final EnumMap<MenuOption, Command<? extends Command<?>>> commands;

    private MenuCommandRegistry(EnumMap<MenuOption, Command<? extends Command<?>>> commands) {
        this.commands = commands;
    }

    public static MenuCommandRegistry defaultRegistry(PairMatchingService service) {
        EnumMap<MenuOption, Command<? extends Command<?>>> map = new EnumMap<>(MenuOption.class);
        map.put(MenuOption.A, new FeatureACommand(service));
        map.put(MenuOption.B, new FeatureBCommand(service));
        map.put(MenuOption.QUIT, new QuitCommand());
        return new MenuCommandRegistry(map);
    }

    public CommandResponse execute(MenuOption option) {
        return commands.get(option).execute();
    }
}
