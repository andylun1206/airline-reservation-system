package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import ca.umanitoba.cs.comp3350.saveonflight.objects.Airline;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Flight;

public class SortFlights {

    public static enum SortParameter {
        DATE, AIRLINE, PRICE, CAPACITY, SEATS_AVAILABLE
    }

    public ArrayList<Flight> sortFlightsBy(ArrayList<Flight> flights, SortParameter sortBy) {
        ArrayList<Flight> result = new ArrayList<>();
        result.addAll(flights);
        Comparator<Flight> comparator = null;

        switch (sortBy) {
            case DATE:
                comparator = new DateComparator();
                break;
            case AIRLINE:
                comparator = new AirlineComparator();
                break;
            case PRICE:
                comparator = new PriceComparator();
                break;
            case CAPACITY:
                comparator = new CapacityComparator();
                break;
            case SEATS_AVAILABLE:

                break;
        }

        result.sort(comparator);
        return result;
    }

    private class DateComparator implements Comparator<Flight> {
        @Override
        public int compare(Flight f1, Flight f2) {
            return f1.getDate().compareTo(f2.getDate());
        }
    }

    private class AirlineComparator implements Comparator<Flight> {
        @Override
        public int compare(Flight f1, Flight f2) {
            return f1.getAirline().compareTo(f2.getAirline());
        }
    }

    private class PriceComparator implements Comparator<Flight> {
        @Override
        public int compare(Flight f1, Flight f2) {
            int res = 0;

            double diff = f1.getPrice() - f2.getPrice();
            if (diff > 0) {
                res = (int) Math.ceil(diff);
            }
            else if (diff < 0) {
                res = (int) Math.floor(diff);
            }

            return res;
        }
    }

    private class CapacityComparator implements Comparator<Flight> {
        @Override
        public int compare(Flight f1, Flight f2) {
            return f1.getCapacity() - f2.getCapacity();
        }
    }

    private class SeatsAvailableComparator implements Comparator<Flight> {
        @Override
        public int compare(Flight f1, Flight f2) {
            return 0;
        }
    }
}
