package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Traveller;

/**
 * Created by zhengyugu on 2017-06-28.
 */

public class TravellerTableSql implements TravellerAccess {
    private Statement st1;
    private Connection c1;
    private ResultSet rs1, rs2, rs3;
    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";
    private ArrayList<Traveller> travellers = null;

    public TravellerTableSql() {
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

    public ArrayList<Traveller> getTravellers() {
        Traveller traveller;
        int ID;
        String name;
        result = null;
        try {
            cmdString = "SELECT * FROM TRAVELLER";
            rs1 = st1.executeQuery(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs1.next()) {
                ID = rs1.getInt("ID");
                name = rs1.getString("NAME");
                traveller = new Traveller(ID, name);

            }
            rs1.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return travellers;
    }

    public int add(Traveller traveller) {
        String values;
        int id = -1;
        result = null;
        try {
            values = "'" + traveller.getName() + "'";
            cmdString = "Insert into Traveller(NAME) " + " Values(" + values + ")";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);

            cmdString = "SELECT MAX(ID) FROM Traveller";
            ResultSet rs = st1.executeQuery(cmdString);
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return id;
    }

    public Traveller findTraveller(int id) {
        Traveller result = null;

        try {
            cmdString = "Select * from Traveller where ID=" + id;
            rs1 = st1.executeQuery(cmdString);

            if (rs1 != null && rs1.next()) {
                result = createTravellerFromResultSet(rs1);
            }
        } catch (Exception e) {
            processSQLError(e);
        }

        return result;

    }

    private Traveller createTravellerFromResultSet(ResultSet rs) throws SQLException, ParseException {
        return new Traveller(rs.getInt("ID"), rs.getString("NAME"));
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


