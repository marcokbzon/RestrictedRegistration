package com.moh.data.bean;

public class AppFormStatusData {

    private String updatedOn;
    private String roleDesc;
    private String firstName;
    private String lastName;
    private String codeDesc;
    private String iconName;

    public AppFormStatusData( String updatedOn, String roleDesc, String firstName, String lastName, String codeDesc, String iconName ) {
        this.updatedOn = updatedOn;
        this.roleDesc = roleDesc;
        this.firstName = firstName;
        this.lastName = lastName;
        this.codeDesc = codeDesc;
        this.iconName = iconName;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName( String iconName ) {
        this.iconName = iconName;
    }

    public String getUpdatedOn() {
        updatedOn = updatedOn.substring( 0, 10 );
        return updatedOn;
    }

    public void setUpdatedOn( String updatedOn ) {
        this.updatedOn = updatedOn;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc( String roleDesc ) {
        this.roleDesc = roleDesc;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc( String codeDesc ) {
        this.codeDesc = codeDesc;
    }
}
