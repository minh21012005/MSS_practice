package a1.minhnb.he191060.carservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID")
    private Integer supplierId;
    @Column(name = "SupplierName")
    private String supplierName;
    @Column(name = "SupplierDescription")
    private String supplierDescription;
    @Column(name = "SupplierAddress")
    private String supplierAddress;

    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public String getSupplierDescription() { return supplierDescription; }
    public void setSupplierDescription(String supplierDescription) { this.supplierDescription = supplierDescription; }
    public String getSupplierAddress() { return supplierAddress; }
    public void setSupplierAddress(String supplierAddress) { this.supplierAddress = supplierAddress; }
}
