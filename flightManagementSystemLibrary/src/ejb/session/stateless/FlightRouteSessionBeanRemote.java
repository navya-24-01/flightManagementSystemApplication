/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.FlightRoute;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface FlightRouteSessionBeanRemote {
    public Long createNewFlightRoute(FlightRoute newFlightRoute, Long originAirportId, Long destinationAirportId);
    public int deleteFlightRoute(Long flightRouteId);

    public List<FlightRoute> viewAllFlightRoutesInOrder();

    public void setReturnRouteTrue(Long flightRouteId);

    public List<FlightRoute> retrieveAllFlightRoutes();

    public FlightRoute retrieveFlightRouteById(Long flightRouteId);
}
