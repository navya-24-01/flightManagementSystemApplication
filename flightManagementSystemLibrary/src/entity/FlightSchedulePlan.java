/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class FlightSchedulePlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightSchedulePlanId;
    
    @Column(nullable = false)
    private String scheduleType;
    
     @Column(nullable = false)
    private boolean returnFsp;
     
      @Column(nullable = false)
    private LocalDateTime firstDeparture;
    
    
    
    
    
    //relationships
    //RS with flight
    @ManyToOne
    private Flight flight; 

    //RS with Flight schedule
    @OneToMany(mappedBy = "flightSchedulePlan")
    private List<FlightSchedule> flightScheduleList;
    
    //RS with Fare
    @OneToMany()
    private List<Fare> fareList;
    
    
    
    
    //Constructors

    public FlightSchedulePlan() {
        List<FlightSchedule> flightScheduleList = new ArrayList<>();
        List<Fare> fareList = new ArrayList<>();
        this.returnFsp = false;
    }

    public FlightSchedulePlan(String scheduleType, boolean returnFsp, LocalDateTime firstDeparture) {
        this.scheduleType = scheduleType;
        List<FlightSchedule> flightScheduleList = new ArrayList<>();
        List<Fare> fareList = new ArrayList<>();
        this.returnFsp = returnFsp;
        this.firstDeparture = firstDeparture;
    }
     
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightSchedulePlanId != null ? flightSchedulePlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightSchedulePlanId fields are not set
        if (!(object instanceof FlightSchedulePlan)) {
            return false;
        }
        FlightSchedulePlan other = (FlightSchedulePlan) object;
        if ((this.flightSchedulePlanId == null && other.flightSchedulePlanId != null) || (this.flightSchedulePlanId != null && !this.flightSchedulePlanId.equals(other.flightSchedulePlanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightSchedulePlan[ id=" + flightSchedulePlanId + " ]";
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<FlightSchedule> getFlightScheduleList() {
        return flightScheduleList;
    }

    public void setFlightScheduleList(List<FlightSchedule> flightScheduleList) {
        this.flightScheduleList = flightScheduleList;
    }

   /*public List<Fare> getFareList() {
        return fareList;
    }

    public void setFareList(List<Fare> fareList) {
        this.fareList = fareList;
    }*/
    
        

    public Long getFlightSchedulePlanId() {
        return flightSchedulePlanId;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public List<Fare> getFareList() {
        return fareList;
    }

    public void setFareList(List<Fare> fareList) {
        this.fareList = fareList;
    }
    
    

    public void setFlightSchedulePlanId(Long flightSchedulePlanId) {
        this.flightSchedulePlanId = flightSchedulePlanId;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public boolean isReturnFsp() {
        return returnFsp;
    }

    public void setReturnFsp(boolean returnFsp) {
        this.returnFsp = returnFsp;
    }

    public LocalDateTime getFirstDeparture() {
        return firstDeparture;
    }

    public void setFirstDeparture(LocalDateTime firstDeparture) {
        this.firstDeparture = firstDeparture;
    }
    
    
    
    
    
}