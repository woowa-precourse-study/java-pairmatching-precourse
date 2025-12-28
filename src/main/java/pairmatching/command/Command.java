package pairmatching.command;

public interface Command<T extends Command<?>> {
    CommandResponse execute();
}
