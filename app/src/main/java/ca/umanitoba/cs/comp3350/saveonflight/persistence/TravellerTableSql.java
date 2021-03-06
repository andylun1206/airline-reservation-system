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

import static ca.umanitoba.cs.comp3350.saveonflight.persistence.DatabaseHandler.checkWarning;
import static ca.umanitoba.cs.comp3350.saveonflight.persistence.DatabaseHandler.processSQLError;

/**
 * Created by zhengyugu on 2017-06-28.
 */

public class TravellerTableSql implements TravellerAccess {
    private Statement st1;
    private Connection c1;
    private ResultSet rs1;
    private String cmdString;
    private int updateCount;
    private String result;

    public TravellerTableSql() {
    }

    public void initialize() {
        try {
            c1 = DatabaseHandler.getConnection();
            st1 = c1.createStatement();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public ArrayList<Traveller> getTravellers() {
        ArrayList<Traveller> travellers = new ArrayList<>();
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
                travellers.add(new Traveller(ID, name));
            }
            rs1.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return travellers;
    }

    public boolean add(Traveller traveller) {
        boolean added = false;

        if (traveller != null && traveller.getName() != null && !traveller.getName().isEmpty()) {
            String values;
            result = null;
            try {
                values = "'" + traveller.getName() + "'";
                cmdString = "Insert into Traveller(NAME) Values(" + values + ")";
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

    public int getMaxId() {
        int id = -1;

        try {
            cmdString = "SELECT MAX(ID) FROM Traveller";
            ResultSet rs = st1.executeQuery(cmdString);
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            processSQLError(e);
        }

        return id;
    }

    public boolean remove(Traveller traveller) {
        boolean removed = false;

        if (traveller != null && traveller.getName() != null && !traveller.getName().isEmpty()) {
            try {
                cmdString = "Delete from TRAVELLER where ID=" + traveller.getTravellerID();
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

    Traveller findTraveller(int id) {
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
}


