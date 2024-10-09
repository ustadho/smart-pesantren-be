package id.smartpesantren.service.dto;

import java.util.*;

public class CompanyInfoDTO {
    private String serverDate;
    private String id;
    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String phone;
    private String country;
    private Date startDate;
    private String taxBranchName;
    private String taxAddress1;
    private String taxAddress2;
    private String taxAddress3;
    private String taxSerialNo;
    private String taxNpwp;
    private String taxPkp;
    private String taxTglPengukuhan;
    private String taxKluSpt;
    private Integer currencyId;

    private Boolean multiBranch;

    private Boolean cashPayment;
    private String cashAccountId;
    private String cashAccountName;
    private String roundedAccountId;
    private String roundedAccountName;

    private Boolean debitCardPayment;

    private Boolean creditCardPayment;

    private Boolean couponPayment;

    private String defaultCustomerId;
    private String defaultWarehouseId;

    public CompanyInfoDTO() {
    }



    public String getServerDate() {
        return serverDate;
    }

    public void setServerDate(String serverDate) {
        this.serverDate = serverDate;
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

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String toString() {
        return "CompanyInfoDTO{" +
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
                ", currencyId=" + currencyId +
                ", multiBranch=" + multiBranch +
                '}';
    }
}
