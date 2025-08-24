import java.time.LocalDateTime;

public class Deadline extends Task {
    private Date date;
    private String time;

    public Deadline(String name, boolean isCompleted, String time) throws TinkertonException {
        super(name, isCompleted);
        this.time = time;
        this.date = new Date(time);

        if (date.date().isBefore(LocalDateTime.now())) {
            throw new TinkertonException("Deadline that is already overdue? A bit too late to add that...");
        }
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