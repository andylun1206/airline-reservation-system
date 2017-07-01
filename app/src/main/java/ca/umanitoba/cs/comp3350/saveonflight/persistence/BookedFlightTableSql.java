package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * Created by zhengyugu on 2017-06-29.
 */

public class BookedFlightTableSql implements BookedFlightAccess {
    private Statement st1;
    private Connection c1;
    private ResultSet rs1, rs2, rs8;
    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";
    private List<BookedFlight> bookedFlights = null;

    public BookedFlightTableSql() {
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
    }

    public List<BookedFlight> gets() {
        BookedFlight bookedFlight;
        int id;
        String flightId, departureTime;
        result = null;
        try {
            cmdString = "SELECT * FROM BOOKEDFLIGHT";
            rs1 = st1.executeQuery(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs1.next()) {
                FlightTableSql flightTableSql = new FlightTableSql();
                flightTableSql.initialize(Main.getDBPathName());
                flightTableSql.getFlights();
                TravellerTableSql travellerTableSql = new TravellerTableSql();
                travellerTableSql.initialize(Main.getDBPathName());
                travellerTableSql.getTravellers();
                id = rs1.getInt("ID");
                flightId = rs1.getString("FLIGHTID");
                departureTime = rs1.getString("DEPARTURETIME");
                bookedFlight = new BookedFlight(travellerTableSql.findTraveller(id), flightTableSql.findFlight(flightId, departureTime));
                bookedFlights.add(bookedFlight);
            }
            rs1.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return bookedFlights;
    }

    public boolean add(BookedFlight bookedFlight) {
        String values;
        Boolean results = false;
        result = null;
        try {
            values = bookedFlight.getTraveller().getTravellerID()
                    + ",'" + bookedFlight.getFlight().getFlightCode() + "','" + bookedFlight.getFlight().getDepartureTime() + "'";
            cmdString = "Insert into Users " + " Values(" + values + ")";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
            results = true;
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return results;
    }

    public ArrayList<BookedFlight> searchByTraveller(Traveller t) {
        ArrayList<BookedFlight> results = new ArrayList<BookedFlight>();
        BookedFlight bookedFlight = null;
        String values, flightId, departureTime;
        int id;
        result = null;
        try {
            cmdString = "Select * from BOOKEDFLIGHT WHERE ID =" + t.getTravellerID();
            rs2 = st1.executeQuery(cmdString);

        } catch (Exception e) {
            result = processSQLError(e);
        }
        try {
            while (rs2.next()) {
                FlightTableSql flightTableSql = new FlightTableSql();
                flightTableSql.initialize(Main.getDBPathName());
                flightTableSql.getFlights();
                TravellerTableSql travellerTableSql = new TravellerTableSql();
                travellerTableSql.initialize(Main.getDBPathName());
                travellerTableSql.getTravellers();
                id = rs2.getInt("ID");
                flightId = rs2.getString("FLIGHTID");
                departureTime = rs2.getString("DEPARTURETIME");
                bookedFlight = new BookedFlight(travellerTableSql.findTraveller(id), flightTableSql.findFlight(flightId, departureTime));
                results.add(bookedFlight);
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return results;
    }

    public ArrayList<BookedFlight> searchByFlight(Flight f) {
        return null;
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
        System.out.println("Closed database ");
    }
}
