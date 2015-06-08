package com.ls.bean;

/**
 * Created by ls on 15-6-5.
 */
public class User {
    /**
     * userId	用户编号	INT
     * userName	用户名称	VARCHAR
     * password	密码	VARCHAR
     * realName	真实姓名	VARCHAR
     * phoneNumber 电话号码 VARCHAR
     * mobileNumber 手机号码 VARCHAR
     * email 邮箱地址 VARCHAR
     */


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Goods  toString {" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(String userName, String userId, String id, String password, String realName, String phoneNumber, String mobileNumber, String email) {
        this.userName = userName;
        this.userId = userId;
        this.id = id;
        this.password = password;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    private String id;
    private String userId;
    private String userName;
    private String password;
    private String realName;
    private String phoneNumber;
    private String mobileNumber;
    private String email;
}
