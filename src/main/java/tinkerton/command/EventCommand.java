package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.Event;
import tinkerton.storage.Save;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    /**
     * Constructs an EventCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Event command, adding a new Event task to the list.
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
        String[] parts = fullCommand.split("/from|/to");

        if (!fullCommand.contains("/from") || !fullCommand.contains("/to")) {
            throw new TinkertonException("Your event has no start and end...");
        }

        if (parts.length < 3) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        String start = parts[1].trim();

        if (!start.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
            throw new TinkertonException("The format of your deadline should be yyyy-MM-dd HHmm!");
        }

        String end = parts[2].trim();

        if (!start.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
            throw new TinkertonException("The format of your deadline should be yyyy-MM-dd HHmm!");
        }

        String eventName = parts[0].substring(6).trim();
        int prevSize = tasks.size();
        tasks.add(new Event(eventName, false, start, end));
        assert tasks.size() == prevSize + 1 : "TaskList size should increase after adding an event";

        save.save(tasks);

        return "Got it, I've added this task:\n" + tasks.get(tasks.size() - 1).toString()
                + "<SPLIT>Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as adding an Event does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
