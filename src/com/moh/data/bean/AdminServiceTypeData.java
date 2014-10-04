package com.moh.data.bean;

public class AdminServiceTypeData {

    private String serviceTypeID;
    private String name_EN;
    private String abbreviation;

    public AdminServiceTypeData( String serviceTypeID, String name_EN, String abbreviation ) {
        this.serviceTypeID = serviceTypeID;
        this.name_EN = name_EN;
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation( String abbreviation ) {
        this.abbreviation = abbreviation;
    }

    public String getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID( String serviceTypeID ) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getName_EN() {
        return name_EN;
    }

    public void setName_EN( String name_EN ) {
        this.name_EN = name_EN;
    }
}
