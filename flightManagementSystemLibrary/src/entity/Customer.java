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
 * @author aman
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    
    @Column(nullable = false,length = 32)
    private String firstName;
    
    @Column(nullable = false,length = 32)
    private String lastName;
    
    @Column(nullable = false,length = 32, unique = true)
    private String email;
    
    @Column(nullable = false,length = 16, unique = true)
    private String phoneNo;
    
    @Column(nullable = false,length = 64)
    private String address;
    
    
    @Column(nullable = false,length = 32)
    private String password;
    
    @Column(nullable = false)
    private Boolean isloggedIn;
    
    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservationList;
    
    
    //relatsionships
    
    
    //Constructors

    public Customer() {
        this.reservationList = new ArrayList<Reservation>();
    }

    public Customer(String firstName, String lastName, String email, String phoneNo, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.password = password;
        this.isloggedIn = false;
        this.reservationList = new ArrayList<Reservation>();
    }
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the customerId fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Customer[ id=" + customerId + " ]";
    }
    
    
    //getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }



    public String getPassword() {
        return password;
    }

    public Boolean getIsloggedIn() {
        return isloggedIn;
    }
    
    
    //Setters
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsloggedIn(Boolean isloggedIn) {
        this.isloggedIn = isloggedIn;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
      
    
}