package com.treecute.plant.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class User implements Serializable{
    @SerializedName("username") public String username;
    @SerializedName("id") public Integer id;
    @SerializedName("password") public String password;
    @SerializedName("gender") public Integer gender;
    @SerializedName("email") public String email;
    @SerializedName("avatar") public String avatar;
    @SerializedName("phone") public String phone;
    @SerializedName("accessToken") public String accessToken;
    @SerializedName("lastLoginTime") public String lastLoginTime;
    @SerializedName("lastLoginIp") public String lastLoginIp;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
    public String getAccessToken() {
        return accessToken;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }


    public String getLastLoginTime() {
        return lastLoginTime;
    }


    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime == null ? null : lastLoginTime.trim();
    }


    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }
}
