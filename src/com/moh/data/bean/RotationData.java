package com.moh.data.bean;

public class RotationData {

    private String serviceType;
    private String rotations;
    private String weeksTotal;
    private String position;

    public RotationData( String serviceType, String rotations, String weeksTotal, String position ) {
        this.serviceType = serviceType;
        this.rotations = rotations;
        this.weeksTotal = weeksTotal;
        this.position = position;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType( String serviceType ) {
        this.serviceType = serviceType;
    }

    public String getRotations() {
        return rotations;
    }

    public void setRotations( String rotations ) {
        this.rotations = rotations;
    }

    public String getWeeksTotal() {
        return weeksTotal;
    }

    public void setWeeksTotal( String weeksTotal ) {
        this.weeksTotal = weeksTotal;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition( String position ) {
        this.position = position;
    }
}
