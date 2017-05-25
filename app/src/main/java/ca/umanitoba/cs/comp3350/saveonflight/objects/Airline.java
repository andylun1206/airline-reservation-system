package ca.umanitoba.cs.comp3350.saveonflight.objects;

public class Airline {
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

    public String toString() {
        return "Airline: " + name;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Airline) {
            Airline other = (Airline) object;
            if (other.name.equals(name)) {
                result = true;
            }
        }

        return result;
    }
}
