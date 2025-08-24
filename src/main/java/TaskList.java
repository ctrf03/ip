import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    public TaskList filter(Predicate<Task> predicate) {
        return new TaskList(this.tasks.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}
