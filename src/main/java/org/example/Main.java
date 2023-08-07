package org.example;

import org.example.main.MainFrame;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * The type Main.
 * This class is the entry point of the application
 * It checks if the driver is installed and if not it installs it.
 * It also checks if the user is admin and if not it asks the user to run the program as admin
 * It also checks if the tools are installed and if not it installs them
 * It also starts the UI
 * It also downloads the driver and the tools
 * It also creates the bat files
 * It also installs the driver
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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

    /**
     * Start ui.
     */
    public static void startUI() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    /**
     * Check is atlas sql driver installed boolean.
     *
     * @return the boolean
     */
    public static boolean checkIsAtlasDriverInstalled() {
        Path path = Paths.get("C:\\Program Files\\MongoDB\\Atlas SQL ODBC Driver\\bin");
        return Files.exists(path);
    }

    /**
     * Is admin boolean.
     * This method checks if the user is running the application as admin
     *
     * @return the boolean
     */
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

    /**
     * Create file.
     * This method creates the bat file that downloads the driver and the tools
     * It also creates the bat file that installs the driver
     * It also creates the bat file that imports the data
     * It creates a new file from the resources InputStream and copies it to the specified path
     */
    public static void createFile() {
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


    /**
     * Download boolean.
     * This method downloads the driver and the tools
     * These files are downloaded from the resources GitHub repository
     * The bat file uses curl to download the files
     *
     * @return the boolean
     */
    public static boolean download() {
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

    /**
     * Install atlas driver boolean.
     * This method installs the driver
     * It uses the bat file that installs the driver
     * The bat file uses msiexec to install the driver
     * The bat file also adds the driver to the system path
     * The bat file also adds the driver to the registry
     *
     * @return the boolean
     */
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

    /**
     * Install tools.
     * This method checks if the tools are installed and if not it installs them
     * This method uses the bat file that download the tools
     * This method uses curl to download the tools
     */
    public static void installTools() {
        String mongoimport = "mongoimport.exe";
        String mongo_bat = "mongo_import.bat";
        String atlas_bat = "atlas_sql_install.bat";

        if (new File(mongoimport).exists() && new File(mongo_bat).exists() && new File(atlas_bat).exists()) {
            System.out.println("All tools are installed");
        } else {
            System.out.println("Installing tools");
            download();
        }
    }

}