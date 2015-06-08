package com.ls.bean;

import java.io.Serializable;

/**
 * Created by ls on 15-6-6.
 * <p/>
 * <p/>
 * orderId	订单编号
 * cargoId	货物编号
 * userId	用户编号
 * doTime	执行时间
 * sourcePlace	出发地
 * endPlace	收货地
 * remarks	备注
 */
public class Waybill  implements Serializable{
    public Waybill(String id, String orderId, String cargoId, String userId, String doTime, String sourcePlace, String endPlace, String remarks) {
        this.id = id;
        this.orderId = orderId;
        this.cargoId = cargoId;
        this.userId = userId;
        this.doTime = doTime;
        this.sourcePlace = sourcePlace;
        this.endPlace = endPlace;
        this.remarks = remarks;
    }



    @Override
    public String toString() {
        return "Waybill{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", cargoId='" + cargoId + '\'' +
                ", userId='" + userId + '\'' +
                ", doTime='" + doTime + '\'' +
                ", sourcePlace='" + sourcePlace + '\'' +
                ", endPlace='" + endPlace + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

public Waybill(){

}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoTime() {
        return doTime;
    }

    public void setDoTime(String doTime) {
        this.doTime = doTime;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getSourcePlace() {
        return sourcePlace;
    }

    public void setSourcePlace(String sourcePlace) {
        this.sourcePlace = sourcePlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    private String id;
    private String orderId;
    private String cargoId;
    private String userId;
    private String doTime;
    private String sourcePlace;
    private String endPlace;
    private String remarks;

}
