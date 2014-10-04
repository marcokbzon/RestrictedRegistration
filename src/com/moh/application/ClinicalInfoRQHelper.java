package com.moh.application;

import com.moh.common.AbstractBean;
import com.moh.utils.Logger;

public class ClinicalInfoRQHelper extends AbstractBean {

    private String rotationSelected;

    public ClinicalInfoRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ClinicalInfoRQHelper" );
    }

    public String view() {
        populate();

        logger.debugPage("/jsp/appForm03.jsp");
        return "applicationForm03";
    }

    public void populate() {
        logger.debugMethod( "populate" );
    }

    public String getRotationSelected() {
        return rotationSelected;
    }

    public void setRotationSelected( String rotationSelected ) {
        this.rotationSelected = rotationSelected;
    }
}
