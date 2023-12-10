/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;



import entity.Airport;
import entity.FlightRoute;
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
public class FlightRouteSessionBean implements FlightRouteSessionBeanRemote,  FlightRouteSessionBeanLocal {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Long createNewFlightRoute(FlightRoute newFlightRoute, Long originAirportId, Long destinationAirportId) 
    {
        em.persist(newFlightRoute);
        em.flush();
        Long flightRouteId = newFlightRoute.getFlightRouteId();
        this.associateOriginAirportAndFlightRoute(flightRouteId, originAirportId);
        this.associateDestinationAirportAndFlightRoute(flightRouteId, destinationAirportId);
        return flightRouteId;
        
        
     }
    
   
    public List<FlightRoute> retrieveAllFlightRoutes() 
    {
        Query query = em.createQuery("SELECT f FROM FlightRoute f ORDER BY f.origin.airportName ASC");
        return query.getResultList();
    }
    
    public List<FlightRoute> viewAllFlightRoutesInOrder() {
        List<FlightRoute> fr1 = this.retrieveAllFlightRoutes();
        
        for(int j = 0; j  < fr1.size(); j++) {
            FlightRoute f1 = fr1.get(j);
            if(f1.getReturnRoute()) {
                for(int i = 0; i < fr1.size(); i++) {
                    FlightRoute f2 = fr1.get(i);
                    if(f2.getDestination().equals(f1.getOrigin()) && f2.getOrigin().equals(f1.getDestination())) {
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
    
    
   
    public  FlightRoute retrieveFlightRouteById(Long flightRouteId)
    {
        FlightRoute newFlightRoute = em.find(FlightRoute.class,  flightRouteId);
        newFlightRoute.getFlightList().size();
        return newFlightRoute;
        
    }
    
    public void associateOriginAirportAndFlightRoute(Long flightRouteId, Long originAirportId) {
        Airport origin = em.find(Airport.class, originAirportId);
        
        FlightRoute flightRoute  = em.find(FlightRoute.class, flightRouteId);
        
        if (flightRoute.getOrigin()!= null) {
            flightRoute.getOrigin().getOriginFlightRouteList().remove(flightRoute);
        }
        
        flightRoute.setOrigin(origin);
       
        origin.getOriginFlightRouteList().add(flightRoute);
        
    }
   
    public void setReturnRouteTrue(Long flightRouteId) {
        FlightRoute flightRoute  = em.find(FlightRoute.class, flightRouteId);
        flightRoute.setReturnRoute(true);
        em.merge(flightRoute);
    }
    
    public void associateDestinationAirportAndFlightRoute(Long flightRouteId, Long destinationAirportId) {
        Airport destination = em.find(Airport.class, destinationAirportId);
        
        FlightRoute flightRoute  = em.find(FlightRoute.class, flightRouteId);
        
        if (flightRoute.getDestination()!= null) {
            flightRoute.getDestination().getDestinationFlightRouteList().remove(flightRoute);
        }
        
        flightRoute.setDestination(destination);
       
        destination.getDestinationFlightRouteList().add(flightRoute);
        
    }
    
    public int deleteFlightRoute(Long flightRouteId){
        FlightRoute flightRoute = this.retrieveFlightRouteById(flightRouteId);
         //Dissassociate flight and AircraftType
        flightRoute.getOrigin().getOriginFlightRouteList().remove(flightRoute);
        flightRoute.getDestination().getDestinationFlightRouteList().remove(flightRoute);
        
        if(flightRoute.getFlightList().size() == 0) {
            em.remove(flightRoute);
            return 1;
        } else {
            flightRoute.setDisabled(true);
            return 2;
        }
    }
    }
    
    
    
    
    
