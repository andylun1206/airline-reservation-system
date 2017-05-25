package ca.umanitoba.cs.comp3350.saveonflight.objects;


public class DepartsAndArrives {
    private Airport departs;
    private Airport arrives;

    public DepartsAndArrives(Airport departs, Airport arrives) {
        this.departs = departs;
        this.arrives = arrives;
    }

    public DepartsAndArrives(String departs, String arrives) {
        this.departs = new Airport(departs);
        this.arrives = new Airport(arrives);
    }

    public String getDeparts() {
        return departs.getAirportCode();
    }

    public void setDeparts(String departs) {
        this.departs.setAirportCode(departs);
    }

    public String getArrives() {
        return arrives.getAirportCode();
    }

    public void setArrives(String arrives) {
        this.arrives.setAirportCode(arrives);
    }

    public String toString() {
        return "Departs: " + departs + "\tArrives:" + arrives;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof DepartsAndArrives) {
            DepartsAndArrives other = (DepartsAndArrives) object;
            if (other.departs.equals(departs) && other.arrives.equals(arrives)) {
                result = true;
            }
        }
        return result;
    }
}
