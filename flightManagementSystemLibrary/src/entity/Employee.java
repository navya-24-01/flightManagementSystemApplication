package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author aman
 */
@Entity
public class Employee implements Serializable {
    
    
    //Attributes

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    
    
    @Column(nullable = false, length = 32)
    private String employeeName;
    
    @Column(nullable = false, length = 32)
    private String employeePassword;
    
    @Column(nullable = false, length = 64)
    private String employeeUserRole;

    
    //Relationships
    
    
    
    //Constructors
    
    
    public Employee() {
    }

    public Employee(String employeeName, String employeePassword, String employeeUserRole) {
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.employeeUserRole = employeeUserRole;
    }
    
    
    
    
    
    
    
     //Getters and Setters
    

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getEmployeeId() != null ? getEmployeeId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the employeeId fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.getEmployeeId() == null && other.getEmployeeId() != null) || (this.getEmployeeId() != null && !this.employeeId.equals(other.employeeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Employee[ id=" + getEmployeeId() + " ]";
    }

    
   
    
    
    
    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the employeePassword
     */
    public String getEmployeePassword() {
        return employeePassword;
    }

    /**
     * @param employeePassword the employeePassword to set
     */
    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    /**
     * @return the employeeUserRole
     */
    public String getEmployeeUserRole() {
        return employeeUserRole;
    }

    /**
     * @param employeeUserRole the employeeUserRole to set
     */
    public void setEmployeeUserRole(String employeeUserRole) {
        this.employeeUserRole = employeeUserRole;
    }
    
}
