package a1.minhnb.he191060.rentingservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RentingTransaction")
public class RentingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RentingTransationID")
    private Integer rentingTransactionId;

    @Column(name = "RentingDate")
    private Date rentingDate;

    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;

    @Column(name = "CustomerID")
    private Integer customerId;

    @Column(name = "RentingStatus")
    private Integer rentingStatus;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentingDetail> details;

    public Integer getRentingTransactionId() { return rentingTransactionId; }
    public void setRentingTransactionId(Integer rentingTransactionId) { this.rentingTransactionId = rentingTransactionId; }
    public Date getRentingDate() { return rentingDate; }
    public void setRentingDate(Date rentingDate) { this.rentingDate = rentingDate; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public Integer getRentingStatus() { return rentingStatus; }
    public void setRentingStatus(Integer rentingStatus) { this.rentingStatus = rentingStatus; }
    public List<RentingDetail> getDetails() { return details; }
    public void setDetails(List<RentingDetail> details) { this.details = details; }
}
