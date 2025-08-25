package tinkerton.task;

import tinkerton.util.Date;
import tinkerton.core.TinkertonException;
import java.time.LocalDateTime;

public class Event extends Task {
    private String start;
    private String end;
    private Date startDate;
    private Date endDate;

    public Event(String name, boolean isCompleted, String start, String end) throws TinkertonException {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
        this.startDate = new Date(start);
        this.endDate = new Date(end);

        if (endDate.date().isBefore(startDate.date())) {
            throw new TinkertonException("Event that ends before it starts? Doesn't make too much sense right...");
        } else if (startDate.date().isBefore(LocalDateTime.now())) {
            throw new TinkertonException("Event that already started in the past? A bit too late to add that...");
        }
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

    @Override
    public boolean onDate(Date date) {
        return date.date().toLocalDate().isEqual(this.startDate.date().toLocalDate());
    }
}
