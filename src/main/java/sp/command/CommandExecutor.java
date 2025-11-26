package sp.command;

public interface CommandExecutor {
    <T> T execute(Command<T> command);
}
