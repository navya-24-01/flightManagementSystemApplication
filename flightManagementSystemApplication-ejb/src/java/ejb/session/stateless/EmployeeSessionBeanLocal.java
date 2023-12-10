/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Employee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author navyamunjal
 */
@Local
public interface EmployeeSessionBeanLocal {
    
    public Employee doLogin(Long employeeId,String password);
    
    public List<Employee> retreiveAllEmployees();
    
    public void doLogout();
    
    public Employee retreiveEmployeeByEmployeeId(Long employeeId);
    
    public Long createNewEmployee(Employee employee);
    

}
