package org.uts.controller;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class CLIUniApp {
    Scanner in;

    public CLIUniApp() {
        in = new Scanner(System.in);
    }

    private void displayAdminSystem() {
        AdminController adminController = new AdminController();
        adminController.startMenu();
    }

    private void displayStudentSystem() {
        StudentController studentController = new StudentController();
        studentController.startMenu();
    }

    public void startMenu() {
        while (true) {
            System.out.print(colorize("University System: (A)dmin, (S)tudent, or X : ", Attribute.BRIGHT_CYAN_TEXT()));
            final Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("A")) {
                displayAdminSystem();
            } else if (input.equals("S")) {
                displayStudentSystem();
            } else if (input.equals("X")) {
                System.out.println(colorize("Thank You", Attribute.BRIGHT_YELLOW_TEXT()));
                break;
            } else {
                System.out.println("Menu options");
                System.out.println("A = Admin System");
                System.out.println("S = Student System");
                System.out.println("X = Exit");
            }
        }
    }


}
