/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;


import entity.CabinClassConfiguration;
import entity.Fare;
import entity.Flight;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.Reservation;
import entity.SeatingInventory;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author navyamunjal
 */
@Stateless
public class FlightScheduleSessionBean implements FlightScheduleSessionBeanRemote, FlightScheduleSessionBeanLocal {
    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
     public FlightSchedulePlan retrieveFlightRouteById(Long flightSchedulePlanId)
    {
        FlightSchedulePlan newFlightRoute = em.find( FlightSchedulePlan.class,  flightSchedulePlanId);
        return newFlightRoute;
        
    }
     
     public FlightSchedule retrieveFlightScheduleById(Long flightSchedulePlanId)
    {
        FlightSchedule newFlightRoute = em.find( FlightSchedule.class,  flightSchedulePlanId);
        //newFlightRoute.getReservationList().size();
        
        newFlightRoute.getSeatingInventory().size();
        newFlightRoute.getFlightSchedulePlan().getFareList().size();
        List<Reservation> r = newFlightRoute.getReservationList();
        for(Reservation r1 : r) {
            r1.getPassengerList().size();
        }
        
        List<CabinClassConfiguration> cc = newFlightRoute.getFlightSchedulePlan().getFlight().getAircraftConfiguration().getCabinClassConfigurationList();
        cc.size();
        return newFlightRoute;
        
    }
    
    public Long createNewFlightSchedule(FlightSchedule flightSchedule) 
    {
        em.persist(flightSchedule);
        em.flush();
        return flightSchedule.getFlightScheduleId();
     }
    
    public Long createNewFlightSchedulePlanSingle(Long flightId, LocalDateTime departureTime, LocalDateTime arrivalTime, Duration duration, FlightSchedulePlan newFlightSchedulePlan) {
        em.persist(newFlightSchedulePlan);
        em.flush();
        Long flightSchedulePlanId = newFlightSchedulePlan.getFlightSchedulePlanId();
        //Flight flight = em.find(Flight.class, flightId);
        FlightSchedule flightSchedule = new FlightSchedule(departureTime, duration, arrivalTime);
        Long flightScheduleId = this.createNewFlightSchedule(flightSchedule);
        this.associateFlightAndFlightSchedulePlan(flightId, flightSchedulePlanId);
        this.associateFlightScheduleAndFlightSchedulePlan(flightScheduleId, flightSchedulePlanId);
        return flightSchedulePlanId;
    }
    
    public Long createRecurringFlightSchedulePlan(Long flightId, LocalDateTime departureTime, LocalDateTime arrivalTime, Duration duration, LocalDateTime endDate, int n, FlightSchedulePlan newFlightSchedulePlan) {
    
    em.persist(newFlightSchedulePlan);
    em.flush();
    
    Long flightSchedulePlanId = newFlightSchedulePlan.getFlightSchedulePlanId();
    LocalDateTime nextDepartureTime = departureTime;
    LocalDateTime nextArrivalTime = arrivalTime;

    
    while (!nextDepartureTime.isAfter(endDate)) {
       
        FlightSchedule flightSchedule = new FlightSchedule(nextDepartureTime, duration, nextArrivalTime);
        System.out.println("*********** HERE create");
        
        Long flightScheduleId = this.createNewFlightSchedule(flightSchedule); 

       
        this.associateFlightScheduleAndFlightSchedulePlan(flightScheduleId, flightSchedulePlanId);

        
        nextDepartureTime = nextDepartureTime.plusDays(n);
        nextArrivalTime = nextArrivalTime.plusDays(n);
        
        System.out.println("nextDepartureDate: " + nextDepartureTime);
        System.out.println("endDate: " + endDate);
       
    }
    
   
    this.associateFlightAndFlightSchedulePlan(flightId, flightSchedulePlanId);
    
    return flightSchedulePlanId;
}
    
