import java.util.ArrayList;
import java.util.Scanner;

public class Tinkerton {

    public static void intro() {
        System.out.println("Hello! I'm Tinkerton");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
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
                        break;

                    case TODO:
                        if (inputList.length < 2) {
                            throw new TinkertonException("You seem to be missing some information...");
                        }
                        String toDOName = input.substring(5).trim();
                        tasks.add(new ToDo(toDOName));
                        System.out.println("Got it, I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
                        String deadlineName = deadlineSplit[0].substring(9).trim();
                        tasks.add(new Deadline(deadlineName, time));
                        System.out.println("Got it, I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
                        String end = eventSplit[2].trim();
                        String eventName = eventSplit[0].substring(6).trim();
                        tasks.add(new Event(eventName, start, end));
                        System.out.println("Got it, I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
