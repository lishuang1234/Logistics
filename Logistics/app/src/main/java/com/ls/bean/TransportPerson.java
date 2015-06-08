package com.ls.bean;

/**
 * Created by ls on 15-6-6.
 * driverID
 * idCard
 * licenseNumber
 * licenseProvince
 * licenseCity
 * licenseCounty
 * 车主编号
 * 身份证号
 * 驾驶证号
 * 驾驶证所在省
 * 驾驶证所在市
 * 驾驶证所在县
 */
public class TransportPerson {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLicenseProvince() {
        return licenseProvince;
    }

    public void setLicenseProvince(String licenseProvince) {
        this.licenseProvince = licenseProvince;
    }

    public String getLicenseCity() {
        return licenseCity;
    }

    public void setLicenseCity(String licenseCity) {
        this.licenseCity = licenseCity;
    }

    public String getLicenseCounty() {
        return licenseCounty;
    }

    public void setLicenseCounty(String licenseCounty) {
        this.licenseCounty = licenseCounty;
    }


    @Override
    public String toString() {
        return "TransportPerson{" +
                "id='" + id + '\'' +
                ", driverID='" + driverID + '\'' +
                ", idCard='" + idCard + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", licenseProvince='" + licenseProvince + '\'' +
                ", licenseCity='" + licenseCity + '\'' +
                ", licenseCounty='" + licenseCounty + '\'' +
                '}';
    }

    private String id;
    private String driverID;
    private String idCard;
    private String licenseNumber;
    private String licenseProvince;
    private String licenseCity;
    private String licenseCounty;

    public TransportPerson(String id, String driverID, String idCard, String licenseNumber, String licenseCity, String licenseProvince, String licenseCounty) {
        this.id = id;
        this.driverID = driverID;
        this.idCard = idCard;
        this.licenseNumber = licenseNumber;
        this.licenseCity = licenseCity;
        this.licenseProvince = licenseProvince;
        this.licenseCounty = licenseCounty;
    }




}
