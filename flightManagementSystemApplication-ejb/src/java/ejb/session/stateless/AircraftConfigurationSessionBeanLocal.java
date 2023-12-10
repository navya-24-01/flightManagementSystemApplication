/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author navyamunjal
 */
@Local
public interface AircraftConfigurationSessionBeanLocal {
    
    public Long createNewAircraftConfiguration(AircraftConfiguration newAircraftConfiguration, List<Long> cabinClassConfigurationList, Long aircraftId); 
    
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations(); 
    
    public AircraftConfiguration retrieveAircraftConfigurationById(Long aircraftConfigurationId);
}
