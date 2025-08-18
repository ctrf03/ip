public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void complete(Task task) {
        this.completed = true;
    }

    @Override
    public String toString() {
        String marker = this.completed ? "[X]" : "[ ]";
        return marker + name;
    }
}
