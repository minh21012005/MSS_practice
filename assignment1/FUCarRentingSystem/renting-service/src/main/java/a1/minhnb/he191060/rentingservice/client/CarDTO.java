package a1.minhnb.he191060.rentingservice.client;
import java.math.BigDecimal;
public class CarDTO {
    private Integer carId;
    private BigDecimal carRentingPricePerDay;
    public Integer getCarId() { return carId; }
    public void setCarId(Integer carId) { this.carId = carId; }
    public BigDecimal getCarRentingPricePerDay() { return carRentingPricePerDay; }
    public void setCarRentingPricePerDay(BigDecimal carRentingPricePerDay) { this.carRentingPricePerDay = carRentingPricePerDay; }
}
