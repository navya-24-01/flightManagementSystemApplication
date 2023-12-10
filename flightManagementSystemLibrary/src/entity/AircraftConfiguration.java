package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 *
 * @author aman
 */
@Entity
public class AircraftConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftConfigurationId;
    
    @Column(nullable = false, length = 32)
    private String aircraftConfigurationName;
    
    @Column(nullable = false)
   // @Min(value = 0, message = "Number of cabin classes cannot be less than 0")
    //@Max(value = 4, message = "Number of cabin classes cannot be greater than 4")
    private int noOfCabinClass;
    
    
    //relationships
    
    @ManyToMany
    private List<CabinClassConfiguration> cabinClassConfigurationList;
    
    @ManyToOne
    private AircraftType aircraftType;
    
    @OneToMany(mappedBy = "aircraftConfiguration")
    private List<Flight> flightList;
    
    //constructors

    public AircraftConfiguration() {
        this.cabinClassConfigurationList = new ArrayList<CabinClassConfiguration>();
        this.flightList = new ArrayList<Flight>();
    }

    public AircraftConfiguration(String aircraftConfigurationName, int noOfCabinClass) {
        this.aircraftConfigurationName = "ac";
        this.noOfCabinClass = noOfCabinClass;
        this.cabinClassConfigurationList = new ArrayList<CabinClassConfiguration>();
        this.flightList = new ArrayList<Flight>();
    }
    
    
    

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aircraftConfigurationId != null ? aircraftConfigurationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the aircraftConfigurationId fields are not set
        if (!(object instanceof AircraftConfiguration)) {
            return false;
        }
        AircraftConfiguration other = (AircraftConfiguration) object;
        if ((this.aircraftConfigurationId == null && other.aircraftConfigurationId != null) || (this.aircraftConfigurationId != null && !this.aircraftConfigurationId.equals(other.aircraftConfigurationId))) {
            return false;
        }
        return true;
    }
    
    
    //getters

    @Override
    public String toString() {
        return "entity.AircraftConfiguration[ id=" + aircraftConfigurationId + " ]";
    }

    public String getAircraftConfigurationName() {
        return aircraftConfigurationName;
    }

    public int getNoOfCabinClass() {
        return noOfCabinClass;
    }
    
    public Long getAircraftConfigurationId() {
        return aircraftConfigurationId;
    }

    public List<CabinClassConfiguration> getCabinClassConfigurationList() {
        return cabinClassConfigurationList;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }
    
    
    //setters

    public void setAircraftConfigurationName(String aircraftConfigurationName) {
        this.aircraftConfigurationName = aircraftConfigurationName;
    }

    public void setNoOfCabinClass(int noOfCabinClass) {
        this.noOfCabinClass = noOfCabinClass;
    }
    
    public void setAircraftConfigurationId(Long aircraftConfigurationId) {
        this.aircraftConfigurationId = aircraftConfigurationId;
    }

    public void setCabinClassConfigurationList(List<CabinClassConfiguration> cabinClassConfigurationList) {
        this.cabinClassConfigurationList = cabinClassConfigurationList;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
    
    
    
    
    
}