package com.moh.admin;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.AdminMaintenanceDAO;
import com.moh.utils.Logger;

public class AdminServiceTypeSSHelper extends AbstractBean {

    private String serviceTypeID;
    private String name_EN;
    private String abbreviation;

    public AdminServiceTypeSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminServiceTypeSSHelper" );
    }

    public String serviceTypeList() {
        populate();

        setServiceTypeID( EMPTY_STRING );

        logger.debugPage( "/jsp/adminServiceType.jsp" );
        return "adminServiceType";
    }

    public String addServiceType() {
        setServiceTypeID( EMPTY_STRING );
        setName_EN( EMPTY_STRING );
        setAbbreviation( EMPTY_STRING );

        logger.debugPage( "/jsp/adminServiceTypeAdd.jsp" );
        return "adminServiceTypeAdd";
    }

    public String editServiceType() {
        if( serviceTypeID == null || serviceTypeID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_servicetype_required" );
            
            logger.debugPage( "/jsp/adminServiceType.jsp" );
            return "adminServiceType";
        }

        populate();

        logger.debugPage( "/jsp/adminServiceTypeEdit.jsp" );
        return "adminServiceTypeEdit";
    }

    public String deleteServiceType() {
        if( serviceTypeID == null || serviceTypeID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_servicetype_required" );
            
            logger.debugPage( "/jsp/adminServiceType.jsp" );
            return "adminServiceType";
        }

        populate();

        logger.debugPage( "/jsp/adminServiceTypeDelete.jsp" );
        return "adminServiceTypeDelete";
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

        if( anErrorOcurred ) {
            logger.debugPage( "/jsp/adminServiceTypeAdd.jsp" );
            return "adminServiceTypeAdd";
        }

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        exists = adminDAO.dataInfoExists( adminDAO.FROM_SERVICETYPE, adminDAO.BY_NAME, name_EN );

        if( exists ) {
            addErrorMessage( "error_name_already_exists" );
            
            logger.debugPage( "/jsp/adminServiceTypeAdd.jsp" );
            return "adminServiceTypeAdd";
        }

        exists = adminDAO.dataInfoExists( adminDAO.FROM_SERVICETYPE, adminDAO.BY_ABBREVIATION, abbreviation );

        if( exists ) {
            addErrorMessage( "error_abbreviation_already_exists" );
            
            logger.debugPage( "/jsp/adminServiceTypeAdd.jsp" );
            return "adminServiceTypeAdd";
        }

        String newID = adminDAO.generateNewID( adminDAO.FROM_SERVICETYPE );

        String[] data = { name_EN, abbreviation };

        result = adminDAO.addDataInfo( adminDAO.FROM_SERVICETYPE, newID, data );

        if( result ) {
            addInfoMessage( "info_servicetype_added_ok" );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
        }

        logger.debugPage( "/jsp/adminServiceType.jsp" );
        return "adminServiceType";
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

        if( anErrorOcurred ) {
            logger.debugPage( "/jsp/adminServiceTypeEdit.jsp" );
            return "adminServiceTypeEdit";
        }

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        String[] data = { name_EN, abbreviation };

        result = adminDAO.editDataInfo( adminDAO.FROM_SERVICETYPE, serviceTypeID, data );

        if( result ) {
            addInfoMessage( "info_servicetype_updated_ok" );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
        }

        logger.debugPage( "/jsp/adminServiceType.jsp" );
        return "adminServiceType";
    }

    public String delete() {
        logger.debugMethod( "delete" );
        boolean result = false;

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        result = adminDAO.deleteDataInfo( adminDAO.FROM_SERVICETYPE, serviceTypeID );

        if( result ) {
            addInfoMessage( "info_servicetype_deleted_ok" );
        }
        else {
            addErrorMessage( "error_db_delete_failed" );
        }

        logger.debugPage( "/jsp/adminServiceType.jsp" );
        return "adminServiceType";
    }

    public void populate() {
        logger.debugMethod( "populate" );

        if( serviceTypeID != null && ! serviceTypeID.trim().equals(EMPTY_STRING) ) {
            AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

            Map<String, Object> serviceTypeMap = adminDAO.getDataInfo( adminDAO.FROM_SERVICETYPE, serviceTypeID );

            setName_EN( ( String ) serviceTypeMap.get( "Name_EN" ) );
            setAbbreviation( ( String ) serviceTypeMap.get( "Abbreviation" ) );
        }
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

    public String getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID( String serviceTypeID ) {
        this.serviceTypeID = serviceTypeID;
    }
}
