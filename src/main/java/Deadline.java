public class Deadline extends Task {
    private Date date;
    private String time;

    public Deadline(String name, boolean isCompleted, String time) {
        super(name, isCompleted);
        this.time = time;
        this.date = new Date(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.date);
    }

    @Override
    public String toFile() {
        String completed = this.isCompleted() ? "1" : "0";
        return "D | " + completed + " | " + this.name() + " | " + this.time;
    }
}