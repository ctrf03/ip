public abstract class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
    }

    public void uncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String marker = this.completed ? "[X]" : "[ ]";
        return marker + " " + name;
    }
}
