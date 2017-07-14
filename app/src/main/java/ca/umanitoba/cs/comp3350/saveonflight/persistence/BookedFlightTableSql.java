package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

import static ca.umanitoba.cs.comp3350.saveonflight.persistence.DatabaseHandler.checkWarning;
import static ca.umanitoba.cs.comp3350.saveonflight.persistence.DatabaseHandler.processSQLError;

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

    public BookedFlightTableSql() {
    }

    public void initialize() {
        try {
            c1 = DatabaseHandler.getConnection();
            st1 = c1.createStatement();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public List<BookedFlight> getAll() {
        List<BookedFlight> bfs = new ArrayList<>();
        BookedFlight bookedFlight;
        int id;
        String flightId, departureTime, seatNumber;
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
                flightTableSql.initialize();
                flightTableSql.getFlights();
                TravellerTableSql travellerTableSql = new TravellerTableSql();
                travellerTableSql.initialize();
                travellerTableSql.getTravellers();
                id = rs1.getInt("ID");
                flightId = rs1.getString("FLIGHTID");
                departureTime = rs1.getString("DEPARTURETIME");
                seatNumber = rs1.getString("SEATNUMBER");
                bookedFlight = new BookedFlight(travellerTableSql.findTraveller(id), flightTableSql.findFlight(flightId, departureTime), seatNumber);
                bfs.add(bookedFlight);
            }
            rs1.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return bfs;
    }

    public boolean add(BookedFlight bookedFlight) {
        String values;
        boolean added = false;
        result = null;

        if (bookedFlight != null && bookedFlight.getFlight() != null && bookedFlight.getTraveller() != null) {
            try {
                values = bookedFlight.getTraveller().getTravellerID()
                        + ",'" + bookedFlight.getFlight().getFlightCode() + "', '"
                        + bookedFlight.getFlight().getDepartureTimeString() + "', '"
                        + bookedFlight.getSeatNumber() + "'";
                cmdString = "Insert into BOOKEDFLIGHT " + " Values(" + values + ")";
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

    public boolean remove(BookedFlight bf) {
        boolean removed = false;
        String where;

        if (bf != null && bf.getFlight() != null && bf.getTraveller() != null) {
            try {
                where = "where ID=" + bf.getTraveller().getTravellerID()
                        + " and FLIGHTID='" + bf.getFlight().getFlightCode()
                        + "' and DEPARTURETIME='" + bf.getFlight().getDepartureTimeString() + "'";

                cmdString = "Delete from BOOKEDFLIGHT " + where;
                updateCount = st1.executeUpdate(cmdString);
                if (updateCount > 0) {
                    removed = true;
                }
            } catch (Exception e) {
                processSQLError(e);
            }
        }

        return removed;
    }

    public ArrayList<BookedFlight> searchByTraveller(Traveller t) {
        ArrayList<BookedFlight> results = new ArrayList<BookedFlight>();
        BookedFlight bookedFlight;
        String flightId, departureTime, seatNumber;
        result = null;
        try {
            cmdString = "Select * from BOOKEDFLIGHT WHERE ID =" + t.getTravellerID();
            rs2 = st1.executeQuery(cmdString);

        } catch (Exception e) {
            result = processSQLError(e);
        }
        try {
            // TODO: maybe join tables and perform a single SQL query instead?
            TravellerTableSql travellerTableSql = new TravellerTableSql();
            travellerTableSql.initialize();
            Traveller traveller = travellerTableSql.findTraveller(t.getTravellerID());

            FlightTableSql flightTableSql = new FlightTableSql();
            flightTableSql.initialize();
            while (rs2.next()) {
                flightId = rs2.getString("FLIGHTID");
                departureTime = rs2.getString("DEPARTURETIME");
                seatNumber = rs2.getString("SEATNUMBER");
                bookedFlight = new BookedFlight(traveller, flightTableSql.findFlight(flightId, departureTime), seatNumber);
                results.add(bookedFlight);
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return results;
    }
}
