package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtils {
    private static Connection currentConnection;

    public static Connection createConnection(String dbPath) {
        String url = "jdbc:hsqldb:file:" + dbPath;

        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            currentConnection = DriverManager.getConnection(url, "SA", "");
        } catch (Exception e) {
            processSQLError(e);
        }

        return currentConnection;
    }

    // TODO: use createConnection method once, instead of creating a bunch of new connections for each table

    public static void closeConnection() {
        try {    // commit all changes to the database
            String cmdString = "shutdown compact";
            Statement st = currentConnection.createStatement();
            st.executeQuery(cmdString);
            currentConnection.close();
            currentConnection = null;
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public static String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
