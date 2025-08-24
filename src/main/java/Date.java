import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");
    private String input;
    private String output;
    private LocalDateTime afterFormat;

    public Date(String input) {
        this.input = input;

        try {
            this.afterFormat = LocalDateTime.parse(input, inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Are you sure this is a valid date?");
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
