package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.File;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() {

        try {

            // Load UCanAccess JDBC Driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // Database file
            File databaseFile = new File("FashionFlairBoutique.accdb");

            // Check whether database exists
            if (!databaseFile.exists()) {

                System.out.println("Database file not found!");
                System.out.println(
                    "Expected location: "
                    + databaseFile.getAbsolutePath()
                );

                return null;
            }

            String databasePath = databaseFile.getAbsolutePath();

            String url = "jdbc:ucanaccess://" + databasePath;

            connection = DriverManager.getConnection(url);

            System.out.println("Database Connected Successfully!");

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("UCanAccess Driver Not Found!");
            e.printStackTrace();

            return null;

        } catch (Exception e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();

            return null;
        }
    }
}