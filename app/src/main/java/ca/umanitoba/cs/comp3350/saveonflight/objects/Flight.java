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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Flight implements Parcelable {
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy, MM, dd", Locale.CANADA);

    private String flightID;
    private Airline airline;
    private Date date;
    private Airport origin;
    private Airport destination;
    private double price;
    private int capacity;
    private int seatsTaken;
    private FlightClassEnum flightClass;

    public Flight(String flightID, Date date, Airline airline, Airport origin, Airport destination,
                  double price, int capacity) {
        this(flightID, date, airline, origin, destination, price, capacity, 0, FlightClassEnum.ECONOMY);
    }

    public Flight(String flightID, Date date, Airline airline, Airport origin, Airport destination,
                  double price, int capacity, int seatsTaken, FlightClassEnum flightClass) {
        this.flightID = flightID;
        this.date = date;
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

        if (getSeatsRemaining() >= numSold) {
            seatsTaken += numSold;
            result = true;
        } else {
            System.out.println("Trying to sell more seats than are available");
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

    public Date getDate() {
        return date;
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

    public FlightClassEnum getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClassEnum flightClass) {
        this.flightClass = flightClass;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFlightID() {
        return flightID;
    }

    public String toString() {
        return "Flight: " + flightID + " " + date + " " + airline + " " + "from " + origin + " to " + destination;
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

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(flightID);
        parcel.writeParcelable(airline, flags);
        parcel.writeSerializable(date);
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
        date = (Date) in.readSerializable();
        origin = in.readParcelable(Airport.class.getClassLoader());
        destination = in.readParcelable(Airport.class.getClassLoader());
        price = in.readDouble();
        capacity = in.readInt();
        seatsTaken = in.readInt();
        flightClass = (FlightClassEnum) in.readSerializable();
    }
	private String flightID;
	private Airline airline;
	private Date departureTime;
	private Date arrivalTime;
	private Airport origin;
	private Airport destination;
	private double price;
	private int capacity;
	private int seatsTaken;
	private FlightClass flightClass;

	public enum FlightClass {
		ECONOMY, BUSINESS, FIRST_CLASS;
	}


	public Flight(String flightID, Date departureTime, Date arrivalTime, Airline airline, Airport origin, Airport destination,
	              double price, int capacity) {
		this(flightID, departureTime, arrivalTime, airline, origin, destination, price, capacity, 0, FlightClass.ECONOMY);
	}

	public Flight(String flightID, Date departureTime, Date arrivalTime, Airline airline, Airport origin, Airport destination,
	              double price, int capacity, int seatsTaken, FlightClass flightClass) {
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

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline.setName(airline);
	}

	public Date getDepartureTime() {
		return departureTime;
	}
	public Date getArrivalTime() {
		return arrivalTime;
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

	public void setDepartureTime(Date date) {
		this.departureTime = date;
	}

	public void setArrivalTime(Date date) {
		this.arrivalTime = date;
	}

	public String getFlightID() {
		return flightID;
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
		int duration = (int) getDateDiff(departureTime, arrivalTime, TimeUnit.MINUTES);
		int hours = duration / 60;
		int minutes = duration % 60;

		return Integer.toString(hours) + "h " + Integer.toString(minutes) + "m";
	}

	private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillis = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
	}

	public String getFlightTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CANADA);
		return sdf.format(departureTime) + " - " + sdf.format(arrivalTime);
	}

	@Override
	public int describeContents() {
		return 0;
	}
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
		departureTime = (Date) in.readSerializable();
		arrivalTime = (Date) in.readSerializable();
		origin = in.readParcelable(Airport.class.getClassLoader());
		destination = in.readParcelable(Airport.class.getClassLoader());
		price = in.readDouble();
		capacity = in.readInt();
		seatsTaken = in.readInt();
		flightClass = (FlightClass) in.readSerializable();
	}
}
