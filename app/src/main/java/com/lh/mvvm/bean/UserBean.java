package com.lh.mvvm.bean;

/**
 * Created by LuHao on 2017/10/31.
 */

public class UserBean {
    private int userId;
    private String userName;
    private double userAge;
    private float userSex;

    public UserBean(int userId, String userName, double userAge, float userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getUserAge() {
        return userAge;
    }

    public void setUserAge(double userAge) {
        this.userAge = userAge;
    }

    public float getUserSex() {
        return userSex;
    }

    public void setUserSex(float userSex) {
        this.userSex = userSex;
    }
}
