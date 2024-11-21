package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class StudentVM {
    private String id;

    private String joinYearId;
    private String institutionId;
    private String categoryId;
    @NotNull
    private String nis;

    private String nisn;
    private String nik;

    @NotNull
    private String name;

    private String phone;
    private String email;

    @NotNull
    private String sex; //F: Female: M: Male

    private Integer pobId;

    private Date dob;

    private String birthCertificateNo;

    private String bloodType;

    private Integer childNo; // Anak ke-

    private Integer numberOfSibling; // Jumlah saudara

    private Integer religionId;

    private Integer nationalityId;

    private String address1; // Perumahan/Kampung
    private String address2; // Alamat jalan/blok
    private String address3; // Nomor Rumah
    private String rt;
    private String rw;

    private Integer subDistrictId;

    private String postalCode;

    private String kksNo; // Nomor KKS (Kartu keluarga sejahtera)

    private Boolean yatim;

    private Integer height; // Tinggi badan
    private Integer weight; // Berat badan

    private String originSchoolId;

    private String examParticipantNo; //Nomor Peserta Ujian (Jika Sudah Memiliki)
    private String certificateNo; //No. Seri Ijazah (Jika Sudah Memiliki)
    private String skhunNo; //No. Seri SKHUN (Jika Memiliki)

    private GuardianVM father;
    private GuardianVM mother;
    private GuardianVM fatherGuardian;
    private GuardianVM motherGuardian;
    private String status;
    private String photo;
    private String notes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StudentVM() {
    }

    public String getJoinYearId() {
        return joinYearId;
    }

    public void setJoinYearId(String joinYearId) {
        this.joinYearId = joinYearId;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public Integer getPobId() {
        return pobId;
    }

    public void setPobId(Integer pobId) {
        this.pobId = pobId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public Integer getReligionId() {
        return religionId;
    }

    public void setReligionId(Integer religionId) {
        this.religionId = religionId;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
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

    public Integer getSubDistrictId() {
        return subDistrictId;
    }

    public void setSubDistrictId(Integer subDistrictId) {
        this.subDistrictId = subDistrictId;
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

    public String getOriginSchoolId() {
        return originSchoolId;
    }

    public void setOriginSchoolId(String originSchoolId) {
        this.originSchoolId = originSchoolId;
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

    public GuardianVM getFather() {
        return father;
    }

    public void setFather(GuardianVM father) {
        this.father = father;
    }

    public GuardianVM getMother() {
        return mother;
    }

    public void setMother(GuardianVM mother) {
        this.mother = mother;
    }

    public GuardianVM getFatherGuardian() {
        return fatherGuardian;
    }

    public void setFatherGuardian(GuardianVM fatherGuardian) {
        this.fatherGuardian = fatherGuardian;
    }

    public GuardianVM getMotherGuardian() {
        return motherGuardian;
    }

    public void setMotherGuardian(GuardianVM motherGuardian) {
        this.motherGuardian = motherGuardian;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
