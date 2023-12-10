package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author aman
 */
@Entity
public class SeatingInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatingInventoryId;
    
    @Column(nullable = false)
    private int availableSeats;
    
    
    @Column(nullable = false)
    private int occupiedSeats;
     
    
    
    
    //Relationships
    @ManyToOne
    CabinClassConfiguration cabinClassConfiguration;
    
    
    //Relationship with Fs is unidirection so dont need to add here but add to FS!!!

    public SeatingInventory() {
       
    }

    public SeatingInventory(int availableSeats, int occupiedSeats) {
        this.availableSeats = availableSeats;
        this.occupiedSeats = occupiedSeats;
    }
   
    

    public Long getSeatingInventoryId() {
        return seatingInventoryId;
    }

    public void setSeatingInventoryId(Long seatingInventoryId) {
        this.seatingInventoryId = seatingInventoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seatingInventoryId != null ? seatingInventoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the seatingInventoryId fields are not set
        if (!(object instanceof SeatingInventory)) {
            return false;
        }
        SeatingInventory other = (SeatingInventory) object;
        if ((this.seatingInventoryId == null && other.seatingInventoryId != null) || (this.seatingInventoryId != null && !this.seatingInventoryId.equals(other.seatingInventoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SeatingInventory[ id=" + seatingInventoryId + " ]";
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
    

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public CabinClassConfiguration getCabinClassConfiguration() {
        return cabinClassConfiguration;
    }

    public void setCabinClassConfiguration(CabinClassConfiguration cabinClassConfiguration) {
        this.cabinClassConfiguration = cabinClassConfiguration;
    }

   
    
}