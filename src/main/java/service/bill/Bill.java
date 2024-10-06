package service.bill;

import service.Customer.Customer;
import service.User.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "bill")
@NamedQuery(name= "findByMonthCustomer", query="select b from Bill b inner join b.customer c where b.month=:month and b.customer=:customer ")
@NamedQuery(name= "deleteByCustomer", query="delete from Bill b  where  b.customer=:customer ")
@NamedQuery(name="findByInvoiceStatus",query="select b from Bill b where b.invoiceStatus=:invoiceStatus")


public class Bill  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "`units_off/peak`")
    private Integer unitsOffPeak;

    @Column(name = "`units_on/peak`")
    private Integer unitsOnPeak;

    @Column(name = "read_date", length = 45)
    private String readDate;

    @Column(name = "issue_date", length = 45)
    private String issueDate;

    @Column(name = "payment_date", length = 45)
    private String paymentDate;

    @Column(name = "deadline_date", length = 45)
    private String deadlineDate;

    @Column(name = "month", length = 45)
    private String month;

    @Column(name="offPeakAmount",precision=8, scale=2)
    private BigDecimal offPeakAmount;

    @Column(name="onPeakAmount",precision=8, scale=2)
    private BigDecimal onPeakAmount;

    @Column(name = "amount", precision = 8, scale = 2)
    private BigDecimal amount;

    @Column(name = "invoice_status")
    private boolean invoiceStatus;

//-----------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;


//-------------------------------------------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnitsOffPeak() {
        return unitsOffPeak;
    }

    public void setUnitsOffPeak(Integer unitsOffPeak) {
        this.unitsOffPeak = unitsOffPeak;
    }

    public Integer getUnitsOnPeak() {
        return unitsOnPeak;
    }

    public void setUnitsOnPeak(Integer unitsOnPeak) {
        this.unitsOnPeak = unitsOnPeak;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getOffPeakAmount() {
        return offPeakAmount;
    }

    public void setOffPeakAmount(BigDecimal offPeakAmount) {
        this.offPeakAmount = offPeakAmount;
    }

    public BigDecimal getOnPeakAmount() {
        return onPeakAmount;
    }

    public void setOnPeakAmount(BigDecimal onPeakAmount) {
        this.onPeakAmount = onPeakAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(boolean invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", unitsOffPeak=" + unitsOffPeak +
                ", unitsOnPeak=" + unitsOnPeak +
                ", readDate='" + readDate + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", deadlineDate='" + deadlineDate + '\'' +
                ", month='" + month + '\'' +
                ", amount=" + amount +
                ", invoiceStatus=" + invoiceStatus +
                ", customer=" + customer +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bill bill = (Bill) obj;
        return Objects.equals(id, bill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}