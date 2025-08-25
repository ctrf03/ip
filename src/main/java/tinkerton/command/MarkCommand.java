package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

public class MarkCommand extends Command {
    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
