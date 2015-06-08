package com.ls.bean;


import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ls on 15-6-5.
 */
public class Goods implements Serializable {
    /*
         * 货物编号
         用户编号
         货物名称
         货物类型
         重量
         体积
         数量
         发布时间
         截止时间
         出发地（省）
         出发地（市）
         出发地（县）
         到达地（省）
         到达地（市）
         到达地（县）
         运输类型
         注意事项
         联系人
         手机号码
         备注
         状态
         成交时间
         长期货源
         *
         * */

    public Goods() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getSourceProvince() {
        return sourceProvince;
    }

    public void setSourceProvince(String sourceProvince) {
        this.sourceProvince = sourceProvince;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getSourceCounty() {
        return sourceCounty;
    }

    public void setSourceCounty(String sourceCounty) {
        this.sourceCounty = sourceCounty;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationProvince() {
        return destinationProvince;
    }

    public void setDestinationProvince(String destinationProvince) {
        this.destinationProvince = destinationProvince;
    }

    public String getDestinationCounty() {
        return destinationCounty;
    }

    public void setDestinationCounty(String destinationCounty) {
        this.destinationCounty = destinationCounty;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getNoticeItem() {
        return noticeItem;
    }

    public void setNoticeItem(String noticeItem) {
        this.noticeItem = noticeItem;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDealtime() {
        return dealtime;
    }

    public void setDealtime(String dealtime) {
        this.dealtime = dealtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLongtimeCargo() {
        return longtimeCargo;
    }

    public void setLongtimeCargo(String longtimeCargo) {
        this.longtimeCargo = longtimeCargo;
    }


    @Override
    public String toString() {
        return "Goods toString {" +
                "id='" + id + '\'' +
                ", cargoId='" + cargoId + '\'' +
                ", userId='" + userId + '\'' +
                ", cargoName='" + cargoName + '\'' +
                ", cargoType='" + cargoType + '\'' +
                ", weight='" + weight + '\'' +
                ", volume='" + volume + '\'' +
                ", quantity='" + quantity + '\'' +
                ", publishTime=" + publishTime +
                ", dueTime=" + dueTime +
                ", sourceProvince='" + sourceProvince + '\'' +
                ", sourceCity='" + sourceCity + '\'' +
                ", sourceCounty='" + sourceCounty + '\'' +
                ", destinationProvince='" + destinationProvince + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", destinationCounty='" + destinationCounty + '\'' +
                ", transportType='" + transportType + '\'' +
                ", noticeItem='" + noticeItem + '\'' +
                ", contact='" + contact + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", remarks='" + remarks + '\'' +
                ", state='" + state + '\'' +
                ", dealtime=" + dealtime +
                ", longtimeCargo='" + longtimeCargo + '\'' +
                '}';
    }


    private String id;
    private String cargoId;
    private String userId;
    private String cargoName;
    private String cargoType;
    private String weight;
    private String volume;
    private String quantity;
    private String publishTime;
    private String dueTime;
    private String sourceProvince;
    private String sourceCity;
    private String sourceCounty;
    private String destinationProvince;
    private String destinationCity;
    private String destinationCounty;
    private String transportType;
    private String noticeItem;
    private String contact;
    private String mobileNumber;
    private String remarks;
    private String state;
    private String dealtime;
    private String longtimeCargo;


    public Goods(String id, String cargoId, String cargoName,
                 String userId, String cargoType, String weight, String volume,
                 String publishTime, String quantity, String sourceProvince,
                 String dueTime, String sourceCity, String sourceCounty,
                 String destinationProvince, String destinationCity, String destinationCounty,
                 String transportType, String noticeItem, String contact, String mobileNumber,
                 String remarks, String state, String dealtime, String longtimeCargo) {
        this.id = id;
        this.cargoId = cargoId;
        this.cargoName = cargoName;
        this.userId = userId;
        this.cargoType = cargoType;
        this.weight = weight;
        this.volume = volume;
        this.publishTime = publishTime;
        this.quantity = quantity;
        this.sourceProvince = sourceProvince;
        this.dueTime = dueTime;
        this.sourceCity = sourceCity;
        this.sourceCounty = sourceCounty;
        this.destinationProvince = destinationProvince;
        this.destinationCity = destinationCity;
        this.destinationCounty = destinationCounty;
        this.transportType = transportType;
        this.noticeItem = noticeItem;
        this.contact = contact;
        this.mobileNumber = mobileNumber;
        this.remarks = remarks;
        this.state = state;
        this.dealtime = dealtime;
        this.longtimeCargo = longtimeCargo;
    }


}
