package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.ToDo;
import tinkerton.storage.Save;

/**
 * Represents a command to add a ToDo task.
 */
public class ToDoCommand extends Command {
    /**
     * Constructs a ToDoCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the ToDo command, adding a new ToDo task to the list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String[] parts = super.getFull().split(" ");

        if (parts.length < 2) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        String toDOName = fullCommand.substring(5).trim();
        tasks.add(new ToDo(toDOName, false));

        ui.print("Got it, I've added this task:");
        ui.print(tasks.get(tasks.size() - 1).toString());
        ui.print("Now you have " + tasks.size() + " tasks in the list.");

        save.save(tasks);
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as adding a ToDo does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
