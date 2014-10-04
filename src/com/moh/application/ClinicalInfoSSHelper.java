package com.moh.application;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ClinicalDAO;
import com.moh.utils.Logger;

public class ClinicalInfoSSHelper extends AbstractBean {

    private String position;
    private String serviceType;
    private String rotations;
    private String weeksTotal;
    private boolean isResident;
    private boolean isAdministrator;
    private boolean isReviewer;
    private String email;

    public ClinicalInfoSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ClinicalInfoSSHelper" );
    }

    public String updateProfile() {
        populate();

        setPosition( EMPTY_STRING );

        logger.debugPage( "/jsp/updateProfile03.jsp" );
        return "updateProfile03";
    }

    public String addRotation() {
        setServiceType( EMPTY_STRING );
        setRotations( EMPTY_STRING );
        setWeeksTotal( EMPTY_STRING );
        setPosition( EMPTY_STRING );

        logger.debugPage( "/jsp/updateProfile03a.jsp" );
        return "updateProfile03a";
    }

    public String editRotation() {
        if( position == null || position.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_rotation_required" );
            
            logger.debugPage( "/jsp/updateProfile03.jsp" );
            return "updateProfile03";
        }

        populate();

        logger.debugPage( "/jsp/updateProfile03e.jsp" );
        return "updateProfile03e";
    }

    public String deleteRotation() {
        if( position == null || position.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_rotation_required" );
            
            logger.debugPage( "/jsp/updateProfile03.jsp" );
            return "updateProfile03";
        }

        populate();

        logger.debugPage( "/jsp/updateProfile03d.jsp" );
        return "updateProfile03d";
    }

    public String create() {
        logger.debugMethod( "create" );
        boolean result = false;
        //boolean exists = true;

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        if( serviceType == null || serviceType.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_servicetype_required" );
            
            logger.debugPage( "/jsp/updateProfile03a.jsp" );
            return "updateProfile03a";
        }

        if( rotations == null || rotations.trim().equals( EMPTY_STRING ) ) {
            rotations = "0";
        }
        if( weeksTotal == null || weeksTotal.trim().equals( EMPTY_STRING ) ) {
            weeksTotal = "0";
        }

        int intWeeksTotal = 0;
        try {
            intWeeksTotal = Integer.parseInt( weeksTotal );
        }
        catch( NumberFormatException nfex ) {
            addErrorMessage( "error_totalweeks_format_invalid" );
            
            logger.debugPage( "/jsp/updateProfile03a.jsp" );
            return "updateProfile03a";
        }

        int intRotations = 0;
        try {
            intRotations = Integer.parseInt( rotations );
        }
        catch( NumberFormatException nfex ) {
            addErrorMessage( "error_rotations_format_invalid" );
            
            logger.debugPage( "/jsp/updateProfile03a.jsp" );
            return "updateProfile03a";
        }

        ClinicalDAO clinicalDAO = new ClinicalDAO();

        //exists = clinicalDAO.rotationExists( getEmail(), position );

        //if ( exists ) {
        //	addErrorMessage( "error_servicetype_already_exists" );
        //
        //      logger.debugPage( "/jsp/updateProfile03a.jsp" );
        //	return "updateProfile03a";
        //}

        int newPosition = clinicalDAO.genNewRotationPosition( getEmail() );

        result = clinicalDAO.addRotation( getEmail(), serviceType, newPosition, intRotations, intWeeksTotal );

        if( result ) {
            addInfoMessage( "info_clinical_added_ok" );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
        }

        logger.debugPage( "/jsp/updateProfile03.jsp" );
        return "updateProfile03";
    }

    public String update() {
        logger.debugMethod( "update" );
        boolean result = false;

        if( position == null || position.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_rotation_required" );
            
            logger.debugPage( "/jsp/updateProfile03.jsp" );
            return "updateProfile03";
        }

        if( serviceType == null || serviceType.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_servicetype_required" );
            
            logger.debugPage( "/jsp/updateProfile03e.jsp" );
            return "updateProfile03e";
        }

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        if( rotations == null || rotations.trim().equals( EMPTY_STRING ) ) {
            rotations = "0";
        }
        if( weeksTotal == null || weeksTotal.trim().equals( EMPTY_STRING ) ) {
            weeksTotal = "0";
        }

        int intWeeksTotal = 0;
        try {
            intWeeksTotal = Integer.parseInt( weeksTotal );
        }
        catch( NumberFormatException nfex ) {
            addErrorMessage( "error_totalweeks_format_invalid" );
            
            logger.debugPage( "/jsp/updateProfile03e.jsp" );
            return "updateProfile03e";
        }

        int intRotations = 0;
        try {
            intRotations = Integer.parseInt( rotations );
        }
        catch( NumberFormatException nfex ) {
            addErrorMessage( "error_rotations_format_invalid" );
            
            logger.debugPage( "/jsp/updateProfile03e.jsp" );
            return "updateProfile03e";
        }

        ClinicalDAO clinicalDAO = new ClinicalDAO();

        result = clinicalDAO.editRotation( getEmail(), serviceType, Integer.parseInt( position ), intRotations, intWeeksTotal );

        if( result ) {
            addInfoMessage( "info_clinical_updated_ok" );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
        }

        logger.debugPage( "/jsp/updateProfile03.jsp" );
        return "updateProfile03";
    }

    public String delete() {
        logger.debugMethod( "delete" );
        boolean result = false;

        if( position == null || position.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_rotation_required" );
            
            logger.debugPage( "/jsp/updateProfile03.jsp" );
            return "updateProfile03";
        }

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        ClinicalDAO clinicalDAO = new ClinicalDAO();

        result = clinicalDAO.deleteRotation( getEmail(), Integer.parseInt( position ) );

        if( result ) {
            addInfoMessage( "info_clinical_deleted_ok" );
        }
        else {
            addErrorMessage( "error_db_delete_failed" );
        }

        logger.debugPage( "/jsp/updateProfile03.jsp" );
        return "updateProfile03";
    }

    public void populate() {
        logger.debugMethod( "populate" );

        setIsResident( false );
        setIsAdministrator( false );
        setIsReviewer( false );

        if( ( ( String ) getFromSession( SESSION_ROLEID ) ).equals( ROLE_RESIDENT ) ) {
            setIsResident( true );
        }
        else {
            if( ( ( String ) getFromSession( SESSION_ROLEID ) ).equals( ROLE_ADMIN ) ) {
                setIsAdministrator( true );
            }
            else {
                setIsReviewer( true );
            }
        }

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        if( position != null && ! position.trim().equals(EMPTY_STRING) ) {
            ClinicalDAO clinicalDAO = new ClinicalDAO();
            Map<String, String> rotationMap = clinicalDAO.getRotation( getEmail(), Integer.parseInt( position ) );

            setServiceType( rotationMap.get( "ServiceType" ) );
            setRotations( rotationMap.get( "Rotations" ) );
            setWeeksTotal( rotationMap.get( "WeeksTotal" ) );
        }
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

    public boolean getIsResident() {
        return isResident;
    }

    public void setIsResident( boolean isResident ) {
        this.isResident = isResident;
    }

    public boolean getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator( boolean isAdministrator ) {
        this.isAdministrator = isAdministrator;
    }

    public boolean getIsReviewer() {
        return isReviewer;
    }

    public void setIsReviewer( boolean isReviewer ) {
        this.isReviewer = isReviewer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition( String position ) {
        this.position = position;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType( String serviceType ) {
        this.serviceType = serviceType;
    }
    
    public String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }
    
    public void setEmail( String email ) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
