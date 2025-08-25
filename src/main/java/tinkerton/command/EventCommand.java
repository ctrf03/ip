package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.Event;
import tinkerton.storage.Save;

public class EventCommand extends Command {
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
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
        tasks.add(new Event(eventName, false, start, end));

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
