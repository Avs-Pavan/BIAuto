package org.example;

import org.example.main.MainFrame;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
        installTools();
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

    public static void createFile(){
        try (InputStream is = Main.class.getResourceAsStream("/downloader.bat")) {
            // Check if resource is not null
            if (is == null) {
                throw new IllegalArgumentException("File not found!");
            } else {
                // Specify the path to the new file
                String newFilePath = "downloader.bat";
                // Create the new file from the InputStream
                Files.copy(is, Paths.get(newFilePath), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean download(){
        createFile();
        ProcessBuilder processBuilder = new ProcessBuilder("downloader.bat");
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
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

    public static void installTools(){
        String mongoimport = "mongoimport.exe";
        String mongo_bat = "mongo_import.bat";
        String atlas_bat = "atlas_sql_install.bat";

        if(new File(mongoimport).exists() && new File(mongo_bat).exists() && new File(atlas_bat).exists()){
            System.out.println("All tools are installed");
        }else{
            System.out.println("Installing tools");
            download();
        }
    }

}