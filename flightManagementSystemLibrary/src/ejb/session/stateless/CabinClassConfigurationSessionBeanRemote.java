/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClassConfiguration;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface CabinClassConfigurationSessionBeanRemote {
    public Long createNewCabinClassConfiguration(CabinClassConfiguration newCabinClassConfiguration);
    
    public List<CabinClassConfiguration> retrieveAllCabinClassConfigurations();
    
    public CabinClassConfiguration retrieveCabinClassConfigurationById(Long cabinClassConfigurationId);
}
