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
 * @author aman
 */
@Entity
public class AircraftType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftTypeId;
    
    @Column(nullable = false, length = 32)
    private String aircraftTypeName;
    
     @Column(nullable = false)
    private int maxPassenegerSeatCapacity;
    
    
     
     //Relationships
     
     @OneToMany(mappedBy = "aircraftType")
     private List<AircraftConfiguration> aircraftConfigurationList;
     
     @OneToMany(mappedBy = "aircraftType")
    private List<Flight> flightList;
     
     
     //Constructors

    public AircraftType() {
        this.aircraftConfigurationList = new ArrayList<AircraftConfiguration>();
        this.flightList = new ArrayList<Flight>();
    }

    public AircraftType(String aircraftTypeName, int maxPassenegerSeatCapacity) {
        this.aircraftTypeName = aircraftTypeName;
        this.maxPassenegerSeatCapacity = maxPassenegerSeatCapacity;
        this.aircraftConfigurationList = new ArrayList<AircraftConfiguration>();
        this.flightList = new ArrayList<Flight>();
    }
     
    
  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aircraftTypeId != null ? aircraftTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the aircraftTypeId fields are not set
        if (!(object instanceof AircraftType)) {
            return false;
        }
        AircraftType other = (AircraftType) object;
        if ((this.aircraftTypeId == null && other.aircraftTypeId != null) || (this.aircraftTypeId != null && !this.aircraftTypeId.equals(other.aircraftTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AircraftType[ id=" + aircraftTypeId + " ]";
    }

    
    //Getters
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAircraftTypeName() {
        return aircraftTypeName;
    }

    public int getMaxPassenegerSeatCapacity() {
        return maxPassenegerSeatCapacity;
    }
    
    public Long getAircraftTypeId() {
        return aircraftTypeId;
    }

    public List<AircraftConfiguration> getAircraftConfigurationList() {
        return aircraftConfigurationList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }
    
    
    
    
    //Setters

    public void setAircraftTypeName(String aircraftTypeName) {
        this.aircraftTypeName = aircraftTypeName;
    }

    public void setMaxPassenegerSeatCapacity(int maxPassenegerSeatCapacity) {
        this.maxPassenegerSeatCapacity = maxPassenegerSeatCapacity;
    }
    
    public void setAircraftTypeId(Long aircraftTypeId) {
        this.aircraftTypeId = aircraftTypeId;
    }

    public void setAircraftConfigurationList(List<AircraftConfiguration> aircraftConfigurationList) {
        this.aircraftConfigurationList = aircraftConfigurationList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
    
    
    
    
    
}