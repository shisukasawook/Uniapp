package org.uts;

import org.uts.controller.CLIUniApp;
import org.uts.controller.DatabaseController;

public class Main {
    public static void main(String[] args) {
        DatabaseController.initialize();
        CLIUniApp cliUniApp = new CLIUniApp();
        cliUniApp.startMenu();
    }
}