public class Event extends Task {
    private String start;
    private String end;
    private Date startDate;
    private Date endDate;

    public Event(String name, boolean isCompleted, String start, String end) {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
        this.startDate = new Date(start);
        this.endDate = new Date(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.startDate, this.endDate);
    }

    @Override
    public String toFile() {
        String completed = this.isCompleted() ? "1" : "0";
        return "E | " + completed + " | " + this.name() + " | " + this.start + " | " + this.end;
    }
}
