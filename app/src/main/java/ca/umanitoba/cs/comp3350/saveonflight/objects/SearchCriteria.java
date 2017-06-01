package ca.umanitoba.cs.comp3350.saveonflight.objects;

/**
 * SearchCriteria.java
 *
 * Object representing a search for flights.
 *
 * @author Andy Lun
 */

import java.util.Date;

public class SearchCriteria {
	private Airport origin;
	private Airport destination;
	private Date departureDate;
	private Date returnDate;
	private int numTravellers;
	private double maxPrice;
	private Airline preferredAirlines;
	private Flight.FlightClass preferredClass;
	private boolean nonstop;
	private boolean refundable;
	
	public SearchCriteria() { };
	
	public SearchCriteria(Airport origin, Airport destination, Date departureDate, Date returnDate,
	                      int numTravellers, double maxPrice, Airline preferredAirlines,
	                      Flight.FlightClass preferredClass, boolean nonstop, boolean refundable) {
		this.origin = origin;
		this.destination = destination;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		this.numTravellers = numTravellers;
		this.maxPrice = maxPrice;
		this.preferredAirlines = preferredAirlines;
		this.preferredClass = preferredClass;
		this.nonstop = nonstop;
		this.refundable = refundable;
	}
	public Airport getOrigin() { return origin; }
	public void setOrigin(Airport origin) { this.origin = origin; }
	
	public Airport getDestination() { return destination; }
	public void setDestination(Airport destination) { this.destination = destination; }
	
	public Date getDepartureDate() { return departureDate; }
	public void setDepartureDate(Date departureDate) { this.departureDate = departureDate; }
	
	public Date getReturnDate() { return returnDate; }
	public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
	
	public int getNumTravellers() {return numTravellers; }
	public void setNumTravellers(int numTravellers) { this.numTravellers = numTravellers; }
	
	public double getMaxPrice() { return maxPrice; }
	public void setMaxPrice(double maxPrice) { this.maxPrice = maxPrice; }
	
	public Airline getPreferredAirlines() { return preferredAirlines; }
	public void setPreferredAirlines(Airline preferredAirlines) { this.preferredAirlines = preferredAirlines; }
	
	public Flight.FlightClass getPreferredClass() { return preferredClass; }
	public void setPreferredClass(Flight.FlightClass preferredClass) { this.preferredClass = preferredClass; }
	
	public boolean isNonstop() { return nonstop; }
	public void setNonstop(boolean nonstop) { this.nonstop = nonstop; }
	
	public boolean isRefundable() { return refundable; }
	public void setRefundable(boolean refundable) { this.refundable = refundable; }
}
