package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "c_foundation")
public class Foundation {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String address1;

    @Column(columnDefinition = "text")
    private String address2;

    @Column(columnDefinition = "text")
    private String address3;
    
    private String phone;

    private String country;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "tax_branch_name")
    private String taxBranchName;

    @Column(name = "tax_address1")
    private String taxAddress1;

    @Column(name = "tax_address2")
    private String taxAddress2;

    @Column(name = "tax_address3")
    private String taxAddress3;

    @Column(name = "tax_serial_no")
    private String taxSerialNo;

    @Column(name = "tax_npwp", columnDefinition = "character varying(100)")
    private String taxNpwp;

    @Column(name = "tax_pkp", columnDefinition = "character varying(100)")
    private String taxPkp;

    @Column(name = "tax_tgl_pengukuhan", columnDefinition = "character varying(100)")
    private String taxTglPengukuhan;

    @Column(name = "tax_klu_spt", columnDefinition = "character varying(100)")
    private String taxKluSpt;

    private Boolean active;

    public Foundation() {
    }

    public Foundation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTaxBranchName() {
        return taxBranchName;
    }

    public void setTaxBranchName(String taxBranchName) {
        this.taxBranchName = taxBranchName;
    }

    public String getTaxAddress1() {
        return taxAddress1;
    }

    public void setTaxAddress1(String taxAddress1) {
        this.taxAddress1 = taxAddress1;
    }

    public String getTaxAddress2() {
        return taxAddress2;
    }

    public void setTaxAddress2(String taxAddress2) {
        this.taxAddress2 = taxAddress2;
    }

    public String getTaxAddress3() {
        return taxAddress3;
    }

    public void setTaxAddress3(String taxAddress3) {
        this.taxAddress3 = taxAddress3;
    }

    public String getTaxSerialNo() {
        return taxSerialNo;
    }

    public void setTaxSerialNo(String taxSerialNo) {
        this.taxSerialNo = taxSerialNo;
    }

    public String getTaxNpwp() {
        return taxNpwp;
    }

    public void setTaxNpwp(String taxNpwp) {
        this.taxNpwp = taxNpwp;
    }

    public String getTaxPkp() {
        return taxPkp;
    }

    public void setTaxPkp(String taxPkp) {
        this.taxPkp = taxPkp;
    }

    public String getTaxTglPengukuhan() {
        return taxTglPengukuhan;
    }

    public void setTaxTglPengukuhan(String taxTglPengukuhan) {
        this.taxTglPengukuhan = taxTglPengukuhan;
    }

    public String getTaxKluSpt() {
        return taxKluSpt;
    }

    public void setTaxKluSpt(String taxKluSpt) {
        this.taxKluSpt = taxKluSpt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", startDate=" + startDate +
                ", taxBranchName='" + taxBranchName + '\'' +
                ", taxAddress1='" + taxAddress1 + '\'' +
                ", taxAddress2='" + taxAddress2 + '\'' +
                ", taxAddress3='" + taxAddress3 + '\'' +
                ", taxSerialNo='" + taxSerialNo + '\'' +
                ", taxNpwp='" + taxNpwp + '\'' +
                ", taxPkp='" + taxPkp + '\'' +
                ", taxTglPengukuhan='" + taxTglPengukuhan + '\'' +
                ", taxKluSpt='" + taxKluSpt + '\'' +
                ", active=" + active +
                '}';
    }
}
