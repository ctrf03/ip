import java.util.Scanner;

public class Tinkerton {

    public static void intro() {
        System.out.println("Hello! I'm Tinkerton");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;
        boolean loop = true;

        while (loop) {
            String input = sc.nextLine();
            String[] inputList = input.split(" ");

            if (inputList[0].equals("bye")) {
                loop = false;
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputList[0].equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                continue;
            } else if (inputList[0].equals("mark")) {
                int taskId = Integer.parseInt(inputList[1]) - 1;
                tasks[taskId].complete();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskId]);
            } else if (inputList[0].equals("unmark")) {
                int taskId = Integer.parseInt(inputList[1]) - 1;
                tasks[taskId].uncomplete();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskId]);
            } else {
                tasks[count] = new Task(inputList[0]);
                count++;
                System.out.println("added " + input);
                continue;
            }
        }
        sc.close();
        return;
    }
}
