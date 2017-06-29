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
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
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
    private static String EOF = "  ";


    private ArrayList<Flight> flights = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
    //AirlineTableSql airlineTableSql;
    public FlightTableSql() {
    }

    public void initialize(String dbPath) {
        //airlineTableSql = new AirlineTableSql();
        //airlineTableSql.initialize(dbPath);

        String url = "jdbc:hsqldb:file:" + dbPath;// TODO: 2017-06-24
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();

        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Opened database ");
    }

    public ArrayList<Flight> getFlights() {
        flights = new ArrayList<Flight>();
        Flight flight;
        String flightID, departureDate, arrivalDate, airline, origin, destination;
        int capacity, seattaken, classInt;
        double price;


        result = null;

        try {
            cmdString = "Select * from FLIGHT";
            rs1 = st1.executeQuery(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs1.next()) {
                flight = creatFlightFromResultSet(rs1);
                flights.add(flight);
            }
            rs1.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return flights;
    }
    public Flight findFlight(String flightId, String departureTime) throws ParseException {
        Flight result = null;

        for (Flight flight : flights) {
            if (flight.getFlightCode().toLowerCase().contains(flightId.toLowerCase())) {
                if ((flight.getDepartureTime()).compareTo(sdf.parse(departureTime)) == 0) {
                    result = flight;
                }
            }
        }

        return result;
    }

    public Flight findByFlightCode(String flightCode) {
        Flight flight = null;
        String values, flightId, departureTime;
        ;
        int id;
        result = null;
        try {
            cmdString = "Select * from Flight where FLIGHTID = '" + flightCode + "'";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        try {
            while (rs8.next()) {
                flight = creatFlightFromResultSet(rs1);
            }
            rs8.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return flight;
    }

    private Flight creatFlightFromResultSet(ResultSet rs) throws SQLException, ParseException {
        Flight flight;
        String flightID, departureDate, arrivalDate, airline, origin, destination;
        int capacity, seattaken, classInt;
        double price;
        Airline company;
        Airport departure,arrive;

        ArrayList<Airline> airlines = new AirlineTableSql().getAirlines();

        //ArrayList<Airline> airlines = airlineTableSql.getAirlines();

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
        flight = builder.setAirline(company)
                .setDepartureTime(sdf.parse(departureDate))
                .setArrivalTime(sdf.parse(arrivalDate))
                .setPrice(price)
                .setCapacity(capacity)
                .build();
//        flight = new Flight(flightID, sdf.parse(departureDate), sdf.parse(arrivalDate), company, arrive, departure,
//                price, capacity, seattaken, FlightClassEnum.values()[classInt]);


//        flight = new Flight(flightID, sdf.parse(departureDate), sdf.parse(arrivalDate),
//                getAirlineByName(airline), getAirportByID(origin), getAirportByID(destination),
//                price, capacity, seattaken, FlightClassEnum.values()[classInt]);
        return flight;
    }


    public boolean add(Flight flight) {
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
            } catch (Exception e) {
                result = processSQLError(e);
            }
        }
        return true;
    }


    public Airline getAirlineByName(String targetName) {
        Airline airline = null;
        String name;
        int icon;
        name = EOF;
        icon = 0;

        result = null;
        try {
            cmdString = "Select * from Airline where NAME='" + targetName + "'";
            rs7 = st2.executeQuery(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }
        try {

            name = rs7.getString("AIRLINENAME");
            airline = new Airline(name);
            rs7.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return airline;
    }
    public ArrayList<Flight> findBySearchCriteria(SearchCriteria criteria) {

//        String flightID,departureDate,arrivalDate,airline,origin,destination;
//        int capacity,seattaken,classInt;
//        double price;
//        flightID = EOF;
//        departureDate =EOF;
//        arrivalDate = EOF;
//        airline = EOF;
//        origin = EOF;
//        destination = EOF;
//        price = 0;
//        capacity = 0;
//        seattaken = 0;
//        classInt = 0;
        ArrayList<Flight> table;
        Flight flight;
        result = null;

        if (criteria != null) {
            table = new ArrayList<Flight>();
            try {
                cmdString = "Select * from Flight Where AIRPORTID1='"
                        + criteria.getOriginString() + "' AND AIRPORTID2='"
                        + criteria.getDestinationString()
                        + "' AND CAPACITY >= "
                        + criteria.getNumTravellers();
                if (!(criteria.getMaxPrice() == 0.0))
                    cmdString += " AND PRICE <= " + criteria.getMaxPrice();
                if (!(criteria.getPreferredAirline() == null))
                    cmdString += " AND AIRLINENAME = " + criteria.getPreferredAirline();
                if (!(criteria.getPreferredClass() == null))
                    cmdString += " AND CLASS = " + criteria.getPreferredClassInt();
                rs3 = st1.executeQuery(cmdString);

            } catch (Exception e) {
                processSQLError(e);
            }
            try {
                while (rs3.next()) {
//                    flightID = rs3.getString(0);
//                    departureDate = rs3.getString(1);
//                    arrivalDate = rs3.getString(2);
//                    airline = rs3.getString(3);
//                    origin = rs3.getString(4);
//                    destination = rs3.getString(5);
//                    price = rs3.getDouble(6);
//                    capacity = rs3.getInt(7);
//                    seattaken = rs3.getInt(8);
//                    classInt = rs3.getInt(9);
//
//
//                    flight = new Flight(flightID, sdf.parse(departureDate), sdf.parse(arrivalDate),
//                            getAirlineByName(airline),getAirportByID(origin),getAirportByID(destination),
//                            price,capacity,seattaken,FlightClassEnum.values()[classInt]);
                    flight = creatFlightFromResultSet(rs3);
                    table.add(flight);
                }
                rs2.close();
            } catch (Exception e) {
                result = processSQLError(e);
            }

            return table;

        }

        return null;
    }
    //// TODO: 2017-06-25
    public Airport getAirportByID(String targetID) {
        Airport airport = null;
        String ID;
        ID = EOF;

        result = null;
        try {
            cmdString = "Select * from Airport where ='" + targetID + "'";
            rs8 = st3.executeQuery(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs8.next()) {
                ID = rs8.getString("AIRPORTID");
                airport = new Airport(ID);
            }
            rs8.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return airport;
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
