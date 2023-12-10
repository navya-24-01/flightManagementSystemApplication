/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Employee;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface EmployeeSessionBeanRemote {
    
    public Employee doLogin(Long employeeId,String password);
    
    public List<Employee> retreiveAllEmployees();
    
    public void doLogout();
    
    public Employee retreiveEmployeeByEmployeeId(Long employeeId);
    
    public Long createNewEmployee(Employee employee);
     
}
