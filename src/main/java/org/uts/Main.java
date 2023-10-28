package org.uts;

import org.uts.controller.DatabaseController;
import org.uts.controller.CLIUniApp;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseController.initialize();
        CLIUniApp cliUniApp = new CLIUniApp();
        cliUniApp.startMenu();
    }
}