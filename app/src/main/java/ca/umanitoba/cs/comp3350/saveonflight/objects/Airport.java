package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * Airport.java
 * <p>
 * Object mapped to the airport table DB
 *
 * @author Andy Lun
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Airport implements Parcelable {
    private String airportCode;

    public Airport(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String toString() {
        return airportCode;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Airport) {
            Airport other = (Airport) object;
            if (other.airportCode.equals(airportCode)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(airportCode);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Airport[] newArray(int size) {
            return new Airport[size];
        }

        @Override
        public Airport createFromParcel(Parcel in) {
            return new Airport(in);
        }
    };

    private Airport(Parcel in) {
        airportCode = in.readString();
    }
}
