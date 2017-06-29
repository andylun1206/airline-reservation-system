package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * Traveller.java
 * <p>
 * Object mapped to the Traveller table in the database.
 *
 * @author Kenny Zhang
 */

public class Traveller {
    private int travellerID;
    private String name;

    public Traveller(int travellerID, String name) {
        this.travellerID = travellerID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTravellerID() {
        return travellerID;
    }

    public void setTravellerID(int travellerID) {
        this.travellerID = travellerID;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Traveller) {
            Traveller other = (Traveller) object;
            if (other.travellerID == travellerID) {
                result = true;
            }
        }

        return result;
    }
}
