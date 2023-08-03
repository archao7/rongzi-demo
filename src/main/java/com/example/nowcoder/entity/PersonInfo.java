package com.example.nowcoder.entity;

import java.util.Date;

public class PersonInfo {

    private int id;
    private String name;
    private String gender;
    private Integer age;
    private String occupation;
    private Integer departmentId;
    private boolean isDeleted;

    public PersonInfo() {
    }

    public PersonInfo(String name, String gender, Integer age, String occupation, Integer departmentId){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.departmentId = departmentId;
    }

    public PersonInfo(int id, String name, String gender, Integer age, String occupation, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.departmentId = departmentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
// Getters and setters

}
