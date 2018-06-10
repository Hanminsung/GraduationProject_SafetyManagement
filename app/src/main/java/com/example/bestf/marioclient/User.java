package com.example.bestf.marioclient;

/**
 * Created by bestf on 2018-04-30.
 */

public class User {
    String userID;
    String userPassword;
    String userName;
    String companyName;

    String helmetState;
    String beltState;
    String shoesState;

    public User(String userID, String userPassword, String userName, String companyName, String helmetState, String beltState, String shoesState) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.companyName = companyName;
        this.helmetState = helmetState;
        this.beltState = beltState;
        this.shoesState = shoesState;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHelmetState() {
        return helmetState;
    }

    public void setHelmetState(String helmetState) {
        this.helmetState = helmetState;
    }

    public String getBeltState() {
        return beltState;
    }

    public void setBeltState(String beltState) {
        this.beltState = beltState;
    }

    public String getShoesState() {
        return shoesState;
    }

    public void setShoesState(String shoesState) {
        this.shoesState = shoesState;
    }
}
