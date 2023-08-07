package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.models.DataSource;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The type File helper.
 * This class contains methods to help with file operations
 * It is used to read and write to json files
 * It is used to check if a file exists
 * It is used to create a file
 * It is used to clear a file
 * It is used to get a file
 * It is used to read a json list from a file
 * It is used to write a json list to a file
 */
public class FileHelper {


    /**
     * File exists boolean.
     * This method checks if a file exists
     *
     * @param path the path
     * @return the boolean
     */
    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * Create file boolean.
     * This method creates a file
     *
     * @param path the path
     * @return the boolean
     */
    public static boolean createFile(String path) {
        File file = new File(path);
        try {
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Clear file boolean.
     * This method clears a file
     *
     * @param path the path
     * @return the boolean
     */
    public static boolean clearFile(String path) {
        File file = new File(path);
        try {
            file.delete();
            return createFile(path);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets file.
     * This method gets a file
     *
     * @param path the path
     * @return the file
     */
    public File getFile(String path) {
        if (!fileExists(path)) {
            createFile(path);
        }
        return new File(path);
    }

    /**
     * Read json list from file list.
     * This method reads a json list from a file
     *
     * @param path the path
     * @return the list
     */
    public List<DataSource> readJsonListFromFile(File path) {
        List<DataSource> myList = new ArrayList<>();
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<DataSource>>() {
            }.getType();
            myList = gson.fromJson(reader, listType);
            if (myList == null) {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return myList;
    }

    /**
     * Write json list to file.
     * This method writes a json list to a file
     *
     * @param list the list
     * @param path the path
     */
    public void writeJsonListToFile(List<DataSource> list, File path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
