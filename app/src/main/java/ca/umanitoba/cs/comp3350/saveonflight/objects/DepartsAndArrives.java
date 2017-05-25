package ca.umanitoba.cs.comp3350.saveonflight.objects;

public class DepartsAndArrives {
    private String departs;
    private String arrives;

    public DepartsAndArrives(String departs, String arrives) {
        this.departs = departs;
        this.arrives = arrives;
    }

    public String getDeparts() {
        return departs;
    }

    public void setDeparts(String departs) {
        this.departs = departs;
    }

    public String getArrives() {
        return arrives;
    }

    public void setArrives(String arrives) {
        this.arrives = arrives;
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
