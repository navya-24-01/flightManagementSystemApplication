/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftType;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface AircraftTypeSessionBeanRemote {
    public Long createNewAircraftType(AircraftType aircraftType);
   public List<AircraftType> retrieveAllAircraftTypes();
    public AircraftType retrieveAircraftTypeById(Long AircraftTypeId);
    
}
