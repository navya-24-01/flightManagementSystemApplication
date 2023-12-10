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
import javax.persistence.OneToMany;

/**
 *
 * @author navyamunjal
 */
@Entity
public class Airport implements Serializable {

    //Attributes
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;
    
    @Column(nullable = false, length = 30)
    private String airportName;
    
    @Column(nullable = false, length = 3, unique = true)
    private String IATACode;
    
    @Column(nullable = false, length = 30)
    private String City;
    
    @Column(nullable = false, length = 30)
    private String State;
    
    @Column(nullable = false, length = 30)
    private String Country;
    
    //relationships
    @OneToMany(mappedBy = "origin")
    private List<FlightRoute> originFlightRouteList;
    
    
    @OneToMany(mappedBy = "destination")
    private List<FlightRoute> destinationFlightRouteList;
    
    
    //Constructors

    public Airport() {
        this.originFlightRouteList = new ArrayList<FlightRoute>();
        this.destinationFlightRouteList = new ArrayList<FlightRoute>();
    }

    public Airport(String airportName, String IATACode, String City, String State, String Country) {
        this.airportName = airportName;
        this.IATACode = IATACode;
        this.City = City;
        this.State = State;
        this.Country = Country;
        this.originFlightRouteList = new ArrayList<FlightRoute>();
        this.destinationFlightRouteList = new ArrayList<FlightRoute>();
    }
    
    
    //getters
    
     public Long getAirportId() {
        return airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getCity() {
        return City;
    }

    public String getIATACode() {
        return IATACode;
    }

    public String getState() {
        return State;
    }

    public String getCountry() {
        return Country;
    }

    public List<FlightRoute> getOriginFlightRouteList() {
        return originFlightRouteList;
    }

    public List<FlightRoute> getDestinationFlightRouteList() {
        return destinationFlightRouteList;
    }
    
    
     
     
    //setters

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }
    
    
    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public void setOriginFlightRouteList(List<FlightRoute> originFlightRouteList) {
        this.originFlightRouteList = originFlightRouteList;
    }

    public void setDestinationFlightRouteList(List<FlightRoute> destinationFlightRouteList) {
        this.destinationFlightRouteList = destinationFlightRouteList;
    }
    
    
    
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (airportId != null ? airportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the airportId fields are not set
        if (!(object instanceof Airport)) {
            return false;
        }
        Airport other = (Airport) object;
        if ((this.airportId == null && other.airportId != null) || (this.airportId != null && !this.airportId.equals(other.airportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Airport[ id=" + airportId + " ]";
    }
    
}
