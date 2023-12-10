/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Airport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author navyamunjal
 */
@Local
public interface AirportSessionBeanLocal {
    
    public Long createNewAirport(Airport newAirport);
  
    public List<Airport> retrieveAllAirports();
    
    public Airport retrieveAirportById(Long airportId);
    
    
}
