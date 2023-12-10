/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClassConfiguration;
import entity.Customer;
import entity.FlightSchedule;
import entity.Partner;
import entity.Passenger;
import entity.Reservation;
import entity.SeatingInventory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author navyamunjal
 */
@Stateless
public class FlightReservationSessionBean implements FlightReservationSessionBeanRemote, FlightReservationSessionBeanLocal {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Long createNewReservation(Reservation reservation, Long customerId, List<Passenger> passengerList, Long cabinClassConfigurationId,Long flightScheduleId) 
    {
        em.persist(reservation);
        em.flush();
        Long reservationId = reservation.getReservationId();
        FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleId);
        this.associateCustomerAndReservation(customerId, reservationId);
        this.associateFlightScheduleAndReservation(flightScheduleId, reservationId);
        this.associateReservationAndCabinClassConfiguration(cabinClassConfigurationId, reservationId);
        for(Passenger passenger : passengerList) {
            em.persist(passenger);
            em.flush();
            this.associateReservationAndPassenger(passenger, reservationId);
        }
        
        List<SeatingInventory> si = flightSchedule.getSeatingInventory();
        for(SeatingInventory s : si ) {
            System.out.println(" here ");
            if(s.getCabinClassConfiguration().getCCCId().equals(cabinClassConfigurationId)) {
                System.out.println(" in");
                s.setOccupiedSeats(s.getOccupiedSeats() + passengerList.size());
                em.merge(s);
            }
        }
        return reservationId;
        
        
     }
    
    public void associateCustomerAndReservation(Long customerId, Long reservationId) {
    Customer customer = em.find(Customer.class, customerId);
    Reservation reservation = em.find(Reservation.class, reservationId);
    
    reservation.setCustomer(customer);
    customer.getReservationList().add(reservation);
    }
    
    public void associateFlightScheduleAndReservation(Long flightScheduleId, Long reservationId) {
    FlightSchedule flightSchedule = em.find(FlightSchedule.class, flightScheduleId);
    Reservation reservation = em.find(Reservation.class, reservationId);
    
    
    
    reservation.setFlightSchedule(flightSchedule);
    flightSchedule.getReservationList().add(reservation);
    }
    
    public void associateReservationAndCabinClassConfiguration(Long cabinClassConfigurationId, Long reservationId) {
    CabinClassConfiguration cabinClassConfiguration = em.find(CabinClassConfiguration.class, cabinClassConfigurationId);
    Reservation reservation = em.find(Reservation.class, reservationId);
    
    reservation.setCabinClassConfiguration(cabinClassConfiguration);
    }
    
    public void associateReservationAndPassenger(Passenger passenger, Long reservationId) {
    Reservation reservation = em.find(Reservation.class, reservationId);
    reservation.getPassengerList().add(passenger);
    }
    
    public List<Reservation> retreiveCustomerReservations(Long id) {
        Customer customer = em.find(Customer.class, id);
        customer.getReservationList().size();
        for (Reservation r : customer.getReservationList()) {
           r.getPassengerList().size();
        }
        return customer.getReservationList();
       
    }
    
    public Reservation retreiveReservationByReservationId(Long Id) {
        
       Reservation r = em.find(Reservation.class, Id);
       r.getPassengerList().size();
       r.getCustomer().getReservationList().size();
       return r;
        
        
    }
    
    public List<Reservation> retreivePartnerReservations(Boolean variable, Long id) {
        Partner partner = em.find(Partner.class, id);
        partner.getReservationList().size();
        for (Reservation r : partner.getReservationList()) {
           r.getPassengerList().size();
        }
        return partner.getReservationList();
       
    }
    
    
    
    
    
}
