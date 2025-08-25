package tinkerton.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import tinkerton.core.TinkertonException;

public class Date {
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
    private String output;
    private LocalDateTime afterFormat;

    public Date(String input) throws TinkertonException {
        try {
            this.afterFormat = LocalDateTime.parse(input, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new TinkertonException("Are you sure this is a valid date?");
        }

        this.output = afterFormat.format(outputFormatter);
    }

    @Override
    public String toString() {
        return output;
    }

    public LocalDateTime date() {
        return afterFormat;
    }
}
