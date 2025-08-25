package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.ToDo;
import tinkerton.storage.Save;

public class ToDoCommand extends Command {
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
