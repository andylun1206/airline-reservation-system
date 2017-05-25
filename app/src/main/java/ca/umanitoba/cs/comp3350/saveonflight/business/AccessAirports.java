package ca.umanitoba.cs.comp3350.saveonflight.business;

import java.util.List;

import ca.umanitoba.cs.comp3350.saveonflight.application.Main;
import ca.umanitoba.cs.comp3350.saveonflight.application.Services;
import ca.umanitoba.cs.comp3350.saveonflight.objects.Airport;
import ca.umanitoba.cs.comp3350.saveonflight.persistence.DataAccessStub;
//get airlines in an airport
public class AccessAirports {
    private DataAccessStub dataAccess;
    private List<Airport> airports;


    AccessAirports(){
        dataAccess = (DataAccessStub) Services.getDataAccess(Main.DB_NAME);
        airports =  null;
    }

    public List<Airport> getAirports(){
        return dataAccess.getAirports();
    }

    public boolean addAirport(Airport a){
        return dataAccess.insertAirport(a);
    }

    public Airport deleteAirport(Airport a){
        Airport deleted = null;
        if(dataAccess.deleteAirport(a))
            deleted = a;
        return deleted;
    }
}
