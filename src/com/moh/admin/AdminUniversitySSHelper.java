package com.moh.admin;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.AdminMaintenanceDAO;
import com.moh.utils.Logger;

public class AdminUniversitySSHelper extends AbstractBean {

    private String universityID;
    private String name_EN;
    private String abbreviation;
    private String enabled;
    private String enabledDesc;

    public AdminUniversitySSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminUniversitySSHelper" );
    }

    public String universityList() {
        populate();

        setUniversityID( EMPTY_STRING );

        logger.debugPage( "/jsp/adminUniversity.jsp" );
        return "adminUniversity";
    }

    public String addUniversity() {
        setUniversityID( EMPTY_STRING );
        setName_EN( EMPTY_STRING );
        setAbbreviation( EMPTY_STRING );
        setEnabled( EMPTY_STRING );

        logger.debugPage( "/jsp/adminUniversityAdd.jsp" );
        return "adminUniversityAdd";
    }

    public String editUniversity() {
        if( universityID == null || universityID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_university_required" );
            
            logger.debugPage( "/jsp/adminUniversity.jsp" );
            return "adminUniversity";
        }

        populate();

        logger.debugPage( "/jsp/adminUniversity.jsp" );
        return "adminUniversityEdit";
    }

    public String deleteUniversity() {
        if( universityID == null || universityID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_university_required" );
            
            logger.debugPage( "/jsp/adminUniversity.jsp" );
            return "adminUniversity";
        }

        populate();

        logger.debugPage( "/jsp/adminUniversityDelete.jsp" );
        return "adminUniversityDelete";
    }

    public String create() {
        logger.debugMethod( "create" );
        boolean result = false;
        boolean exists = true;
        boolean anErrorOcurred = false;

        if( name_EN == null || name_EN.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_name_required" );
            anErrorOcurred = true;
        }
        if( abbreviation == null || abbreviation.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_abbreviation_required" );
            anErrorOcurred = true;
        }
        if( enabled == null || enabled.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_enabled_required" );
            anErrorOcurred = true;
        }

        if( anErrorOcurred ) {
            logger.debugPage( "/jsp/adminUniversityAdd.jsp" );
            return "adminUniversityAdd";
        }

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        exists = adminDAO.dataInfoExists( adminDAO.FROM_UNIVERSITY, adminDAO.BY_NAME, name_EN );

        if( exists ) {
            addErrorMessage( "error_name_already_exists" );
            
            logger.debugPage( "/jsp/adminUniversityAdd.jsp" );
            return "adminUniversityAdd";
        }

        exists = adminDAO.dataInfoExists( adminDAO.FROM_UNIVERSITY, adminDAO.BY_ABBREVIATION, abbreviation );

        if( exists ) {
            addErrorMessage( "error_abbreviation_already_exists" );
            
            logger.debugPage( "/jsp/adminUniversityAdd.jsp" );
            return "adminUniversityAdd";
        }

        String newID = adminDAO.generateNewID( adminDAO.FROM_UNIVERSITY );

        String[] data = { name_EN, abbreviation, enabled };

        result = adminDAO.addDataInfo( adminDAO.FROM_UNIVERSITY, newID, data );

        if( result ) {
            addInfoMessage( "info_university_added_ok" );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
        }

        logger.debugPage( "/jsp/adminUniversity.jsp" );
        return "adminUniversity";
    }

    public String update() {
        logger.debugMethod( "update" );
        boolean result = false;
        boolean anErrorOcurred = false;

        if( name_EN == null || name_EN.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_name_required" );
            anErrorOcurred = true;
        }
        if( abbreviation == null || abbreviation.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_abbreviation_required" );
            anErrorOcurred = true;
        }
        if( enabled == null || enabled.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_enabled_required" );
            anErrorOcurred = true;
        }

        if( anErrorOcurred ) {
            logger.debugPage( "/jsp/adminUniversity.jsp" );
            return "adminUniversityEdit";
        }

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        String[] data = { name_EN, abbreviation, enabled };

        result = adminDAO.editDataInfo( adminDAO.FROM_UNIVERSITY, universityID, data );

        if( result ) {
            addInfoMessage( "info_university_updated_ok" );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
        }

        logger.debugPage( "/jsp/adminUniversity.jsp" );
        return "adminUniversity";
    }

    public String delete() {
        logger.debugMethod( "delete" );
        boolean result = false;

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        result = adminDAO.deleteDataInfo( adminDAO.FROM_UNIVERSITY, universityID );

        if( result ) {
            addInfoMessage( "info_university_deleted_ok" );
        }
        else {
            addErrorMessage( "error_db_delete_failed" );
        }

        logger.debugPage( "/jsp/adminUniversity.jsp" );
        return "adminUniversity";
    }

    public void populate() {
        logger.debugMethod( "populate" );
        
        if( universityID != null && ! universityID.trim().equals(EMPTY_STRING) ) {
            AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

            Map<String, Object> universityMap = adminDAO.getDataInfo( adminDAO.FROM_UNIVERSITY, universityID );

            setName_EN( ( String ) universityMap.get( "Name_EN" ) );
            setAbbreviation( ( String ) universityMap.get( "Abbreviation" ) );
            setEnabled( ( String ) universityMap.get( "Enabled" ) );
            if( enabled.equals( "1" ) ) {
                setEnabledDesc( "Yes" );
            }
            else {
                setEnabledDesc( "No" );
            }
        }
    }

    public String getUniversityID() {
        return universityID;
    }

    public void setUniversityID( String universityID ) {
        this.universityID = universityID;
    }

    public String getName_EN() {
        return name_EN;
    }

    public void setName_EN( String name_EN ) {
        this.name_EN = name_EN;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation( String abbreviation ) {
        this.abbreviation = abbreviation;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled( String enabled ) {
        this.enabled = enabled;
    }

    public String getEnabledDesc() {
        return enabledDesc;
    }

    public void setEnabledDesc( String enabledDesc ) {
        this.enabledDesc = enabledDesc;
    }
}
