package com.moh.admin;

import java.util.Map;

import com.moh.common.AbstractBean;
import com.moh.data.dao.AdminMaintenanceDAO;
import com.moh.data.dao.ListAbDAO;
import com.moh.utils.Logger;

public class AdminInstitutionSSHelper extends AbstractBean {

    private String institutionID;
    private String institutionName;
    private String abbreviation;
    private String uapDesignated;
    private String uapDesignatedDesc;
    private String enabled;
    private String enabledDesc;
    private String lhinID;
    private String lhinName;

    public AdminInstitutionSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminInstitutionSSHelper" );
    }

    public String institutionList() {
        populate();

        setInstitutionID( EMPTY_STRING );

        logger.debugPage( "/jsp/adminInstitution.jsp" );
        return "adminInstitution";
    }

    public String addInstitution() {
        setInstitutionID( EMPTY_STRING );
        setInstitutionName( EMPTY_STRING );
        setAbbreviation( EMPTY_STRING );
        setUapDesignated( EMPTY_STRING );
        setEnabled( EMPTY_STRING );
        setLhinID( EMPTY_STRING );
        setLhinName( EMPTY_STRING );

        logger.debugPage( "/jsp/adminInstitutionAdd.jsp" );
        return "adminInstitutionAdd";
    }

    public String editInstitution() {
        if( institutionID == null || institutionID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_institution_required" );
            
            logger.debugPage( "/jsp/adminInstitution.jsp" );
            return "adminInstitution";
        }

        populate();

        logger.debugPage( "/jsp/adminInstitutionEdit.jsp" );
        return "adminInstitutionEdit";
    }

    public String deleteInstitution() {
        if( institutionID == null || institutionID.trim().equals(EMPTY_STRING) ) {
            addErrorMessage( "error_institution_required" );
            
            logger.debugPage( "/jsp/adminInstitution.jsp" );
            return "adminInstitution";
        }

        populate();

        logger.debugPage( "/jsp/adminInstitutionDelete.jsp" );
        return "adminInstitutionDelete";
    }

    public String create() {
        logger.debugMethod( "create" );
        boolean result = false;
        boolean exists = true;
        boolean anErrorOcurred = false;

        if( institutionName == null || institutionName.trim().equals( EMPTY_STRING ) ) {
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
        if( lhinID == null || lhinID.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_lhin_required" );
            anErrorOcurred = true;
        }

        if( anErrorOcurred ) {
            logger.debugPage( "/jsp/adminInstitutionAdd.jsp" );
            return "adminInstitutionAdd";
        }

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        exists = adminDAO.dataInfoExists( adminDAO.FROM_INSTITUTION, adminDAO.BY_NAME, institutionName );

        if( exists ) {
            addErrorMessage( "error_name_already_exists" );
            
            logger.debugPage( "/jsp/adminInstitutionAdd.jsp" );
            return "adminInstitutionAdd";
        }

        //exists = adminDAO.dataInfoExists( adminDAO.FROM_INSTITUTION, adminDAO.BY_ABBREVIATION, abbreviation );

        //if ( exists ) {
        //	addErrorMessage( "error_abbreviation_already_exists" );
        //
        //      logger.debugPage( "/jsp/adminInstitutionAdd.jsp" );
        //	return "adminInstitutionAdd";
        //}

        String newID = adminDAO.generateNewID( adminDAO.FROM_INSTITUTION );

        String[] data = { institutionName, abbreviation, uapDesignated, enabled };

        result = adminDAO.addDataInfo( adminDAO.FROM_INSTITUTION, newID, data );

        if( result ) {
            addInfoMessage( "info_institution_added_ok" );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
        }

        if( lhinID.length() > COL_LENGTH_LHINID ) {
            lhinID = lhinID.substring( lhinID.length() - COL_LENGTH_LHINID );
        }

        String[] data2 = { lhinID };

        result = adminDAO.addDataInfo( adminDAO.FROM_INSTLHIN, newID, data2 );

        if( result ) {
            //addInfoMessage( "info_institution_added_ok" );
        }
        else {
            addErrorMessage( "error_db_insert_failed" );
        }

        logger.debugPage( "/jsp/adminInstitution.jsp" );
        return "adminInstitution";
    }

    public String update() {
        logger.debugMethod( "update" );
        boolean result = false;
        boolean anErrorOcurred = false;

        if( institutionName == null || institutionName.trim().equals( EMPTY_STRING ) ) {
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
        if( lhinID == null || lhinID.trim().equals( EMPTY_STRING ) ) {
            addErrorMessage( "error_lhin_required" );
            anErrorOcurred = true;
        }

        if( anErrorOcurred ) {
            logger.debugPage( "/jsp/adminInstitutionEdit.jsp" );
            return "adminInstitutionEdit";
        }

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        String[] data = { institutionName, abbreviation, uapDesignated, enabled };

        result = adminDAO.editDataInfo( adminDAO.FROM_INSTITUTION, institutionID, data );

        if( result ) {
            addInfoMessage( "info_institution_updated_ok" );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
        }

        if( lhinID.length() > COL_LENGTH_LHINID ) {
            lhinID = lhinID.substring( lhinID.length() - COL_LENGTH_LHINID );
        }

        String[] data2 = { lhinID };

        result = adminDAO.editDataInfo( adminDAO.FROM_INSTLHIN, institutionID, data2 );

        if( result ) {
            //addInfoMessage( "info_institution_updated_ok" );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
        }

        logger.debugPage( "/jsp/adminInstitution.jsp" );
        return "adminInstitution";
    }

    public String delete() {
        logger.debugMethod( "delete" );
        boolean result = false;

        AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

        result = adminDAO.deleteDataInfo( adminDAO.FROM_INSTLHIN, institutionID );

        if( result ) {
            //addInfoMessage( "info_institution_deleted_ok" );
        }
        else {
            addErrorMessage( "error_db_delete_failed" );
        }

        result = adminDAO.deleteDataInfo( adminDAO.FROM_INSTITUTION, institutionID );

        if( result ) {
            addInfoMessage( "info_institution_deleted_ok" );
        }
        else {
            addErrorMessage( "error_db_delete_failed" );
        }

        logger.debugPage( "/jsp/adminInstitution.jsp" );
        return "adminInstitution";
    }

    public void populate() {
        logger.debugMethod( "populate" );
        
        if( institutionID != null && ! institutionID.trim().equals(EMPTY_STRING) ) {
            AdminMaintenanceDAO adminDAO = new AdminMaintenanceDAO();

            Map<String, Object> institutionMap = adminDAO.getDataInfo( adminDAO.FROM_INSTITUTION, institutionID );

            setInstitutionName( ( String ) institutionMap.get( "InstitutionName" ) );
            setAbbreviation( ( String ) institutionMap.get( "Abbreviation" ) );
            setUapDesignated( ( String ) institutionMap.get( "UAPdesignated" ) );
            if( uapDesignated != null && uapDesignated.trim().equals(EMPTY_STRING) ) {
                if( uapDesignated.equals( "1" ) ) {
                    setUapDesignatedDesc( "Yes" );
                }
                else {
                    setUapDesignatedDesc( "No" );
                }
            }
            setEnabled( ( String ) institutionMap.get( "Enabled" ) );
            if( enabled != null && enabled.trim().equals(EMPTY_STRING) ) {
                if( enabled.equals( "1" ) ) {
                    setEnabledDesc( "Yes" );
                }
                else {
                    setEnabledDesc( "No" );
                }
            }

            ListAbDAO listDAO = new ListAbDAO();

            listDAO.getLHINs( ( String ) institutionMap.get( "LHIN_ID" ) );

            setLhinID( listDAO.getNumberedLHINID() );
            setLhinName( ( String ) institutionMap.get( "LHIN_Name" ) );
        }
    }

    public String getInstitutionID() {
        return institutionID;
    }

    public void setInstitutionID( String institutionID ) {
        this.institutionID = institutionID;
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

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName( String institutionName ) {
        this.institutionName = institutionName;
    }

    public String getUapDesignated() {
        return uapDesignated;
    }

    public void setUapDesignated( String uapDesignated ) {
        this.uapDesignated = uapDesignated;
    }

    public String getUapDesignatedDesc() {
        return uapDesignatedDesc;
    }

    public void setUapDesignatedDesc( String uapDesignatedDesc ) {
        this.uapDesignatedDesc = uapDesignatedDesc;
    }

    public String getLhinID() {
        return lhinID;
    }

    public void setLhinID( String lhinID ) {
        this.lhinID = lhinID;
    }

    public String getLhinName() {
        return lhinName;
    }

    public void setLhinName( String lhinName ) {
        this.lhinName = lhinName;
    }
}
