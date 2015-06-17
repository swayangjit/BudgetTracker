package com.skytopper.budgettracker.model;

/**
 * Created by swayangjit on 5/3/2015.
 */
public class Company {


    long id;
    private String companyName;
    private String databaseName;
    private String financialYear;
    private String address1;
    private String address2;
    private String emailId;

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTaxRegd1() {
        return taxRegd1;
    }

    public void setTaxRegd1(String taxRegd1) {
        this.taxRegd1 = taxRegd1;
    }

    public String getTaxRegd2() {
        return taxRegd2;
    }

    public void setTaxRegd2(String taxRegd2) {
        this.taxRegd2 = taxRegd2;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanySignature() {
        return companySignature;
    }

    public void setCompanySignature(String companySignature) {
        this.companySignature = companySignature;
    }

    private String phoneNumber;
    private String taxRegd1;
    private String taxRegd2;
    private String companyLogo;
    private String companySignature;





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }




}
