/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.CabinClassConfiguration;
import entity.Fare;
import entity.FlightSchedule;
import entity.FlightSchedulePlan;
import entity.SeatingInventory;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author navyamunjal
 */
@Remote
public interface FlightScheduleSessionBeanRemote {
     public Long createNewFlightSchedule(FlightSchedule flightSchedule);
     public Long createRecurringFlightSchedulePlan(Long flightId, LocalDateTime departureTime, LocalDateTime arrivalTime, Duration duration, LocalDateTime endDate, int n, FlightSchedulePlan newFlightSchedulePlan);
     public Long createNewFlightSchedulePlanSingle(Long flightId, LocalDateTime departureTime, LocalDateTime arrivalTime, Duration duration, FlightSchedulePlan newFlightSchedulePlan);

    public void createNewFareForFlightSchedulePlan(Fare newFare, Long flightSchedulePlanId, Long cabinClassConfigurationId);

    public void createNewInventoryForFlightSchedule(SeatingInventory seatingInventory, Long flightScheduleId, Long cabinClassConfigurationId);

    public FlightSchedulePlan retriveFlightSchedulePlanByID(Long flightSchedulePlanId);

    public List<FlightSchedule> searchFlightNDaysBeforeOneWay(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate);
    
     public List<FlightSchedule> searchFlightNDaysBeforeReturn(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate,LocalDate returnDate);

    public List<List<FlightSchedule>> searchFlightsWithOneStopover(Long originAirportId, Long destinationAirportId, LocalDate departureDate);

    public FlightSchedulePlan retrieveFlightRouteById(Long flightSchedulePlanId);

    public FlightSchedule retrieveFlightScheduleById(Long flightSchedulePlanId);

    public List<FlightSchedulePlan> viewAllFlightSchedulePlansInOrder();

    public Long createFlightSchedulePlanMultiple(Long flightId, List<LocalDateTime> departureTime, List<LocalDateTime> arrivalTime, List<Duration> duration, int n, FlightSchedulePlan newFlightSchedulePlan);

    public List<FlightSchedule> searchFlightNDaysAfterOneWay(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate);

    //public List<List<FlightSchedule>> searchConnectingFlights(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate);

    public List<List<FlightSchedule>> searchConnectingFlightsNDaysAfter(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate);

    public List<List<FlightSchedule>> searchConnectingFlightsNDaysBefore(Long originAirportId, Long destinationAirportId, int n, LocalDate departureDate);

    public CabinClassConfiguration retrieveCabinClassConfigurationById(Long cabinClassConfigurationId);
}
