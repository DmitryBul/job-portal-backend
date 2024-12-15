package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "datas")
public class Data {

    @Id
    private String id;
    private String companyName;
    private String location;
    private String technology;
    private String seniority;
    private double avgSalary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(double avgSalary) {
        this.avgSalary = avgSalary;
    }
}