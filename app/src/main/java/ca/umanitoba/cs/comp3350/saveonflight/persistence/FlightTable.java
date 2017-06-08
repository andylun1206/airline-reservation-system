package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.BookedFlight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

import static ca.umanitoba.cs.comp3350.saveonflight.persistence.AirlineTable.getAirlines;
import static ca.umanitoba.cs.comp3350.saveonflight.persistence.AirportTable.getAirports;

/**
 * FlightTable.java
 * <p>
 * Database table for Flight.
 *
 * @author Long Yu
 */


public class FlightTable implements DataAccessStub<Flight>, FlightAccess {
    private String dbName;
    private static ArrayList<Flight> flights = null;

    public FlightTable(String dbName) {
        this.dbName = dbName;
    }

    public void initialize() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
        flights = new ArrayList<Flight>();

        ArrayList<Airport> airports = AirportTable.getAirports();
        Airline airCanada = AirlineTable.findAirline("Air Canada");
        Airline westJet = AirlineTable.findAirline("WestJet");

        if (airports != null && airCanada != null && westJet != null) {
            try {
                //AC
                flights.add(new Flight("AC 256", sdf.parse("2017-11-11 05:30"), sdf.parse("2017-11-11 08:51"), airCanada,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 350.52, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("AC 260", sdf.parse("2017-11-11 07:30"), sdf.parse("2017-11-11 10:52"), airCanada,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 325.82, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("AC 264", sdf.parse("2017-11-11 10:50"), sdf.parse("2017-11-11 14:10"), airCanada,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 403.20, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("AC 266", sdf.parse("2017-11-11 12:20"), sdf.parse("2017-11-11 15:41"), airCanada,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 467.29, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("AC 268", sdf.parse("2017-11-11 14:35"), sdf.parse("2017-11-11 17:56"), airCanada,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 210.87, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("AC 270", sdf.parse("2017-11-11 16:45"), sdf.parse("2017-11-11 20:06"), airCanada,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 629.05, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("AC 272", sdf.parse("2017-11-11 18:45"), sdf.parse("2017-11-11 22:07"), airCanada,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 320.49, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("AC 274", sdf.parse("2017-11-11 21:00"), sdf.parse("2017-11-12 00:21"), airCanada,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 420.12, 200, 0, FlightClassEnum.ECONOMY));
                //WJ
                flights.add(new Flight("WJ 520", sdf.parse("2017-11-11 05:10"), sdf.parse("2017-11-12 08:30"), westJet,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 342.10, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("WJ 534", sdf.parse("2017-11-11 09:00"), sdf.parse("2017-11-12 12:20"), westJet,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 562.23, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("WJ 546", sdf.parse("2017-11-11 11:20"), sdf.parse("2017-11-12 14:40"), westJet,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 178.23, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("WJ 258", sdf.parse("2017-11-11 16:00"), sdf.parse("2017-11-12 19:20"), westJet,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 297.23, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("WJ 562", sdf.parse("2017-11-11 17:15"), sdf.parse("2017-11-12 20:35"), westJet,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 344.25, 200, 0, FlightClassEnum.ECONOMY));
                flights.add(new Flight("WJ 490", sdf.parse("2017-11-11 18:15"), sdf.parse("2017-11-12 21:35"), westJet,
                        AirportTable.findAirport("YWG"), AirportTable.findAirport("YYZ"), 547.67, 200, 0, FlightClassEnum.ECONOMY));


            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    public boolean add(Flight flight) {
        return flights.add(flight);
    }

    public boolean update(Flight flight) {
        boolean isUpdated = false;
        Date departureTime = flight.getDepartureTime();
        String flightId = flight.getFlightID();
        int changes = 0;
        int index = 0;
        Flight temp;
        for (int i = 0; i < flights.size(); i++) {
            temp = flights.get(i);
            if (temp.getDepartureTime().equals(departureTime) && temp.getFlightID().equals(flightId)) {
                changes++;
                index = i;
            }
        }
        if (changes != 0) {
            isUpdated = true;
            flights.get(index).setAirline(flight.getAirline());
            flights.get(index).setArrivalTime(flight.getArrivalTime());
            flights.get(index).setOrigin(flight.getOrigin());
            flights.get(index).setDestination(flight.getDestination());
            flights.get(index).setPrice(flight.getPrice());
            flights.get(index).setCapacity(flight.getCapacity());
            flights.get(index).setSeatsTaken(flight.getSeatsTaken());
            flights.get(index).setFlightClass(flight.getFlightClass());
        }
        return isUpdated;
    }

    public boolean remove(Flight flight) {
        int index;
        index = flights.indexOf(flight);
        if (index >= 0) {
            flights.remove(index);
            return true;
        }
        return false;
    }

    public ArrayList<Flight> findBySearchCriteria(SearchCriteria criteria) {
        ArrayList<Flight> table;
        table = createTableByOriginAndDestination(new ArrayList<Flight>(), criteria.getOrigin(), criteria.getDestination());
        table = removeByDepartureDate(table, criteria.getDepartureDate());
        table = removeByNumTravellers(table, criteria.getNumTravellers());
        if (!(criteria.getMaxPrice() == 0.0))
            table = removeByMaxPrice(table, criteria.getMaxPrice());
        if (!(criteria.getPreferredAirlines() == null))
            table = removeByPreferredAirlines(table, criteria.getPreferredAirlines());
        if (!(criteria.getPreferredClass() == null))
            table = removeByPreferredClass(table, criteria.getPreferredClass());
        table = removeByNonstop(table);
        table = removeByRefundable(table);

        return table;
    }


    // TRAVELLERS TABLE METHODS
    /*public ArrayList<Traveller> getTravellers() {
        return travellerTable.getTravellers();
    }*/

    /*public boolean insertTraveller(Traveller traveller) {}

    public boolean updateTraveller(Traveller traveller, int newID, String newName) {}

    public boolean deleteTraveller(Traveller traveller) {}
*/
    private ArrayList<Flight> createTableByOriginAndDestination(ArrayList<Flight> table, Airport origin, Airport destination) {
        Flight temp;
        for (int i = 0; i < flights.size(); i++) {
            temp = flights.get(i);
            if (temp.getOrigin().equals(origin) && temp.getDestination().equals(destination)) {
                table.add(temp);
            }
        }
        if (table.isEmpty())
            return new ArrayList<Flight>();
        return table;
    }

    private ArrayList<Flight> removeByDepartureDate(ArrayList<Flight> table, Date departureDate) {
        Calendar calFilterBy = Calendar.getInstance();
        calFilterBy.setTime(departureDate);

        if (!table.isEmpty()) {
            Flight temp;
            Calendar calTemp = Calendar.getInstance();
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                calTemp.setTime(temp.getDepartureTime());
                //if (!temp.getDepartureTime().equals(departureDate)) {
                if (calTemp.get(Calendar.YEAR) != calFilterBy.get(Calendar.YEAR) ||
                        calTemp.get(Calendar.DAY_OF_YEAR) != calFilterBy.get(Calendar.DAY_OF_YEAR)) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByNumTravellers(ArrayList<Flight> table, int numTravellers) {
        if (!table.isEmpty()) {
            Flight temp;
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                if (temp.getCapacity() < numTravellers) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByMaxPrice(ArrayList<Flight> table, double maxPrice) {
        if (!table.isEmpty()) {
            Flight temp;
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                if (temp.getPrice() > maxPrice) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByPreferredAirlines(ArrayList<Flight> table, Airline preferredAirlines) {
        if (!table.isEmpty()) {
            Flight temp;
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                if (!temp.getAirline().equals(preferredAirlines)) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByPreferredClass(ArrayList<Flight> table, FlightClassEnum preferredClass) {
        if (!table.isEmpty()) {
            Flight temp;
            for (int i = 0; i < table.size(); i++) {
                temp = table.get(i);
                if (!temp.getFlightClass().equals(preferredClass)) {
                    table.remove(temp);
                    i--;
                }
            }
        }
        return table;
    }

    private ArrayList<Flight> removeByNonstop(ArrayList<Flight> table) {
        /*if (table.isEmpty())
            return null;
         */
        // Adding more stuff later
        return table;
    }

    private ArrayList<Flight> removeByRefundable(ArrayList<Flight> table) {
        /*if (table.isEmpty())
            return null;
         */
        // Adding more stuff later
        return table;
    }
}
