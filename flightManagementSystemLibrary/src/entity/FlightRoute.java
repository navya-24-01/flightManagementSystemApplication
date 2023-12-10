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

/**
 *
 * @author aman
 */
@Entity
public class FlightRoute implements Serializable { //Check on the client side if need revserse

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightRouteId;
    
  
    @Column(nullable = false)
    private Boolean disabled; 
    
    @Column(nullable = false)
    private Boolean returnRoute; 
    
    //relationships
    @ManyToOne
    private Airport origin;
    
    @ManyToOne
    private Airport destination;
    
    @OneToMany(mappedBy = "flightRoute")
    private List<Flight> flightList;
    
    
    //constructors


    public FlightRoute() {
        this.disabled = false;
        this.flightList = new ArrayList<Flight>();
        this.returnRoute = false;
    }
    
    public FlightRoute(boolean returnRoute) {
        this.disabled = false;
        this.flightList = new ArrayList<Flight>();
        this.returnRoute = returnRoute;
    }
    
    //getters
     public Long getFlightRouteId() {
        return flightRouteId;
    }

    public Boolean getReturnRoute() {
        return returnRoute;
    }

    public void setReturnRoute(Boolean returnRoute) {
        this.returnRoute = returnRoute;
    }
     
      public Boolean getDisabled() {
        return disabled;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }
    
    
     
      
      
    //setters

    public void setFlightRouteId(Long flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightRouteId != null ? flightRouteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightRouteId fields are not set
        if (!(object instanceof FlightRoute)) {
            return false;
        }
        FlightRoute other = (FlightRoute) object;
        if ((this.flightRouteId == null && other.flightRouteId != null) || (this.flightRouteId != null && !this.flightRouteId.equals(other.flightRouteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightRoute[ id=" + flightRouteId + " ]";
    }
       
}