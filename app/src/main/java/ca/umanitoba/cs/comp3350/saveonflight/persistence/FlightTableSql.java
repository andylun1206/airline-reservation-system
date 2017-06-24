package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;

/**
 * Created by zhengyugu on 2017-06-18.
 */

public class FlightTableSql implements FlightAccess {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs1,rs2, rs3, rs4, rs5,rs6,rs7,rs8;

    private ArrayList<Flight> flights;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);

    public FlightTableSql(){}
    public void initialize(){
        String url = "";// TODO: 2017-06-24
        try
        {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " + " database ");
    }

    public ArrayList<Flight> getFlights() {
        Flight flight;
        String flightID,departureDate,arrivalDate,airline,origin,destination;
        int capacity,seattaken,classInt;
        double price;
        flightID = EOF;
        departureDate =EOF;
        arrivalDate = EOF;
        airline = EOF;
        origin = EOF;
        destination = EOF;
        price = 0;
        capacity = 0;
        seattaken = 0;
        classInt = 0;
        result = null;

        try
        {
            cmdString = "Select * from Flight";
            rs2 = st1.executeQuery(cmdString);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs2.next())
            {
                flightID = rs2.getString(0);
                departureDate = rs2.getString(1);
                arrivalDate = rs2.getString(2);
                airline = rs2.getString(3);
                origin = rs2.getString(4);
                destination = rs2.getString(5);
                price = rs2.getInt(6);
                capacity = rs2.getInt(7);
                seattaken = rs2.getInt(8);
                classInt = rs2.getInt(9);


                flight = new Flight(flightID, sdf.parse(departureDate), sdf.parse(arrivalDate),
                        getAirlineByName(airline),getAirportByID(origin),getAirportByID(destination),
                        price,capacity,seattaken,FlightClassEnum.values()[classInt]);
                flights.add(flight);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return flights;
    }
    public boolean add(Flight flight) {
        String values;
        String departDate;
        String arriveDate;


        departDate = sdf.format(flight.getDepartureTime());
        arriveDate = sdf.format(flight.getArrivalTime());
        if(flight!=null) {

            try {
                values = "'" + flight.getFlightID()
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
        return false;
    }
    public boolean update(Flight flight) {

        return false;
    }
    public boolean remove(Flight flight){

        return false;
    }
    public ArrayList<Flight> findBySearchCriteria(SearchCriteria criteria){
        return null;
    }

    public Airline getAirlineByName(String targetName){
        Airline airline=null;
        String name;
        int icon;
        name = EOF;
        icon = 0;

        result=null;
        try
        {
            cmdString = "Select * from Airline where NAME='"+targetName+"'";
            rs7 = st1.executeQuery(cmdString);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs7.next())
            {
                name = rs7.getString(0);
                icon = rs7.getInt(1);
                airline = new Airline(name, icon);
            }
            rs7.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return airline;
    }
    public Airport getAirportByID(String targetID){
        Airport airport=null;
        String ID;
        ID = EOF;

        result=null;
        try
        {
            cmdString = "Select * from Airport where ='"+targetID+"'";
            rs8 = st1.executeQuery(cmdString);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs8.next())
            {
                ID = rs8.getString(0);
                airport = new Airport(ID);
            }
            rs8.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return airport;
    }
    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
