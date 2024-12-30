package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ac_student")
public class Student extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @ManyToOne
    @JoinColumn(name = "join_year_id", nullable = false)
    AcademicYear joinYear;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    Institution institution;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    StudentCategory category;

    @Column(length = 12, nullable = false)
    private String nis;

    @Column(length = 12, nullable = false)
    private String nisn;

    @Column(length = 20)
    private String nik;

    @Column(length = 100, nullable = false)
    private String name;

    private String phone;
    private String email;

    @Column(nullable = false)
    private String sex; //F: Female: M: Male

    @ManyToOne
    @JoinColumn(name = "pob_id")
    private City pob;

    private Date dob;

    @Column(name = "kk_no")
    private String kkNo; //Nomor KK (Kartu Keluarga)

    @Column(name = "birth_certificate_no")
    private String birthCertificateNo;

    @Column(length = 2)
    private String bloodType;

    private Integer childNo; // Anak ke-

    private Integer numberOfSibling; // Jumlah saudara

    @ManyToOne
    @JoinColumn(name = "religion_id", nullable = false)
    private Religion religion;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Country nationality;

    private String address1; // Perumahan/Kampung
    private String address2; // Alamat jalan/blok
    private String address3; // Nomor Rumah
    private String rt;
    private String rw;

    @ManyToOne
    @JoinColumn(name = "sub_district_id")
    private SubDistrict subDistrict;

    private String postalCode;

    private String kksNo; // Nomor KKS (Kartu keluarga sejahtera)

    private Boolean yatim;

    private Integer height; // Tinggi badan
    private Integer weight; // Berat badan

    @ManyToOne
    @JoinColumn(name = "origin_school_id")
    private ReferalInstitution originSchool;

    private String examParticipantNo; //Nomor Peserta Ujian (Jika Sudah Memiliki)
    private String certificateNo; //No. Seri Ijazah (Jika Sudah Memiliki)
    private String skhunNo; //No. Seri SKHUN (Jika Memiliki)

    @ManyToOne
    @JoinColumn(name = "father_id")
    private PersonData father;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    private PersonData mother;

    @ManyToOne
    @JoinColumn(name = "father_guardian_id")
    private PersonData fatherGuardian;

    @ManyToOne
    @JoinColumn(name = "mother_guardian_id")
    private PersonData motherGuardian;

    private String photo;

    @Column(columnDefinition = "text")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "class_room_id")
    private ClassRoom classRoom;

    @Column(columnDefinition = "integer default 1")
    private Integer status; //0: NOT ACTIVE, 1: ACTIVE, 2: LULUS, 3: PINDAH

    public Student() {
    }

    public Student(String id) {
        setId(id);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }

    public AcademicYear getJoinYear() {
        return joinYear;
    }

    public void setJoinYear(AcademicYear joinYear) {
        this.joinYear = joinYear;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public StudentCategory getCategory() {
        return category;
    }

    public void setCategory(StudentCategory category) {
        this.category = category;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public City getPob() {
        return pob;
    }

    public void setPob(City pob) {
        this.pob = pob;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getKkNo() {
        return kkNo;
    }

    public void setKkNo(String kkNo) {
        this.kkNo = kkNo;
    }

    public String getBirthCertificateNo() {
        return birthCertificateNo;
    }

    public void setBirthCertificateNo(String birthCertificateNo) {
        this.birthCertificateNo = birthCertificateNo;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getChildNo() {
        return childNo;
    }

    public void setChildNo(Integer childNo) {
        this.childNo = childNo;
    }

    public Integer getNumberOfSibling() {
        return numberOfSibling;
    }

    public void setNumberOfSibling(Integer numberOfSibling) {
        this.numberOfSibling = numberOfSibling;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
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

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public SubDistrict getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getKksNo() {
        return kksNo;
    }

    public void setKksNo(String kksNo) {
        this.kksNo = kksNo;
    }

    public Boolean getYatim() {
        return yatim;
    }

    public void setYatim(Boolean yatim) {
        this.yatim = yatim;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public ReferalInstitution getOriginSchool() {
        return originSchool;
    }

    public void setOriginSchool(ReferalInstitution originSchool) {
        this.originSchool = originSchool;
    }

    public String getExamParticipantNo() {
        return examParticipantNo;
    }

    public void setExamParticipantNo(String examParticipantNo) {
        this.examParticipantNo = examParticipantNo;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getSkhunNo() {
        return skhunNo;
    }

    public void setSkhunNo(String skhunNo) {
        this.skhunNo = skhunNo;
    }

    public PersonData getFather() {
        return father;
    }

    public void setFather(PersonData father) {
        this.father = father;
    }

    public PersonData getMother() {
        return mother;
    }

    public void setMother(PersonData mother) {
        this.mother = mother;
    }

    public PersonData getFatherGuardian() {
        return fatherGuardian;
    }

    public void setFatherGuardian(PersonData fatherGuardian) {
        this.fatherGuardian = fatherGuardian;
    }

    public PersonData getMotherGuardian() {
        return motherGuardian;
    }

    public void setMotherGuardian(PersonData motherGuardian) {
        this.motherGuardian = motherGuardian;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
