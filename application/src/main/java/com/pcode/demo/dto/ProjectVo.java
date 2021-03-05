package com.pcode.demo.dto;

public class ProjectVo {
    private String name;
    private String logo;
    private String description;
    private String createdID;
    private String cusIds;

    public String getCusIds() {
        return cusIds;
    }

    public void setCusIds(String cusIds) {
        this.cusIds = cusIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedID() {
        return createdID;
    }

    public void setCreatedID(String createdID) {
        this.createdID = createdID;
    }
}
