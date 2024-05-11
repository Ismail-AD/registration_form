package com.appdev.registration_form;

import java.util.List;

public class UserInfo {
    private String name;
    private List<String> skills;
    private String country;
    private String gender;

    public UserInfo(){}
    public UserInfo(String name, List<String> skills, String country, String gender) {
        this.name = name;
        this.skills = skills;
        this.country = country;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
