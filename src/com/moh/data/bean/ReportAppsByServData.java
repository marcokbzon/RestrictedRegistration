package com.moh.data.bean;

public class ReportAppsByServData {

    private String entityName;
    private String entityCount;

    public ReportAppsByServData( String entityName, String entityCount ) {
        this.entityName = entityName;
        this.entityCount = entityCount;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName( String entityName ) {
        this.entityName = entityName;
    }

    public String getEntityCount() {
        return entityCount;
    }

    public void setEntityCount( String entityCount ) {
        this.entityCount = entityCount;
    }
}
