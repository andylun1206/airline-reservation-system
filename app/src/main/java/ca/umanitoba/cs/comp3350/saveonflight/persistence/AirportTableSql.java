package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;

import static ca.umanitoba.cs.comp3350.saveonflight.persistence.DatabaseHandler.processSQLError;

/**
 * Created by longyu on 2017-06-27.
 */

public class AirportTableSql implements AirportAccess {
    private Statement st1;
    private Connection c1;
    private ResultSet rs1;
    private String cmdString;

    public AirportTableSql() {
    }

    public void initialize() {
        try {
            c1 = DatabaseHandler.getConnection();
            st1 = c1.createStatement();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public List<Airport> getAirports() {
        List<Airport> airports = new ArrayList<>();
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

    public Airport findAirport(String key) {
        Airport airport = null;

        try {
            cmdString = "Select * from Airport where AIRPORTID='" + key + "'";
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

}
