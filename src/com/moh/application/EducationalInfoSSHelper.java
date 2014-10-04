package com.moh.application;

import java.util.Map;

import javax.faces.event.ValueChangeEvent;

import com.moh.common.AbstractBean;
import com.moh.data.dao.EducationDAO;
import com.moh.data.dao.ListAaDAO;
import com.moh.utils.Logger;

public class EducationalInfoSSHelper extends AbstractBean {

    private String postGradYear;
    private String schoolResidency;
    private String pgProgram;
    private String countryCode;
    private String provStateCode;
    private String medSchool;
    private boolean atlsIndicator;
    private String atlsYear;
    private boolean aclsIndicator;
    private String aclsYear;
    private boolean palsIndicator;
    private String palsYear;
    private boolean nalsIndicator;
    private String nalsYear;
    private boolean northernStreamIndicator;
    private String mccqe1Month;
    private String mccqe1Year;
    private String mccqe2Month;
    private String mccqe2Year;
    private String cpsoNumber;
    private String cmpaNumber;
    private String email;

    public EducationalInfoSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "EducationalInfoSSHelper" );
    }

    public void changeSchool( ValueChangeEvent event ) {
        String newSchoolResidency = EMPTY_STRING;
		
        if ( event != null ) {
            try {
                Object eventNewValue = event.getNewValue();

                if ( eventNewValue != null ) {
                    newSchoolResidency = event.getNewValue().toString();
                }
            }
            catch ( NullPointerException npex ) {
                logger.exception( npex );
            }
        }

        if( newSchoolResidency.trim().length() == ( COL_LENGTH_UNIVERSITYID + COL_SORT_LENGTH ) ) {
            newSchoolResidency = newSchoolResidency.substring( COL_SORT_LENGTH );
        }

        setPgProgram( "00000" );

        addToSession( SESSION_UNIVERSITYID, newSchoolResidency );

        //FacesContext.getCurrentInstance().renderResponse();
    }

    public String update() {
        logger.debugMethod( "update" );
        boolean result = false;

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        if( atlsIndicator & atlsYear.equals( "0" ) ) {
            addErrorMessage( "error_atlsYear_required" );
            
            logger.debugPage( "/jsp/updateProfile02.jsp" );
            return "updateProfile02";
        }
        if( aclsIndicator & aclsYear.equals( "0" ) ) {
            addErrorMessage( "error_aclsYear_required" );
            
            logger.debugPage( "/jsp/updateProfile02.jsp" );
            return "updateProfile02";
        }
        if( palsIndicator & palsYear.equals( "0" ) ) {
            addErrorMessage( "error_palsYear_required" );
            
            logger.debugPage( "/jsp/updateProfile02.jsp" );
            return "updateProfile02";
        }
        if( nalsIndicator & nalsYear.equals( "0" ) ) {
            addErrorMessage( "error_nalsYear_required" );
            
            logger.debugPage( "/jsp/updateProfile02.jsp" );
            return "updateProfile02";
        }

        if( ( !atlsYear.equals( "0" ) ) & ( !atlsIndicator ) ) {
            addErrorMessage( "error_atlsInd_required" );
            
            logger.debugPage( "/jsp/updateProfile02.jsp" );
            return "updateProfile02";
        }
        if( ( !aclsYear.equals( "0" ) ) & ( !aclsIndicator ) ) {
            addErrorMessage( "error_aclsInd_required" );
            
            logger.debugPage( "/jsp/updateProfile02.jsp" );
            return "updateProfile02";
        }
        if( ( !palsYear.equals( "0" ) ) & ( !palsIndicator ) ) {
            addErrorMessage( "error_palsInd_required" );
            
            logger.debugPage( "/jsp/updateProfile02.jsp" );
            return "updateProfile02";
        }
        if( ( !nalsYear.equals( "0" ) ) & ( !nalsIndicator ) ) {
            addErrorMessage( "error_nalsInd_required" );
            
            logger.debugPage( "/jsp/updateProfile02.jsp" );
            return "updateProfile02";
        }

        String mccqe1 = EMPTY_STRING;

        if( mccqe1Year == null || mccqe1Year.trim().equals(EMPTY_STRING) ) {
            mccqe1Year = "0000";
        }
        if( mccqe1Month == null || mccqe1Month.trim().equals(EMPTY_STRING) ) {
            mccqe1Month = "00";
        }
        if( mccqe2Year == null || mccqe2Year.trim().equals(EMPTY_STRING) ) {
            mccqe2Year = "0000";
        }
        if( mccqe2Month == null || mccqe2Month.trim().equals(EMPTY_STRING) ) {
            mccqe2Month = "00";
        }

        if( !( mccqe1Year.equals( "0000" ) || mccqe1Month.equals( "00" ) ) ) {
            mccqe1 = mccqe1Year + mccqe1Month;
        }
        else {
            if( mccqe1Year.equals( "0000" ) & ( !mccqe1Month.equals( "00" ) ) ) {
                addErrorMessage( "error_mccqe1_required_both" );
                
                logger.debugPage( "/jsp/updateProfile02.jsp" );
                return "updateProfile02";
            }
            else {
                if( ( !mccqe1Year.equals( "0000" ) ) & mccqe1Month.equals( "00" ) ) {
                    addErrorMessage( "error_mccqe1_required_both" );
                    
                    logger.debugPage( "/jsp/updateProfile02.jsp" );
                    return "updateProfile02";
                }
            }
        }

        String mccqe2 = EMPTY_STRING;

        if( !( mccqe2Year.equals( "0000" ) || mccqe2Month.equals( "00" ) ) ) {
            mccqe2 = mccqe2Year + mccqe2Month;
        }
        else {
            if( mccqe2Year.equals( "0000" ) & ( !mccqe2Month.equals( "00" ) ) ) {
                addErrorMessage( "error_mccqe2_required_both" );
                
                logger.debugPage( "/jsp/updateProfile02.jsp" );
                return "updateProfile02";
            }
            else {
                if( ( !mccqe2Year.equals( "0000" ) ) & mccqe2Month.equals( "00" ) ) {
                    addErrorMessage( "error_mccqe2_required_both" );
                    
                    logger.debugPage( "/jsp/updateProfile02.jsp" );
                    return "updateProfile02";
                }
            }
        }

        if( countryCode == null || countryCode.equals( "000000" ) ) {
            countryCode = EMPTY_STRING;
        }

        EducationDAO educationDAO = new EducationDAO();
        result = educationDAO.updateEducation( getEmail(), Integer.parseInt( postGradYear ), schoolResidency, pgProgram, northernStreamIndicator, countryCode, EMPTY_STRING, medSchool, Integer.parseInt( atlsYear ), Integer.parseInt( aclsYear ), Integer.parseInt( palsYear ), Integer.parseInt( nalsYear ), mccqe1, mccqe2, cpsoNumber, cmpaNumber );

        if( result ) {
            addInfoMessage( "info_education_updated_ok" );
        }
        else {
            addErrorMessage( "error_db_update_failed" );
        }

        logger.debugPage( "/jsp/updateProfile02.jsp" );
        return "updateProfile02";
    }

    public String updateProfile() {
        populate();

        logger.debugPage( "/jsp/updateProfile02.jsp" );
        return "updateProfile02";
    }

    public void populate() {
        logger.debugMethod( "populate" );
        EducationDAO educationDAO = new EducationDAO();
        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );
        Map<String, Object> sql_result = educationDAO.getEducation( getEmail() );

        ListAaDAO listDAO = new ListAaDAO();

        setPostGradYear( ( String ) sql_result.get( "PostGraduateYear" ) );

        listDAO.getUniversities( ( String ) sql_result.get( "UniversityID" ) );
        
        String numberedUniversityID = listDAO.getNumberedUniversityID();
        
        setSchoolResidency( numberedUniversityID );

        String selectedUniversity = EMPTY_STRING;

        if( numberedUniversityID != null && ! numberedUniversityID.trim().equals(EMPTY_STRING) ) {
            selectedUniversity = numberedUniversityID.substring( COL_SORT_LENGTH );
        }

        addToSession( SESSION_UNIVERSITYID, selectedUniversity );

        listDAO.getCustomPrograms( ( String ) sql_result.get( "ProgramID" ), ( String ) getFromSession( SESSION_UNIVERSITYID ) );

        String selectedProg = "00000";

        String numberedProgramID = listDAO.getNumberedProgramID();
        
        if( numberedProgramID != null && ! numberedProgramID.trim().equals(EMPTY_STRING) ) {
            selectedProg = numberedProgramID;
        }

        setPgProgram( selectedProg );

        if( sql_result.get( "NorthernStream" ) != null ) {
            setNorthernStreamIndicator( ( Boolean ) sql_result.get( "NorthernStream" ) );
        }

        listDAO.getCountries( ( String ) sql_result.get( "CountryCode" ) );
        setCountryCode( listDAO.getNumberedCountryCode() );

        listDAO.getProvStates( "CAN", ( String ) sql_result.get( "ProvStateCode" ) );
        setProvStateCode( listDAO.getNumberedProvStateCode() );

        setMedSchool( ( String ) sql_result.get( "MedicalSchool" ) );
        setAtlsYear( ( String ) sql_result.get( "ATLSyear" ) );
        setAclsYear( ( String ) sql_result.get( "ACLSyear" ) );
        setPalsYear( ( String ) sql_result.get( "PALSyear" ) );
        setNalsYear( ( String ) sql_result.get( "NALSyear" ) );

        if( atlsYear.equals( "0" ) ) {
            setAtlsIndicator( false );
        }
        else {
            setAtlsIndicator( true );
        }
        if( aclsYear.equals( "0" ) ) {
            setAclsIndicator( false );
        }
        else {
            setAclsIndicator( true );
        }
        if( palsYear.equals( "0" ) ) {
            setPalsIndicator( false );
        }
        else {
            setPalsIndicator( true );
        }
        if( nalsYear.equals( "0" ) ) {
            setNalsIndicator( false );
        }
        else {
            setNalsIndicator( true );
        }

        setMccqe1Year( ( String ) sql_result.get( "MCCQE1year" ) );
        setMccqe1Month( ( String ) sql_result.get( "MCCQE1month" ) );
        setMccqe2Year( ( String ) sql_result.get( "MCCQE2year" ) );
        setMccqe2Month( ( String ) sql_result.get( "MCCQE2month" ) );

        setCpsoNumber( ( String ) sql_result.get( "CPSOnumber" ) );
        setCmpaNumber( ( String ) sql_result.get( "CMPAnumber" ) );
    }

    public String getPostGradYear() {
        return postGradYear;
    }

    public void setPostGradYear( String postGradYear ) {
        this.postGradYear = postGradYear;
    }

    public String getSchoolResidency() {
        return schoolResidency;
    }

    public void setSchoolResidency( String schoolResidency ) {
        this.schoolResidency = schoolResidency;
    }

    public String getPgProgram() {
        return pgProgram;
    }

    public void setPgProgram( String pgProgram ) {
        this.pgProgram = pgProgram;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode( String countryCode ) {
        this.countryCode = countryCode;
    }

    public String getProvStateCode() {
        return provStateCode;
    }

    public void setProvStateCode( String provStateCode ) {
        this.provStateCode = provStateCode;
    }

    public String getMedSchool() {
        if( medSchool != null && ! medSchool.trim().equals(EMPTY_STRING) ) {
            return medSchool.trim();
        }
        else {
            return EMPTY_STRING;
        }
    }

    public void setMedSchool( String medSchool ) {
        this.medSchool = medSchool;
    }

    public boolean getAtlsIndicator() {
        return atlsIndicator;
    }

    public void setAtlsIndicator( boolean atlsIndicator ) {
        this.atlsIndicator = atlsIndicator;
    }

    public String getAtlsYear() {
        return atlsYear;
    }

    public void setAtlsYear( String atlsYear ) {
        this.atlsYear = atlsYear;
    }

    public boolean getAclsIndicator() {
        return aclsIndicator;
    }

    public void setAclsIndicator( boolean aclsIndicator ) {
        this.aclsIndicator = aclsIndicator;
    }

    public String getAclsYear() {
        return aclsYear;
    }

    public void setAclsYear( String aclsYear ) {
        this.aclsYear = aclsYear;
    }

    public boolean getPalsIndicator() {
        return palsIndicator;
    }

    public void setPalsIndicator( boolean palsIndicator ) {
        this.palsIndicator = palsIndicator;
    }

    public String getPalsYear() {
        return palsYear;
    }

    public void setPalsYear( String palsYear ) {
        this.palsYear = palsYear;
    }

    public boolean getNalsIndicator() {
        return nalsIndicator;
    }

    public void setNalsIndicator( boolean nalsIndicator ) {
        this.nalsIndicator = nalsIndicator;
    }

    public String getNalsYear() {
        return nalsYear;
    }

    public void setNalsYear( String nalsYear ) {
        this.nalsYear = nalsYear;
    }

    public String getMccqe1Month() {
        return mccqe1Month;
    }

    public void setMccqe1Month( String mccqe1Month ) {
        this.mccqe1Month = mccqe1Month;
    }

    public String getMccqe1Year() {
        return mccqe1Year;
    }

    public void setMccqe1Year( String mccqe1Year ) {
        this.mccqe1Year = mccqe1Year;
    }

    public String getMccqe2Month() {
        return mccqe2Month;
    }

    public void setMccqe2Month( String mccqe2Month ) {
        this.mccqe2Month = mccqe2Month;
    }

    public String getMccqe2Year() {
        return mccqe2Year;
    }

    public void setMccqe2Year( String mccqe2Year ) {
        this.mccqe2Year = mccqe2Year;
    }

    public String getCpsoNumber() {
        if( cpsoNumber != null && ! cpsoNumber.trim().equals(EMPTY_STRING) ) {
            return cpsoNumber.trim();
        }
        else {
            return EMPTY_STRING;
        }
    }

    public void setCpsoNumber( String cpsoNumber ) {
        this.cpsoNumber = cpsoNumber;
    }

    public String getCmpaNumber() {
        if( cmpaNumber != null && ! cmpaNumber.trim().equals(EMPTY_STRING) ) {
            return cmpaNumber.trim();
        }
        else {
            return EMPTY_STRING;
        }
    }

    public void setCmpaNumber( String cmpaNumber ) {
        this.cmpaNumber = cmpaNumber;
    }

    public boolean getNorthernStreamIndicator() {
        return northernStreamIndicator;
    }

    public void setNorthernStreamIndicator( boolean northernStreamIndicator ) {
        this.northernStreamIndicator = northernStreamIndicator;
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
