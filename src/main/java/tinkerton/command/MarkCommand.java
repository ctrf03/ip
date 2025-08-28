package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    /**
     * Constructs a MarkCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the mark command, marking the specified task as completed.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command format is invalid or the task index is out of
     *         bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new TinkertonException("Mark what...");
        }

        int markId = Integer.parseInt(parts[1]) - 1;

        if (markId < 0 || markId > tasks.size() - 1) {
            throw new TinkertonException("Your numbering for your tasks may be abit off...");
        }

        tasks.get(markId).complete();

        ui.print("Nice! I've marked this task as done:");
        ui.print(tasks.get(markId).toString());

        save.save(tasks);
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as mark does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
