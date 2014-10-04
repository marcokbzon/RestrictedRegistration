package com.moh.admin;

import com.moh.common.AbstractBean;
import com.moh.data.load.Table_Agreement;
import com.moh.data.load.Table_Classification;
import com.moh.data.load.Table_Code;
import com.moh.data.load.Table_Country;
import com.moh.data.load.Table_InstClassification;
import com.moh.data.load.Table_InstLHIN;
import com.moh.data.load.Table_InstServType;
import com.moh.data.load.Table_Institution;
import com.moh.data.load.Table_LHIN;
import com.moh.data.load.Table_Program;
import com.moh.data.load.Table_ProvinceState;
import com.moh.data.load.Table_Role;
import com.moh.data.load.Table_ServiceType;
import com.moh.data.load.Table_University;
import com.moh.data.load.Table_UserInfo;
import com.moh.data.load.Table_UserRole;
import com.moh.utils.Logger;

public class AdminDatabaseRQHelper extends AbstractBean {

    public AdminDatabaseRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "AdminDatabaseRQHelper" );
    }

    public String loadCountry() {
        logger.debugMethod( "loadCountry" );

        Table_Country country = new Table_Country();
        country.clearTable();
        country.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadProvinceState() {
        logger.debugMethod( "loadProvinceState" );

        Table_ProvinceState provinceState = new Table_ProvinceState();
        provinceState.clearTable();
        provinceState.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadUniversity() {
        logger.debugMethod( "loadUniversity" );

        Table_University university = new Table_University();
        university.clearTable();
        university.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadProgram() {
        logger.debugMethod( "loadProgram" );

        Table_Program program = new Table_Program();
        program.clearTable();
        program.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadRole() {
        logger.debugMethod( "loadRole" );

        Table_Role role = new Table_Role();
        role.clearTable();
        role.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadCode() {
        logger.debugMethod( "loadCode" );

        Table_Code code = new Table_Code();
        code.clearTable();
        code.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadInstitution() {
        logger.debugMethod( "loadInstitution" );

        Table_Institution institution = new Table_Institution();
        institution.clearTable();
        institution.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadServiceType() {
        logger.debugMethod( "loadServiceType" );

        Table_ServiceType serviceType = new Table_ServiceType();
        serviceType.clearTable();
        serviceType.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadLHIN() {
        logger.debugMethod( "loadLHIN" );

        Table_LHIN lhin = new Table_LHIN();
        lhin.clearTable();
        lhin.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadClassification() {
        logger.debugMethod( "loadClassification" );

        Table_Classification classification = new Table_Classification();
        classification.clearTable();
        classification.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadAgreement() {
        logger.debugMethod( "loadAgreement" );

        Table_Agreement agreement = new Table_Agreement();
        agreement.clearTable();
        agreement.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadInstLHIN() {
        logger.debugMethod( "loadInstLHIN" );

        Table_InstLHIN instLHIN = new Table_InstLHIN();
        instLHIN.clearTable();
        instLHIN.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadInstClassification() {
        logger.debugMethod( "loadInstClassification" );

        Table_InstClassification instClassification = new Table_InstClassification();
        instClassification.clearTable();
        instClassification.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadInstServiceType() {
        logger.debugMethod( "loadInstServiceType" );

        Table_InstServType instServiceType = new Table_InstServType();
        instServiceType.clearTable();
        instServiceType.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadUserInfo() {
        logger.debugMethod( "loadUserInfo" );

        Table_UserInfo userInfo = new Table_UserInfo();
        userInfo.clearTable();
        userInfo.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }

    public String loadUserRole() {
        logger.debugMethod( "loadUserRole" );

        Table_UserRole userRole = new Table_UserRole();
        userRole.clearTable();
        userRole.loadTable();

        logger.debugPage( "/jsp/adminDataLoad.jsp" );
        return "adminDataLoad";
    }
}
