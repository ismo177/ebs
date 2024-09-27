package service.Customer;

import bill.Bill;
import service.Tax.Tax;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
@NamedQuery(name="findByID", query="select c from Customer c where c.id=:id")
@NamedQuery(name="findByName", query="select c from Customer c where c.name=:name")


public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "city", length = 45)
    private String city;

    @Column(name = "country", length = 45)
    private String country;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "contract_no", nullable = false, length = 45)
    private String contractNo;

    @Column(name = "debt_balance", precision = 8, scale = 2)
    private BigDecimal debtBalance;

    @Column(name = "meter_no", nullable = false)
    private Integer meterNo;
    //------------------------------------------------
    @OneToMany(mappedBy = "customer")
    private List<Bill> bills;

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_id", referencedColumnName = "id")
    private Tax tax;

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
    //--------------------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getDebtBalance() {
        return debtBalance;
    }

    public void setDebtBalance(BigDecimal debtBalance) {
        this.debtBalance = debtBalance;
    }

    public int getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(Integer meterNo) {
        this.meterNo = meterNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone()+ '\'' +
                ", contractNo='" + getContractNo() + '\'' +
                ", debtBalance=" + getDebtBalance() +
                ", meterNo=" + getMeterNo() +
                ", bills=" + getBills() +
                ", tax=" + getTax() +
                '}';
    }

    @Override
   public boolean equals(Object obj) {
        if (this == obj) return true;
       if (obj == null || getClass() != obj.getClass()) return false;
       Customer customer = (Customer) obj;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
   }
}