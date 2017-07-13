package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {

    public static Connection createConnection(String dbPath) {
        String url = "jdbc:hsqldb:file:" + dbPath;
        Connection c = null;

        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            c = DriverManager.getConnection(url, "SA", "");
        } catch (Exception e) {
            processSQLError(e);
        }

        return c;
    }

    // TODO: refactor close() method into this class
    // TODO: use createConnection method once, instead of creating a bunch of new connections for each table

    public static String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
