/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package ejb.session.ws;
 

import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.FlightReservationSessionBeanLocal;
import entity.Customer;
import entity.Partner;
import entity.Reservation;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author aman
 */
@WebService(serviceName = "CustomerWebService")
@Stateless()
public class CustomerWebService {

    @EJB
    private FlightReservationSessionBeanLocal flightReservationSessionBeanLocal;

    @EJB
    private CustomerSessionBeanLocal customerSessionBeanLocal;

    
    
    /**
     *  public List<Reservation> retreiveCustomerReservations(Boolean variable, Long id);
     */
    @WebMethod(operationName = "Login")
     public Partner doLoginForPartner(@WebParam(name = "customerId") Long customerId, 
                                @WebParam(name = "password") String password) {
         Boolean var = true;
        Partner p = customerSessionBeanLocal.doLoginForPartner(var, customerId, password);
      
      return p;
    }
     
     
    @WebMethod(operationName = "retreiveCustomerReservations")
     public List<Reservation> retreiveCustomerReservations(@WebParam(name = "id") Long customerId) {
         Boolean var = true;
        List<Reservation> reservationList = this.flightReservationSessionBeanLocal.retreivePartnerReservations(var, customerId);
          
                return reservationList;
      
    }
     
}