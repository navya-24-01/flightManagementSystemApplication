/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.AircraftType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aman
 */
@Local
public interface AircraftTypeSessionBeanLocal {
    
   public Long createNewAircraftType(AircraftType aircraftType);
   public List<AircraftType> retrieveAllAircraftTypes();
    public AircraftType retrieveAircraftTypeById(Long AircraftTypeId);
    
    
}