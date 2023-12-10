/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Employee;
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
public class EmployeeSessionBean implements EmployeeSessionBeanRemote, EmployeeSessionBeanLocal {

    @PersistenceContext(unitName = "flightManagementSystemApplication-ejbPU")
    private EntityManager em;

    public Long createNewEmployee(Employee employee) {
        em.persist(employee);
        em.flush();
        return employee.getEmployeeId();
    }

    
    public Employee doLogin(Long employeeId,String password) { // should throw exception
        
        Employee employee = retreiveEmployeeByEmployeeId(employeeId);
            
            if(employee.getEmployeePassword().equals(password))
            {
                //staffEntity.getSaleTransactionEntities().size();  
                
                System.out.println("Login successful!!");
            }
            
            else {
                System.out.println("Login failed!!");
            }
       
        return employee;
    }
    
    
    public List<Employee> retreiveAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
        
    }
    
  
    
    
    public void doLogout() {
        
    }
    
    
    public Employee retreiveEmployeeByEmployeeId(Long employeeId) {
        
       Employee employee = em.find(Employee.class, employeeId);
       return employee;
        
        
    }
    
    
    
     public void persist(Object object) {
        em.persist(object);
    }
    
  
}
   

   
