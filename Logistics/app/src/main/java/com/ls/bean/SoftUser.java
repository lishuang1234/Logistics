package com.ls.bean;

/**
 * Created by ls on 15-6-7.
 */
public class SoftUser {

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "SoftUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", sex='" + sex + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }



    public SoftUser(String id, String userName, String userPassword, String sex, String realName) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.sex = sex;
        this.realName = realName;
    }

    public SoftUser() {
    }

    private String id;
    private String userName;
    private String userPassword;
    private String sex;
    private String realName;


}
