/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frsreservationclient;

import ejb.session.stateless.AirportSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.FlightReservationSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import entity.Airport;
import entity.CabinClassConfiguration;
import entity.Customer;
import entity.Fare;
import entity.FlightSchedule;
import entity.Passenger;
import entity.Reservation;
import entity.SeatingInventory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import oracle.jrockit.jfr.parser;

public class MainApp
{
  
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private FlightReservationSessionBeanRemote flightReservationSessionBeanRemote;
    private FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    private AirportSessionBeanRemote airportSessionBeanRemote;
    
    private Customer currentCustomer;

    
    
    public MainApp() 
    {
        currentCustomer = null;
    }

    
    
    public MainApp(CustomerSessionBeanRemote customerSessionBeanRemote, FlightReservationSessionBeanRemote flightReservationSessionBeanRemote, FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote, AirportSessionBeanRemote airportSessionBeanRemote) 
    {
        this();
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.flightReservationSessionBeanRemote = flightReservationSessionBeanRemote;
        this.flightScheduleSessionBeanRemote = flightScheduleSessionBeanRemote;
        this.airportSessionBeanRemote = airportSessionBeanRemote;
    }



    public void runApp()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            System.out.println("*** Welcome to Flight Reservation System ***\n");
            
            if(currentCustomer != null)
            {
                System.out.println("You are login as " + currentCustomer.getFirstName() + "\n");
                System.out.println("3: Search Flights");
                System.out.println("4: Make a reservation");
                System.out.println("5: View my reservations");
                System.out.println("6: View my reservation details");
                System.out.println("7: Logout\n");
                
            }
            else
            {            
                System.out.println("1: Customer Login");
                System.out.println("2: Register as Customer");
                
            }
                
            
           
            response = 0;
            
            while(response < 1 || response > 7)
            {
                System.out.print("> ");

                response = scanner.nextInt();
                

                if(response == 1)
                {
                    if(currentCustomer == null)
                    {
                       
                            doLogin();
                            
                     /*    try
                        {
                            doLogin();
                            System.out.println("Login successful as " + currentCustomer.getFullName() + "!\n");                                                
                        }
                        catch(InvalidLoginCredentialException ex) 
                        {
                            System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                        }*/
                           
                    }
                    else
                    {
                        System.out.println("You are already login as " + currentCustomer.getFirstName() + "\n");
                    }
                }
                else if (response == 2)
                {
                    //doSearchHoliday();
                    registerAsCustomer();
                }
                 else if (response == 3)
                {
                    //doSearchHoliday();
                    doSearchFlight();
                    ///createReservation();
                    
                }
                else if (response == 4)
                {
                    this.createReservation();
                }
                 else if (response == 5)
                {
                   if(currentCustomer != null) {
                     //view my flight reservations

                     //Long custId = currentCustomer.getCustomerId();
                     List<Reservation> reservationList = flightReservationSessionBeanRemote.retreiveCustomerReservations(this.currentCustomer.getCustomerId());
                     
                     for(Reservation reservation : reservationList) {
                         System.out.println("Reservation Id ::" + reservation.getReservationId());
                         System.out.println("Flight Number ::" + reservation.getFlightSchedule().getFlightSchedulePlan().getFlight().getFlightNumber());
                         System.out.println("Flight Departure Date ::" + reservation.getFlightSchedule().getDepartureDateTime());
                         System.out.println("Flight Arrival Date ::" + reservation.getFlightSchedule().getArrivalDateTime());
                         System.out.println("Flight Origin ::" + reservation.getFlightSchedule().getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName());
                         System.out.println("Flight Destination ::" + reservation.getFlightSchedule().getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName());
                         System.out.println("Number Of passengers:" + reservation.getPassengerList().size());
                     }
                     
                 } 
                  
                 
             
                
            }
                 else if (response == 6) {
                      Scanner sc = new Scanner(System.in);
                 if(currentCustomer != null) {
                     List<Reservation> reservationList = flightReservationSessionBeanRemote.retreiveCustomerReservations(this.currentCustomer.getCustomerId());
                     for(Reservation reservation : reservationList) {
                         System.out.println( reservation.getReservationId() +   reservation.getFlightSchedule().getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName()+ " - "  +  reservation.getFlightSchedule().getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName());
                     }
                     System.out.println(" Enter Reservation Id ::");
                     Long resId =  sc.nextLong();
                     
                     Reservation reservation = this.flightReservationSessionBeanRemote.retreiveReservationByReservationId(resId);
                     
                     System.out.println("Reservation Id ::" + reservation.getReservationId());
                     
                      System.out.println("Reservation Id ::" + reservation.getReservationId());
                         System.out.println("Flight Number ::" + reservation.getFlightSchedule().getFlightSchedulePlan().getFlight().getFlightNumber());
                         System.out.println("Flight Departure Date ::" + reservation.getFlightSchedule().getDepartureDateTime());
                         System.out.println("Flight Arrival Date ::" + reservation.getFlightSchedule().getArrivalDateTime());
                         System.out.println("Flight Origin ::" + reservation.getFlightSchedule().getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName());
                         System.out.println("Flight Destination ::" + reservation.getFlightSchedule().getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName());
                         System.out.println("Number Of passengers:" + reservation.getPassengerList().size());
                         System.out.println("Credit Card Number:" + reservation.getCreditCardNumber());
                         System.out.println("Credit Card Expiry Date:" + reservation.getExpiryDate());
                         System.out.println("Cabin Class Configuration:" + reservation.getCabinClassConfiguration().getCCCName());
                     
                     
                     
                     
                     
                     
                     //view my flight reservations details along with resId
                     //print out the list of reservations and then retreive using resId
                 }else {
                     System.out.println("Please login first before viewing your flight reservation details!\n");
                 }
                 }
                 
            else if (response == 7) {
                     this.doLogout();
                 }
            
        }
        
