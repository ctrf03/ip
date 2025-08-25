package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.Deadline;
import tinkerton.storage.Save;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String[] parts = fullCommand.split("/by");

        if (!fullCommand.contains("/by")) {
            throw new TinkertonException("Your deadline task has no deadline...");
        }

        if (parts.length < 2) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        String time = parts[1].trim();

        if (!time.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
            throw new TinkertonException("The format of your deadline should be yyyy-MM-dd HHmm!");
        }

        String deadlineName = parts[0].substring(9).trim();
        tasks.add(new Deadline(deadlineName, false, time));

        ui.print("Got it, I've added this task:");
        ui.print(tasks.get(tasks.size() - 1).toString());
        ui.print("Now you have " + tasks.size() + " tasks in the list.");

        save.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
