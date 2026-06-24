package a1.minhnb.he191060.carservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ManufacturerID")
    private Integer manufacturerId;
    @Column(name = "ManufacturerName")
    private String manufacturerName;
    @Column(name = "Description")
    private String description;
    @Column(name = "ManufacturerCountry")
    private String manufacturerCountry;

    public Integer getManufacturerId() { return manufacturerId; }
    public void setManufacturerId(Integer manufacturerId) { this.manufacturerId = manufacturerId; }
    public String getManufacturerName() { return manufacturerName; }
    public void setManufacturerName(String manufacturerName) { this.manufacturerName = manufacturerName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getManufacturerCountry() { return manufacturerCountry; }
    public void setManufacturerCountry(String manufacturerCountry) { this.manufacturerCountry = manufacturerCountry; }
}
