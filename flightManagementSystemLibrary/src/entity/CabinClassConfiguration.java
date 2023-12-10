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
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author navyamunjal
 */
@Entity
public class CabinClassConfiguration implements Serializable {

    //Attributes
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CCCId;
    
    @Column(nullable = false, length = 30)
    private String CCCName;
    
    @Column(nullable = false)
    @Min(value = 0, message = "Please enter value greater than 0")
    @Max(value = 2, message = "Please enter value less than 2")
    private int numOfAisle;
    
    @Column(nullable = false)
    private String seatConfiguration;
    
    @Column(nullable = false)
    private int numOfRows;
    
    @Column(nullable = false)
    private int numOfSeatsAbrest;
    
    @Column(nullable = false)
    private int totalSeatCapacity;
    //Relationships
    
    @ManyToMany(mappedBy = "cabinClassConfigurationList")
    List<AircraftConfiguration> aircraftConfigurationList;
    
    //Constructors

    public CabinClassConfiguration() {
        this.aircraftConfigurationList = new ArrayList<AircraftConfiguration>();
        
    }

    public CabinClassConfiguration(String CCCName,int numOfAisle,int numOfRows,int numOfSeatsAbrest,String seatConfiguration, int totalSeatCapacity) {
        this.CCCName = CCCName;
        this.numOfAisle = numOfAisle;
        this.seatConfiguration = seatConfiguration;
        this.numOfRows = numOfRows;
        this.numOfSeatsAbrest = numOfSeatsAbrest;
        this.totalSeatCapacity = totalSeatCapacity;
        this.aircraftConfigurationList = new ArrayList<AircraftConfiguration>();
    }
    
    //Getters

    public Long getCCCId() {
        return CCCId;
    }

    public String getCCCName() {
        return CCCName;
    }

    public int getNumOfAisle() {
        return numOfAisle;
    }

   

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getTotalSeatCapacity() {
        return totalSeatCapacity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<AircraftConfiguration> getAircraftConfigurationList() {
        return aircraftConfigurationList;
    }
    
    
    
    
    
    
    //Setters
    public void setCCCId(Long CCCId) {
        this.CCCId = CCCId;
    }

    public void setCCCName(String CCCName) {
        this.CCCName = CCCName;
    }

    public void setNumOfAisle(int numOfAisle) {
        this.numOfAisle = numOfAisle;
    }

    
    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

 
    public void setTotalSeatCapacity(int totalSeatCapacity) {    
        this.totalSeatCapacity = totalSeatCapacity;
    }

    public void setAircraftConfigurationList(List<AircraftConfiguration> aircraftConfigurationList) {
        this.aircraftConfigurationList = aircraftConfigurationList;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (CCCId != null ? CCCId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabinClassConfiguration)) {
            return false;
        }
        CabinClassConfiguration other = (CabinClassConfiguration) object;
        if ((this.CCCId == null && other.CCCId != null) || (this.CCCId != null && !this.CCCId.equals(other.CCCId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CabinClassConfiguration[ id=" + CCCId + " ]";
    }

    public void setNumOfSeatsAbrest(int numOfSeatsAbrest) {
        this.numOfSeatsAbrest = numOfSeatsAbrest;
    }

    public int getNumOfSeatsAbrest() {
        return numOfSeatsAbrest;
    }

    public void setSeatConfiguration(String seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }

    public String getSeatConfiguration() {
        return seatConfiguration;
    }
    
}
