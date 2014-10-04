package com.moh.admin;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.AdminMaintenanceDAO;
import com.moh.utils.Logger;

public class AdminProgramSSHelper extends AbstractBean {

    private String programID;
    private String description_EN;
    private String abbreviation;
    private String enabled;
    private String enabledDesc;

    public AdminProgramSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminProgramSSHelper" );
    }

    public String programList() {
        populate();

        setProgramID( EMPTY_STRING );

        logger.debugPage( "/jsp/adminProgram.jsp" );
        return "adminProgram";
    }

    public String addProgram() {
        setProgramID( EMPTY_STRING );
        setDescription_EN( EMPTY_STRING );
        setAbbreviation( EMPTY_STRING );
        setEnabled( EMPTY_STRING );

        logger.debugPage( "/jsp/adminProgramAdd.jsp" );
        return "adminProgramAdd";
    }

    public String editProgram() {
        if( programID == null || programID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_program_required" );
            
            logger.debugPage( "/jsp/adminProgram.jsp" );
            return "adminProgram";
        }

        populate();

        logger.debugPage( "/jsp/adminProgramEdit.jsp" );
        return "adminProgramEdit";
    }

    public String deleteProgram() {
        if( programID == null || programID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_program_required" );
            
            logger.debugPage( "/jsp/adminProgram.jsp" );
            return "adminProgram";
        }

        populate();

        logger.debugPage( "/jsp/adminProgramDelete.jsp" );
        return "adminProgramDelete";
    }

    public String create() {
        logger.debugMethod( "create" );
        boolean result = false;
        boolean exists = true;
        boolean anErrorOcurred = false;

        if( description_EN == null || description_EN.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_description_required" );
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
            logger.debugPage( "/jsp/adminProgramAdd.jsp" );
            return "adminProgramAdd";
        }

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        exists = adminDAO.dataInfoExists( adminDAO.FROM_PROGRAM, adminDAO.BY_DESCRIPTION, description_EN );

        if( exists ) {
            addErrorMessage( "error_description_already_exists" );
            
            logger.debugPage( "/jsp/adminProgramAdd.jsp" );
            return "adminProgramAdd";
        }

        exists = adminDAO.dataInfoExists( adminDAO.FROM_PROGRAM, adminDAO.BY_ABBREVIATION, abbreviation );

        if( exists ) {
            addErrorMessage( "error_abbreviation_already_exists" );
            
            logger.debugPage( "/jsp/adminProgramAdd.jsp" );
            return "adminProgramAdd";
        }

        String newID = adminDAO.generateNewID( adminDAO.FROM_PROGRAM );

        String[] data = { description_EN, abbreviation, enabled };

        result = adminDAO.addDataInfo( adminDAO.FROM_PROGRAM, newID, data );

        if( result ) {
            addInfoMessage( "info_program_added_ok" );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
        }

        logger.debugPage( "/jsp/adminProgram.jsp" );
        return "adminProgram";
    }

    public String update() {
        logger.debugMethod( "update" );
        boolean result = false;

        boolean anErrorOcurred = false;

        if( description_EN == null || description_EN.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_description_required" );
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
            logger.debugPage( "/jsp/adminProgramEdit.jsp" );
            return "adminProgramEdit";
        }

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        String[] data = { description_EN, abbreviation, enabled };

        result = adminDAO.editDataInfo( adminDAO.FROM_PROGRAM, programID, data );

        if( result ) {
            addInfoMessage( "info_program_updated_ok" );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
        }

        logger.debugPage( "/jsp/adminProgram.jsp" );
        return "adminProgram";
    }

    public String delete() {
        logger.debugMethod( "delete" );
        boolean result = false;

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        result = adminDAO.deleteDataInfo( adminDAO.FROM_PROGRAM, programID );

        if( result ) {
            addInfoMessage( "info_program_deleted_ok" );
        }
        else {
            addErrorMessage( "error_db_delete_failed" );
        }

        logger.debugPage( "/jsp/adminProgram.jsp" );
        return "adminProgram";
    }

    public void populate() {
        logger.debugMethod( "populate" );
        
        if( programID != null && ! programID.trim().equals(EMPTY_STRING) ) {
            AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

            Map<String, Object> programMap = adminDAO.getDataInfo( adminDAO.FROM_PROGRAM, programID );

            setDescription_EN( ( String ) programMap.get( "Description_EN" ) );
            setAbbreviation( ( String ) programMap.get( "Abbreviation" ) );
            setEnabled( ( String ) programMap.get( "Enabled" ) );
            if( enabled.equals( "1" ) ) {
                setEnabledDesc( "Yes" );
            }
            else {
                setEnabledDesc( "No" );
            }
        }
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

    public String getProgramID() {
        return programID;
    }

    public void setProgramID( String programID ) {
        this.programID = programID;
    }

    public String getDescription_EN() {
        return description_EN;
    }

    public void setDescription_EN( String description_EN ) {
        this.description_EN = description_EN;
    }
}
