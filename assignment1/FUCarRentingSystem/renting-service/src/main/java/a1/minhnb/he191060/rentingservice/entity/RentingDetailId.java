package a1.minhnb.he191060.rentingservice.entity;

import java.io.Serializable;
import java.util.Objects;

public class RentingDetailId implements Serializable {
    private Integer rentingTransactionId;
    private Integer carId;

    public RentingDetailId() {}

    public RentingDetailId(Integer rentingTransactionId, Integer carId) {
        this.rentingTransactionId = rentingTransactionId;
        this.carId = carId;
    }

    public Integer getRentingTransactionId() { return rentingTransactionId; }
    public void setRentingTransactionId(Integer rentingTransactionId) { this.rentingTransactionId = rentingTransactionId; }
    public Integer getCarId() { return carId; }
    public void setCarId(Integer carId) { this.carId = carId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentingDetailId that = (RentingDetailId) o;
        return Objects.equals(rentingTransactionId, that.rentingTransactionId) && Objects.equals(carId, that.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentingTransactionId, carId);
    }
}
