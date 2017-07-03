package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

/**
 * Created by longyu on 2017-06-27.
 */

public class AirportTableSql implements AirportAccess {
    private Statement st1;
    private Connection c1;
    private ResultSet rs1, rs2;
    private String cmdString;

    private List<Airport> airports = null;

    public AirportTableSql() {
    }

    public void initialize(String dbPath) {
        airports = new ArrayList<Airport>();
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

    public List<Airport> getAirports() {
        Airport airport;
        String result = null;

        try {
            cmdString = "Select * from Airport";
            rs1 = st1.executeQuery(cmdString);

        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs1.next()) {
                airport = createAirportFromResultSet(rs1);
                airports.add(airport);
            }
            rs1.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return airports;
    }

    public Airport findAirport(String city) {
        Airport airport = null;

        try {
            cmdString = "Select * from Airport where AIRPORTID='" + city + "'";
            rs1 = st1.executeQuery(cmdString);

            if (rs1 != null && rs1.next()) {
                airport = createAirportFromResultSet(rs1);
            }
        } catch (Exception e) {
            processSQLError(e);
        }

        return airport;
    }

    private Airport createAirportFromResultSet(ResultSet rs) throws SQLException, ParseException {
        return new Airport(rs.getString("AIRPORTID"));
    }

    public String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

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
