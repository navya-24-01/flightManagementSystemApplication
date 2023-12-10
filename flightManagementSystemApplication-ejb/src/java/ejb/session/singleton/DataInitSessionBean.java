/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.singleton;

import ejb.session.stateless.AircraftTypeSessionBeanLocal;
import ejb.session.stateless.AirportSessionBeanLocal;
import ejb.session.stateless.CabinClassConfigurationSessionBeanLocal;
import ejb.session.stateless.EmployeeSessionBeanLocal;
import entity.AircraftType;
import entity.Airport;
import entity.Employee;
import entity.Partner;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author navyamunjal
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;
    
    @EJB
    private EmployeeSessionBeanLocal employeeSessionBean;
    
    @EJB
    private CabinClassConfigurationSessionBeanLocal cabinClassConfigurationSessionBean;
    
    @EJB
    private AircraftTypeSessionBeanLocal aircraftTypeSessionBean;
    
    @EJB
    private AirportSessionBeanLocal airportSessionBean;
    
   
    
    

    
    
    
     @PostConstruct
    public void postConstruct() {
        if (em.find(Employee.class, 1l) == null) {
        employeeSessionBean.createNewEmployee(new Employee("Fleet Manager","password", "Fleet Manager"));
        employeeSessionBean.createNewEmployee(new Employee("Route Planner","password", "Route Planner"));
        employeeSessionBean.createNewEmployee(new Employee("Schedule Manager","password", "Schedule Manager"));
        employeeSessionBean.createNewEmployee(new Employee("Sales Manager","password", "Sales Manager"));
    }
            
        
  
        
       if (em.find(AircraftType.class, 1l) == null) {
        aircraftTypeSessionBean.createNewAircraftType(new AircraftType("Boeing 737", 200));
        aircraftTypeSessionBean.createNewAircraftType(new AircraftType("Boeing 747", 400));
            
        }
       
       if (em.find(Airport.class, 1l) == null) {
        airportSessionBean.createNewAirport(new Airport("Changi", "SIN", "Singapore", "Singapore", "Singapore"));
        airportSessionBean.createNewAirport(new Airport("Hong Kong", "HKG", "Chek Lap Kok", "Hong Kong", "China"));
        airportSessionBean.createNewAirport(new Airport("Taoyuan", "TPE", "Taoyuan", "Taipei", "Taiwan R.O.C."));
        airportSessionBean.createNewAirport(new Airport("Narita", "NRT", "Narita", "Chiba", "Japan"));
        airportSessionBean.createNewAirport(new Airport("Sydney", "SYD", "Sydney", "New South Wales", "Australia"));
        }
       
       if (em.find(Partner.class, 1L) == null) {
            
            em.persist(new Partner("Holiday.com", "holidaydotcom", "password"));
       
    }

    
    }
    
    
    public void persist(Object object) {
        em.persist(object);
    }

    }
