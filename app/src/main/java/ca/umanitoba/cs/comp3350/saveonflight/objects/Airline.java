package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * Airline.java
 * <p>
 * Object mapped to the airline table DB
 *
 * @author Andy Lun
 */

public class Airline implements Comparable {
    private String name;

    public Airline(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Airline) {
            Airline other = (Airline) object;
            if (other.name.equals(this.name)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public int compareTo(Object o) {
        int result = 0;

        if (o instanceof Airline) {
            Airline a = (Airline) o;
            result = name.compareTo(a.getName());
        }

        return result;
    }
}
