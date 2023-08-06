package org.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {
    private static final String FILE_NAME = "database.properties";

    private final Properties properties = new Properties();

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

    public String getDbUrl() {
        return properties.getProperty("dbUrl");
    }

    public void setDbUrl(String dbUrl) {
        properties.setProperty("dbUrl", dbUrl);
    }

    public String getDbName() {
        return properties.getProperty("dbName");
    }

    public void setDbName(String dbName) {
        properties.setProperty("dbName", dbName);
    }

    public void save() throws IOException {
        File file = new File(FILE_NAME);
        try (FileWriter writer = new FileWriter(file)) {
            properties.store(writer, null);
        }
    }
}
