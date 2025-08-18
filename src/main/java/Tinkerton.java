import java.util.Scanner;

public class Tinkerton {

    public static void intro() {
        System.out.println("Hello! I'm Tinkerton");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int count = 0;
        boolean loop = true;

        while (loop) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                loop = false;
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                continue;
            } else {
                tasks[count] = input;
                count++;
                System.out.println("added " + input);
                continue;
            }
        }
        sc.close();
        return;
    }
}
