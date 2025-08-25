package tinkerton.core;

import tinkerton.util.Ui;
import tinkerton.util.Parser;
import tinkerton.command.Command;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

public class Tinkerton {
    private Save save;
    private TaskList tasks;
    private Ui ui;

    public Tinkerton(String filePath) {
        this.save = new Save("data/Tinkerton.txt");
        this.tasks = save.load();
        this.ui = new Ui();
    }

    public void run() {
        this.ui.intro();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, save);
                isExit = command.isExit();
            } catch (TinkertonException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Tinkerton("data/tasks.txt").run();
    }
}
