package com.pccw.beaconbank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

/**
 * Created by 80979040 on 26/04/2017.
 */

public class UserDTO {

    @JsonProperty("user_id")
    Integer user_id;

    @JsonProperty("first_name")
    String first_name;

    @JsonProperty("last_name")
    String last_name;

    @JsonProperty("middle_name")
    String middle_name;

    @JsonProperty("birth_date")
    Date birth_date;

    @JsonProperty("address")
    String address;

    @JsonProperty("gender")
    String gender;

    @JsonProperty("email")
    String email;

    @JsonProperty("password")
    String password;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject getUserObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", user_id);
        jsonObject.put("first_name", first_name);
        jsonObject.put("last_name", last_name);
        jsonObject.put("middle_name", middle_name);
        jsonObject.put("birth_date", birth_date);
        jsonObject.put("address", address);
        jsonObject.put("gender", gender);
        jsonObject.put("email", email);
        jsonObject.put("password", password);

        return jsonObject;
    }

}
