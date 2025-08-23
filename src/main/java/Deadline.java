public class Deadline extends Task {
    private String time;

    public Deadline(String name, boolean isCompleted, String time) {
        super(name, isCompleted);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", time);
    }

    @Override
    public String toFile() {
        String completed = this.isCompleted() ? "1" : "0";
        return "D | " + completed + " | " + this.name() + " | " + this.time;
    }
}