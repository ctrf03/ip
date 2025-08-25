package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

public class ListCommand extends Command {
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        if (tasks.size() == 0) {
            throw new TinkertonException(
                    "I feel like your list is empty so there is no list to show...");
        }

        ui.print("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            ui.print((i + 1) + ". " + tasks.get(i));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
