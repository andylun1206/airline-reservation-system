package ca.umanitoba.cs.comp3350.saveonflight.objects;

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

    public String toString() {
        return "Traveller: " + travellerID + " " + name;
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
