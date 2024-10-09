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
