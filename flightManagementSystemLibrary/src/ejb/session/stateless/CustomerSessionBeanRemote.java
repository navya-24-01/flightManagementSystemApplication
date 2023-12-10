/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface CustomerSessionBeanRemote {

    public Long createNewCustomer(Customer customer);

    public Customer doLogin(Long customerId, String password);

    public void doLogout(Customer customer);

    public Customer retreiveCustomerByCustomerId(Long customerId);
    
}
