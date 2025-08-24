import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tinkerton {
    private Save save;
    private TaskList tasks;

    public static void intro() {
        System.out.println("Hello! I'm Tinkerton");
        System.out.println("What can I do for you?");
    }

    public Tinkerton(String filePath) {
        this.save = new Save("data/Tinkerton.txt");
        this.tasks = save.load();
    }

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);
        // Save save = new Save("data/Tinkerton.txt");
        // ArrayList<Task> tasks = save.load();
        boolean loop = true;

        while (loop) {
            String input = sc.nextLine();
            String[] inputList = input.split(" ");

            CommandType command = CommandType.command(inputList[0]);

            try {
                switch (command) {
                    case BYE:
                        loop = false;

                        System.out.println("Bye. Hope to see you again soon!");

                        break;

                    case LIST:
                        if (tasks.size() == 0) {
                            throw new TinkertonException(
                                    "I feel like your list is empty so there is no list to show...");
                        }

                        System.out.println("Here are the tasks in your list:");

                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }

                        break;

                    case SHOW:
                        if (tasks.size() == 0) {
                            throw new TinkertonException(
                                    "I feel like your list is empty so there is no list to show...");
                        } else if (!input.contains("/on")) {
                            throw new TinkertonException("Show tasks on what date..");
                        }

                        String[] showSplit = input.split("/on");

                        if (showSplit.length < 2) {
                            throw new TinkertonException("You seem to be missing some information...");
                        }

                        String date = showSplit[1].trim();

                        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                            throw new TinkertonException("The format of your deadline should be yyyy-MM-dd!");
                        }

                        Date check = new Date(date + " 0000");

                        List<Task> filtered = tasks.stream()
                                .filter(t -> t.onDate(check))
                                .toList();

                        if (filtered.size() == 0) {
                            throw new TinkertonException(
                                    "No tasks on that day yay!");
                        }

                        System.out.println("Here are the tasks on that day");

                        for (int i = 0; i < filtered.size(); i++) {
                            System.out.println((i + 1) + ". " + filtered.get(i));
                        }

                        break;

                    case MARK:
                        if (inputList.length < 2) {
                            throw new TinkertonException("Mark what...");
                        }

                        int markId = Integer.parseInt(inputList[1]) - 1;

                        if (markId < 0 || markId > tasks.size() - 1) {
                            throw new TinkertonException("Your numbering for your tasks may be abit off...");
                        }

                        tasks.get(markId).complete();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(markId));

                        save.save(tasks);
                        break;

                    case UNMARK:
                        if (inputList.length < 2) {
                            throw new TinkertonException("Unmark what...");
                        }

                        int unmarkId = Integer.parseInt(inputList[1]) - 1;

                        if (unmarkId < 0 || unmarkId > tasks.size() - 1) {
                            throw new TinkertonException("Your numbering for your tasks may be abit off...");
                        }

                        tasks.get(unmarkId).uncomplete();

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks.get(unmarkId));

                        save.save(tasks);
                        break;

                    case TODO:
                        if (inputList.length < 2) {
                            throw new TinkertonException("You seem to be missing some information...");
                        }

                        String toDOName = input.substring(5).trim();
                        tasks.add(new ToDo(toDOName, false));

                        System.out.println("Got it, I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                        save.save(tasks);
                        break;

                    case DEADLINE:
                        if (!input.contains("/by")) {
                            throw new TinkertonException("Your deadline task has no deadline...");
                        }

                        String[] deadlineSplit = input.split("/by");

                        if (deadlineSplit.length < 2) {
                            throw new TinkertonException("You seem to be missing some information...");
                        }

                        String time = deadlineSplit[1].trim();

                        if (!time.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
                            throw new TinkertonException("The format of your deadline should be yyyy-MM-dd HHmm!");
                        }

                        String deadlineName = deadlineSplit[0].substring(9).trim();
                        tasks.add(new Deadline(deadlineName, false, time));

                        System.out.println("Got it, I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                        save.save(tasks);
                        break;

                    case EVENT:
                        if (!input.contains("/from") || !input.contains("/to")) {
                            throw new TinkertonException("Your event has no start and end...");
                        }

                        String[] eventSplit = input.split("/from|/to");

                        if (eventSplit.length < 3) {
                            throw new TinkertonException("You seem to be missing some information...");
                        }

                        String start = eventSplit[1].trim();

                        if (!start.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
                            throw new TinkertonException("The format of your deadline should be yyyy-MM-dd HHmm!");
                        }

                        String end = eventSplit[2].trim();

                        if (!start.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
                            throw new TinkertonException("The format of your deadline should be yyyy-MM-dd HHmm!");
                        }

                        String eventName = eventSplit[0].substring(6).trim();
                        tasks.add(new Event(eventName, false, start, end));

                        System.out.println("Got it, I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                        save.save(tasks);
                        break;

                    case DELETE:
                        if (inputList.length < 2) {
                            throw new TinkertonException("Delete what...");
                        }

                        int taskId = Integer.parseInt(inputList[1]) - 1;

                        if (taskId < 0 || taskId > tasks.size() - 1) {
                            throw new TinkertonException("Your numbering for your tasks may be abit off...");
                        }

                        Task removed = tasks.remove(taskId);

                        System.out.println("Noted, I've removed this task:");
                        System.out.println(removed);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                        save.save(tasks);
                        break;

                    case UNKNOWN:
                    default:
                        throw new TinkertonException("Erm... What are you saying?");
                }
            } catch (TinkertonException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        return;
    }
}
