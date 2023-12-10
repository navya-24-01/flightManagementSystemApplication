/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Passenger;
import entity.Reservation;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface FlightReservationSessionBeanRemote {

    public Long createNewReservation(Reservation reservation, Long customerId, List<Passenger> passengerList, Long cabinClassConfigurationId, Long flightScheduleId);

    public List<Reservation> retreiveCustomerReservations(Long id);

     public Reservation retreiveReservationByReservationId(Long Id);
    
}
