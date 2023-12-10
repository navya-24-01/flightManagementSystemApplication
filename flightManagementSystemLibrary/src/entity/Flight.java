
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author aman
 */
@Entity
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    
    //@Pattern(regexp = "ML[A-Z0-9]{3}", message = "Flight number must start with Merlion Airlines IATA code (ML) followed by three alphanumeric characters.")
    //@Size(max = 6, message = "Flight number must be maximum 6 characters.")
    @Column(nullable = false, length = 6)
    private String flightNumber;
    
    @Column(nullable = false)
    private Boolean disabled;
    
    @Column(nullable = false)
    private Boolean returnFlight;
    
    
    //relations
    @ManyToOne
    private FlightRoute flightRoute;
    
    @ManyToOne
    private AircraftType aircraftType;
    
    @ManyToOne
    private AircraftConfiguration aircraftConfiguration;
    
    @OneToMany(mappedBy = "flight")
    private List<FlightSchedulePlan> flightSchedulePlanList;
    
    
    //R/S with flight route and aircraft configuration
    
    
    //constructors

    public Flight() {
        this.flightSchedulePlanList = new ArrayList<FlightSchedulePlan>();
        this.disabled = false;
    }

    public Flight(String flightNumber, boolean returnFlight) {
        this.flightNumber = flightNumber;
        this.disabled = false;
        this.flightSchedulePlanList = new ArrayList<FlightSchedulePlan>();
        this.returnFlight = returnFlight;
    }
    
    
    

    public Long getFlightId() {
        return flightId;
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public Boolean getDisabled() {
        return disabled;
        
    }

    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public AircraftConfiguration getAircraftConfiguration() {
        return aircraftConfiguration;
    }

    public List<FlightSchedulePlan> getFlightSchedulePlanList() {
        return flightSchedulePlanList;
    }
    
    
    
    
    
    
    //setters
    
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }


    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setAircraftConfiguration(AircraftConfiguration aircraftConfiguration) {
        this.aircraftConfiguration = aircraftConfiguration;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public void setFlightSchedulePlanList(List<FlightSchedulePlan> flightSchedulePlanList) {
        this.flightSchedulePlanList = flightSchedulePlanList;
    }

    public Boolean getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Boolean returnFlight) {
        this.returnFlight = returnFlight;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightId != null ? flightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightId fields are not set
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.flightId == null && other.flightId != null) || (this.flightId != null && !this.flightId.equals(other.flightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Flight[ id=" + flightId + " ]";
    }
      
    
}