package org.example;

import org.example.main.MainFrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (checkIsAtlasDriverInstalled()) {
            System.out.println("Atlas SQL ODBC Driver is installed");
            startUI();
        } else {
            if (isAdmin()) {
                System.out.println("Installing Atlas SQL ODBC Driver");
                if (installAtlasDriver()) {
                    System.out.println("Installation complete");
                    startUI();
                } else
                    System.out.println("Installation failed");
            } else {
                System.out.println("Please run this program as Administrator");
            }
        }
    }

    public static void startUI() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    public static boolean checkIsAtlasDriverInstalled() {
        Path path = Paths.get("C:\\Program Files\\MongoDB\\Atlas SQL ODBC Driver\\bin");
        return Files.exists(path);
    }

    public static boolean isAdmin() {
        String command = "net session";
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
            Process p = builder.start();
            p.waitFor();  // Wait for the command to finish
            int exitValue = p.exitValue();

            return exitValue == 0;  // If the command was successful, the exit value will be 0
        } catch (IOException | InterruptedException e) {
            return false;  // An exception occurred, so the app does not have Administrator privileges
        }
    }


    public static boolean installAtlasDriver() {
        ProcessBuilder processBuilder = new ProcessBuilder("atlas_sql_install.bat");
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}