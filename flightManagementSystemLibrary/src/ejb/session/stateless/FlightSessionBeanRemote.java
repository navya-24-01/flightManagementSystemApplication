/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface FlightSessionBeanRemote {
    public Long createNewFlight(Flight newFlight, Long aircraftTypeId, Long flightRouteId, Long aircraftConfigurationId);
    
    public int deleteFlight(Long flightId);

    public List<Flight> retreiveAllFlights();

    public Flight retriveFlightbyID(Long flightId);

    public Flight retrieveFlightByFlightNumber(String flightNumber);

    public void setReturnFlightTrue(Long flightId);

    public List<Flight> viewAllFlightRoutesInOrder();
}
