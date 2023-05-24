package util;

import java.io.*;

public class DBController {
    public static final String DB_PATH = "db.txt";

    public static void createDB() {
        try {
            File db = new File(DB_PATH);
            if (db.createNewFile()) {
                System.out.println("Database created");
            } else {
                System.out.println("Database already exists");
            }
        } catch (IOException e) {
            System.err.println("Failed to create database: " + e.getMessage());
        }
    }

    public static void writeData(String data) {
        try (FileWriter writer = new FileWriter(DB_PATH, true)) {
            writer.write(data + "\n");
            System.out.println("Data written to database");
        } catch (IOException e) {
            System.err.println("Failed to write data to database: " + e.getMessage());
        }
    }

    public static String readData() {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            System.out.println("Data read from database");
        } catch (IOException e) {
            System.err.println("Failed to read data from database: " + e.getMessage());
        }
        return stringBuilder.toString();
    }

    public static void clearDataFile() {
        try (FileWriter writer = new FileWriter(DB_PATH)) {
            writer.write("");
            System.out.println("Database cleared");
        } catch (IOException e) {
            System.err.println("Failed to clear database: " + e.getMessage());
        }
    }
}
