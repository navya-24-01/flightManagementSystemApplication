/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frsreservationclient;

/**
 *
 * @author aman
 */

//import ejb.session.stateful.HolidayReservationSessionBeanRemote;
import ejb.session.stateless.AirportSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.FlightReservationSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import javax.ejb.EJB;



public class Main 
{
    
    @EJB
    private static CustomerSessionBeanRemote customerSessionBeanRemote;
    @EJB
    private static FlightReservationSessionBeanRemote flightReservationSessionBeanRemote;
    @EJB
    private static FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    @EJB
    private static AirportSessionBeanRemote airportSessionBeanRemote;
    
    
    public static void main(String[] args)
    {
        MainApp mainApp = new MainApp(customerSessionBeanRemote,flightReservationSessionBeanRemote, flightScheduleSessionBeanRemote, airportSessionBeanRemote);
        mainApp.runApp();
    }   
}