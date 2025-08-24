public class Parser {
    public static CommandType parse(String fullCommand) {
        String[] parts = fullCommand.split(" ");
        return CommandType.command(parts[0]);
    }
}
