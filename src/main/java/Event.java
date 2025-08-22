public class Event extends Task {
    private String start;
    private String end;

    public Event(String name, boolean isCompleted, String start, String end) {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", start, end);
    }

    @Override
    public String toFile() {
        String completed = this.isCompleted() ? "1" : "0";
        return "T | " + completed + " | " + this.name() + " | " + start + " " + end;
    }
}
