package a1.minhnb.he191060.customerservice.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Integer customerId;

    @Column(name = "CustomerName")
    private String customerName;

    @Column(name = "Telephone")
    private String telephone;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "CustomerBirthday")
    private Date customerBirthday;

    @Column(name = "CustomerStatus")
    private Integer customerStatus;

    @Column(name = "Password")
    private String password;

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getCustomerBirthday() { return customerBirthday; }
    public void setCustomerBirthday(Date customerBirthday) { this.customerBirthday = customerBirthday; }
    public Integer getCustomerStatus() { return customerStatus; }
    public void setCustomerStatus(Integer customerStatus) { this.customerStatus = customerStatus; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
