public abstract class Command {
    private String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String getFull() {
        return fullCommand;
    }

    public abstract void execute(TaskList tasks, Ui ui, Save save) throws TinkertonException;

    public abstract boolean isExit();
}
