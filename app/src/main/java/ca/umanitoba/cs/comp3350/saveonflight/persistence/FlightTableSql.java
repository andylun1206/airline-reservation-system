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
import java.util.List;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

/**
 * Created by zhengyugu on 2017-06-18.
 */

public class FlightTableSql implements FlightAccess {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8;

    private String cmdString;
    private int updateCount;
    private String result;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);

    public FlightTableSql() {
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

    public ArrayList<Flight> getFlights() {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        Flight flight;
        result = null;

        ResultSet rs = null;
        try {
            cmdString = "Select * from FLIGHT";
            rs = st1.executeQuery(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            if (rs != null) {
                while (rs.next()) {
                    flight = createFlightFromResultSet(rs);
                    flights.add(flight);
                }
                rs.close();
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return flights;
    }

    public Flight findFlight(String flightId, String departureTime) throws ParseException {
        Flight flight = null;
        ResultSet rs = null;

        try {
            cmdString = "Select * from FLIGHT where FLIGHTID = '" + flightId + "'" +
                    " AND DEPARTURETIME = '" + departureTime + "'";
            rs = st1.executeQuery(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            if (rs != null) {
                while (rs.next()) {
                    flight = createFlightFromResultSet(rs);
                    rs.close();
                }
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return flight;
    }

    public Flight findByFlightCode(String flightCode) {
        Flight flight = null;
        result = null;
        try {
            cmdString = "Select * from Flight where FLIGHTID = '" + flightCode + "'";
            rs1 = st1.executeQuery(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        try {
            while (rs1.next()) {
                flight = createFlightFromResultSet(rs1);
            }
            rs1.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return flight;
    }

    private Flight createFlightFromResultSet(ResultSet rs) throws SQLException, ParseException {
        Flight flight;
        String flightID, departureDate, arrivalDate, airline, origin, destination;
        int capacity, seattaken, classInt;
        double price;
        Airline company;
        Airport departure, arrive;

        flightID = rs.getString("FLIGHTID");
        departureDate = rs.getString("DEPARTURETIME");
        arrivalDate = rs.getString("ARRIVALTIME");
        airline = rs.getString("AIRLINENAME");
        origin = rs.getString("AIRPORTID1");
        destination = rs.getString("AIRPORTID2");
        price = rs.getDouble("PRICE");
        capacity = rs.getInt("CAPACITY");
        seattaken = rs.getInt("SEATSTAKEN");
        classInt = rs.getInt("CLASS");
        AirlineTableSql airlineTableSql = new AirlineTableSql();

        airlineTableSql.initialize(Main.getDBPathName());
        List<Airline> airlines1 = airlineTableSql.getAirlines();
        company = airlineTableSql.findAirline(airline);
        AirportTableSql airportTableSql = new AirportTableSql();
        airportTableSql.initialize(Main.getDBPathName());
        List<Airport> airports = airportTableSql.getAirports();
        arrive = airportTableSql.findAirport(origin);
        departure = airportTableSql.findAirport(destination);

        Flight.FlightBuilder builder = new Flight.FlightBuilder(flightID, arrive, departure);
        return builder.setAirline(company)
                .setDepartureTime(sdf.parse(departureDate))
                .setArrivalTime(sdf.parse(arrivalDate))
                .setPrice(price)
                .setCapacity(capacity)
                .setSeatsTaken(seattaken)
                .build();
    }

    public boolean add(Flight flight) {
        boolean added = false;

        String values;
        String departDate;
        String arriveDate;

        if (flight != null) {
            departDate = sdf.format(flight.getDepartureTime());
            arriveDate = sdf.format(flight.getArrivalTime());

            try {
                values = "'" + flight.getFlightCode()
                        + "', '" + departDate
                        + "', '" + arriveDate
                        + "', '" + flight.getAirlineString()
                        + "', '" + flight.getOrigin()
                        + "', '" + flight.getDestination()
                        + "', " + flight.getPrice()
                        + ", " + flight.getCapacity()
                        + ", " + flight.getSeatsTaken()
                        + ", " + flight.getFlightClassInt();
                cmdString = "Insert into Flight " + " Values(" + values + ")";
                //System.out.println(cmdString);
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

    public ArrayList<Flight> findBySearchCriteria(SearchCriteria criteria) {
        ArrayList<Flight> table = new ArrayList<>();
        Flight flight;
        result = null;

        if (criteria != null) {
            try {
                cmdString = "Select * from Flight Where AIRPORTID1='"
                        + criteria.getOriginString() + "' AND AIRPORTID2='"
                        + criteria.getDestinationString()
                        + "' AND CAPACITY >= "
                        + criteria.getNumTravellers()
                        + " AND DEPARTURETIME LIKE '%" + criteria.getDepartureDateString() + "%'";
                if (!(criteria.getMaxPrice() == 0.0))
                    cmdString += " AND PRICE <= " + criteria.getMaxPrice();
                if (!(criteria.getPreferredAirline() == null))
                    cmdString += " AND AIRLINENAME = '" + criteria.getPreferredAirline().getName() + "'";
                if (!(criteria.getPreferredClass() == null))
                    cmdString += " AND CLASS = " + criteria.getPreferredClassInt();

                rs3 = st1.executeQuery(cmdString);
            } catch (Exception e) {
                processSQLError(e);
            }
            try {
                while (rs3.next()) {
                    flight = createFlightFromResultSet(rs3);
                    table.add(flight);
                }
                rs3.close();
            } catch (Exception e) {
                result = processSQLError(e);
            }
        }

        return table;
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
        // System.out.println("Closed database ");
    }
}
