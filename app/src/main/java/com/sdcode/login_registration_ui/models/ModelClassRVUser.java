package com.sdcode.login_registration_ui.models;

public class ModelClassRVUser {
    private String gender;
    private Integer userId;
    private String email;
    private String fName;
    private String lName;

    public ModelClassRVUser(Integer userId, String gender, String email, String fName, String lName) {
        this.gender = gender;
        this.email = email;
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getUserId() {
        return userId;
    }
}
