public class Deadline extends Task {
    private String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", time);
    }
}