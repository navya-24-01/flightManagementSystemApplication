/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;


import entity.Airport;
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
public class AirportSessionBean implements AirportSessionBeanRemote, AirportSessionBeanLocal {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Long createNewAirport(Airport newAirport) 
    {
        em.persist(newAirport);
        em.flush();
        return newAirport.getAirportId();
     }
    
   
    public List<Airport> retrieveAllAirports() 
    {
        Query query = em.createQuery("SELECT a FROM Airport a");
        return query.getResultList();
    }
    
    
   
    public Airport retrieveAirportById(Long airportId)
    {
        Airport newAirport = em.find(Airport.class, airportId);
        return newAirport;
        
    }
    
}
