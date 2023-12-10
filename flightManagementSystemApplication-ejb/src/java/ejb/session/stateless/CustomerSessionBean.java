/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Partner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aman
 */
@Stateless
public class CustomerSessionBean implements CustomerSessionBeanRemote, CustomerSessionBeanLocal {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

   

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    public Long createNewCustomer(Customer customer) {
        em.persist(customer);
        em.flush();
        return customer.getCustomerId();
    }
    
    public Customer retreiveCustomerByCustomerId(Long customerId) {
        
       Customer customer = em.find(Customer.class, customerId);
       return customer;
        
        
    }
    
    
    
    
    public Customer doLogin(Long customerId,String password) { // should throw exception
        
        Customer customer = retreiveCustomerByCustomerId(customerId);
            
            if(customer.getPassword().equals(password))
            {
                //staffEntity.getSaleTransactionEntities().size();  
                
                System.out.println("Login successful!!");
                customer.setIsloggedIn(true);
            }
            
            else {
                System.out.println("Login failed!!");
            }
       
        return customer;
    }
    
    
    public Partner doLoginForPartner(Boolean detach, Long Id,String password) { 
        
        Partner partner = em.find(Partner.class, Id);
            
            if(partner.getPassword().equals(password))
            {
                //staffEntity.getSaleTransactionEntities().size();  
                
                System.out.println("Login successful!!");
  
            }
            
            else {
                System.out.println("Login failed!!");
            }
       
        return partner;
    }
    
    
    public void doLogout(Customer customer) {
        customer.setIsloggedIn(false);
        
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    
}