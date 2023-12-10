/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author aman
 */
@Entity
public class FlightSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightScheduleId;
    
    @Column(nullable = false)
    private LocalDateTime departureDateTime;
    
    @Column(nullable = false)
    private Duration estimatedFlightDuration;
    
    @Column(nullable = false)
    private LocalDateTime arrivalDateTime;
    
    @ManyToOne
    private FlightSchedulePlan flightSchedulePlan;
    
    @OneToMany
    private List<SeatingInventory> seatingInventory;
    
    @OneToMany(mappedBy = "flightSchedule")
    private List<Reservation> reservationList;
    
    
    
    

    public FlightSchedule() {
         this.seatingInventory = new ArrayList<SeatingInventory>();
         this.reservationList = new ArrayList<Reservation>();
    }

    public FlightSchedule(LocalDateTime departureDateTime, Duration estimatedFlightDuration, LocalDateTime arrivalDateTime) {
        this.departureDateTime = departureDateTime;
        this.estimatedFlightDuration = estimatedFlightDuration;
        this.arrivalDateTime = arrivalDateTime;
        this.seatingInventory = new ArrayList<SeatingInventory>();
        this.reservationList = new ArrayList<Reservation>();
        
    }

    
    public Long getFlightScheduleId() {
        return flightScheduleId;
    }

    public void setFlightScheduleId(Long flightScheduleId) {
        this.flightScheduleId = flightScheduleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightScheduleId != null ? flightScheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightScheduleId fields are not set
        if (!(object instanceof FlightSchedule)) {
            return false;
        }
        FlightSchedule other = (FlightSchedule) object;
        if ((this.flightScheduleId == null && other.flightScheduleId != null) || (this.flightScheduleId != null && !this.flightScheduleId.equals(other.flightScheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightSchedule[ id=" + flightScheduleId + " ]";
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public FlightSchedulePlan getFlightSchedulePlan() {
        return flightSchedulePlan;
    }

    public void setFlightSchedulePlan(FlightSchedulePlan flightSchedulePlan) {
        this.flightSchedulePlan = flightSchedulePlan;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Duration getEstimatedFlightDuration() {
        return estimatedFlightDuration;
    }

    public void setEstimatedFlightDuration(Duration estimatedFlightDuration) {
        this.estimatedFlightDuration = estimatedFlightDuration;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public List<SeatingInventory> getSeatingInventory() {
        return seatingInventory;
    }

    public void setSeatingInventory(List<SeatingInventory> seatingInventory) {
        this.seatingInventory = seatingInventory;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
        
    
}