        //flightReservationSessionBeanRemote.remove();
        
    }
    }
    
    private void registerAsCustomer() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Flight Reservation System :: Register as New Customer ***\n");
        System.out.print("Enter First Name> ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter Last Name> ");
        String lastName = scanner.nextLine().trim();
        System.out.print("Enter email> ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Phone Number> ");
        String phoneNo = scanner.nextLine().trim();
        System.out.print("Enter Address> ");
        String address = scanner.nextLine().trim();
        System.out.print("Enter Password> ");
        String password = scanner.nextLine().trim();
    
        Customer cust = new Customer(firstName,lastName,email,phoneNo,address,password);
        Long id = customerSessionBeanRemote.createNewCustomer(cust);
        currentCustomer = this.customerSessionBeanRemote.retreiveCustomerByCustomerId(id);
        System.out.print("Customer with id " + id + " has been Registered! ");
    }
    
    
    private void doLogin() //throws InvalidLoginCredentialException
    {
        Scanner scanner = new Scanner(System.in);
        
        String password = "";
        
        
        System.out.println("*** Flight Reservation System :: Login ***\n");
        System.out.print("Enter Customer Id> ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter password> ");
        password = scanner.nextLine().trim();
        
        if(password.length() > 0)
        {
            currentCustomer = customerSessionBeanRemote.doLogin(id, password);
        }
        
        else 
        {
            System.out.print("Please enter Valid Password ");
            //Throw an exception here : throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
    
    private void findFlights(LocalDate departureDate, Long departureAirport, Long destinationAirport, boolean connecting, int n, String pref) {
         System.out.print("DIRECT FLIGHTS ");
         System.out.println();
         System.out.println();
         System.out.print("Departing 0 - 3 Days after: " + departureDate);
         System.out.println("   ");
         System.out.println("   ");
         
               List<FlightSchedule> fs1 =  flightScheduleSessionBeanRemote.searchFlightNDaysAfterOneWay(departureAirport,destinationAirport, 3, departureDate);
           
               if(fs1.isEmpty()) {
                   System.out.print("No Results ");
                   
               }
               for(FlightSchedule f : fs1) {
                   System.out.println("|| " + f.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
                   System.out.println(f.getFlightScheduleId() + " Departure Date : " + f.getDepartureDateTime() + " || " + "Arrival Date : " + f.getArrivalDateTime() + " || ");
                    System.out.println("Available Cabin Classes : ");
                    List<SeatingInventory> s = f.getSeatingInventory();
                    List<Fare> fL = f.getFlightSchedulePlan().getFareList();
                    int count = 0;
                    for(int i = 0; i < s.size(); i++) {
                        if(pref.equals("N")) {
                            System.out.println(s.get(i).getCabinClassConfiguration().getCCCName() + " ||  Number of Seats : " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats()) + " || Fare Per Passenger " + fL.get(i).getFareAmount() + " || Total Fare : " +  fL.get(i).getFareAmount() * n);
                            count = count + 1;
                        } else {
                            if(s.get(i).getCabinClassConfiguration().getCCCName().equals(pref)) {
                                 System.out.println(s.get(i).getCabinClassConfiguration().getCCCName() + " ||  Number of Seats : " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats()) + " || Fare Per Passenger " + fL.get(i).getFareAmount() + " || Total Fare : " +  fL.get(i).getFareAmount() * n);
                                 count = count + 1;
                            }
                        }
                        
                    }
                    if(count == 0) {
                        System.out.println("Preferred cabin class is unavailable.");
                    }
               }
                System.out.println("   ");
         System.out.println("   ");
                  
               
                System.out.print("Departing On the Date: " + departureDate);
                 System.out.println();
                  System.out.println("   ");
      
               List<FlightSchedule> fs4 =  flightScheduleSessionBeanRemote.searchFlightNDaysAfterOneWay(departureAirport,destinationAirport, 0, departureDate);
               if(fs4.isEmpty()) {
                   System.out.print("No Results");
                   
               }
               
     
               for(FlightSchedule f : fs4) {
                   System.out.println("|| " + f.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
                   System.out.println(f.getFlightScheduleId() + " Departure Date : " + f.getDepartureDateTime() + " || " + "Arrival Date : " + f.getArrivalDateTime() + " || ");
                    System.out.println("Available Cabin Classes : ");
                    List<SeatingInventory> s = f.getSeatingInventory();
                    List<Fare> fL = f.getFlightSchedulePlan().getFareList();
                    int count = 0;
                    for(int i = 0; i < s.size(); i++) {
                        if(pref.equals("N")) {
                            System.out.println(s.get(i).getCabinClassConfiguration().getCCCName() + " ||  Number of Seats : " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats()) + " || Fare Per Passenger " + fL.get(i).getFareAmount() + " || Total Fare : " +  fL.get(i).getFareAmount() * n);
                            count = count + 1;
                        } else {
                            if(s.get(i).getCabinClassConfiguration().getCCCName().equals(pref)) {
                                 System.out.println(s.get(i).getCabinClassConfiguration().getCCCName() + " ||  Number of Seats : " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats()) + " || Fare Per Passenger " + fL.get(i).getFareAmount() + " || Total Fare : " +  fL.get(i).getFareAmount() * n);
                                 count = count + 1;
                            }
                        }
                        
                    }
                    if(count == 0) {
                        System.out.println("Preferred cabin class is unavailable.");
                    }
               }
                System.out.println("   ");
         System.out.println("   ");
              
              
                System.out.println("Departing 0 - 3 days before : " + departureDate);
                System.out.println();
                 System.out.println("   ");
        
                
               List<FlightSchedule> fs7 =  flightScheduleSessionBeanRemote.searchFlightNDaysBeforeOneWay(departureAirport,destinationAirport, 3, departureDate);
               if(fs7.isEmpty()) {
                   System.out.print("No Results");
                   
               }
               
     
               for(FlightSchedule f : fs7) {
                   System.out.println("|| " + f.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
                   System.out.println(f.getFlightScheduleId() + " Departure Date : " + f.getDepartureDateTime() + " || " + "Arrival Date : " + f.getArrivalDateTime() + " || ");
                    System.out.println("Available Cabin Classes : ");
                    List<SeatingInventory> s = f.getSeatingInventory();
                    List<Fare> fL = f.getFlightSchedulePlan().getFareList();
                    int count = 0;
                    for(int i = 0; i < s.size(); i++) {
                        if(pref.equals("N")) {
                            System.out.println(s.get(i).getCabinClassConfiguration().getCCCName() + " ||  Number of Seats : " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats()) + " || Fare Per Passenger " + fL.get(i).getFareAmount() + " || Total Fare : " +  fL.get(i).getFareAmount() * n);
                            count = count + 1;
                        } else {
                            if(s.get(i).getCabinClassConfiguration().getCCCName().equals(pref)) {
                                 System.out.println(s.get(i).getCabinClassConfiguration().getCCCName() + " ||  Number of Seats : " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats()) + " || Fare Per Passenger " + fL.get(i).getFareAmount() + " || Total Fare : " +  fL.get(i).getFareAmount() * n);
                                 count = count + 1;
                            }
                        }
                        
                    }
                    if(count == 0) {
                        System.out.println("Preferred cabin class is unavailable.");
                    }
               }
         if(connecting == true) {
             
        
         System.out.println();
         System.out.println();      
         System.out.print("CONNECTING FLIGHTS ");
         System.out.println();
         System.out.println();
         
         System.out.println("Departing 0 - 3 Days after: " + departureDate);
         System.out.println("   ");
         System.out.println("   ");
                
         List<List<FlightSchedule>> flightsWithOneStopover = this.flightScheduleSessionBeanRemote.searchConnectingFlightsNDaysAfter(departureAirport,destinationAirport,1, departureDate);
                
         if(flightsWithOneStopover.size() == 0) {
            System.out.println("No Results");
            }
         
         for (List<FlightSchedule> flightPair : flightsWithOneStopover) {
             
             FlightSchedule firstLegFlight = flightPair.get(0);
             FlightSchedule firstLeg = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(firstLegFlight.getFlightScheduleId());
             FlightSchedule secondLegFlight = flightPair.get(1);
             FlightSchedule secondLeg = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(secondLegFlight.getFlightScheduleId());
             System.out.println("Journey Option:");
             System.out.println("|| " + firstLeg.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
             System.out.println("Leg 1: " + firstLeg.getFlightSchedulePlan().getFlight().getFlightNumber() +
                       " from " + firstLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName() +
                       " to " + firstLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName() +
                       " Departure: " + firstLeg.getDepartureDateTime() +
                       " Arrival: " + firstLeg.getArrivalDateTime());
             
             List<SeatingInventory> s = firstLeg.getSeatingInventory();
             List<Fare> fL = firstLeg.getFlightSchedulePlan().getFareList();
     System.out.println("|| " + secondLeg.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
    System.out.println("Leg 2: " + secondLeg.getFlightSchedulePlan().getFlight().getFlightNumber() +
                       " from " + secondLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName() +
                       " to " + secondLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName() +
                       " Departure: " + secondLeg.getDepartureDateTime() +
                       " Arrival: " + secondLeg.getArrivalDateTime());
    
    List<SeatingInventory> s1 = secondLeg.getSeatingInventory();
                    List<Fare> fL1 = secondLeg.getFlightSchedulePlan().getFareList();
                    for(int i = 0; i < s1.size(); i++) {
                        int count = 0 ;
                        if(pref.equals("N")) {
                             System.out.println(s1.get(i).getCabinClassConfiguration().getCCCName()  + " ||  Number of Seats in leg 1: " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats())  + " ||  Number of Seats in leg 2 : " + (s1.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s1.get(i).getOccupiedSeats()) + " Fare per passenger : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount()) + " || Total fare : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount() * n) );
                            count = count + 1;
                        } else {
                            if(s1.get(i).getCabinClassConfiguration().equals(pref)) {
                                System.out.println(s1.get(i).getCabinClassConfiguration().getCCCName()  + " ||  Number of Seats in leg 1: " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats())  + " ||  Number of Seats in leg 2 : " + (s1.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s1.get(i).getOccupiedSeats()) + " Fare per passenger : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount()) + " || Total fare : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount() * n) );
                            }
                        }
                      if(count == 0) {
                          System.out.println("Preferred cabin class is unavailable.");
                      }
                    }

    System.out.println("\n-----------------------------------------------------\n");
}
         System.out.println("   ");
         System.out.println("   ");
                System.out.println("Departing 0 - 3 Days before : " + departureDate);
                System.out.println("   ");
                System.out.println("   ");
                List<List<FlightSchedule>> flightsWithOneStopover2 = this.flightScheduleSessionBeanRemote.searchConnectingFlightsNDaysBefore(departureAirport,destinationAirport,3, departureDate);
                
                if(flightsWithOneStopover2.size() == 0) {
                    System.out.println("No Results");
                }
                
                for (List<FlightSchedule> flightPair : flightsWithOneStopover2) {
                    FlightSchedule firstLegFlight = flightPair.get(0);
                    FlightSchedule firstLeg = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(firstLegFlight.getFlightScheduleId());
                    FlightSchedule secondLegFlight = flightPair.get(1);
                    FlightSchedule secondLeg = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(secondLegFlight.getFlightScheduleId());
                    System.out.println("Journey Option:");
                     System.out.println("|| " + firstLeg.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
                    System.out.println("Leg 1: " + firstLeg.getFlightSchedulePlan().getFlight().getFlightNumber() +
                       " from " + firstLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName() +
                       " to " + firstLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName() +
                       " Departure: " + firstLeg.getDepartureDateTime() +
                       " Arrival: " + firstLeg.getArrivalDateTime());
                    
             List<SeatingInventory> s = firstLeg.getSeatingInventory();
             List<Fare> fL = firstLeg.getFlightSchedulePlan().getFareList();
     System.out.println("|| " + secondLeg.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
    System.out.println("Leg 2: " + secondLeg.getFlightSchedulePlan().getFlight().getFlightNumber() +
                       " from " + secondLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName() +
                       " to " + secondLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName() +
                       " Departure: " + secondLeg.getDepartureDateTime() +
                       " Arrival: " + secondLeg.getArrivalDateTime());
    
   List<SeatingInventory> s1 = secondLeg.getSeatingInventory();
                    List<Fare> fL1 = secondLeg.getFlightSchedulePlan().getFareList();
                    /*for(int i = 0; i < s1.size(); i++) {
                        int count = 0 ;
                        if(pref.equals("N")) {
                            System.out.println(s1.get(i).getCabinClassConfiguration().getCCCName()  + " ||  Number of Seats in leg 1: " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats())  + " ||  Number of Seats in leg 2 : " + (s1.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s1.get(i).getOccupiedSeats()) + " Fare per passenger : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount()) + " || Total fare : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount() * n) );
                            count = count + 1;
                        } else {
                            if(s1.get(i).getCabinClassConfiguration().equals(pref)) {
                                 System.out.println(s1.get(i).getCabinClassConfiguration().getCCCName()  + " ||  Number of Seats in leg 1: " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats())  + " ||  Number of Seats in leg 2 : " + (s1.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s1.get(i).getOccupiedSeats()) + " Fare per passenger : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount()) + " || Total fare : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount() * n) );
                                 count = count + 1;
                            }
                        }
                      if(count == 0) {
                          System.out.println("Preferred cabin class is unavailable.");
                      }*/
                    

    System.out.println("\n-----------------------------------------------------\n");
}
 System.out.println("   ");
         System.out.println("   ");
     

  
                System.out.println("Departing on the day :" + departureDate);
                 System.out.println("   ");
         System.out.println("   ");
                List<List<FlightSchedule>> flightsWithOneStopover4 = this.flightScheduleSessionBeanRemote.searchConnectingFlightsNDaysBefore(departureAirport,destinationAirport,0, departureDate);
                
                if(flightsWithOneStopover4.size() == 0) {
                    System.out.println("No Results");
                }

for (List<FlightSchedule> flightPair : flightsWithOneStopover4) {
    FlightSchedule firstLegFlight = flightPair.get(0);
    FlightSchedule firstLeg = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(firstLegFlight.getFlightScheduleId());
    
    FlightSchedule secondLegFlight = flightPair.get(1);
    FlightSchedule secondLeg = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(secondLegFlight.getFlightScheduleId());
    
    System.out.println("Journey Option:");
     System.out.println("|| " + firstLeg.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
    System.out.println("Leg 1: " + firstLeg.getFlightSchedulePlan().getFlight().getFlightNumber() +
                       " from " + firstLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName() +
                       " to " + firstLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName() +
                       " Departure: " + firstLeg.getDepartureDateTime() +
                       " Arrival: " + firstLeg.getArrivalDateTime());
    List<SeatingInventory> s = firstLeg.getSeatingInventory();
    List<Fare> fL = firstLeg.getFlightSchedulePlan().getFareList();
                     System.out.println("|| " + secondLeg.getFlightSchedulePlan().getFlight().getFlightNumber() + " ||");
    System.out.println("Leg 2: " + secondLeg.getFlightSchedulePlan().getFlight().getFlightNumber() +
                       " from " + secondLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getOrigin().getAirportName() +
                       " to " + secondLeg.getFlightSchedulePlan().getFlight().getFlightRoute().getDestination().getAirportName() +
                       " Departure: " + secondLeg.getDepartureDateTime() +
                       " Arrival: " + secondLeg.getArrivalDateTime());
    List<SeatingInventory> s1 = secondLeg.getSeatingInventory();
    List<Fare> fL1 = secondLeg.getFlightSchedulePlan().getFareList();
    for(int i = 0; i < s1.size(); i++) {
                        int count = 0 ;
                        /*if(pref.equals("N")) {
                            System.out.println(s1.get(i).getCabinClassConfiguration().getCCCName()  + " ||  Number of Seats in leg 1: " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats())  + " ||  Number of Seats in leg 2 : " + (s1.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s1.get(i).getOccupiedSeats()) + " Fare per passenger : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount()) + " || Total fare : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount() * n) );
                            count = count + 1;
                        } else {
                            if(s1.get(i).getCabinClassConfiguration().equals(pref)) {
                                 System.out.println(s1.get(i).getCabinClassConfiguration().getCCCName()  + " ||  Number of Seats in leg 1: " + (s.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s.get(i).getOccupiedSeats())  + " ||  Number of Seats in leg 2 : " + (s1.get(i).getCabinClassConfiguration().getTotalSeatCapacity() - s1.get(i).getOccupiedSeats()) + " Fare per passenger : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount()) + " || Total fare : " + (fL.get(i).getFareAmount() + fL1.get(i).getFareAmount() * n) );
                            }
                        }
                      if(count == 0) {
                          System.out.println("Preferred cabin class is unavailable.");
                      }
                    }*/
    }

    System.out.println("-----------------------------------------------------");
}
         }
        
               
 
        
    }
    
    private void doSearchFlight()
    {
        
            
        //try {
            Scanner scanner = new Scanner(System.in);
            Integer response = 0;
            LocalDate departureDate;
            LocalDate returnDate;
            Long tripType;
            Long departureAirport;
            Long destinationAirport;
             
            
            System.out.println("*** Flight Reservation System :: Search Flight ***\n");

                 List<Airport> as =  this.airportSessionBeanRemote.retrieveAllAirports();
                 for(Airport a : as) {
                   System.out.println(a.getAirportId() + " " + a.getIATACode() + " city : " + a.getCity());
        }
            
            System.out.print("Enter Departure Airport Id > ");
            departureAirport = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Enter Destination Airport Id > ");
            destinationAirport = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Enter Departure Date (dd/mm/yyyy) > ");
            departureDate = parseDateTime(scanner.nextLine().trim());
            System.out.print("Enter number of passengers > ");
            int numOfPassengers = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter preferred cabin class configuration (F/Y/J/). Enter N for No Preference > ");
            String pref = scanner.nextLine().trim();
            System.out.print("Include Connecting Flights? (Y/N) > ");
            String connecting = scanner.nextLine();
            
            if(connecting.equals("Y")) {
                 this.findFlights(departureDate, departureAirport, destinationAirport, true, numOfPassengers, pref);
            } else {
                 this.findFlights(departureDate, departureAirport, destinationAirport, false, numOfPassengers, pref);
            }
           
            System.out.println("Display return flights? (Y/N)> ");
            String returnf = scanner.nextLine();
            
            if(returnf.equals("Y")) {
                
                System.out.print("Enter Return Date (dd/mm/yyyy)> ");
            LocalDate returningDate = parseDateTime(scanner.nextLine().trim());
            System.out.print("Include Connecting Flights? (Y/N)> ");
            String connecting2 = scanner.nextLine();
            
            if(connecting2.equals("Y")) {
                 this.findFlights(returningDate,destinationAirport,departureAirport, true, numOfPassengers,pref);
            } else {
                this.findFlights(returningDate,destinationAirport,departureAirport, false, numOfPassengers,pref);
            }    
                
            }
            
                
                
               
            
            
          
             
             
            
            
            
            
   
            
              
            
                  
        } 
        /*catch (ParseException ex) {
           System.out.println("Invalid date input!\n");
        }
       
    }*/
    
     private static LocalDate parseDateTime(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(userInput, formatter);
    }
    
    
     private void doLogout() {
        customerSessionBeanRemote.doLogout(this.currentCustomer);
        this.currentCustomer = null;
        System.out.println("Logout Successfull!\n");
    }
     
     private void createReservation() {
         System.out.println("Enter number of reservations :");
         Scanner sc = new Scanner(System.in);
         int num = sc.nextInt();
         sc.nextLine();
         
         for(int j = 0; j < num; j++ ) {
         
         System.out.println("Enter Flight Schedule Id: ");
         Long fsId = sc.nextLong();
         sc.nextLine();
         FlightSchedule fs = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(fsId);
        
         System.out.println("Available cabin class configurations : ");
         List<CabinClassConfiguration> cc = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(fsId).getFlightSchedulePlan().getFlight().getAircraftConfiguration().getCabinClassConfigurationList();
         for(CabinClassConfiguration c : cc) {
             System.out.println(c.getCCCId() + " " + c.getCCCName());
         }
         System.out.println("Enter Preferred Cabin Class Configuration ID > ");
         Long cId = sc.nextLong();
         sc.nextLine();
         
         CabinClassConfiguration c = this.flightScheduleSessionBeanRemote.retrieveCabinClassConfigurationById(cId);
         
         
         boolean reserve = true;
         List<SeatingInventory> si = fs.getSeatingInventory();
         for(SeatingInventory s : si) {
             if(s.getCabinClassConfiguration().getCCCName().equals(c.getCCCName())) {
                 //System.out.println("Im here 1");
                 
                 if(s.getOccupiedSeats() >= s.getCabinClassConfiguration().getTotalSeatCapacity()) {
                     reserve = false;
                     //System.out.println("Im here 2");
                 }
             }
         }
         if(reserve == true) {
         System.out.println("Enter Credit Card Number: ");
         String ccNum = sc.nextLine();
         System.out.println("Enter Credit Card Expiration Date : ");
         String ccExpiry= sc.nextLine();
         System.out.println("Enter Credit Card CVV Number :");
         String cvvNum = sc.nextLine();
         
         Reservation r = new Reservation(ccNum, ccExpiry, cvvNum);
         System.out.println("Enter Number of Passengers :");
          Long n = sc.nextLong();
          sc.nextLine();
          List<Passenger> p = new ArrayList<Passenger>();
          for( int i = 0; i < n; i++) {
         System.out.println("Enter First Name for passenger : " + ( i + 1));
         String fname= sc.nextLine();
         System.out.println("Enter Last Name for passenger : " + ( i + 1));
         String lname = sc.nextLine();
         System.out.println("Enter Passport Number for passenger : " + ( i + 1));
         String passport= sc.nextLine();
         Passenger passenger = new Passenger(fname, lname, passport);
              p.add(passenger);
          }
          flightReservationSessionBeanRemote.createNewReservation(r, this.currentCustomer.getCustomerId(), p, cId, fsId);
         } else {
             System.out.println("There are no more available seats in this cabin class and flight schedule.");
         }
         }
          
         
     }
}
    
    