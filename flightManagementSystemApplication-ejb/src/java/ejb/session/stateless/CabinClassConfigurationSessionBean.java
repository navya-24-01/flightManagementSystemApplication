/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;


import entity.CabinClassConfiguration;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author navyamunjal
 */
@Stateless
public class CabinClassConfigurationSessionBean implements CabinClassConfigurationSessionBeanRemote, CabinClassConfigurationSessionBeanLocal {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Long createNewCabinClassConfiguration(CabinClassConfiguration newCabinClassConfiguration) 
    {
        em.persist(newCabinClassConfiguration);
        em.flush();
        return newCabinClassConfiguration.getCCCId();
     }
    
    //@Override
    public List<CabinClassConfiguration> retrieveAllCabinClassConfigurations() 
    {
        Query query = em.createQuery("SELECT c FROM CabinClassConfiguration c");
        return query.getResultList();
    }
    
    
    //@Override
    public CabinClassConfiguration retrieveCabinClassConfigurationById(Long cabinClassConfigurationId)
    {
        CabinClassConfiguration newAircraftConfiguration = em.find(CabinClassConfiguration.class, cabinClassConfigurationId);
        return newAircraftConfiguration;
        
    }
   
}
