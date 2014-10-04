package com.moh.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import com.moh.common.AbstractBean;
import com.moh.data.dao.SecurityDAO;
import com.moh.utils.Logger;

public class MultiRoleRQHelper extends AbstractBean {

    private String selectedRole;
    private Map<String, String> roles;
    private List<SelectItem> roleList;
    private String email;

    public MultiRoleRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "MultiRoleRQHelper" );
    }

    public String execute() {
        addToSession( SESSION_ROLEID, getSelectedRole() );

        if( getSelectedRole().equals( ROLE_SUPERVISOR ) || getSelectedRole().equals( ROLE_DIRECTOR ) || getSelectedRole().equals( ROLE_DEAN ) || getSelectedRole().equals( ROLE_COMMITTEE ) || getSelectedRole().equals( ROLE_ADMIN_COMMITTEE ) ) {
            Map<String, String> memRoles = getRoles();
            String roleDescription = memRoles.get( getSelectedRole() );
            addToSession( SESSION_ROLEDESC, roleDescription );

            SecurityDAO securityDAO = new SecurityDAO();
            setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
            String universityID = securityDAO.getUniversityID( getEmail() );

            addToSession( SESSION_UNIVERSITYID, universityID );

            logger.debugPage( "/jsp/mainReviewer.jsp" );
            return "mainReviewer";
        }
        else {
            if( getSelectedRole().equals( ROLE_ADMIN ) ) {
                logger.debugPage( "/jsp/mainAdmin.jsp" );
                return "mainAdmin";
            }
            else {
                logger.debugPage( "/jsp/mainResident.jsp" );
                return "mainResident";
            }
        }
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole( String selectedRole ) {
        this.selectedRole = selectedRole;
    }

    @SuppressWarnings( "unchecked" )
    public Map<String, String> getRoles() {
        logger.debugMethod( "getRoles" );
        if( roles == null ) {
            roles = new HashMap<>();
            roles.putAll( ( Map<String, String> ) getFromSession( SESSION_ROLES ) );
        }

        return roles;
    }

    public void setRoles( Map<String, String> roles ) {
        this.roles = roles;
    }

    public List<SelectItem> getRoleList() {
        logger.debugMethod( "getRoleList" );
        if( roleList == null ) {
            roleList = new ArrayList<>();

            Iterator<Map.Entry<String, String>> roleIterator = ( Iterator<Map.Entry<String, String>> ) getRoles().entrySet().iterator();

            while( roleIterator.hasNext() ) {
                Map.Entry<String, String> role = roleIterator.next();
                roleList.add( new SelectItem( role.getKey(), role.getValue() ) );
            }
        }

        return roleList;
    }

    public void setRoleList( List<SelectItem> roleList ) {
        this.roleList = roleList;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }

    private void setEmail(String email) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
