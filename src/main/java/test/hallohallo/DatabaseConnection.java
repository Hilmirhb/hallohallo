package test.hallohallo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = YourLocation.Command();

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found. Make sure it's included in the classpath.");
            e.printStackTrace();
            return;
        }

        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            dbConnection.createTables();
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating tables:");
            e.printStackTrace();
        }
    }
    public void createTables() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String createTourTable = "CREATE TABLE IF NOT EXISTS Tour (" +
                    "id INTEGER PRIMARY KEY," +
                    "title TEXT," +
                    "description TEXT," +
                    "price REAL," +
                    "host TEXT," +
                    "type TEXT," +
                    "country TEXT," +
                    "address TEXT," +
                    "post_code TEXT," +
                    "city TEXT," +
                    "date TEXT," +
                    "time TEXT," +
                    "available_slots INTEGER)";

            String createReviewTable = "CREATE TABLE IF NOT EXISTS Review (" +
                    "id INTEGER PRIMARY KEY," +
                    "rating INTEGER," +
                    "name TEXT," +
                    "comment TEXT," +
                    "tour_id INTEGER," +
                    "FOREIGN KEY (tour_id) REFERENCES Tour(id))";

            String createBookingTable = "CREATE TABLE IF NOT EXISTS Booking (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT," +
                    "phone TEXT," +
                    "number_of_people INTEGER," +
                    "tour_id INTEGER," +
                    "FOREIGN KEY(tour_id) REFERENCES Tour(id))";
            String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin (" +
                    "id INTEGER PRIMARY KEY," +
                    "username TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL)";
            stmt.execute(createAdminTable);
            stmt.execute(createTourTable);
            stmt.execute(createReviewTable);
            stmt.execute(createBookingTable);
        }
    }
}

