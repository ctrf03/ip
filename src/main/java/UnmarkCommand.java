public class UnmarkCommand extends Command {
    public UnmarkCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new TinkertonException("Unmark what...");
        }

        int unmarkId = Integer.parseInt(parts[1]) - 1;

        if (unmarkId < 0 || unmarkId > tasks.size() - 1) {
            throw new TinkertonException("Your numbering for your tasks may be abit off...");
        }

        tasks.get(unmarkId).uncomplete();

        ui.print("OK, I've marked this task as not done yet:");
        ui.print(tasks.get(unmarkId).toString());

        save.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
