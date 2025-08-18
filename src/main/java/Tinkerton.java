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
        int count = 0;
        boolean loop = true;

        while (loop) {
            String input = sc.nextLine();
            String[] inputList = input.split(" ");

            try {
                if (inputList[0].equals("bye")) {
                    loop = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (inputList[0].equals("list")) {
                    if (count == 0) {
                        throw new TinkertonException("I feel like your list is empty so there is no list to show...");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    continue;
                } else if (inputList[0].equals("mark")) {
                    int taskId = Integer.parseInt(inputList[1]) - 1;
                    if (taskId < 0 || taskId > count - 1) {
                        throw new TinkertonException("Your numbering for your tasks may be abit off...");
                    }
                    tasks.get(taskId).complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskId));
                } else if (inputList[0].equals("unmark")) {
                    int taskId = Integer.parseInt(inputList[1]) - 1;
                    if (taskId < 0 || taskId > count - 1) {
                        throw new TinkertonException("Your numbering for your tasks may be abit off...");
                    }
                    tasks.get(taskId).uncomplete();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskId));
                } else if (inputList[0].equals("todo")) {
                    if (inputList.length < 2) {
                        throw new TinkertonException("You seem to be missing some information...");
                    }
                    String name = input.substring(5).trim();
                    tasks.add(new ToDo(name));
                    count++;
                    System.out.println("Got it, I've added this task:");
                    System.out.println(tasks.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                } else if (inputList[0].equals("deadline")) {
                    if (!input.contains("/by")) {
                        throw new TinkertonException("Your deadline task has no deadline...");
                    }
                    String[] split = input.split("/by");
                    if (split.length < 2) {
                        throw new TinkertonException("You seem to be missing some information...");
                    }
                    String time = split[1].trim();
                    String name = split[0].substring(9).trim();
                    tasks.add(new Deadline(name, time));
                    count++;
                    System.out.println("Got it, I've added this task:");
                    System.out.println(tasks.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                } else if (inputList[0].equals("event")) {
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new TinkertonException("Your event has no start and end...");
                    }
                    String[] split = input.split("/from|/to");
                    if (split.length < 3) {
                        throw new TinkertonException("You seem to be missing some information...");
                    }
                    String start = split[1].trim();
                    String end = split[2].trim();
                    String name = split[0].substring(6).trim();
                    tasks.add(new Event(name, start, end));
                    count++;
                    System.out.println("Got it, I've added this task:");
                    System.out.println(tasks.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                } else {
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
