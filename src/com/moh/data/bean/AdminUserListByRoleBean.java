package com.moh.data.bean;

import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ListUserDAO;

public class AdminUserListByRoleBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel userDM = new ListDataModel();

    public AdminUserListByRoleBean() {
        ListUserDAO listUserDAO = new ListUserDAO();

        String roleID = ( String ) getFromSession( SESSION_SELECTED_ROLEID );

        List<AdminUserListByRoleData> userList = listUserDAO.getUserListByRole( roleID );

        Iterator<AdminUserListByRoleData> iter = userList.iterator();

        int counter = 0;

        while( iter.hasNext() ) {
            iter.next().getEmail();
            counter++;
        }

        addToSession( SESSION_LIST_COUNT, EMPTY_STRING + counter );

        userDM.setWrappedData( userList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getUserDM() {
        return userDM;
    }

    public void setUserDM( Object data ) {
        userDM.setWrappedData( data );
    }
}