    public Long createFlightSchedulePlanMultiple(Long flightId, List<LocalDateTime> departureTime, List<LocalDateTime> arrivalTime, List<Duration> duration, int n, FlightSchedulePlan newFlightSchedulePlan) {
    
    em.persist(newFlightSchedulePlan);
    em.flush();
    
    Long flightSchedulePlanId = newFlightSchedulePlan.getFlightSchedulePlanId();
    
    for(int i = 0; i < n; i++) {
        LocalDateTime nextDepartureTime = departureTime.get(i);
        LocalDateTime nextArrivalTime = arrivalTime.get(i);
        
        FlightSchedule flightSchedule = new FlightSchedule(nextDepartureTime, duration.get(i), nextArrivalTime);
        Long flightScheduleId = this.createNewFlightSchedule(flightSchedule); 
        
        this.associateFlightScheduleAndFlightSchedulePlan(flightScheduleId, flightSchedulePlanId);
         
        
    }
   
    this.associateFlightAndFlightSchedulePlan(flightId, flightSchedulePlanId);
    
    return flightSchedulePlanId;
}

    public void associateFlightScheduleAndFlightSchedulePlan(Long flightScheduleId, Long flightSchedulePlanId) {
    FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
    FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleId);
    
    
    flightSchedulePlan.getFlightScheduleList().add(flightSchedule);
    flightSchedule.setFlightSchedulePlan(flightSchedulePlan);
    
            
}
  
    
    public void associateFlightAndFlightSchedulePlan(Long flightId, Long flightSchedulePlanId) {
        Flight flight = em.find(Flight.class, flightId);
        
        FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
        
        
         if (flightSchedulePlan.getFlight()!= null) {
        
            flightSchedulePlan.getFlight().getFlightSchedulePlanList().remove(flightSchedulePlan);
         }
            
            flightSchedulePlan.setFlight(flight);
        
        
       
        flight.getFlightSchedulePlanList().add(flightSchedulePlan);
        
        
    }
    
    
    
    
    
    public void createNewFareForFlightSchedulePlan(Fare newFare, Long flightSchedulePlanId, Long cabinClassConfigurationId) {
       
        em.persist(newFare);
        em.flush();
        CabinClassConfiguration cabinClassConfiguration = em.find(CabinClassConfiguration.class, cabinClassConfigurationId);
        newFare.setCabinClassConfiguration(cabinClassConfiguration);
        FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
        
        flightSchedulePlan.getFareList().add(newFare);
        
        
        
    }
    
    public void createNewInventoryForFlightSchedule(SeatingInventory seatingInventory, Long flightScheduleId, Long cabinClassConfigurationId) {
       
        em.persist(seatingInventory);
        em.flush();
        CabinClassConfiguration cabinClassConfiguration = em.find(CabinClassConfiguration.class, cabinClassConfigurationId);
        seatingInventory.setCabinClassConfiguration(cabinClassConfiguration);
        FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleId);
        
        flightSchedule.getSeatingInventory().add(seatingInventory);
        
        
        
    }
    
     public FlightSchedulePlan retriveFlightSchedulePlanByID(Long flightSchedulePlanId) {
        FlightSchedulePlan fsp = em.find(FlightSchedulePlan.class, flightSchedulePlanId);
        fsp.getFlightScheduleList().size();
        fsp.getFlight().getAircraftConfiguration().getCabinClassConfigurationList().size();
        fsp.getFareList().size();
        return fsp;
         
     }
     
     public List<FlightSchedule> searchFlightNDaysBeforeOneWay(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate) {
    String jpql = "SELECT fs FROM FlightSchedule fs WHERE fs.flightSchedulePlan.flight.flightRoute.origin.airportId = :originAirportId AND fs.flightSchedulePlan.flight.flightRoute.destination.airportId = :destinationAirportId AND fs.departureDateTime BETWEEN :startDate AND :endDate ORDER BY fs.departureDateTime ASC";

    LocalDateTime startDate = departureDate.minusDays(n).atStartOfDay(); // Convert to LocalDateTime at start of day
    LocalDateTime endDate = departureDate.atTime(23, 59, 59); // Convert to LocalDateTime at end of day

    TypedQuery<FlightSchedule> query = em.createQuery(jpql, FlightSchedule.class);
    query.setParameter("originAirportId", originAirportId);
    query.setParameter("destinationAirportId", destinationAirportId);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);

  List<FlightSchedule> allFl =  query.getResultList();
    
     /*Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.flightSchedulePlan.flight.flightRoute.origin.airportId = originAirportId AND f.flightSchedulePlan.flight.flightRoute.destination.airportId = destinationAirportId ");
     List<FlightSchedule> allFl = query.getResultList();*/
    for(FlightSchedule a : allFl) {
       a.getSeatingInventory().size();
       FlightSchedulePlan p = a.getFlightSchedulePlan();
       p.getFareList().size();
    }
    
    return allFl;
     }
     
     

     public List<FlightSchedule> searchFlightNDaysAfterOneWay(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate) {
    String jpql = "SELECT fs FROM FlightSchedule fs WHERE fs.flightSchedulePlan.flight.flightRoute.origin.airportId = :originAirportId AND fs.flightSchedulePlan.flight.flightRoute.destination.airportId = :destinationAirportId AND fs.departureDateTime BETWEEN :startDate AND :endDate ORDER BY fs.departureDateTime ASC";

    LocalDateTime startDate = departureDate.atStartOfDay(); // Convert to LocalDateTime at start of day
    LocalDateTime endDate = departureDate.plusDays(n).atTime(23, 59, 59); // Convert to LocalDateTime at end of day

    TypedQuery<FlightSchedule> query = em.createQuery(jpql, FlightSchedule.class);
    query.setParameter("originAirportId", originAirportId);
    query.setParameter("destinationAirportId", destinationAirportId);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);

    List<FlightSchedule> allFl =  query.getResultList();
    
     /*Query query = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.flightSchedulePlan.flight.flightRoute.origin.airportId = originAirportId AND f.flightSchedulePlan.flight.flightRoute.destination.airportId = destinationAirportId ");
     List<FlightSchedule> allFl = query.getResultList();*/
     for(FlightSchedule a : allFl) {
       a.getSeatingInventory().size();
       FlightSchedulePlan p = a.getFlightSchedulePlan();
       p.getFareList().size();
    }
    
    return allFl;
     }
     
     public List<List<FlightSchedule>> searchConnectingFlightsNDaysAfter(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate) {
         String jpql = "SELECT fs FROM FlightSchedule fs WHERE fs.flightSchedulePlan.flight.flightRoute.origin.airportId = :originAirportId AND fs.departureDateTime BETWEEN :startDate AND :endDate ORDER BY fs.departureDateTime ASC";
          LocalDateTime startDate = departureDate.atStartOfDay(); // Convert to LocalDateTime at start of day
          LocalDateTime endDate = departureDate.plusDays(n).atTime(23, 59, 59); // Convert to LocalDateTime at end of day

          TypedQuery<FlightSchedule> query = em.createQuery(jpql, FlightSchedule.class);
          query.setParameter("originAirportId", originAirportId);
          query.setParameter("startDate", startDate);
          query.setParameter("endDate", endDate);
          
          List<FlightSchedule> allFl =  query.getResultList();
          List<List<FlightSchedule>> allConnectingFlightSchedules = new ArrayList<List<FlightSchedule>>();
          
          
          for(FlightSchedule a : allFl) {
              String jpql2 = "SELECT fs FROM FlightSchedule fs WHERE fs.flightSchedulePlan.flight.flightRoute.origin.airportId = :originAirportId AND fs.flightSchedulePlan.flight.flightRoute.destination.airportId = :destinationAirportId AND fs.departureDateTime BETWEEN :startDate AND :endDate ORDER BY fs.departureDateTime ASC";
              TypedQuery<FlightSchedule> query2 = em.createQuery(jpql2, FlightSchedule.class);
               query2.setParameter("originAirportId", a.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId());
               query2.setParameter("destinationAirportId", destinationAirportId);
               query2.setParameter("startDate", a.getArrivalDateTime());
               query2.setParameter("endDate", endDate);
               List<FlightSchedule> paths = query2.getResultList();
               for(FlightSchedule b : paths) {
                   List<FlightSchedule> connectingFlightSchedule = new ArrayList<FlightSchedule>();
                   connectingFlightSchedule.add(a);
                   connectingFlightSchedule.add(b);
                   allConnectingFlightSchedules.add(connectingFlightSchedule);
               }
          
              
          }
          
          return allConnectingFlightSchedules;
          
          
    
          
     }
     
        public List<List<FlightSchedule>> searchConnectingFlightsNDaysBefore(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate) {
         String jpql = "SELECT fs FROM FlightSchedule fs WHERE fs.flightSchedulePlan.flight.flightRoute.origin.airportId = :originAirportId AND fs.departureDateTime BETWEEN :startDate AND :endDate ORDER BY fs.departureDateTime ASC";
          LocalDateTime startDate = departureDate.minusDays(n).atStartOfDay(); // Convert to LocalDateTime at start of day
          LocalDateTime endDate = departureDate.atTime(23, 59, 59); // Convert to LocalDateTime at end of day

          TypedQuery<FlightSchedule> query = em.createQuery(jpql, FlightSchedule.class);
          query.setParameter("originAirportId", originAirportId);
          query.setParameter("startDate", startDate);
          query.setParameter("endDate", endDate);
          
          List<FlightSchedule> allFl =  query.getResultList();
          List<List<FlightSchedule>> allConnectingFlightSchedules = new ArrayList<List<FlightSchedule>>();
          
          
          for(FlightSchedule a : allFl) {
              String jpql2 = "SELECT fs FROM FlightSchedule fs WHERE fs.flightSchedulePlan.flight.flightRoute.origin.airportId = :originAirportId AND fs.flightSchedulePlan.flight.flightRoute.destination.airportId = :destinationAirportId AND fs.departureDateTime BETWEEN :startDate AND :endDate ORDER BY fs.departureDateTime ASC";
              TypedQuery<FlightSchedule> query2 = em.createQuery(jpql2, FlightSchedule.class);
               query2.setParameter("originAirportId", a.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportId());
               query2.setParameter("destinationAirportId", destinationAirportId);
               query2.setParameter("startDate", a.getArrivalDateTime());
               query2.setParameter("endDate", endDate);
               List<FlightSchedule> paths = query2.getResultList();
               for(FlightSchedule b : paths) {
                   List<FlightSchedule> connectingFlightSchedule = new ArrayList<FlightSchedule>();
                   connectingFlightSchedule.add(a);
                   connectingFlightSchedule.add(b);
                   allConnectingFlightSchedules.add(connectingFlightSchedule);
               }
          
              
          }
          
          return allConnectingFlightSchedules;
          
          
    
          
     }
     
     
   public List<FlightSchedule> searchFlightNDaysBeforeReturn(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate,LocalDate returnDate) {
        // Query for the outbound flights
    String jpqlOutbound = "SELECT fs FROM FlightSchedule fs WHERE fs.flightSchedulePlan.flight.flightRoute.origin.airportId = :originAirportId AND fs.flightSchedulePlan.flight.flightRoute.destination.airportId = :destinationAirportId AND fs.departureDateTime BETWEEN :startDate AND :endDate ORDER BY fs.departureDateTime ASC";

    // Query for the return flights
    String jpqlReturn = "SELECT fs FROM FlightSchedule fs WHERE fs.flightSchedulePlan.flight.flightRoute.origin.airportId = :destinationAirportId AND fs.flightSchedulePlan.flight.flightRoute.destination.airportId = :originAirportId AND fs.departureDateTime BETWEEN :returnStartDate AND :returnEndDate ORDER BY fs.departureDateTime ASC";

    LocalDateTime startDate = departureDate.minusDays(n).atStartOfDay();
    LocalDateTime endDate = departureDate.atTime(23, 59, 59);

    LocalDateTime returnStartDate = returnDate.minusDays(n).atStartOfDay();
    LocalDateTime returnEndDate = returnDate.atTime(23, 59, 59);

    TypedQuery<FlightSchedule> queryOutbound = em.createQuery(jpqlOutbound, FlightSchedule.class);
    queryOutbound.setParameter("originAirportId", originAirportId);
    queryOutbound.setParameter("destinationAirportId", destinationAirportId);
    queryOutbound.setParameter("startDate", startDate);
    queryOutbound.setParameter("endDate", endDate);

    TypedQuery<FlightSchedule> queryReturn = em.createQuery(jpqlReturn, FlightSchedule.class);
    queryReturn.setParameter("originAirportId", originAirportId);
    queryReturn.setParameter("destinationAirportId", destinationAirportId);
    queryReturn.setParameter("returnStartDate", returnStartDate);
    queryReturn.setParameter("returnEndDate", returnEndDate);

    List<FlightSchedule> outboundFlights = queryOutbound.getResultList();
    List<FlightSchedule> returnFlights = queryReturn.getResultList();

    // Combine both lists or process them as required
    outboundFlights.addAll(returnFlights);

    return outboundFlights;
   }
   
   /*void deleteFlightSchedulePlan(Long flightSchedulePlanId) {
    FlightSchedulePlan flightSchedulePlan = em.find(FlightSchedulePlan.class, flightSchedulePlanId );

    // Dissociate FlightSchedulePlan and Flight
    for (Flight flight : flightSchedulePlan.getFlightList()) {
        flight.setFlightSchedulePlan(null);
    }
    flightSchedulePlan.getFlightList().clear();

    // Dissociate FlightSchedulePlan and FlightSchedule
    for (FlightSchedule flightSchedule : flightSchedulePlan.getFlightScheduleList()) {
        flightSchedule.setFlightSchedulePlan(null);
    }
    flightSchedulePlan.getFlightScheduleList().clear();

    // Remove associated Fares
    for (Fare fare : flightSchedulePlan.getFares()) {
        em.remove(fare);
    }
    flightSchedulePlan.getFares().clear();

    // Remove FlightSchedulePlan
    em.remove(flightSchedulePlan);
}*/       
   
   public List<List<FlightSchedule>> searchFlightsWithOneStopover(Long originAirportId, Long destinationAirportId, LocalDate departureDate) {
    String jpql = "SELECT fs1, fs2 FROM FlightSchedule fs1, FlightSchedule fs2 " +
                  "WHERE fs1.flightSchedulePlan.flight.flightRoute.origin.airportId = :originAirportId " +
                  "AND fs1.flightSchedulePlan.flight.flightRoute.destination.airportId != :destinationAirportId " +
                  "AND fs2.flightSchedulePlan.flight.flightRoute.origin.airportId = fs1.flightSchedulePlan.flight.flightRoute.destination.airportId " +
                  "AND fs2.flightSchedulePlan.flight.flightRoute.destination.airportId = :destinationAirportId " +
                  "AND fs1.departureDateTime <= :startDate " +
                  "AND fs2.arrivalDateTime >= :endDate " +
                  "AND fs1.arrivalDateTime < fs2.departureDateTime";

    LocalDateTime startDate = departureDate.atStartOfDay();
    LocalDateTime endDate = departureDate.atTime(23, 59, 59);

    TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
    query.setParameter("originAirportId", originAirportId);
    query.setParameter("destinationAirportId", destinationAirportId);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);

    List<Object[]> results = query.getResultList();
    List<List<FlightSchedule>> flightsWithOneStopover = new ArrayList<>();

    for (Object[] result : results) {
        flightsWithOneStopover.add(Arrays.asList((FlightSchedule) result[0], (FlightSchedule) result[1]));
    }

    return flightsWithOneStopover;
}
   
   public List<FlightSchedulePlan> viewAllFlightSchedulePlansInOrder() {
        List<FlightSchedulePlan> fr1 = this.retrieveAllFlightSchedulePlans();
        
        for(int j = 0; j  < fr1.size(); j++) {
            FlightSchedulePlan f1 = fr1.get(j);
            if(f1.isReturnFsp()) {
                for(int i = 0; i < fr1.size(); i++) {
                    FlightSchedulePlan f2 = fr1.get(i);
                    if(f2.getFlight().getFlightRoute().getDestination().equals(f1.getFlight().getFlightRoute().getOrigin()) && f2.getFlight().getFlightRoute().getOrigin().equals(f1.getFlight().getFlightRoute().getDestination())) {
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
   
   public List<FlightSchedulePlan> retrieveAllFlightSchedulePlans() 
    {
        Query query = em.createQuery("SELECT f FROM FlightSchedulePlan f ORDER BY f.flight.flightNumber ASC, f.firstDeparture DESC ");
        return query.getResultList();
    }
   
   
   public CabinClassConfiguration retrieveCabinClassConfigurationById(Long cabinClassConfigurationId)
    {
        CabinClassConfiguration newAircraftConfiguration = em.find(CabinClassConfiguration.class, cabinClassConfigurationId);
        return newAircraftConfiguration;
        
    }
   
}
   

    
