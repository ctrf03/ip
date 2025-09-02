package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.Deadline;
import tinkerton.storage.Save;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructs a DeadlineCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Deadline command, adding a new Deadline task to the list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command format is invalid.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
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

        save.save(tasks);

        return "Got it, I've added this task:\n" + tasks.get(tasks.size() - 1).toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as adding a Deadline does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
