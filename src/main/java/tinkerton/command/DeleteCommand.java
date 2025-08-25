package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.Task;
import tinkerton.storage.Save;

public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String[] parts = fullCommand.split(" ");

        if (parts.length < 2) {
            throw new TinkertonException("Delete what...");
        }

        int taskId = Integer.parseInt(parts[1]) - 1;

        if (taskId < 0 || taskId > tasks.size() - 1) {
            throw new TinkertonException("Your numbering for your tasks may be abit off...");
        }

        Task removed = tasks.remove(taskId);

        ui.print("Noted, I've removed this task:");
        ui.print(removed.toString());
        ui.print("Now you have " + tasks.size() + " tasks in the list.");

        save.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
