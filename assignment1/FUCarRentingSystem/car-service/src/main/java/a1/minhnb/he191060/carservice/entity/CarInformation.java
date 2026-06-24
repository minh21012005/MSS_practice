package a1.minhnb.he191060.carservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CarInformation")
public class CarInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private Integer carId;
    @Column(name = "CarName")
    private String carName;
    @Column(name = "CarDescription")
    private String carDescription;
    @Column(name = "NumberOfDoors")
    private Integer numberOfDoors;
    @Column(name = "SeatingCapacity")
    private Integer seatingCapacity;
    @Column(name = "FuelType")
    private String fuelType;
    @Column(name = "Year")
    private Integer year;
    @Column(name = "ManufacturerID")
    private Integer manufacturerId;
    @Column(name = "SupplierID")
    private Integer supplierId;
    @Column(name = "CarStatus")
    private Integer carStatus;
    @Column(name = "CarRentingPricePerDay")
    private BigDecimal carRentingPricePerDay;

    public Integer getCarId() { return carId; }
    public void setCarId(Integer carId) { this.carId = carId; }
    public String getCarName() { return carName; }
    public void setCarName(String carName) { this.carName = carName; }
    public String getCarDescription() { return carDescription; }
    public void setCarDescription(String carDescription) { this.carDescription = carDescription; }
    public Integer getNumberOfDoors() { return numberOfDoors; }
    public void setNumberOfDoors(Integer numberOfDoors) { this.numberOfDoors = numberOfDoors; }
    public Integer getSeatingCapacity() { return seatingCapacity; }
    public void setSeatingCapacity(Integer seatingCapacity) { this.seatingCapacity = seatingCapacity; }
    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getManufacturerId() { return manufacturerId; }
    public void setManufacturerId(Integer manufacturerId) { this.manufacturerId = manufacturerId; }
    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }
    public Integer getCarStatus() { return carStatus; }
    public void setCarStatus(Integer carStatus) { this.carStatus = carStatus; }
    public BigDecimal getCarRentingPricePerDay() { return carRentingPricePerDay; }
    public void setCarRentingPricePerDay(BigDecimal carRentingPricePerDay) { this.carRentingPricePerDay = carRentingPricePerDay; }
}
