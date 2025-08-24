import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save {
    private final String filePath;

    public Save(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                return tasks;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] parts = nextLine.split("\\|");
                String type = parts[0].trim();
                boolean isCompleted = parts[1].trim().equals("1");

                switch (type) {
                    case "T":
                        tasks.add(new ToDo(parts[2].trim(), isCompleted));
                        break;

                    case "D":
                        tasks.add(new Deadline(parts[2].trim(), isCompleted, parts[3].trim()));
                        break;

                    case "E":
                        tasks.add(new Event(parts[2].trim(), isCompleted, parts[3].trim(), parts[4].trim()));
                        break;

                    default:
                        throw new TinkertonException("Task type unknown");
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("File Reading Error: " + e.getMessage());
        } catch (TinkertonException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (Task curr : tasks) {
                writer.write(curr.toFile());
                writer.write(System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("File Writing Error: " + e.getMessage());
        }
    }
}
