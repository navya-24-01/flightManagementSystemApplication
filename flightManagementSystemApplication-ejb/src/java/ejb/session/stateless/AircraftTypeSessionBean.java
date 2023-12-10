/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AircraftType;
import entity.CabinClassConfiguration;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aman
 */
@Stateless
public class AircraftTypeSessionBean implements AircraftTypeSessionBeanRemote, AircraftTypeSessionBeanLocal {
    
    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Long createNewAircraftType(AircraftType aircraftType) 
    {
        em.persist(aircraftType);
        em.flush();
        return aircraftType.getAircraftTypeId();
     }
    
    //@Override
    @Override
    public List<AircraftType> retrieveAllAircraftTypes() 
    {
        Query query = em.createQuery("SELECT a FROM AircraftType a");
        return query.getResultList();
    }
    
    
    //@Override
    @Override
    public AircraftType retrieveAircraftTypeById(Long AircraftTypeId)
    {
        AircraftType newAircrafttype = em.find(AircraftType.class, AircraftTypeId);
        return newAircrafttype;
        
    }


    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}