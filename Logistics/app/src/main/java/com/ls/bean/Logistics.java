package com.ls.bean;

/**
 * Created by ls on 15-6-6.
 * <p/>
 * companyId
 * licenseId
 * corperateRepresentative
 * headquartersProvince
 * headquartersCity
 * headquartersCounty
 * headquartersStreet
 * complainNumber
 * consultingNumber
 * website
 * foundationTime
 * paymentCollection
 * introduction
 * 公司编号
 * 营业执照号
 * 法人代表
 * 总部所在省
 * 总部所在市
 * 总部所在县
 * 总部所在街道
 * 投诉电话
 * 咨询电话
 * 公司网站
 * 成立时间
 * 代收贷款
 * 公司简介
 */
public class Logistics {


    public Logistics(String headquartersProvince, String licenseId, String companyId, String id, String corperateRepresentative, String headquartersCity, String headquartersCounty, String complainNumber, String website, String introduction, String paymentCollection, String foundationTime, String consultingNumber, String headquartersStreet) {
        this.headquartersProvince = headquartersProvince;
        this.licenseId = licenseId;
        this.companyId = companyId;
        this.id = id;
        this.corperateRepresentative = corperateRepresentative;
        this.headquartersCity = headquartersCity;
        this.headquartersCounty = headquartersCounty;
        this.complainNumber = complainNumber;
        this.website = website;
        this.introduction = introduction;
        this.paymentCollection = paymentCollection;
        this.foundationTime = foundationTime;
        this.consultingNumber = consultingNumber;
        this.headquartersStreet = headquartersStreet;
    }


    @Override
    public String toString() {
        return "Logistics{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", licenseId='" + licenseId + '\'' +
                ", corperateRepresentative='" + corperateRepresentative + '\'' +
                ", headquartersProvince='" + headquartersProvince + '\'' +
                ", headquartersCity='" + headquartersCity + '\'' +
                ", headquartersCounty='" + headquartersCounty + '\'' +
                ", headquartersStreet='" + headquartersStreet + '\'' +
                ", complainNumber='" + complainNumber + '\'' +
                ", consultingNumber='" + consultingNumber + '\'' +
                ", website='" + website + '\'' +
                ", foundationTime='" + foundationTime + '\'' +
                ", paymentCollection='" + paymentCollection + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getCorperateRepresentative() {
        return corperateRepresentative;
    }

    public void setCorperateRepresentative(String corperateRepresentative) {
        this.corperateRepresentative = corperateRepresentative;
    }

    public String getHeadquartersProvince() {
        return headquartersProvince;
    }

    public void setHeadquartersProvince(String headquartersProvince) {
        this.headquartersProvince = headquartersProvince;
    }

    public String getHeadquartersCity() {
        return headquartersCity;
    }

    public void setHeadquartersCity(String headquartersCity) {
        this.headquartersCity = headquartersCity;
    }

    public String getHeadquartersCounty() {
        return headquartersCounty;
    }

    public void setHeadquartersCounty(String headquartersCounty) {
        this.headquartersCounty = headquartersCounty;
    }

    public String getHeadquartersStreet() {
        return headquartersStreet;
    }

    public void setHeadquartersStreet(String headquartersStreet) {
        this.headquartersStreet = headquartersStreet;
    }

    public String getComplainNumber() {
        return complainNumber;
    }

    public void setComplainNumber(String complainNumber) {
        this.complainNumber = complainNumber;
    }

    public String getConsultingNumber() {
        return consultingNumber;
    }

    public void setConsultingNumber(String consultingNumber) {
        this.consultingNumber = consultingNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFoundationTime() {
        return foundationTime;
    }

    public void setFoundationTime(String foundationTime) {
        this.foundationTime = foundationTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(String paymentCollection) {
        this.paymentCollection = paymentCollection;
    }


    private String id;
    private String companyId;
    private String licenseId;
    private String corperateRepresentative;
    private String headquartersProvince;
    private String headquartersCity;
    private String headquartersCounty;
    private String headquartersStreet;
    private String complainNumber;
    private String consultingNumber;
    private String website;
    private String foundationTime;
    private String paymentCollection;
    private String introduction;


}
