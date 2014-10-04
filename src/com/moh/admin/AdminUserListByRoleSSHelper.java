package com.moh.admin;

import javax.faces.event.ValueChangeEvent;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListUserDAO;
import com.moh.utils.Logger;

public class AdminUserListByRoleSSHelper extends AbstractBean {

    private String selectedRole;
    private String listCount;

    public AdminUserListByRoleSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminUserListByRoleSSHelper" );
    }

    public String userList() {
        setSelectedRole( ROLE_RESIDENT );

        populate();

        logger.debugPage( "/jsp/adminUserListByRole.jsp" );
        return "adminUserListByRole";
    }

    public void populate() {
        logger.debugMethod( "populate" );

        if( selectedRole == null || selectedRole.trim().equals( EMPTY_STRING ) ) {
            addToSession( SESSION_SELECTED_ROLEID, EMPTY_STRING );
        }
        else {
            addToSession( SESSION_SELECTED_ROLEID, selectedRole );
        }

        ListUserDAO listUserDAO = new ListUserDAO();
        String userCount = listUserDAO.getInitialUserCounter();

        setListCount( userCount );
    }

    public String go() {
        logger.debugMethod( "go" );

        setListCount( ( String ) getFromSession( SESSION_LIST_COUNT ) );

        logger.debugPage( "/jsp/adminUserListByRole.jsp" );
        return "adminUserListByRole";
    }

    public void changeRole( ValueChangeEvent event ) {
        String newRole = EMPTY_STRING;
        
        // PAIRO
        if ( event != null ) {
            try {
                Object eventNewValue = event.getNewValue();

                if ( eventNewValue != null ) {
                    newRole = event.getNewValue().toString();
                }
            }
            catch ( NullPointerException npex ) {
                logger.exception( npex );
            }
        }

        addToSession( SESSION_SELECTED_ROLEID, newRole );

        setSelectedRole( newRole );
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount( String listCount ) {
        this.listCount = listCount;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole( String selectedRole ) {
        this.selectedRole = selectedRole;
    }
}
