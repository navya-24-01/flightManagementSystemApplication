/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftConfiguration;
import entity.AircraftType;
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
public class AircraftConfigurationSessionBean implements AircraftConfigurationSessionBeanRemote, AircraftConfigurationSessionBeanLocal {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Long createNewAircraftConfiguration(AircraftConfiguration newAircraftConfiguration, List<Long> cabinClassConfigurationList, Long aircraftId) 
    {
        em.persist(newAircraftConfiguration);
        em.flush();
        Long aircraftConfigurationId =  newAircraftConfiguration.getAircraftConfigurationId();
        
        for(Long cId : cabinClassConfigurationList) {
            this.associateCabinClassConfigurationAndAircraftConfiguration(cId, aircraftConfigurationId);
        }
        
        this.associateAicraftTypeAndAirCraftConfiguration(aircraftId, aircraftConfigurationId);
        
        return aircraftConfigurationId;
        
        
     }
    
    //@Override
    public List<AircraftConfiguration> retrieveAllAircraftConfigurations() 
    {
        Query query = em.createQuery("SELECT a FROM AircraftConfiguration a");
        return query.getResultList();
    }
    
    
    //@Override
    public AircraftConfiguration retrieveAircraftConfigurationById(Long aircraftConfigurationId)
    {
        AircraftConfiguration newAircraftConfiguration = em.find(AircraftConfiguration.class, aircraftConfigurationId);
        newAircraftConfiguration.getCabinClassConfigurationList().size();
        return newAircraftConfiguration;
        
    }
    
    public void associateCabinClassConfigurationAndAircraftConfiguration(Long CCCId, Long aircraftConfigurationId)
    {
        CabinClassConfiguration b = em.find(CabinClassConfiguration.class, CCCId);
        AircraftConfiguration c = em.find(AircraftConfiguration.class, aircraftConfigurationId);
        
        if(!b.getAircraftConfigurationList().contains(c))
        {
            b.getAircraftConfigurationList().add(c);
        }
        
        if(!c.getCabinClassConfigurationList().contains(b))
        {
            c.getCabinClassConfigurationList().add(b);
        }
   
}
    
    public void associateAicraftTypeAndAirCraftConfiguration(Long aircraftTypeId, Long aircraftConfigurationId) {
        AircraftType aircraftType  = em.find(AircraftType.class, aircraftTypeId);
        
        AircraftConfiguration aircraftConfiguration  = em.find(AircraftConfiguration.class, aircraftConfigurationId);
        
        if (aircraftConfiguration.getAircraftType()!= null) {
            aircraftConfiguration.getAircraftType().getAircraftConfigurationList().remove(aircraftConfiguration);
        }
        
        aircraftConfiguration.setAircraftType(aircraftType);
        
        aircraftType.getAircraftConfigurationList().add(aircraftConfiguration);
        
        
    }
}
