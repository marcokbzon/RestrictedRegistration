package com.moh.security;

import com.moh.common.AbstractBean;
import com.moh.utils.Logger;

public class LogoutRQHelper extends AbstractBean {

    public LogoutRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "LogoutRQHelper" );
    }

    public String execute() {
        logger.debugMethod( "execute" );
        clearSession();

        logger.debugPage( "/jsp/logout.jsp" );
        return "logout";
    }
}
