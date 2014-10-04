package com.moh.data.bean;

import com.moh.common.AbstractBean;

public class AdminUserListByRoleData extends AbstractBean {

    private String position;
    private String firstName;
    private String lastName;
    private String email;
    private String info1;
    private String info2;
    private String role;

    public AdminUserListByRoleData( String position, String firstName, String lastName, String email, String info1, String info2, String role ) {
        setPosition( position );
        setFirstName( firstName );
        setLastName( lastName );
        setEmail( email );
        setInfo1( info1 );
        setInfo2( info2 );
        setRole( role );
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    private void setPosition( String position ) {
        this.position = position;
    }

    public String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }

    private void setEmail( String email ) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }

    public String getInfo1() {
        return info1;
    }

    private void setInfo1( String info1 ) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    private void setInfo2( String info2 ) {
        this.info2 = info2;
    }

    public String getRole() {
        return role;
    }

    private void setRole( String role ) {
        this.role = role;
    }
}
