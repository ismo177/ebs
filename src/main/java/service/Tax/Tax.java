package service.Tax;

import service.Customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tax")
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "meter_loc", length = 45)
    private String meterLoc;

    @Column(name = "meter_type", length = 45)
    private String meterType;

    @Column(name = "phase_code")
    private Integer phaseCode;

    @Column(name = "type", length = 45)
    private String type;

    @Column(name = "meter_rent", precision = 4, scale = 2)
    private BigDecimal meterRent;

    @Column(name = "service_rent", precision = 4, scale = 2)
    private BigDecimal serviceRent;

    @Column(name = "tier_rate", precision = 4, scale = 2)
    private BigDecimal tierRate;

    @Column(name="off_peak_price", precision = 4, scale = 2)
    private BigDecimal offPeakPrice;

    @Column(name="on_peak_price", precision = 4, scale = 2)
    private BigDecimal onPeakPrice;

    @Column(name="PDVTax", precision = 4, scale = 2)
    private BigDecimal PDVTax;


//---------------------------------------------
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "tax")
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
//----------------------------------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeterLoc() {
        return meterLoc;
    }

    public void setMeterLoc(String meterLoc) {
        this.meterLoc = meterLoc;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public Integer getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(Integer phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMeterRent() {
        return meterRent;
    }

    public void setMeterRent(BigDecimal meterRent) {
        this.meterRent = meterRent;
    }

    public BigDecimal getServiceRent() {
        return serviceRent;
    }

    public void setServiceRent(BigDecimal serviceRent) {
        this.serviceRent = serviceRent;
    }

    public BigDecimal getTierRate() {
        return tierRate;
    }

    public void setTierRate(BigDecimal tierRate) {
        this.tierRate = tierRate;
    }

    public BigDecimal getOffPeakPrice() {
        return offPeakPrice;
    }

    public BigDecimal getOnPeakPrice() {
        return onPeakPrice;
    }

    public void setOnPeakPrice(BigDecimal onPeakPrice) {
        this.onPeakPrice = onPeakPrice;
    }

    public void setOffPeakPrice(BigDecimal offPeakPrice) {
        this.offPeakPrice = offPeakPrice;
    }

    public BigDecimal getPDVTax() {
        return PDVTax;
    }

    public void setPDVTax(BigDecimal PDVTax) {
        this.PDVTax = PDVTax;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tax tax = (Tax) obj;
        return Objects.equals(id, tax.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}