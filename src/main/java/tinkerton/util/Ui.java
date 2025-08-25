package tinkerton.util;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void intro() {
        System.out.println("Hello! I'm Tinkerton");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void showError(String string) {
        System.out.println(string);
    }

    public void close() {
        scanner.close();
    }
}
