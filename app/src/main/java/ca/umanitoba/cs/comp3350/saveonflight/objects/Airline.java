package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * Airline.java
 * <p>
 * Object mapped to the airline table DB
 *
 * @author Andy Lun
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Airline implements Comparable  {
    private String name;
    private int icon;

    public Airline(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) { this.icon = icon; }

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
    public int compareTo(@NonNull Object o) {
        int result = 0;

        if (o instanceof Airline) {
            Airline a = (Airline) o;
            result = name.compareTo(a.getName());
        }

        return result;
    }
}
