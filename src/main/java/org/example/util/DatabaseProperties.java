package org.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Database properties.
 * This class is used to store the database properties
 * It is used to store the database name and the database url
 * It is also used to save the properties to a file
 * It is also used to load the properties from a file
 * It is also used to get the properties
 * It is also used to set the properties
 * It is also used to get the database name
 * It is also used to set the database name
 */
public class DatabaseProperties {
    private static final String FILE_NAME = "database.properties";

    private final Properties properties = new Properties();

    /**
     * Instantiates a new Database properties.
     * This constructor loads the properties from the file
     */
    public DatabaseProperties() {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                properties.load(new FileInputStream(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets db url.
     * This method returns the database url
     *
     * @return the db url
     */
    public String getDbUrl() {
        return properties.getProperty("dbUrl");
    }

    /**
     * Sets db url.
     * This method sets the database url
     *
     * @param dbUrl the db url
     */
    public void setDbUrl(String dbUrl) {
        properties.setProperty("dbUrl", dbUrl);
    }

    /**
     * Gets db name.
     * This method returns the database name
     *
     * @return the db name
     */
    public String getDbName() {
        return properties.getProperty("dbName");
    }

    /**
     * Sets db name.
     * This method sets the database name
     *
     * @param dbName the db name
     */
    public void setDbName(String dbName) {
        properties.setProperty("dbName", dbName);
    }

    /**
     * Save.
     * This method saves the properties to the file
     *
     * @throws IOException the io exception
     */
    public void save() throws IOException {
        File file = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(file)) {
            properties.store(writer, null);
        }
    }
}
