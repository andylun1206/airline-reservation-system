package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * Flight.java
 *
 * Object mapped to the flights table DB
 *
 * @author Andy Lun
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Flight implements Parcelable {
    private String flightID;
    private Airline airline;
    private Date departTime;
    private Date arriveTime;
    private Airport origin;
    private Airport destination;
    private double price;
    private int capacity;
    private int seatsTaken;
    private FlightClass flightClass;

    public enum FlightClass {
        ECONOMY, BUSINESS, FIRST_CLASS;
    }


    public Flight(String flightID, Date departTime,Date arriveTime, Airline airline, Airport origin, Airport destination,
                  double price, int capacity) {
        this(flightID, departTime,arriveTime, airline, origin, destination, price, capacity, 0, FlightClass.ECONOMY);
    }

    public Flight(String flightID, Date departTime,Date arriveTime, Airline airline, Airport origin, Airport destination,

                  double price, int capacity, int seatsTaken, FlightClass flightClass) {
        this.flightID = flightID;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.capacity = capacity;
        this.seatsTaken = seatsTaken;
        this.flightClass = flightClass;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline.setName(airline);
    }

    public Date getDepartTime() {
        return departTime;
    }
    public Date getArriveTime() {
        return arriveTime;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public void setDepartTime(Date date) {
        this.departTime = date;
    }

    public void setArriveTime(Date date){this.arriveTime = date; }

    public String getFlightID() {
        return flightID;
    }

    public String toString() {
        return "Flight: " + flightID + " " + departTime +" "+ arriveTime + " " + airline + " " + "from " + origin + " to " + destination;
    }

    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof Flight) {
            Flight other = (Flight) object;
            if (other.flightID.equals(flightID)) {
                result = true;
            }
        }

        return result;
    }

    //calculate time duration
    public String getDurationInMinutes(){
        String r = "";
        int result = (int)(getDateDiff(departTime,arriveTime,TimeUnit.MINUTES));

        int hours = result/60;
        int minutes = result%60;

        String hrs = Integer.toString(hours);
        String mins = Integer.toString(minutes);

        r += hrs + "h " + mins + "m";

        return r;
    }

    private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    //end
    
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(flightID);
        parcel.writeParcelable(airline, flags);
        parcel.writeSerializable(departTime);
        parcel.writeSerializable(arriveTime);
        parcel.writeParcelable(origin, flags);
        parcel.writeParcelable(destination, flags);
        parcel.writeDouble(price);
        parcel.writeInt(capacity);
        parcel.writeInt(seatsTaken);
        parcel.writeSerializable(flightClass);
    }
    
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
        
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }
    };
    
    public Flight(Parcel in) {
        flightID = in.readString();
        airline = in.readParcelable(Airline.class.getClassLoader());
        departTime = (Date) in.readSerializable();
        arriveTime = (Date) in.readSerializable();
        origin = in.readParcelable(Airport.class.getClassLoader());
        destination = in.readParcelable(Airport.class.getClassLoader());
        price = in.readDouble();
        capacity = in.readInt();
        seatsTaken = in.readInt();
        flightClass = (FlightClass) in.readSerializable();
    }
}
