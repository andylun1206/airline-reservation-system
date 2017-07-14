package ca.umanitoba.cs.comp3350.saveonflight.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;
import ca.umanitoba.cs.comp3350.saveonflight.objects.FlightClassEnum;
import ca.umanitoba.cs.comp3350.saveonflight.objects.SearchCriteria;

/**
 * FlightTable.java
 * <p>
 * Database table for Flight.
 *
 * @author Long Yu
 */


public class FlightTable implements FlightAccess {
    private static ArrayList<Flight> flights = null;

    public FlightTable() {
    }

    public void initialize() {
        if (flights == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CANADA);
            flights = new ArrayList<Flight>();

            AirlineTable airlineTable = new AirlineTable();
            if (airlineTable.getAirlines() == null) {
                new AirlineTable().initialize();
            }

            AirportTable airportTable = new AirportTable();
            if (airportTable.getAirports() == null) {
                new AirportTable().initialize();
            }

            try {
                Airline airCanada = airlineTable.findAirline("Air Canada");
                Airline westJet = airlineTable.findAirline("WestJet");
                Airport wpg = airportTable.findAirport("Winnipeg YWG");
                Airport tor = airportTable.findAirport("Toronto YYZ");

                Flight.FlightBuilder builder = new Flight.FlightBuilder("AC 256", wpg, tor);

                // Create the Flights using a FlightBuilder
                flights.add(builder.setAirline(airCanada)
                        .setDepartureTime(sdf.parse("2017-11-11 05:30"))
                        .setArrivalTime(sdf.parse("2017-11-11 08:51"))
                        .setPrice(350.52)
                        .setCapacity(200)
                        .build());
                flights.add(builder.setFlightId("AC 260")
                        .setDepartureTime(sdf.parse("2017-11-11 07:30"))
                        .setArrivalTime(sdf.parse("2017-11-11 10:52"))
                        .setPrice(325.82)
                        .build());
                flights.add(builder.setFlightId("AC 264")
                        .setDepartureTime(sdf.parse("2017-11-11 10:50"))
                        .setArrivalTime(sdf.parse("2017-11-11 14:10"))
                        .setPrice(403.20)
                        .build());
                flights.add(builder.setFlightId("AC 266")
                        .setDepartureTime(sdf.parse("2017-11-11 12:20"))
                        .setArrivalTime(sdf.parse("2017-11-11 15:41"))
                        .setPrice(467.29)
                        .build());
                flights.add(builder.setFlightId("AC 268")
                        .setDepartureTime(sdf.parse("2017-11-11 14:35"))
                        .setArrivalTime(sdf.parse("2017-11-11 17:56"))
                        .setPrice(210.87)
                        .build());
                flights.add(builder.setFlightId("AC 270")
                        .setDepartureTime(sdf.parse("2017-11-11 16:45"))
                        .setArrivalTime(sdf.parse("2017-11-11 20:06"))
                        .setPrice(629.05)
                        .build());
                flights.add(builder.setFlightId("AC 272")
                        .setDepartureTime(sdf.parse("2017-11-11 18:45"))
                        .setArrivalTime(sdf.parse("2017-11-11 22:07"))
                        .setPrice(320.49)
                        .build());
                flights.add(builder.setFlightId("AC 274")
                        .setDepartureTime(sdf.parse("2017-11-11 21:00"))
                        .setArrivalTime(sdf.parse("2017-11-12 00:21"))
                        .setPrice(420.12)
                        .build());

                flights.add(builder.setAirline(westJet)
                        .setFlightId("WJ 520")
                        .setDepartureTime(sdf.parse("2017-11-11 05:10"))
                        .setArrivalTime(sdf.parse("2017-11-11 08:30"))
                        .setPrice(342.10)
                        .build());
                flights.add(builder.setFlightId("WJ 534")
                        .setDepartureTime(sdf.parse("2017-11-11 09:00"))
                        .setArrivalTime(sdf.parse("2017-11-11 12:20"))
                        .setPrice(562.23)
                        .build());
                flights.add(builder.setFlightId("WJ 546")
                        .setDepartureTime(sdf.parse("2017-11-11 11:20"))
                        .setArrivalTime(sdf.parse("2017-11-11 14:40"))
                        .setPrice(178.23)
                        .build());
                flights.add(builder.setFlightId("WJ 258")
                        .setDepartureTime(sdf.parse("2017-11-11 16:00"))
                        .setArrivalTime(sdf.parse("2017-11-11 19:20"))
                        .setPrice(297.23)
                        .build());
                flights.add(builder.setFlightId("WJ 562")
                        .setDepartureTime(sdf.parse("2017-11-11 17:15"))
                        .setArrivalTime(sdf.parse("2017-11-11 20:35"))
                        .setPrice(344.25)
                        .build());
                flights.add(builder.setFlightId("WJ 490")
                        .setDepartureTime(sdf.parse("2017-11-11 18:15"))
                        .setArrivalTime(sdf.parse("2017-11-11 21:35"))
                        .setPrice(547.67)
                        .build());

                flights.add(builder.setFlightId("AC 100")
                        .setDepartureTime(sdf.parse("2017-12-11 08:00"))
                        .setArrivalTime(sdf.parse("2017-12-11 11:21"))
                        .setAirline(airCanada)
                        .setOrigin(tor).setDestination(wpg)
                        .setPrice(420.12)
                        .build());
                flights.add(builder.setFlightId("AC 101")
                        .setDepartureTime(sdf.parse("2017-12-11 21:00"))
                        .setArrivalTime(sdf.parse("2017-12-12 00:21"))
                        .build());
                flights.add(builder.setFlightId("WJ 490")
                        .setAirline(westJet)
                        .setDepartureTime(sdf.parse("2017-12-11 18:15"))
                        .setArrivalTime(sdf.parse("2017-12-11 21:35"))
                        .setPrice(205.25)
                        .build());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Flight findByFlightCode(String flightCode) {
        Flight flight = null;

        for (Flight f : flights) {
            if (f.getFlightCode().equalsIgnoreCase(flightCode)) {
                flight = f;
            }
        }

        return flight;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public ArrayList<Flight> findBySearchCriteria(SearchCriteria criteria) {
        ArrayList<Flight> table;
        table = createTableByOriginAndDestination(new ArrayList<Flight>(), criteria.getOrigin(), criteria.getDestination());
        table = removeByDepartureDate(table, criteria.getDepartureDate());
        if (!(criteria.getMaxPrice() == 0.0))
            table = removeByMaxPrice(table, criteria.getMaxPrice());
        if (!(criteria.getPreferredAirline() == null))
            table = removeByPreferredAirlines(table, criteria.getPreferredAirline());
        if (!(criteria.getPreferredClass() == null))
            table = removeByPreferredClass(table, criteria.getPreferredClass());

        return table;
    }

    private ArrayList<Flight> createTableByOriginAndDestination(ArrayList<Flight> table, Airport origin, Airport destination) {
        Flight temp;
        if (flights == null)
            return table;
        for (int i = 0; i < flights.size(); i++) {
            temp = flights.get(i);
            if (temp.getOrigin().contains(origin) && temp.getDestination().contains(destination)) {
                table.add(temp);
            }
        }

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
                if (calTemp.get(Calendar.YEAR) != calFilterBy.get(Calendar.YEAR) ||
                        calTemp.get(Calendar.DAY_OF_YEAR) != calFilterBy.get(Calendar.DAY_OF_YEAR)) {
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
    public void close()
    {
        System.out.println("Closed  database " );
    }

}
