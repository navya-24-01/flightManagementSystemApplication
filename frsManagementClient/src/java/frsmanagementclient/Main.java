/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
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
import entity.Flight;
import entity.FlightRoute;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author navyamunjal
 */

public class Main 
{
    @EJB
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;
    @EJB(name = "AircraftConfigurationSessionBeanRemote")
    private static AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote;
    @EJB(name = "CabinClassConfigurationSessionBeanRemote")
    private static CabinClassConfigurationSessionBeanRemote cabinClassConfigurationSessionBeanRemote;
    @EJB(name = "AircraftTypeSessionBeanRemote")
    private static AircraftTypeSessionBeanRemote aircraftTypeSessionBeanRemote;
    @EJB(name = "FlightRouteSessionBeanRemote")
    private static FlightRouteSessionBeanRemote flightRouteSessionBeanRemote;
    @EJB(name = "AirportSessionBeanRemote")
    private static AirportSessionBeanRemote airportSessionBeanRemote;
    @EJB(name = "FlightSessionBeanRemote")
    private static FlightSessionBeanRemote flightSessionBeanRemote;
    @EJB(name = "FlightScheduleSessionBeanRemote")
    private static FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    
    
    
    
    
    
    public static void main(String[] args)
    {
        MainApp mainApp = new MainApp(employeeSessionBeanRemote, aircraftConfigurationSessionBeanRemote, cabinClassConfigurationSessionBeanRemote, aircraftTypeSessionBeanRemote, flightRouteSessionBeanRemote, airportSessionBeanRemote, flightSessionBeanRemote, flightScheduleSessionBeanRemote );
        mainApp.runApp();
    }   
}
/*public class Main {
    @EJB(name = "EmployeeSessionBeanRemote")
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;
    
    @EJB(name = "CabinClassConfigurationSessionBeanRemote")
    private static CabinClassConfigurationSessionBeanRemote cabinClassConfigurationSessionBeanRemote;
    
    @EJB(name = "AircraftTypeSessionBeanRemote")
    private static AircraftTypeSessionBeanRemote aircraftTypeSessionBeanRemote;
    
    @EJB(name = "AircraftConfigurationSessionBeanRemote")
    private static AircraftConfigurationSessionBeanRemote aircraftConfigurationSessionBeanRemote;
    
    @EJB(name = "AirportSessionBeanRemote")
    private static AirportSessionBeanRemote airportSessionBeanRemote;
    
    @EJB(name = "FlightRouteSessionBeanRemote")
    private static FlightRouteSessionBeanRemote flightRouteSessionBeanRemote;
     
    @EJB(name = "FlightSessionBeanRemote")
    private static FlightSessionBeanRemote flightSessionBeanRemote;
     
    @EJB(name = "FlightScheduleSessionBeanRemote")
    private static FlightScheduleSessionBeanRemote flightScheduleSessionBeanRemote;
    
    

    
    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Option: ");
        System.out.println(" 1 : Create a new Employee ");
        System.out.println(" 2 : Create a new Cabin Class Configuration ");
        System.out.println(" 3 : Create a new Aircraft Type ");
        System.out.println(" 4 : Create a new Aircraft Configuration ");
        System.out.println(" 5 : Create a new Airport");
        System.out.println(" 6 : Create a new FlightRoute");
        System.out.println(" 7 : Create a new Flight");
        System.out.println(" 8 : Delete Flight");
        System.out.println(" 9 : Delete Flight Route");
        System.out.println(" 10 : Create new flight schedule");
        System.out.println(" - 1 : Employee Login ");
        System.out.println(" - 2 : Employee Logout ");
        System.out.println(" - 3 : Retrieve all Employees ");
        System.out.println(" - 4 : Retrieve all  Aircraft Types");
        System.out.println(" - 5 : Retrieve all Aircraft Configurations");
        System.out.println(" - 6 : Retrieve all Cabin Class Configurations");
        
        
          
        int option  = sc.nextInt();
        sc.nextLine();
        
        if(option == 1) {
        System.out.print("create employee");
        String name = sc.nextLine();
        String password = sc.nextLine();
        String role = sc.nextLine();
        
        Employee e = new Employee(name, password, role);
        Long id = employeeSessionBeanRemote.createNewEmployee(e);
        System.out.print(id);
        }
       
        if(option == 2) {
            System.out.println("create cabin class config:");
            System.out.println("cabin cals name:");
            String cabinName = sc.nextLine();
            System.out.println("num of aisles");
            int numOfAisles = sc.nextInt();
            sc.nextLine();
            System.out.print("num of sections of seats");
            int numOfSections  = sc.nextInt();
            ArrayList<Integer> seats = new ArrayList<Integer>();
            for(int i = 0 ; i < numOfSections; i++ ) {
                System.out.print("enter num of seats in section " + i);
                seats.add(sc.nextInt());
                sc.nextLine();
            }
            System.out.println("num of rows");
            int numOfRows = sc.nextInt();
            sc.nextLine();
            CabinClassConfiguration c = new CabinClassConfiguration(cabinName, numOfAisles, seats, numOfRows);
            Long Id = cabinClassConfigurationSessionBeanRemote.createNewCabinClassConfiguration(c);
            System.out.println(Id);
       
        }
        
        if(option == 3) {
            System.out.println("create aircraft type:");
            System.out.println("aircraft type name:");
            String aircraftTypeName = sc.nextLine();
             System.out.println("max passenger seat capacity");
            int maxPassengerSeatCapacity = sc.nextInt();
            sc.nextLine();
            AircraftType a = new AircraftType(aircraftTypeName, maxPassengerSeatCapacity);
            Long Id = aircraftTypeSessionBeanRemote.createNewAircraftType(a);
            System.out.println(Id);
       
        }
        
        if(option == 4) {
            System.out.println("create aircraft configuration:");
            System.out.println("aircraft configuration name:");
            String aircraftConfigurationTypeName = sc.nextLine();
            int numOfCabinClass = sc.nextInt();
            sc.nextLine();
            ArrayList<Long> cabinClassConfigurationList = new ArrayList<Long>();
             for(int i = 0 ; i < numOfCabinClass; i++ ) {
                System.out.println("enter cabin class configuration id for cabin class " + i);
                cabinClassConfigurationList.add(sc.nextLong());
                sc.nextLine();
             }
            
            System.out.println("enter aircraft type id");
            Long aircraftId = sc.nextLong();
            
            AircraftConfiguration a = new AircraftConfiguration(aircraftConfigurationTypeName, numOfCabinClass);
            Long Id = aircraftConfigurationSessionBeanRemote.createNewAircraftConfiguration(a, cabinClassConfigurationList, aircraftId);
            System.out.println(Id);
       
        }
        
        if(option == -1) {
            System.out.println("create aircraft configuration:");
            System.out.println("aircraft configuration name:");
            String aircraftConfigurationTypeName = sc.nextLine();
            int numOfCabinClass = sc.nextInt();
            sc.nextLine();
            ArrayList<Long> cabinClassConfigurationList = new ArrayList<Long>();
             for(int i = 0 ; i < numOfCabinClass; i++ ) {
                System.out.println("enter cabin class configuration id for cabin class " + i);
                cabinClassConfigurationList.add(sc.nextLong());
                sc.nextLine();
             }
            
            System.out.println("enter aircraft type id");
            Long aircraftId = sc.nextLong();
            
            AircraftConfiguration a = new AircraftConfiguration(aircraftConfigurationTypeName, numOfCabinClass);
            Long Id = aircraftConfigurationSessionBeanRemote.createNewAircraftConfiguration(a, cabinClassConfigurationList, aircraftId);
            System.out.println(Id);
       
        }
        
        if(option == - 2 ) {
            System.out.println("create aircraft configuration:");
            System.out.println("aircraft configuration name:");
            String aircraftConfigurationTypeName = sc.nextLine();
            int numOfCabinClass = sc.nextInt();
            sc.nextLine();
            ArrayList<Long> cabinClassConfigurationList = new ArrayList<Long>();
             for(int i = 0 ; i < numOfCabinClass; i++ ) {
                System.out.println("enter cabin class configuration id for cabin class " + i);
                cabinClassConfigurationList.add(sc.nextLong());
                sc.nextLine();
             }
            
            System.out.println("enter aircraft type id");
            Long aircraftId = sc.nextLong();
            
            AircraftConfiguration a = new AircraftConfiguration(aircraftConfigurationTypeName, numOfCabinClass);
            Long Id = aircraftConfigurationSessionBeanRemote.createNewAircraftConfiguration(a, cabinClassConfigurationList, aircraftId);
            System.out.println(Id);
       
        }
        
        if(option == -3) {
            System.out.println("create aircraft configuration:");
            System.out.println("aircraft configuration name:");
            String aircraftConfigurationTypeName = sc.nextLine();
            int numOfCabinClass = sc.nextInt();
            sc.nextLine();
            ArrayList<Long> cabinClassConfigurationList = new ArrayList<Long>();
             for(int i = 0 ; i < numOfCabinClass; i++ ) {
                System.out.println("enter cabin class configuration id for cabin class " + i);
                cabinClassConfigurationList.add(sc.nextLong());
                sc.nextLine();
             }
            
            System.out.println("enter aircraft type id");
            Long aircraftId = sc.nextLong();
            
            AircraftConfiguration a = new AircraftConfiguration(aircraftConfigurationTypeName, numOfCabinClass);
            Long Id = aircraftConfigurationSessionBeanRemote.createNewAircraftConfiguration(a, cabinClassConfigurationList, aircraftId);
            System.out.println(Id);
       
        }
        
        if(option == -4) {
            System.out.println("create aircraft configuration:");
            System.out.println("aircraft configuration name:");
            String aircraftConfigurationTypeName = sc.nextLine();
            int numOfCabinClass = sc.nextInt();
            sc.nextLine();
            ArrayList<Long> cabinClassConfigurationList = new ArrayList<Long>();
             for(int i = 0 ; i < numOfCabinClass; i++ ) {
                System.out.println("enter cabin class configuration id for cabin class " + i);
                cabinClassConfigurationList.add(sc.nextLong());
                sc.nextLine();
             }
            
            System.out.println("enter aircraft type id");
            Long aircraftId = sc.nextLong();
            
            AircraftConfiguration a = new AircraftConfiguration(aircraftConfigurationTypeName, numOfCabinClass);
            Long Id = aircraftConfigurationSessionBeanRemote.createNewAircraftConfiguration(a, cabinClassConfigurationList, aircraftId);
            System.out.println(Id);
       
        }
        
        if(option == 5) {
            System.out.println("create new airport:");
            System.out.println("airport name:");
            String airportName = sc.nextLine();
            
            System.out.println("iata code:");
            String iataCode = sc.nextLine();
            
            System.out.println("city");
            String city = sc.nextLine();
            
            System.out.println("state ");
            String state = sc.nextLine();
            
            System.out.println("country:");
            String country = sc.nextLine();
            
            /*System.out.println("origin airport id");
            Long originAirportId = sc.nextLong();
            sc.nextLine();
            
            System.out.println("destination airport id");
            Long destinationAirportId = sc.nextLong();
            sc.nextLine();*/
            /*Airport newAirport = new Airport(airportName, iataCode, city, state, country);
           
            Long Id = airportSessionBeanRemote.createNewAirport(newAirport);
            System.out.println(Id);
       
        }
        
        if(option == 6) {
            System.out.println("create new flight route:");
            
             System.out.println("origin airport id");
            Long originAirportId = sc.nextLong();
            sc.nextLine();
            
            System.out.println("destination airport id");
            Long destinationAirportId = sc.nextLong();
            sc.nextLine();
            
            FlightRoute newFlightRoute = new FlightRoute();
            
            Long Id = flightRouteSessionBeanRemote.createNewFlightRoute(newFlightRoute, originAirportId, destinationAirportId);
           
            System.out.println(Id);
       
        }
        
        if(option == 7) {
            System.out.println("create new flight:");
            
            System.out.println("flight number");
            String flightNum = sc.nextLine();
            
            System.out.println("flight route id");
            Long flightRouteId = sc.nextLong();
            sc.nextLine();
            
            System.out.println("aircraft type id");
            Long aircraftTypeId = sc.nextLong();
            sc.nextLine();
            
            System.out.println("aircraft config id");
            Long aircrafConfigurationId = sc.nextLong();
            sc.nextLine();
            
            Flight newFlight = new Flight(flightNum);
            
            Long Id = flightSessionBeanRemote.createNewFlight(newFlight,aircraftTypeId,flightRouteId, aircrafConfigurationId );
           
            System.out.println(Id);
       
        }
        
        if(option == 8) {
            System.out.println("delete flight:");
           
            
            System.out.println("flight  id");
            Long flightId = sc.nextLong();
            sc.nextLine();
            
           flightSessionBeanRemote.deleteFlight(flightId);
            System.out.println("deleted");
       
        }
        
        
        if(option == 9) {
            System.out.println("delete flight route:");
           
            
            System.out.println("enter flight route id");
            Long flightRouteId = sc.nextLong();
            sc.nextLine();
            
           flightRouteSessionBeanRemote.deleteFlightRoute(flightRouteId);
            System.out.println("deleted");
       
        }
        
        if(option == 10) {
            System.out.println("create flight schedule:");
           
            System.out.print("Enter departure date and time (yyyy-MM-ddTHH:mm:ss): ");
            String departureInput = sc.nextLine();
            LocalDateTime departureDateTime = parseDateTime(departureInput);
            System.out.print("Enter duration (e.g., PT2H30M): ");
            String durationInput = sc.nextLine();
            Duration duration = parseDuration(durationInput);// Calculate arrival date and time
            LocalDateTime arrivalDateTime = calculateArrivalDateTime(departureDateTime, duration);// Display the results
            System.out.println("Departure Date/Time: " + departureDateTime);
            System.out.println("Duration: " + duration);
            System.out.println("Updated Arrival Date/Time: " + arrivalDateTime);
            
            FlightSchedule flightSchedule = new FlightSchedule(departureDateTime, duration, arrivalDateTime);
            
            flightScheduleSessionBeanRemote.createNewFlightSchedule(flightSchedule);
        }
        
        if(option == 11) {
            System.out.println("create flight schedule plan");
           
            System.out.print("Enter departure date and time (yyyy-MM-ddTHH:mm:ss): ");
            String departureInput = sc.nextLine();
            
            LocalDateTime departureDateTime = parseDateTime(departureInput);
            System.out.print("Enter duration (e.g., PT2H30M): ");
            String durationInput = sc.nextLine();
            Duration duration = parseDuration(durationInput);// Calculate arrival date and time
            LocalDateTime arrivalDateTime = calculateArrivalDateTime(departureDateTime, duration);// Display the results
            //System.out.println("Departure Date/Time: " + departureDateTime);
            //System.out.println("Duration: " + duration);
            //System.out.println("Updated Arrival Date/Time: " + arrivalDateTime);
            
            System.out.println("enter flight id");
            Long flightId = sc.nextLong();
            
            System.out.println("enter flight schedule plan type");
            String type= sc.nextLine();
            
            FlightSchedulePlan newFlightSchedulePlan = new FlightSchedulePlan(type);
                    
            
            flightScheduleSessionBeanRemote.createNewFlightSchedulePlanSingle(flightId, departureDateTime, arrivalDateTime, duration, newFlightSchedulePlan);
        }
        
         if(option == 12) {
            System.out.println("create flight schedule plan");
           
            System.out.print("Enter departure date and time (yyyy-MM-ddTHH:mm:ss): ");
            String departureInput = sc.nextLine();
            LocalDateTime departureDateTime = parseDateTime(departureInput);
            System.out.print("Enter duration (e.g., PT2H30M): ");
            String durationInput = sc.nextLine();
            Duration duration = parseDuration(durationInput);// Calculate arrival date and time
            LocalDateTime arrivalDateTime = calculateArrivalDateTime(departureDateTime, duration);// Display the results
            //System.out.println("Departure Date/Time: " + departureDateTime);
            //System.out.println("Duration: " + duration);
            //System.out.println("Updated Arrival Date/Time: " + arrivalDateTime);
            
            System.out.print("Enter n): ");
            int Input = sc.nextInt();
            sc.nextLine();
            
             System.out.print("Enter end date and time (yyyy-MM-ddTHH:mm:ss): ");
            String lastdayInput = sc.nextLine();
            LocalDateTime lastDateTime = parseDateTime(lastdayInput);
            
            
            System.out.println("enter flight id");
            Long flightId = sc.nextLong();
            sc.nextLine();
            
            System.out.println("enter flight schedule plan type");
            String type= sc.nextLine();
            
            FlightSchedulePlan newFlightSchedulePlan = new FlightSchedulePlan(type);
                    
             //public Long createRecurringFlightSchedulePlan(Long flightId, LocalDateTime departureTime, LocalDateTime arrivalTime, Duration duration, LocalDateTime endDate, int n, FlightSchedulePlan newFlightSchedulePlan) {
            flightScheduleSessionBeanRemote.createRecurringFlightSchedulePlan(flightId, departureDateTime, arrivalDateTime, duration, lastDateTime, Input, newFlightSchedulePlan);
                    //createNewFlightSchedulePlanSingle(flightId, departureDateTime, arrivalDateTime, duration, newFlightSchedulePlan);
        }
        
        

                
    }
    
    private static Duration parseDuration(String userInput) {
       
            // Assuming the input format is PT2H30M
            // Extracting hours and minutes from the input string
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
    
    
}*/
   

