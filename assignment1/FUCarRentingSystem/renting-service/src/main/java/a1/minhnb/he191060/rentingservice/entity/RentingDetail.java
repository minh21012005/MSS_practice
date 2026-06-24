package a1.minhnb.he191060.rentingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "RentingDetail")
@IdClass(RentingDetailId.class)
public class RentingDetail {

    @Id
    @Column(name = "RentingTransactionID")
    private Integer rentingTransactionId;

    @Id
    @Column(name = "CarID")
    private Integer carId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RentingTransactionID", insertable = false, updatable = false)
    @JsonIgnore
    private RentingTransaction transaction;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "EndDate")
    private Date endDate;

    @Column(name = "Price")
    private BigDecimal price;

    public RentingTransaction getTransaction() { return transaction; }
    public void setTransaction(RentingTransaction transaction) { this.transaction = transaction; }
    public Integer getRentingTransactionId() { return rentingTransactionId; }
    public void setRentingTransactionId(Integer rentingTransactionId) { this.rentingTransactionId = rentingTransactionId; }
    public Integer getCarId() { return carId; }
    public void setCarId(Integer carId) { this.carId = carId; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
