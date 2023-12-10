/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface AircraftConfigurationSessionBeanRemote {
    
    public Long createNewAircraftConfiguration(AircraftConfiguration newAircraftConfiguration, List<Long> cabinClassConfigurationList, Long aircraftId); 
    
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations(); 
    
    public AircraftConfiguration retrieveAircraftConfigurationById(Long aircraftConfigurationId);
}
