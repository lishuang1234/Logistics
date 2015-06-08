package com.ls.bean;

/**
 * Created by ls on 15-6-6.
 * vehicleId
 * userId
 * plateNumber
 * length
 * maxLoad
 * vehicleType
 * bodyType
 * contact
 * mobileNumber
 * description
 * state
 * <p/>
 * 车辆编号
 * 用户编号
 * 车牌号
 * 车长
 * 载重
 * 车辆类型
 * 车体类型
 * 随车联系人
 * 联系电话
 * 说明
 * 状态
 */
public class Car {


    public Car(String vehicleId, String id, String userId, String length, String plateNumber, String maxLoad, String vehicleType, String bodyType, String contact, String mobileNumber, String description, String state) {
        this.vehicleId = vehicleId;
        this.id = id;
        this.userId = userId;
        this.length = length;
        this.plateNumber = plateNumber;
        this.maxLoad = maxLoad;
        this.vehicleType = vehicleType;
        this.bodyType = bodyType;
        this.contact = contact;
        this.mobileNumber = mobileNumber;
        this.description = description;
        this.state = state;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", userId='" + userId + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", length='" + length + '\'' +
                ", maxLoad='" + maxLoad + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", contact='" + contact + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(String maxLoad) {
        this.maxLoad = maxLoad;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String id;
    private String vehicleId;
    private String userId;
    private String plateNumber;
    private String length;
    private String maxLoad;
    private String vehicleType;
    private String bodyType;
    private String contact;
    private String mobileNumber;
    private String description;
    private String state;

}
