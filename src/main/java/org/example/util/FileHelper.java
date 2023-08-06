package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.models.DataSource;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelper {


    public File getFile(String path) {
        if (!fileExists(path)) {
            createFile(path);
        }
        return new File(path);
    }

    // check if file exists in the path specified
    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    // create a new file in the path specified
    public static boolean createFile(String path) {
        File file = new File(path);
        try {
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // clear the contents of the file in the path specified
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

    // read json list from file
    public List<DataSource> readJsonListFromFile(File path) {
        List<DataSource> myList = new ArrayList<>();

        try (FileReader reader = new FileReader(path)) {
            // Create Gson object
            Gson gson = new Gson();

            // Define the type of the object you want to deserialize
            Type listType = new TypeToken<List<DataSource>>() {
            }.getType();

            // Deserialize the JSON string from file into a list
            myList = gson.fromJson(reader, listType);

            // Print the list elements
            if (myList == null) {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
        return myList;
    }

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
