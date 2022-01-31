package com.sdcode.login_registration_ui.models;

public class ModelClassRVUser {
    private String gender;
    private Integer userId;
    private String email;
    private String fName;
    private String lName;
    private String phone;

    public ModelClassRVUser(Integer userId, String gender, String email, String fName, String lName, String phone) {
        this.gender = gender;
        this.email = email;
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }
}
