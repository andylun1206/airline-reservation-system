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
    private String code;
    private String city;

    public Airport(String code, String city) {
        this.code = code;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return code;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Airport) {
            Airport other = (Airport) object;
            if (other.code.equals(code)) {
                result = true;
            }
        }

        return result;
    }

    public boolean contains(Object object) {
        boolean result = false;

        if (object instanceof Airport) {
            Airport airport = (Airport) object;
            if (code.toLowerCase().contains(airport.getCode().toLowerCase())) {
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
        parcel.writeString(code);
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
        code = in.readString();
    }

}
