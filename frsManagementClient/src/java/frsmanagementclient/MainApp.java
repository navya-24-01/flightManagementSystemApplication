package frsmanagementclient;

import ejb.session.stateless.AircraftConfigurationSessionBeanRemote;
import ejb.session.stateless.AircraftTypeSessionBeanRemote;
import ejb.session.stateless.AirportSessionBeanRemote;
import ejb.session.stateless.CabinClassConfigurationSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import ejb.session.stateless.FlightRouteSessionBeanRemote;
import ejb.session.stateless.FlightScheduleSessionBeanRemote;
import ejb.session.stateless.FlightSessionBeanRemote;
import entity.AircraftConfiguration;
import entity.AircraftType;
import entity.Airport;
import entity.CabinClassConfiguration;
import entity.Employee;
import entity.Fare;
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.Passenger;
import entity.Reservation;
import entity.SeatingInventory;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.exception.InvalidLoginException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class MainApp {
    private EmployeeSessionBeanRemote employeeSessionBeanRemote;
    private AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote;
    private CabinClassConfigurationSessionBeanRemote cabinClassConfigurationSessionBeanRemote;
    private AircraftTypeSessionBeanRemote aircraftTypeSessionBeanRemote;
    private FlightRouteSessionBeanRemote flightRouteSessionBeanRemote;
    private AirportSessionBeanRemote airportSessionBeanRemote;
    private FlightSessionBeanRemote flightSessionBeanRemote;
    private FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    
    
    Employee currentEmployee;
    
    public MainApp() 
    {
        currentEmployee = null;
    }

    
    
    public MainApp(EmployeeSessionBeanRemote employeeSessionBeanRemote, AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote,  CabinClassConfigurationSessionBeanRemote cabinClassConfigurationSessionBeanRemote, AircraftTypeSessionBeanRemote aircraftTypeSessionBeanRemote,  FlightRouteSessionBeanRemote flightRouteSessionBeanRemote , AirportSessionBeanRemote airportSessionBeanRemote, FlightSessionBeanRemote flightSessionBeanRemote, FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote) 
    {
        this();
        
        this.employeeSessionBeanRemote = employeeSessionBeanRemote;
        this.aircraftConfigurationSessionBeanRemote = aircraftConfigurationSessionBeanRemote;
        this.cabinClassConfigurationSessionBeanRemote =  cabinClassConfigurationSessionBeanRemote;
        this.aircraftTypeSessionBeanRemote = aircraftTypeSessionBeanRemote;
        this. flightRouteSessionBeanRemote =  flightRouteSessionBeanRemote;
        this.airportSessionBeanRemote = airportSessionBeanRemote;
        this.flightSessionBeanRemote = flightSessionBeanRemote;
        this.flightScheduleSessionBeanRemote = flightScheduleSessionBeanRemote;
    }
    
     public void runApp()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response = 0;
        
        while(true)
        {
            
            System.out.println("                 ");
            System.out.println("                 ");
            System.out.println("*** Welcome to the Flight Management System ***\n");
            
            if(currentEmployee != null)
            {
                System.out.println("You are login as " + currentEmployee.getEmployeeName() + "\n");
                 
                  
            System.out.println("2: Create Aircraft Configuration");
            System.out.println("3: View All Aircraft Configurations");
            System.out.println("4: View Aircraft Configuration Details");
            System.out.println("5: Create Flight Route");
            System.out.println("6: View All Flight Routes");
            System.out.println("7: Delete Flight Route");
            System.out.println("8: Create Flight");
            System.out.println("9: View All Flights");
            System.out.println("10: View Flight Details");
            System.out.println("11: Update Flight");
            System.out.println("12: Delete Flight");          
            System.out.println("13: Create Flight Schedule Plan");   
            System.out.println("14: View All Flight Schedule Plans"); 
            System.out.println("15: Update Flight Schedule Plans");
            System.out.println("16: Delete Flight Schedule Plans");
            System.out.println("17: View Seats Inventory");
            System.out.println("18: View Flight Reservations");
            System.out.println("19: View Flight Schedule Plan details"); 
            System.out.println("20: Logout");
            System.out.println("                 ");
            System.out.println("                 ");
            System.out.println("                 ");
            System.out.println("                 ");
            System.out.println("                 ");
            
            }
            else
            {            
                System.out.println("1: Login");
            }
           
            response = 0;
            
            while(response < 1 || response > 20)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    if(currentEmployee == null)
                    {
                        try
                        {
                            doLogin();
                            System.out.println("Login successful as " + currentEmployee.getEmployeeName() + "!\n");                                                
                        }
                        catch(InvalidLoginException ex) 
                        {
                            System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                        }
                    }
                    else
                    {
                        System.out.println("You are already login as " + currentEmployee.getEmployeeName() + "\n");
                    }
                }
                
                if(response == 2)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Fleet Manager")) 
                    {
                        createAircraftConfiguration();
                    }
                    
                    else {
                        
                        System.out.println("You are not authorised to do this!");
                    }
                    
                }
                
                 if(response == 3)
                {
                     if(this.currentEmployee.getEmployeeUserRole().equals("Fleet Manager")) 
                    {
                        viewAllAircraftConfigurations();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                     
                   
                    
                }
                 
                  
                 if(response == 4)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Fleet Manager")) 
                    {
                        viewAircraftConfiguration();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                    
                   
                    
                }
                 
                  if(response == 5)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Route Planner")) 
                    {
                         createFlightRoute();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                    
                  
                    
                }
                  
                  
                
                  if(response == 6)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Route Planner")) 
                    {
                         viewAllFlightRoutes();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                    
                }
                  
                  
                  
                 if(response == 7)
                {
                    
                    if(this.currentEmployee.getEmployeeUserRole().equals("Route Planner")) 
                    {
                          deleteFlightRoute();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                    
                  
                    
                }
                 
                 if(response == 8)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                        createFlight();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                  
                    
                }
                 
                 if(response == 9)
                {
                     if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                         viewAllFlights();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                  
                    
                }
                 
                  if(response == 10)
                {
                     if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                        Long Id = viewFlightDetails();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                }
                  
                   if(response == 11)
                {
                     if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                       Long Id = viewFlightDetails();
                       updateFlight(Id);
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                }
                   
                   if(response == 12)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                       
                       deleteFlight();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                }
                 
                  if(response == 13)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                      createFlightSchedulePlan();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                }
                  
                  if(response == 14)
                {
                     if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                      viewAllFlightSchedulePlans();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                }
                  
                  if(response == 15)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                       Long Id = viewFlightSchedulePlanDetails();
                      updateFlightSchedulePlan(Id);
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                } 
                  
                    if(response == 16)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                       Long Id = viewFlightSchedulePlanDetails();
                       deleteFlightSchedulePlan(Id);
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                  
                    
                } 
                    
                    if(response == 17)
                {
                    
                     if(this.currentEmployee.getEmployeeUserRole().equals("Sales Manager")) 
                    {
                       this.viewSeatInventory();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                } 
                    
                      if(response == 18)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Sales Manager")) 
                    {
                       viewFlightReservations();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                } 
                      
                     if(response == 19)
                {
                    if(this.currentEmployee.getEmployeeUserRole().equals("Schedule Manager")) 
                    {
                       Long Id = viewFlightSchedulePlanDetails();
                        
                    }
                     
                    else {
                         
                        System.out.println("You are not authorised to do this!");
                    }
                   
                    
                }
            
                  
                    if(response == 20)
                {
                   doLogout();
                    
                } 
                  
        
    }
        }
    }
     
     private void doLogin() throws InvalidLoginException
    {
        Scanner scanner = new Scanner(System.in);
        String password = "";
        
        System.out.println("*** Flight Management System :: Login ***\n");
        System.out.print("Enter employee Id> ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter password> ");
        password = scanner.nextLine().trim();
        
        if(employeeId > 0 && password.length() > 0)
        {
            currentEmployee = employeeSessionBeanRemote.doLogin(employeeId, password);
        }
        else
        {
            throw new InvalidLoginException("Missing login credential!");
        }
    }
     
      private void createAircraftConfiguration() 
    {
        Scanner sc = new Scanner(System.in);
        String aircraftConfigurationTypeName = "";
     
        System.out.println("Enter number of Cabin Classes >");
        int num = sc.nextInt();
        sc.nextLine();
        
        List<Long> ccList = new ArrayList<Long>();
           
            for( int i = 0; i < num; i++) {
                System.out.println("Enter details for cabin class > " + (i + 1));
                System.out.println("Enter Cabin Class Name >");
                String name = sc.nextLine();
                
                System.out.println("Enter No of Aisles >");
                int aisles = sc.nextInt();
                sc.nextLine();
                
                System.out.println("Enter No of Rows >");
                int rows = sc.nextInt();
                sc.nextLine();
                
                System.out.println("Enter No of Seats Abreast >");
                int seatsAbrest = sc.nextInt();
                sc.nextLine();
                
                System.out.println("Enter Seat Configuration  >");
                String config = sc.nextLine();
                
                
                System.out.println("Enter Max capacity of Cabin Class >");
                int max = sc.nextInt();
                sc.nextLine();
                
                CabinClassConfiguration cc = new CabinClassConfiguration(name,aisles,rows,seatsAbrest,config, max);
                
                
                Long cId = this.cabinClassConfigurationSessionBeanRemote.createNewCabinClassConfiguration(cc);
                ccList.add(cId);
               
            }
            
            System.out.println("Enter Aircraft Type name:");
            String actName = sc.nextLine();
            
            System.out.println("Enter Aircraft Max Capacity:");
            int maxC = sc.nextInt();

            AircraftType at = new AircraftType(actName,maxC);
            Long atId = this.aircraftTypeSessionBeanRemote.createNewAircraftType(at);
            

            AircraftConfiguration a = new AircraftConfiguration(aircraftConfigurationTypeName, num);
            Long Id = aircraftConfigurationSessionBeanRemote.createNewAircraftConfiguration(a, ccList, atId);
            System.out.println("New Aircraft Configuration created with Id " + Id);
        
    }
      
      private void viewAllAircraftConfigurations() {
          System.out.println("All Aircraft configurations are : ");
          List<AircraftConfiguration> ac = aircraftConfigurationSessionBeanRemote.retrieveAllAircraftConfigurations();
          for(AircraftConfiguration a : ac) {
              System.out.println(a.getAircraftConfigurationId() + " " + a.getAircraftType().getAircraftTypeName());
          }
                  
      }
      
    public void viewAircraftConfiguration() {
        System.out.println("All Aircraft configurations are : ");
          List<AircraftConfiguration> ac = aircraftConfigurationSessionBeanRemote.retrieveAllAircraftConfigurations();
          for(AircraftConfiguration a : ac) {
              System.out.println(a.getAircraftConfigurationId() + " " + a.getAircraftType().getAircraftTypeName());
          }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Aircraft Configuration Id: ");
        Long Id = sc.nextLong();
        sc.nextLine();
        AircraftConfiguration a = aircraftConfigurationSessionBeanRemote.retrieveAircraftConfigurationById(Id);
          System.out.print(a.getAircraftConfigurationId() + " || Aircraft Type is : " + a.getAircraftType().getAircraftTypeName() + " Max Capacity is : " +a.getAircraftType().getMaxPassenegerSeatCapacity() + " Cabin Classes are : ");
          int counter = 0;
          for(CabinClassConfiguration c :  a.getCabinClassConfigurationList()) {
              if(counter ==  a.getCabinClassConfigurationList().size() - 1) {
                   System.out.print(c.getCCCName());
                   System.out.println();
              } else {
                  System.out.print(c.getCCCName() + " , ");
              }
               counter++;
          }
    }
    
    public void createFlightRoute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("All Airports :  ");
   
        List<Airport> as =  airportSessionBeanRemote.retrieveAllAirports();
        for(Airport a : as) {
            System.out.println(a.getAirportId() + " " + a.getIATACode() + " city : " + a.getCity());
        }
        System.out.println("Add a new airport : (Y/N)");
        String ans = sc.nextLine(); 
        if(ans.equals("Y")) {
            
            System.out.println("Enter Airport Name :");
            String airportName = sc.nextLine();
            
            System.out.println("Enter IATA code > ");
            String iataCode = sc.nextLine();
            
            System.out.println("Enter City >");
            String city = sc.nextLine();
            
            System.out.println("Enter State >");
            String state = sc.nextLine();
            
            System.out.println("Enter Country >");
            String country = sc.nextLine();
            
           
            Airport newAirport = new Airport(airportName, iataCode, city, state, country);
           
            Long Id = airportSessionBeanRemote.createNewAirport(newAirport);
            System.out.println("New Airport created with Id : " + Id);
             List<Airport> ass =  airportSessionBeanRemote.retrieveAllAirports();
        for(Airport a : ass) {
            System.out.println(a.getAirportId() + " " + a.getIATACode() + " city : " + a.getCity());
        }
        }
        
        System.out.println("Enter origin airport Id :");
        Long oId = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter destination airport Id :");
        Long dId = sc.nextLong();
        sc.nextLine();
        boolean exists = false;
        List<FlightRoute> frs = this.flightRouteSessionBeanRemote.retrieveAllFlightRoutes();
        for(FlightRoute f : frs) {
            if(f.getOrigin().getAirportId().equals(oId) && f.getDestination().getAirportId().equals(dId)) {
                this.flightRouteSessionBeanRemote.setReturnRouteTrue(f.getFlightRouteId());
                exists = true;
                
            }
        }
        
        if( exists ) {
            System.out.println("Flight Route already exists.");
        } else  {
             boolean complementary = false;
        
        List<FlightRoute> fr = this.flightRouteSessionBeanRemote.retrieveAllFlightRoutes();
        for(FlightRoute f : fr) {
            if(f.getOrigin().getAirportId().equals(dId) && f.getDestination().getAirportId().equals(oId)) {
                this.flightRouteSessionBeanRemote.setReturnRouteTrue(f.getFlightRouteId());
                complementary = true;
                
            }
        }
        
        
         System.out.println("Do you want to create a return route ? : (Y/N)");
         String resp = sc.nextLine();
         if(resp.equals("Y")) {
         Long created = flightRouteSessionBeanRemote.createNewFlightRoute(new FlightRoute(true), oId, dId);
         System.out.println("Flight Route created with Id : " + created);
             
         created = flightRouteSessionBeanRemote.createNewFlightRoute(new FlightRoute(true), dId, oId);
         System.out.println("Flight Route created with Id : " + created);
        
    } else {
             if(complementary == true) {
              Long created = flightRouteSessionBeanRemote.createNewFlightRoute(new FlightRoute(true), oId, dId);
              System.out.println("Flight Route created with Id : " + created);
             } else  {
              Long created = flightRouteSessionBeanRemote.createNewFlightRoute(new FlightRoute(), oId, dId);
              System.out.println("Flight Route created with Id : " + created);
             }
              
         }
            
        }
         
         
    }
    
    private void deleteFlightRoute() {
        Scanner sc = new Scanner(System.in);
        this.viewAllFlightRoutes();
        System.out.println("Enter Flight Route Id to delete :  ");
        Long Id = sc.nextLong();
        FlightRoute frr = this.flightRouteSessionBeanRemote.retrieveFlightRouteById(Id);
        Long dId = frr.getDestination().getAirportId();
        Long oId = frr.getOrigin().getAirportId();
        sc.nextLine();
        List<FlightRoute> fr = this.flightRouteSessionBeanRemote.retrieveAllFlightRoutes();
        for(FlightRoute f : fr) {
            if(f.getOrigin().getAirportId().equals(dId) && f.getDestination().getAirportId().equals(oId)) {
                f.setReturnRoute(true);
                
            }
        }
       int deleted = flightRouteSessionBeanRemote.deleteFlightRoute(Id);
       if(deleted == 1) {
           System.out.println("Flight Route : " + Id + " is deleted. "); 
       } else {
           System.out.println("Flight Route : " + Id + " is in use and has been marked disabled. "); 
       }
       
        
    }
    
    private void createFlight() {
          Scanner sc = new Scanner(System.in);
          System.out.println("Create new flight:");
            
          System.out.print("Flight Number > ");
          String flightNum = sc.nextLine();
          
          List<Flight> fs = this.flightSessionBeanRemote.retreiveAllFlights();
          boolean repeat  = false;
          for(Flight f : fs) {
              if(f.getFlightNumber().equals(flightNum)) {
                  repeat = true;
              }
              
          }
          
          if(repeat) {
              System.out.println("A Flight with this Flight Number already exists.");
          } else {
                this.viewAllFlightRoutes();
                    
            System.out.print("Flight Route Id >");
            Long flightRouteId = sc.nextLong();
            sc.nextLine();
            
            FlightRoute frr = this.flightRouteSessionBeanRemote.retrieveFlightRouteById(flightRouteId);
             Long dId = frr.getDestination().getAirportId();
             Long oId = frr.getOrigin().getAirportId();
             Long complementaryFlightRouteId = 0l;
            
            boolean complementary = false;
            List<FlightRoute> fr = this.flightRouteSessionBeanRemote.retrieveAllFlightRoutes();
            for(FlightRoute f : fr) {
            if(f.getOrigin().getAirportId().equals(dId) && f.getDestination().getAirportId().equals(oId)) {
                complementary = true;
                complementaryFlightRouteId = f.getFlightRouteId();
            }
            }
            System.out.println("Select Aircraft Configuration from > ");
            List<AircraftConfiguration> ac = aircraftConfigurationSessionBeanRemote.retrieveAllAircraftConfigurations();
            for(AircraftConfiguration a : ac ) {
                System.out.println(a.getAircraftConfigurationId() + " "  + a.getAircraftType().getAircraftTypeName() + " "  );
            }
            
            System.out.print("Aircraft Configuration Id > ");
            Long aircrafConfigurationId = sc.nextLong();
            sc.nextLine();
            
            Long aircraftTypeId = this.aircraftConfigurationSessionBeanRemote.retrieveAircraftConfigurationById(aircrafConfigurationId).getAircraftType().getAircraftTypeId();
            
            String resp = "";
            if(complementary == true) {
                System.out.println("A complementary flight route exists for the selected flight route. Do you want to create a complementary return flight as well?(Y/N");
                resp = sc.nextLine();
            }
            
            if(resp.equals("Y")) {
                System.out.println("Enter Flight Number for complementary flight >");
                String complementaryFlightRouteNum = sc.nextLine();
                Flight newFlight = new Flight(flightNum, true);
                Flight complementaryNewFlight = new Flight(complementaryFlightRouteNum, true);
                Long Id1 = flightSessionBeanRemote.createNewFlight(newFlight,aircraftTypeId,flightRouteId, aircrafConfigurationId);
                Long id2 = flightSessionBeanRemote.createNewFlight(complementaryNewFlight,aircraftTypeId,complementaryFlightRouteId, aircrafConfigurationId);
                System.out.println("New Flight created with Id " + Id1 );
                System.out.println("New Flight created with Id " + id2 );
                
                
            } else {
                Flight newFlight = new Flight(flightNum, false);
                List<Flight> flights = this.flightSessionBeanRemote.retreiveAllFlights();
                for(Flight f : flights) {
                    if(f.getFlightRoute().getOrigin().getAirportId().equals(dId) && f.getFlightRoute().getDestination().getAirportId().equals(oId)) {
                    this.flightSessionBeanRemote.setReturnFlightTrue(f.getFlightId());
                    newFlight = new Flight(flightNum, true);
                    }
                }
                Long Id = flightSessionBeanRemote.createNewFlight(newFlight,aircraftTypeId,flightRouteId, aircrafConfigurationId );
                System.out.println("New Flight Route created with Id : " + Id);
                
            } 
              
          }
            
            
            
    }
    
    private void updateFlight(Long flightId) {
         Scanner sc = new Scanner(System.in);
         
         System.out.println("Enter new Flight Number > ");
         Long flightNum = sc.nextLong();
         sc.nextLine();
         //this.flightSessionBeanRemote.updateFlight(flightnum);
         System.out.println("Flight details updated Successfully! > ");
         
         
    }
    
    private void viewAllFlights() {
          Scanner sc = new Scanner(System.in);
          System.out.println("All flights > ");
          List<Flight> fs = flightSessionBeanRemote.viewAllFlightRoutesInOrder();
          
          for(Flight f : fs) {
              System.out.print("Flight Number " + f.getFlightNumber() + " || Origin :  " +  f.getFlightRoute().getOrigin().getAirportName() + " || Destination : " + f.getFlightRoute().getDestination().getAirportName());
              if(f.getDisabled()) {
               System.out.print("[Disabled]");
               System.out.println("");
           } else {
                System.out.println("");
           }
          }
                
    }
    
    private void deleteFlight() {
          this.viewAllFlights();
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter Flight Id to delete > ");
          Long Id = sc.nextLong();
          sc.nextLine();
          int deleted = flightSessionBeanRemote.deleteFlight(Id);
          if(deleted == 1) {
              System.out.println("Flight " + Id + " is in use, and has been marked disabled.");
          } else {
              System.out.println("Flight " + Id + " is deleted.");
          }
          
    }
    
    private long viewFlightDetails(){
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter Flight Number to view > ");
         String Id = sc.nextLine();
         
         Flight flight = this.flightSessionBeanRemote.retrieveFlightByFlightNumber(Id);
         System.out.println("Origin > " + flight.getFlightRoute().getOrigin().getAirportName());
         System.out.println("Destination > " + flight.getFlightRoute().getDestination().getAirportName());
         List<CabinClassConfiguration> cabinClassConfigurationList = flight.getAircraftConfiguration().getCabinClassConfigurationList();
         System.out.println("Number of Cabin Class Configurations > " + cabinClassConfigurationList.size());
         int counter = 0;
         for (CabinClassConfiguration cabinConfig : cabinClassConfigurationList) {
             counter++;
            System.out.println(counter + " Cabin Class Name > " + cabinConfig.getCCCName() + " || Total Seats > " + cabinConfig.getTotalSeatCapacity());
        }
         return flight.getFlightId();
               
    }
     
    private void createFlightSchedulePlan() {
          Scanner sc = new Scanner(System.in);
          System.out.println("Select Type of Flight Schedule Plan > ");
          System.out.println("1 : Single");
          System.out.println("2 : Multiple");
          System.out.println("3 : Recurring");
          System.out.println("4 : Weekly");
          
          int resp = sc.nextInt();
          sc.nextLine();
          
          if(resp == 1) {
              
            System.out.print("Enter departure date and time (yyyy-MM-ddTHH:mm:ss) > ");
            String departureInput = sc.nextLine();
            LocalDateTime departureDateTime = parseDateTime(departureInput);
            System.out.print("Enter duration Of flight (PT2H30M) > ");
            String durationInput = sc.nextLine();
            Duration duration = parseDuration(durationInput);// Calculate arrival date and time
            LocalDateTime arrivalDateTime = calculateArrivalDateTime(departureDateTime, duration);
            System.out.println("Enter Flight Number > ");
            String flightNum = sc.nextLine();
            Long flightId = this.flightSessionBeanRemote.retrieveFlightByFlightNumber(flightNum).getFlightId();
           
            Flight f = flightSessionBeanRemote.retriveFlightbyID(flightId);
            Long oId = f.getFlightRoute().getOrigin().getAirportId();
            Long dId = f.getFlightRoute().getDestination().getAirportId();
            
            Long f2 = 0l;
            LocalDateTime newArrivalDateTime = arrivalDateTime;
            LocalDateTime newDepartureDateTime = departureDateTime;
            
                boolean complementary =  false;
                if(f.getReturnFlight()) {
                    System.out.println("A complementary flight exists for this flight. Do you want to create a complementary flight schedule plan? (Y / N)");
                    String ans = sc.nextLine();
                    if(ans.equals("Y")) {
                       List<Flight> allFlights = this.flightSessionBeanRemote.retreiveAllFlights();
                       for(Flight fl : allFlights) {
                           if(fl.getFlightRoute().getOrigin().getAirportId().equals(dId) && fl.getFlightRoute().getDestination().getAirportId().equals(oId)) {
                               f2 = fl.getFlightId();
                               complementary = true;
                           }
                       }
                       System.out.println("Enter Layover Duration > (PT2H30M)");
                       String layoverDuration = sc.nextLine();
                       Duration layover = parseDuration(layoverDuration);
                       newArrivalDateTime = arrivalDateTime.plus(layover).plus(duration);
                       newDepartureDateTime = departureDateTime.plus(layover).plus(duration);
                    }
                    
                }
                
            List<CabinClassConfiguration> cs = f.getAircraftConfiguration().getCabinClassConfigurationList();
            FlightSchedulePlan newFlightSchedulePlan = new FlightSchedulePlan();
            if(complementary == true) {
                newFlightSchedulePlan = new FlightSchedulePlan("Single", true, departureDateTime);
            } else  {
                newFlightSchedulePlan = new FlightSchedulePlan("Single", false, departureDateTime);
            }
           
            FlightSchedulePlan newFlightSchedulePlanComplementary = new FlightSchedulePlan("Single", true, newDepartureDateTime);
            Long Id1 = flightScheduleSessionBeanRemote.createNewFlightSchedulePlanSingle(flightId, departureDateTime, arrivalDateTime, duration, newFlightSchedulePlan);
            FlightSchedulePlan fsp1 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id1);
            FlightSchedulePlan fsp2 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id1);
            Long Id2 = 0l;
            if(complementary == true) {
                Id2 = this.flightScheduleSessionBeanRemote.createNewFlightSchedulePlanSingle(f2, newDepartureDateTime, newArrivalDateTime, duration, newFlightSchedulePlanComplementary);
                fsp2 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id2);
            }
            
            System.out.println("Enter a fare for each cabin class > ");
            for(CabinClassConfiguration c : cs) {
                System.out.println("Enter fare basis code > ");
                String code = sc.nextLine();
                System.out.println("Enter fare amount > ");
                Long amount = sc.nextLong();
                sc.nextLine();
                Fare newFare = new Fare(code, amount);
                flightScheduleSessionBeanRemote.createNewFareForFlightSchedulePlan(newFare, Id1, c.getCCCId());
                for(FlightSchedule fs : fsp1.getFlightScheduleList()) {
                     this.flightScheduleSessionBeanRemote.createNewInventoryForFlightSchedule(new SeatingInventory(0,0), fs.getFlightScheduleId(), c.getCCCId());
                }
           
                if(complementary == true) {
                    flightScheduleSessionBeanRemote.createNewFareForFlightSchedulePlan(newFare, Id2, c.getCCCId());
                    for(FlightSchedule fs : fsp2.getFlightScheduleList()) {
                     this.flightScheduleSessionBeanRemote.createNewInventoryForFlightSchedule(new SeatingInventory(0,0), fs.getFlightScheduleId(), c.getCCCId());
                }
                }
          }
            }
          
          if(resp == 2) {
               System.out.println("Enter number of flight schedules > ");
               int n = sc.nextInt();
               sc.nextLine();
               
            System.out.println("Enter Flight Number > ");
            String flightNum = sc.nextLine();
            Long flightId = this.flightSessionBeanRemote.retrieveFlightByFlightNumber(flightNum).getFlightId();
           
           
            Flight f = flightSessionBeanRemote.retriveFlightbyID(flightId);
            Long oId = f.getFlightRoute().getOrigin().getAirportId();
            Long dId = f.getFlightRoute().getDestination().getAirportId();
            Long f2 = 0l;
            boolean complementary =  false;
            Duration layover = null;
                if(f.getReturnFlight()) {
                    System.out.println("A complementary flight exists for this flight. Do you want to create a complementary flight schedule plan? (Y / N)");
                    String ans = sc.nextLine();
                    if(ans.equals("Y")) {
                       List<Flight> allFlights = this.flightSessionBeanRemote.retreiveAllFlights();
                       for(Flight fl : allFlights) {
                           if(fl.getFlightRoute().getOrigin().getAirportId().equals(dId) && fl.getFlightRoute().getDestination().getAirportId().equals(oId)) {
                               f2 = fl.getFlightId();
                               complementary = true;
                           }
                       }
                       System.out.println("Enter Layover Duration > (e.g., PT2H30M)");
                       String layoverDuration = sc.nextLine();
                       layover = parseDuration(layoverDuration);
                    }
                    
                }
            List<LocalDateTime> arrivals = new ArrayList<LocalDateTime>();
            List<LocalDateTime> departures = new ArrayList<LocalDateTime>();
            List<LocalDateTime> complementaryArrivals = new ArrayList<LocalDateTime>();
            List<LocalDateTime> complementaryDepartures = new ArrayList<LocalDateTime>();
            List<Duration> durations = new ArrayList<Duration>();
            
               for(int i = 0; i < n; i++) {
                   
            System.out.print("Enter departure date and time (yyyy-MM-ddTHH:mm:ss) > ");
            String departureInput = sc.nextLine();
            LocalDateTime departureDateTime = parseDateTime(departureInput);
            System.out.print("Enter duration Of flight (PT2H30M) > ");
            String durationInput = sc.nextLine();
            Duration duration = parseDuration(durationInput);// Calculate arrival date and time
            durations.add(duration);
            LocalDateTime arrivalDateTime = calculateArrivalDateTime(departureDateTime, duration);
            arrivals.add(arrivalDateTime);
            departures.add(departureDateTime);
            
    
            
            if(complementary == true) {
                LocalDateTime newDepartureDateTime = departureDateTime.plus(layover).plus(duration);
                LocalDateTime newArrivalDateTime = arrivalDateTime.plus(layover).plus(duration);
                complementaryArrivals.add(newArrivalDateTime);
                complementaryDepartures.add(newDepartureDateTime);
                   
               }
               }
               
                FlightSchedulePlan newFlightSchedulePlan = new FlightSchedulePlan();
            if(complementary == true) {
                newFlightSchedulePlan = new FlightSchedulePlan("Multiple", true, departures.get(0));
            } else  {
                newFlightSchedulePlan = new FlightSchedulePlan("Multiple", false, departures.get(0));
            }
            
            Long Id1 = flightScheduleSessionBeanRemote.createFlightSchedulePlanMultiple(flightId, departures, arrivals, durations, n, newFlightSchedulePlan);
            FlightSchedulePlan fsp1 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id1);
            FlightSchedulePlan fsp2 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id1);
            Long Id2 = 0l;
            if(complementary == true) {
                FlightSchedulePlan newFlightSchedulePlanComplementary = new FlightSchedulePlan("Multiple", true, complementaryDepartures.get(0));
                Id2 = this.flightScheduleSessionBeanRemote.createFlightSchedulePlanMultiple(f2, complementaryDepartures, complementaryArrivals, durations, n, newFlightSchedulePlanComplementary);
                fsp2 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id2);
            }
            
             List<CabinClassConfiguration> cs = f.getAircraftConfiguration().getCabinClassConfigurationList();
              System.out.println("Enter a fare for each cabin class > ");
            for(CabinClassConfiguration c : cs) {
                System.out.println("Enter fare basis code > ");
                String code = sc.nextLine();
                System.out.println("Enter fare amount > ");
                Long amount = sc.nextLong();
                sc.nextLine();
                Fare newFare = new Fare(code, amount);
                flightScheduleSessionBeanRemote.createNewFareForFlightSchedulePlan(newFare, Id1, c.getCCCId());
                for(FlightSchedule fs : fsp1.getFlightScheduleList()) {
                     this.flightScheduleSessionBeanRemote.createNewInventoryForFlightSchedule(new SeatingInventory(0,0), fs.getFlightScheduleId(), c.getCCCId());
                }
                if(complementary == true) {
                    flightScheduleSessionBeanRemote.createNewFareForFlightSchedulePlan(newFare, Id2, c.getCCCId());
                    for(FlightSchedule fs : fsp2.getFlightScheduleList()) {
                     this.flightScheduleSessionBeanRemote.createNewInventoryForFlightSchedule(new SeatingInventory(0,0), fs.getFlightScheduleId(), c.getCCCId());
                }
            
          }
            }
          }
          
          
            
         
          
          
          if(resp == 3) {
    System.out.print("Enter departure date and time (yyyy-MM-ddTHH:mm:ss) > ");
     String departureInput = sc.nextLine();
     LocalDateTime departureDateTime = parseDateTime(departureInput);
     System.out.print("Enter duration Of flight (PT2H30M) > ");
     String durationInput = sc.nextLine();
     Duration duration = parseDuration(durationInput);// Calculate arrival date and time
     LocalDateTime arrivalDateTime = calculateArrivalDateTime(departureDateTime, duration);
     System.out.print("Enter n > ");
     int Input = sc.nextInt();
     sc.nextLine();
     
      System.out.print("Enter end date and time (yyyy-MM-ddTHH:mm:ss) > ");
     String lastdayInput = sc.nextLine();
     LocalDateTime lastDateTime = parseDateTime(lastdayInput);
     System.out.println("enter flight Number > ");
     String flightNum = sc.nextLine();
     Long flightId = this.flightSessionBeanRemote.retrieveFlightByFlightNumber(flightNum).getFlightId();
    
     Flight f = flightSessionBeanRemote.retriveFlightbyID(flightId);
     Long oId = f.getFlightRoute().getOrigin().getAirportId();
     Long dId = f.getFlightRoute().getDestination().getAirportId();
     
     Long f2 = 0l;
     LocalDateTime newArrivalDateTime = arrivalDateTime;
     LocalDateTime newDepartureDateTime = departureDateTime;
     
         boolean complementary =  false;
         if(f.getReturnFlight()) {
             System.out.println("A complementary flight exists for this flight. Do you want to create a complementary flight schedule plan? (Y / N)");
             String ans = sc.nextLine();
             if(ans.equals("Y")) {
                List<Flight> allFlights = this.flightSessionBeanRemote.retreiveAllFlights();
                for(Flight fl : allFlights) {
                    if(fl.getFlightRoute().getOrigin().getAirportId().equals(dId) && fl.getFlightRoute().getDestination().getAirportId().equals(oId)) {
                        f2 = fl.getFlightId();
                        complementary = true;
                    }
                }
                System.out.println("Enter Layover Duration > (e.g. PT2H30M)");
                String layoverDuration = sc.nextLine();
                Duration layover = parseDuration(layoverDuration);
                newArrivalDateTime = arrivalDateTime.plus(layover).plus(duration);
                newDepartureDateTime = departureDateTime.plus(layover).plus(duration);
             }
             
         }
         
     List<CabinClassConfiguration> cs = f.getAircraftConfiguration().getCabinClassConfigurationList();
     FlightSchedulePlan newFlightSchedulePlan = new FlightSchedulePlan();
     if(complementary == true) {
         newFlightSchedulePlan = new FlightSchedulePlan("Recurring", true, departureDateTime);
     } else  {
         newFlightSchedulePlan = new FlightSchedulePlan("Recurring", false, departureDateTime);
     }
     FlightSchedulePlan newFlightSchedulePlanComplementary = new FlightSchedulePlan("Recurring", true, newDepartureDateTime);
     Long Id1 = flightScheduleSessionBeanRemote.createRecurringFlightSchedulePlan(flightId, departureDateTime, arrivalDateTime, duration, lastDateTime, Input, newFlightSchedulePlan);
     FlightSchedulePlan fsp1 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id1);
     FlightSchedulePlan fsp2 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id1);
     Long Id2 = 0l;
     if(complementary == true) {
         Id2 = this.flightScheduleSessionBeanRemote.createRecurringFlightSchedulePlan(f2, newDepartureDateTime, newArrivalDateTime, duration, lastDateTime, Input, newFlightSchedulePlanComplementary);
         fsp2 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id2);
     }
     
     System.out.println("Enter a fare for each cabin class > ");
     for(CabinClassConfiguration c : cs) {
         System.out.println("Enter fare basis code > ");
         String code = sc.nextLine();
         System.out.println("Enter fare amount > ");
         Long amount = sc.nextLong();
         sc.nextLine();
         Fare newFare = new Fare(code, amount);
         flightScheduleSessionBeanRemote.createNewFareForFlightSchedulePlan(newFare, Id1, c.getCCCId());
         for(FlightSchedule fs : fsp1.getFlightScheduleList()) {
              this.flightScheduleSessionBeanRemote.createNewInventoryForFlightSchedule(new SeatingInventory(0,0), fs.getFlightScheduleId(), c.getCCCId());
         }
         if(complementary == true) {
             flightScheduleSessionBeanRemote.createNewFareForFlightSchedulePlan(newFare, Id2, c.getCCCId());
             for(FlightSchedule fs : fsp2.getFlightScheduleList()) {
              this.flightScheduleSessionBeanRemote.createNewInventoryForFlightSchedule(new SeatingInventory(0,0), fs.getFlightScheduleId(), c.getCCCId());
         }
         }
   }
       
   }
          
          if(resp == 4) {
           System.out.print("Enter departure date and time (yyyy-MM-ddTHH:mm:ss) > ");
            String departureInput = sc.nextLine().trim();
            LocalDateTime departureDateTime = parseDateTime(departureInput);
            System.out.println("Enter start day :");
            String day = sc.nextLine().trim();
            DayOfWeek targetDayOfWeek = DayOfWeek.valueOf(day.toUpperCase());
            DayOfWeek start = DayOfWeek.FRIDAY;
            
            int daysDifference = targetDayOfWeek.getValue() - start.getValue();
           if (daysDifference < 0) {
           daysDifference += 7; 
}
            LocalDate targetDate = departureDateTime.toLocalDate().plusDays(daysDifference);
            LocalTime departureTime = departureDateTime.toLocalTime();
            
            LocalDateTime targetDateTime = targetDate.atTime(departureTime);
            System.out.print("Enter duration Of flight (PT2H30M) > ");
            String durationInput = sc.nextLine().trim();
            Duration duration = parseDuration(durationInput);// Calculate arrival date and time
            LocalDateTime arrivalDateTime = calculateArrivalDateTime(targetDateTime, duration);
            
            
            System.out.print("Enter end date and time (yyyy-MM-ddTHH:mm:ss) > ");
            String lastdayInput = sc.nextLine().trim();
            LocalDateTime lastDateTime = parseDateTime(lastdayInput);
            System.out.println("Enter Flight Number > ");
            String flightNum = sc.nextLine().trim();
            Long flightId = this.flightSessionBeanRemote.retrieveFlightByFlightNumber(flightNum).getFlightId();
           
            Flight f = flightSessionBeanRemote.retriveFlightbyID(flightId);
            Long oId = f.getFlightRoute().getOrigin().getAirportId();
            Long dId = f.getFlightRoute().getDestination().getAirportId();
            
            Long f2 = 0l;
            LocalDateTime newArrivalDateTime = arrivalDateTime;
            LocalDateTime newDepartureDateTime = targetDateTime;
            
                boolean complementary =  false;
                if(f.getReturnFlight()) {
                    System.out.println("A complementary flight exists for this flight. Do you want to create a complementary flight schedule plan? (Y / N)");
                    String ans = sc.nextLine();
                    if(ans.equals("Y")) {
                       List<Flight> allFlights = this.flightSessionBeanRemote.retreiveAllFlights();
                       for(Flight fl : allFlights) {
                           if(fl.getFlightRoute().getOrigin().getAirportId().equals(dId) && fl.getFlightRoute().getDestination().getAirportId().equals(oId)) {
                               f2 = fl.getFlightId();
                               complementary = true;
                           }
                       }
                       System.out.println("Enter Layover Duration > (e.g., PT2H30M");
                       String layoverDuration = sc.nextLine();
                       Duration layover = parseDuration(layoverDuration);
                       newArrivalDateTime = arrivalDateTime.plus(duration).plus(layover);
                       newDepartureDateTime = targetDateTime.plus(layover).plus(duration);
                    }
                    
                }
                
            List<CabinClassConfiguration> cs = f.getAircraftConfiguration().getCabinClassConfigurationList();
            FlightSchedulePlan newFlightSchedulePlan = new FlightSchedulePlan();
            if(complementary == true) {
                newFlightSchedulePlan = new FlightSchedulePlan("Weekly", true,targetDateTime);
            } else  {
                newFlightSchedulePlan = new FlightSchedulePlan("Weekly", false, targetDateTime);
            }
            FlightSchedulePlan newFlightSchedulePlanComplementary = new FlightSchedulePlan("Weekly", true, newDepartureDateTime);
            Long Id1 = flightScheduleSessionBeanRemote.createRecurringFlightSchedulePlan(flightId, targetDateTime, arrivalDateTime, duration, lastDateTime, 7, newFlightSchedulePlan);
            FlightSchedulePlan fsp1 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id1);
            FlightSchedulePlan fsp2 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id1);
            Long Id2 = 0l;
            if(complementary == true) {
                Id2 = this.flightScheduleSessionBeanRemote.createRecurringFlightSchedulePlan(f2, newDepartureDateTime, newArrivalDateTime, duration, lastDateTime, 7, newFlightSchedulePlanComplementary);
                fsp2 = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(Id2);
            }
            
            System.out.println("Enter a fare for each cabin class > ");
            for(CabinClassConfiguration c : cs) {
                System.out.println("Enter fare basis code > ");
                String code = sc.nextLine();
                System.out.println("Enter fare amount > ");
                Long amount = sc.nextLong();
                sc.nextLine();
                Fare newFare = new Fare(code, amount);
                flightScheduleSessionBeanRemote.createNewFareForFlightSchedulePlan(newFare, Id1, c.getCCCId());
                for(FlightSchedule fs : fsp1.getFlightScheduleList()) {
                     this.flightScheduleSessionBeanRemote.createNewInventoryForFlightSchedule(new SeatingInventory(0,0), fs.getFlightScheduleId(), c.getCCCId());
                }
                if(complementary == true) {
                    flightScheduleSessionBeanRemote.createNewFareForFlightSchedulePlan(newFare, Id2, c.getCCCId());
                    for(FlightSchedule fs : fsp2.getFlightScheduleList()) {
                     this.flightScheduleSessionBeanRemote.createNewInventoryForFlightSchedule(new SeatingInventory(0,0), fs.getFlightScheduleId(), c.getCCCId());
                }
                }
          }
              
          }
            
    }
    
   private void viewAllFlightSchedulePlans(){
       
       Scanner sc = new Scanner(System.in);
        System.out.println("All Flight Schedule Plans > ");
        
        List<FlightSchedulePlan> fspList = this.flightScheduleSessionBeanRemote.viewAllFlightSchedulePlansInOrder();
        
        for(FlightSchedulePlan fsp : fspList) {
              System.out.println(fsp.getFlightSchedulePlanId() + " Flight Number : " + fsp.getFlight().getFlightNumber() + " First departure date || : " + fsp.getFirstDeparture());
          }
       
   }
   
   private void  updateFlightSchedulePlan(Long fspId) {
       
        Scanner sc = new Scanner(System.in);
         
         sc.nextLine();
         System.out.println("Enter new type of Flight Schedule Plan > ");
         String fspType = sc.nextLine();
         
         //this.flightSessionBeanRemote.updateFlightSchedulePlan(fspId,fspType);
         System.out.println("Flight Schedule Plan updated Successfully!  ");
         
       
   }
   
   private void deleteFlightSchedulePlan(Long fspId) {
       
         
          //flightScheduleSessionBeanRemote.deleteFlightSchedulePlan(fspId);
          System.out.println("Flight Schedule Plan Deleted Successfully!  ");
       
   }    
   
   private void doLogout() {
       this.employeeSessionBeanRemote.doLogout();
       this.currentEmployee = null;
       System.out.println("Logout Successfull!\n");
   }
   
   private void viewAllFlightRoutes() {
       List<FlightRoute> flightRouteList = this.flightRouteSessionBeanRemote.viewAllFlightRoutesInOrder();
       for( FlightRoute fr : flightRouteList) {
           System.out.print(fr.getFlightRouteId() + " Origin : " + fr.getOrigin().getAirportName() + " || Destination : " +  fr.getDestination().getAirportName());
           if(fr.getDisabled()) {
               System.out.print("[Disabled]");
               System.out.println("");
           } else {
                System.out.println("");
           }
       }
   }
   
   private void viewSeatInventory(){
       Scanner sc = new Scanner(System.in);
       System.out.println("Enter Flight Number > ");
       String flightNum = sc.nextLine().trim();
       Flight flight  = this.flightSessionBeanRemote.retrieveFlightByFlightNumber(flightNum);
       List<FlightSchedulePlan> fsp = flight.getFlightSchedulePlanList();
       System.out.println("Available Flight Schedules are :");
       for( FlightSchedulePlan fp : fsp) {
           List<FlightSchedule> fs = fp.getFlightScheduleList();
           for(FlightSchedule f : fs) {
               System.out.println(f.getFlightScheduleId() + " || Departure Date : " + f.getDepartureDateTime() + " || Arrival Date : " + f.getArrivalDateTime());
           }
       }
       System.out.println("Enter Flight Schedule Id : > ");
       Long fsID = sc.nextLong();
       sc.nextLine();
       
       FlightSchedule fs = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(fsID);
       
       List<SeatingInventory> si = fs.getSeatingInventory();
       int totalMaxCapacity = 0;
       int totalAvailableSeats = 0;
       for(SeatingInventory s : si) {
           System.out.println("Cabin class : " + s.getCabinClassConfiguration().getCCCName() + " Total Seats : " + s.getCabinClassConfiguration().getTotalSeatCapacity() + " Available Seats : " + (s.getCabinClassConfiguration().getTotalSeatCapacity() - s.getOccupiedSeats()));
           totalMaxCapacity = totalMaxCapacity + s.getCabinClassConfiguration().getTotalSeatCapacity();
           totalAvailableSeats = totalAvailableSeats + s.getOccupiedSeats();
           
       }
       
       System.out.println("Total Number of Seats in the Flight Schedule : " + totalMaxCapacity + " ||  Number of Available seats in the Flight Schedule : " + (totalMaxCapacity - totalAvailableSeats));
       
   }   
   private void viewFlightReservations() {
       //retrieveReservationsByCustomerId
       
       Scanner sc = new Scanner(System.in);
       System.out.println("Enter Flight Number  > ");
       String flightNum = sc.nextLine();
       
       Flight f = this.flightSessionBeanRemote.retrieveFlightByFlightNumber(flightNum);
       
       System.out.println("Associated Flight Schedules are : ");
       
       List<FlightSchedulePlan> fsps = f.getFlightSchedulePlanList();
       
       for(FlightSchedulePlan fsp : fsps) {
           for(FlightSchedule fs : fsp.getFlightScheduleList()){
               System.out.println(fs.getFlightScheduleId() + " Flight Departure Date : " + fs.getDepartureDateTime());
           }
       }
       System.out.println("Enter Flight Schedule Id : ");
       Long Id = sc.nextLong();
       sc.nextLine();
       
       FlightSchedule fs = this.flightScheduleSessionBeanRemote.retrieveFlightScheduleById(Id);
       
       List<Reservation> reservations = fs.getReservationList();
       
   
       
       for(Reservation r : reservations) {
           System.out.println(r.getReservationId() + "Passengers : " );
           List<Passenger> p = r.getPassengerList();
           for(Passenger p1 : p){
               System.out.println(p1.getFirstName() + " ||  " + p1.getLastName() + " || " + p1.getPassportNumber());
           }
       }
       
      
   }
   
   private Long viewFlightSchedulePlanDetails() {
        Scanner sc = new Scanner(System.in);
        this.viewAllFlightSchedulePlans();
         System.out.println("Enter Flight Schedule Plan Id > ");
         Long fspId = sc.nextLong();
         sc.nextLine();
         FlightSchedulePlan fsp = this.flightScheduleSessionBeanRemote.retriveFlightSchedulePlanByID(fspId);
       
       
       System.out.println("Origin:  " + fsp.getFlight().getFlightRoute().getOrigin().getAirportName());
       System.out.println("Destination:  " + fsp.getFlight().getFlightRoute().getDestination().getAirportName());
       for (FlightSchedule schedule : fsp.getFlightScheduleList()) {
         System.out.println("Flight Schedule Id  : " + schedule.getFlightScheduleId());
         System.out.println("Departure Date and Time : " + schedule.getDepartureDateTime());
         System.out.println("Arrival Date and Time : " + schedule.getArrivalDateTime() );
         System.out.println("Estimated Flight Duration : " + schedule.getEstimatedFlightDuration());
         System.out.println("-------------------xxxxxxxxxxx-------------------------" );
         
         
        }
       
       List<Fare> f = fsp.getFareList();
       List<CabinClassConfiguration> c = fsp.getFlight().getAircraftConfiguration().getCabinClassConfigurationList();
       
       for(int i = 0; i < c.size(); i++ ) {
           System.out.println("Fare for Cabin Class Configuration " + c.get(i).getCCCName() + " is " + f.get(i).getFareAmount() );
       }
       
       return fspId;
   }
    
    private static Duration parseDuration(String userInput) {
       
     
            int hours = Integer.parseInt(userInput.substring(2, userInput.indexOf('H')));
            int minutes = Integer.parseInt(userInput.substring(userInput.indexOf('H') + 1, userInput.indexOf('M')));

            // Create a Duration using the extracted values
            return Duration.ofHours(hours).plusMinutes(minutes);}
    
    private static LocalDateTime calculateArrivalDateTime(LocalDateTime departureDateTime, Duration duration) {
        return departureDateTime.plus(duration);
    }
    
    private static LocalDateTime parseDateTime(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
               return LocalDateTime.parse(userInput, formatter);
    }
    
    
}

    
