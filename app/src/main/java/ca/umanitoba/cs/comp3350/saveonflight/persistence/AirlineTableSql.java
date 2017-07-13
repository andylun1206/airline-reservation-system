package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;

import static ca.umanitoba.cs.comp3350.saveonflight.persistence.DatabaseUtils.processSQLError;

/**
 * Created by longyu on 2017-06-27.
 */

public class AirlineTableSql implements AirlineAccess {
    private Statement st1;
    private Connection c1;
    private ResultSet rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public AirlineTableSql() {
    }

    public void initialize(String dbPath) {
        String url = "jdbc:hsqldb:file:" + dbPath;
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
        } catch (Exception e) {
            processSQLError(e);
        }
        //System.out.println("Opened database ");
    }

    public ArrayList<Airline> getAirlines() {
        ArrayList<Airline> airlines = new ArrayList<>();
        result = null;

        try {
            cmdString = "Select * from AIRLINE";
            rs1 = st1.executeQuery(cmdString);

        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs1.next()) {
                 airlines.add(createAirlineFromResultSet(rs1));
            }
            rs1.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return airlines;
    }

    public Airline findAirline(String airlineName) {
        Airline result = null;

        try {
            cmdString = "Select * from AIRLINE where AIRLINENAME = '" + airlineName + "'";
            rs1 = st1.executeQuery(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            if (rs1.next()) {
                result = createAirlineFromResultSet(rs1);
            }
            rs1.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return result;
    }

    private Airline createAirlineFromResultSet(ResultSet rs) throws SQLException, ParseException {
        Airline airline;
        String name;
        name = rs.getString("AIRLINENAME");
        airline = new Airline(name);
        return airline;
    }

    public boolean add(Airline airline) {
        boolean added = false;

        String values;
        if (airline != null && !airline.getName().isEmpty()) {
            try {
                values = "'" + airline.getName() + "'";
                cmdString = "Insert into Airline " + " Values(" + values + ")";
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
                if (updateCount > 0) {
                    added = true;
                }
            } catch (Exception e) {
                result = processSQLError(e);
            }
        }

        return added;
    }


    public String checkWarning(Statement st, int updateCount) {
        String result;

        result = null;
        try {
            SQLWarning warning = st.getWarnings();
            if (warning != null) {
                result = warning.getMessage();
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }
        if (updateCount != 1) {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public void close() {
        try {    // commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e) {
            processSQLError(e);
        }
        //System.out.println("Closed database ");
    }
}
