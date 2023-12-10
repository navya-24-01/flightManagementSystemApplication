/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClassConfiguration;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author navyamunjal
 */
@Local
public interface CabinClassConfigurationSessionBeanLocal {
    
    public Long createNewCabinClassConfiguration(CabinClassConfiguration newCabinClassConfiguration);
    
    public List<CabinClassConfiguration> retrieveAllCabinClassConfigurations();
    
    public CabinClassConfiguration retrieveCabinClassConfigurationById(Long cabinClassConfigurationId);
    
}
