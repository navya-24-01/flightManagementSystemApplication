/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;


import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author navyamunjal
 */
@Stateless
public class FlightSessionBean implements FlightSessionBeanRemote, FlightSessionBeanLocal {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Long createNewFlight(Flight newFlight, Long aircraftTypeId, Long flightRouteId, Long aircraftConfigurationId) 
    {
        em.persist(newFlight);
        em.flush();
        Long flightId = newFlight.getFlightId();
        this.associateAircraftConfigurationAndFlight(flightId, aircraftConfigurationId);
        this.associateAircraftTypeAndFlight(flightId, aircraftTypeId);
        this.associateFlightAndFlightRoute(flightId, flightRouteId);
        return flightId;
        
        
     }
    
   
    public void associateAircraftTypeAndFlight(Long flightId, Long aircraftTypeId) {
    AircraftType aircraftType = em.find(AircraftType.class, aircraftTypeId);
    Flight flight = em.find(Flight.class, flightId);
    
    
    if (flight.getAircraftType() != null) {
        flight.getAircraftType().getFlightList().remove(flight);
    }
    
    flight.setAircraftType(aircraftType);
    
    aircraftType.getFlightList().add(flight);
    }
    
    public void associateAircraftConfigurationAndFlight(Long flightId, Long aircraftConfigurationId) {
    AircraftConfiguration aircraftConfiguration = em.find(AircraftConfiguration.class, aircraftConfigurationId);
    Flight flight = em.find(Flight.class, flightId);
    
   
    if (flight.getAircraftConfiguration() != null) {
        flight.getAircraftConfiguration().getFlightList().remove(flight);
    }
    
    flight.setAircraftConfiguration(aircraftConfiguration);
    
    
    
    aircraftConfiguration.getFlightList().add(flight);
    
}
    
    public void associateFlightAndFlightRoute(Long flightId, Long flightRouteId) {
    FlightRoute flightRoute = em.find(FlightRoute.class, flightRouteId);
    Flight flight = em.find(Flight.class, flightId);
    
    if (flight.getFlightRoute() != null) {
        flight.getFlightRoute().getFlightList().remove(flight);
    }
   
    flight.setFlightRoute(flightRoute);
    
    flightRoute.getFlightList().add(flight);
    
    
}
    
    public List<Flight> retreiveAllFlights() {
        Query query = em.createQuery("SELECT f FROM Flight f ORDER BY f.flightNumber ASC");
        return query.getResultList();
     }
     
     
     public Flight retriveFlightbyID(Long flightId) {
        Flight flight = em.find(Flight.class, flightId);
        flight.getAircraftConfiguration().getCabinClassConfigurationList().size();
       return flight;
         
     }
     
     public void setReturnFlightTrue(Long flightId) {
        Flight flight  = em.find(Flight.class, flightId);
        flight.setReturnFlight(true);
        em.merge(flight);
    }
     
     
     
     public int deleteFlight(Long flightId) {
        Flight flight = this.retriveFlightbyID(flightId);
         
        if(flight.getFlightSchedulePlanList() != null) {
            flight.setDisabled(true);
            return 1;
        } else {
             //dissassociate flight with FSP and associated FS,Fare
       /*
         for (FlightSchedulePlan flightSchedulePlan : flight.getFlightSchedulePlanList()) {
        for (FlightSchedule flightSchedule : flightSchedulePlan.getFlightScheduleList()) {
            FlightSchedule.getFlightSchedulePlanList().remove(flightSchedulePlan);
        }
        flightSchedulePlan.getFlightScheduleList().clear();
        
        for (Fare fare : flightSchedulePlan.getFares()) {
            em.remove(fare);
        }
        flightSchedulePlan.getFareList().clear();

        em.remove(flightSchedulePlan);
    }
    
    
        flight.getFlightSchedulePlanList.clear()
    }*/
       
       // Dissassociate flight and flight configuration
        flight.getAircraftConfiguration().getFlightList().remove(flight);
        
        
        
        //Dissassociate flight and AircraftType
        flight.getAircraftType().getFlightList().remove(flight);
        
        flight.getFlightRoute().getFlightList().remove(flight);
        

         
         em.remove(flight);
            
        }
         
       return 2;
     }
     
      public Flight retrieveFlightByFlightNumber(String flightNumber) {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber", Flight.class);
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight) query.getSingleResult();
        flight.getFlightSchedulePlanList().size();
        flight.getAircraftConfiguration().getCabinClassConfigurationList().size();
        List<FlightSchedulePlan> fsps = flight.getFlightSchedulePlanList();
        for(FlightSchedulePlan fsp : fsps) {
            fsp.getFlightScheduleList().size();
            List<FlightSchedule> fss =  fsp.getFlightScheduleList();
            for(FlightSchedule fs : fss) {
                fs.getSeatingInventory().size();
                
            }
            
        }
        
        return flight;
    
   
      }
      
      
      public List<Flight> viewAllFlightRoutesInOrder() {
        List<Flight> fr1 = this.retreiveAllFlights();
        
        for(int j = 0; j < fr1.size(); j++) {
            Flight f3 = new Flight();
            Flight f1 = fr1.get(j);
            if(f1.getReturnFlight()) {
                for(int i = 0; i < fr1.size(); i++) {
                    Flight f2 = fr1.get(i);
                    if(f2.getFlightRoute().getDestination().equals(f1.getFlightRoute().getOrigin()) && f2.getFlightRoute().getOrigin().equals(f1.getFlightRoute().getDestination())) {
                            fr1.remove(f2);
                            if(j + 1 < fr1.size()) {
                                fr1.add(j + 1, f2);
                            } else  {
                                fr1.add(f2);
                            }
                            j = j + 1;
                    }
                        
                }
            }
        }
        
        return fr1;
    }
}
