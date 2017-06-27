package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * Flight.java
 * <p>
 * Object mapped to the flights table DB
 *
 * @author Andy Lun
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Flight implements Parcelable {
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy, MM, dd, HH, mm", Locale.CANADA);
    private String flightID;
    private Airline airline;
    private Date departureTime;
    private Date arrivalTime;
    private Airport origin;
    private Airport destination;
    private double price;
    private int capacity;
    private int seatsTaken;
    private FlightClassEnum flightClass;

    private Flight(String flightID, Date departureTime, Date arrivalTime,
                   Airline airline, Airport origin, Airport destination,
                   double price, int capacity, int seatsTaken, FlightClassEnum flightClass) {
        this.flightID = flightID;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.capacity = capacity;
        this.seatsTaken = seatsTaken;
        this.flightClass = flightClass;
    }

    public boolean isFull() {
        return getSeatsRemaining() <= 0;
    }

    public int getSeatsRemaining() {
        return capacity - seatsTaken;
    }

    public boolean sellSeats(int numSold) {
        boolean result = false;

        if (numSold >= 0 && getSeatsRemaining() >= numSold) {
            seatsTaken += numSold;
            result = true;
        }

        return result;
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

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getOrigin() {
        return origin;
    }

    public String getDepartAirportCode(){ return origin.getAirportCode(); }

    public String getArrivalAirportCode(){ return destination.getAirportCode(); }

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

    public FlightClassEnum getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClassEnum flightClass) {
        this.flightClass = flightClass;
    }

    public String getFlightID() {
        return flightID;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String toString() {
        return "Flight: " + flightID + " " + departureTime + " " + arrivalTime + " " + airline + " " + "from " + origin + " to " + destination;
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

    public String getFlightDuration() {
        int duration = (int) getDateDiff(TimeUnit.MINUTES);
        int hours = duration / 60;
        int minutes = duration % 60;

        return Integer.toString(hours) + "h " + Integer.toString(minutes) + "m";
    }

    public long getDateDiff(TimeUnit timeUnit) {
        if (arrivalTime == null) {
            return Long.MIN_VALUE;
        } else if (departureTime == null) {
            return Long.MAX_VALUE;
        } else {
            long diffInMillis = arrivalTime.getTime() - departureTime.getTime();
            return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
        }
    }

    public String getFlightTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CANADA);
        return sdf.format(departureTime) + " - " + sdf.format(arrivalTime);
    }

    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(flightID);
        parcel.writeParcelable(airline, flags);
        parcel.writeSerializable(departureTime);
        parcel.writeSerializable(arrivalTime);
        parcel.writeParcelable(origin, flags);
        parcel.writeParcelable(destination, flags);
        parcel.writeDouble(price);
        parcel.writeInt(capacity);
        parcel.writeInt(seatsTaken);
        parcel.writeSerializable(flightClass);
    }

    public Flight(Parcel in) {
        flightID = in.readString();
        airline = in.readParcelable(Airline.class.getClassLoader());
        departureTime = (Date) in.readSerializable();
        arrivalTime = (Date) in.readSerializable();
        origin = in.readParcelable(Airport.class.getClassLoader());
        destination = in.readParcelable(Airport.class.getClassLoader());
        price = in.readDouble();
        capacity = in.readInt();
        seatsTaken = in.readInt();
        flightClass = (FlightClassEnum) in.readSerializable();
    }

    public static final class FlightBuilder {
        private String flightId;
        private Airline airline;
        private Date departureTime;
        private Date arrivalTime;
        private Airport origin;
        private Airport destination;
        private double price;
        private int capacity;
        private int seatsTaken;
        private FlightClassEnum flightClass;

        public FlightBuilder(String flightId, Airport origin, Airport destination) {
            this.flightId = flightId;
            this.origin = origin;
            this.destination = destination;

            airline = null;
            departureTime = null;
            arrivalTime = null;
            price = -1;
            capacity = -1;
            seatsTaken = 0;
            flightClass = FlightClassEnum.ECONOMY;
        }

        public FlightBuilder setFlightId(String flightId) {
            this.flightId = flightId;
            return this;
        }

        public FlightBuilder setAirline(Airline airline) {
            this.airline = airline;
            return this;
        }

        public FlightBuilder setDepartureTime(Date departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public FlightBuilder setArrivalTime(Date arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public FlightBuilder setOrigin(Airport origin) {
            this.origin = origin;
            return this;
        }

        public FlightBuilder setDestination(Airport destination) {
            this.destination = destination;
            return this;
        }

        public FlightBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public FlightBuilder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public FlightBuilder setSeatsTaken(int seatsTaken) {
            this.seatsTaken = seatsTaken;
            return this;
        }

        public FlightBuilder setFlightClass(FlightClassEnum flightClass) {
            this.flightClass = flightClass;
            return this;
        }

        public Flight build() {
            return new Flight(flightId, departureTime, arrivalTime, airline, origin, destination,
                    price, capacity, seatsTaken, flightClass);
        }
    }

}
