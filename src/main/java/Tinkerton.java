import java.util.Scanner;

public class Tinkerton {

    public static void intro() {
        System.out.println("Hello! I'm Tinkerton");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            String echo = sc.nextLine();

            if (echo.equals("bye")) {
                loop = false;
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(echo);
        }
        sc.close();
        return;
    }
}
