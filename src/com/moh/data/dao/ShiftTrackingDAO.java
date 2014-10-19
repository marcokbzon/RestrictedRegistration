package com.moh.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.moh.common._ParentDAO;
import com.moh.data.bean.ReportShiftsByApplicationData;
import com.moh.data.bean.ReportShiftsByDateData;
import com.moh.data.bean.ReportShiftsByInstitutionData;
import com.moh.data.bean.ReportShiftsByServTypeData;
import com.moh.data.bean.ShiftCalendarData;
import com.moh.data.bean.ShiftFormData;
import com.moh.data.bean.ShiftTrackingData;
import com.moh.utils.Logger;

public class ShiftTrackingDAO extends _ParentDAO {

    private String GET_CALENDAR_INFO_SQL =
            "SELECT Month, Shifts, Hours "
            + "FROM ShiftSummary "
            + "WHERE Email = ? "
            + "AND YearValue = ? "
            + "ORDER BY Month";
    private String GET_APP_LIST_INFO_P1_SQL =
            "SELECT aprcmt.ApplicationID, ins.Name_EN AS InstName, srv.Name_EN ServName "
            + "FROM ApprovalCommittee aprcmt, Application app, Institution ins, ServiceType srv "
            + "WHERE app.ApprovedByCPSO = 1 "
            + "AND aprcmt.ApplicationID = app.ApplicationID "
            + "AND app.Email = ? "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID";
    private String GET_APP_LIST_INFO_P2_SQL =
            "SELECT Shifts, Hours "
            + "FROM ShiftTracking "
            + "WHERE ApplicationID = ? "
            + "AND YearValue = ? "
            + "AND Month = ?";
    private String GET_LIST_HEADER_SQL =
            "SELECT Shifts, Hours "
            + "FROM ShiftSummary "
            + "WHERE Email = ? "
            + "AND YearValue = ? "
            + "AND Month = ?";
    private String GET_FORM_HEADER_SQL =
            "SELECT ins.Name_EN AS InstName, srv.Name_EN ServName "
            + "FROM Application app, Institution ins, ServiceType srv "
            + "WHERE app.ApplicationID = ? "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID";
    private String GET_FORM_DATA_SQL =
            "SELECT Shifts, Weekday, Weeknight, WeekendDay, WeekendNight, Hours, LocumWeek "
            + "FROM ShiftTracking "
            + "WHERE ApplicationID = ? "
            + "AND YearValue = ? "
            + "AND Month = ?";
    // ShiftTracking Table
    private String UPDATE_FORM_DATA_SQL =
            "UPDATE ShiftTracking "
            + "SET Shifts = ?, Weekday = ?, Weeknight = ?, WeekendDay = ?, WeekendNight = ?, Hours = ?, LocumWeek = ? "
            + "WHERE ApplicationID = ? "
            + "AND YearValue = ? "
            + "AND Month = ?";
    private String ADD_FORM_DATA_SQL =
            "INSERT INTO ShiftTracking "
            + "( ApplicationID, YearValue, Month, Shifts, Weekday, Weeknight, WeekendDay, WeekendNight, Hours, LocumWeek ) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private String CHECK_FORM_DATA_SQL =
            "SELECT COUNT(*) AS ShiftCount "
            + "FROM ShiftTracking "
            + "WHERE ApplicationID = ? "
            + "AND YearValue = ? "
            + "AND Month = ?";
    // ShiftSummary Table
    private String UPDATE_SUMMARY_DATA_SQL =
            "UPDATE ShiftSummary "
            + "SET Shifts = ?, Hours = ? "
            + "WHERE Email = ? "
            + "AND YearValue = ? "
            + "AND Month = ?";
    private String ADD_SUMMARY_DATA_SQL =
            "INSERT INTO ShiftSummary "
            + "( Email, YearValue, Month, Shifts, Hours ) "
            + "VALUES ( ?, ?, ?, ?, ? )";
    private String CHECK_SUMMARY_DATA_SQL =
            "SELECT COUNT(*) AS ShiftCount "
            + "FROM ShiftSummary "
            + "WHERE Email = ? "
            + "AND YearValue = ? "
            + "AND Month = ?";
    private String GET_TOTAL_BY_DATE_SQL =
            "SELECT SUM(shf.Shifts) AS SumShifts, SUM(shf.Hours) AS SumHours "
            + "FROM ShiftTracking shf, Application app "
            + "WHERE shf.ApplicationID = app.ApplicationID "
            + "AND app.Email = ? "
            + "AND YearValue = ? "
            + "AND Month = ?";
    // Reports
    private String GET_SHIFTS_BY_SERVICE_SQL =
            "SELECT srv.ServiceTypeID, srv.Name_EN, SUM(shf.Shifts) AS ShiftsTotal, SUM(shf.Hours) AS HoursTotal "
            + "FROM ShiftTracking shf, Application app, ServiceType srv "
            + "WHERE shf.ApplicationID = app.ApplicationID "
            + "AND app.ServiceTypeID = srv.ServiceTypeID "
            + "GROUP BY srv.ServiceTypeID, srv.Name_EN "
            + "ORDER BY srv.Name_EN ASC";
    private String GET_SHIFTS_BY_INSTITUTION_SQL =
            "SELECT ins.InstitutionID, ins.Name_EN, SUM(shf.Shifts) AS ShiftsTotal, SUM(shf.Hours) AS HoursTotal "
            + "FROM ShiftTracking shf, Application app, Institution ins "
            + "WHERE shf.ApplicationID = app.ApplicationID "
            + "AND app.InstitutionID = ins.InstitutionID "
            + "GROUP BY ins.InstitutionID, ins.Name_EN "
            + "ORDER BY ins.Name_EN";
    private String GET_WEEKDAY_SHIFTS_HOURS_SQL =
            "SELECT COUNT(*) AS WeekCount "
            + "FROM ShiftTracking "
            + "WHERE Weekday = 1";
    private String GET_WEEKNIGHT_SHIFTS_HOURS_SQL =
            "SELECT COUNT(*) AS WeekCount "
            + "FROM ShiftTracking "
            + "WHERE WeekNight = 1";
    private String GET_WEEKENDDAY_SHIFTS_HOURS_SQL =
            "SELECT COUNT(*) AS WeekCount "
            + "FROM ShiftTracking "
            + "WHERE WeekendDay = 1";
    private String GET_WEEKENDNIGHT_SHIFTS_HOURS_SQL =
            "SELECT COUNT(*) AS WeekCount "
            + "FROM ShiftTracking "
            + "WHERE WeekendNight = 1";
    private String GET_LOCUMWEEK_SHIFTS_HOURS_SQL =
            "SELECT COUNT(*) AS WeekCount "
            + "FROM ShiftTracking "
            + "WHERE LocumWeek = 1";
    /*
    private String GET_SHIFTS_BY_APPLICATION_SQL =
            "SELECT ApplicationID, SUM(Shifts) AS ShiftsTotal, SUM(Hours) AS HoursTotal "
            + "FROM ShiftTracking "
            + "GROUP BY ApplicationID "
            + "ORDER BY ApplicationID ASC";
    */
    private String GET_SHIFTS_BY_APPLICATION_SQL =
    		"SELECT ap.ApplicationID, ui.FirstName, ui.LastName, ap.Email, ap.SupervisorName, it.Name_EN AS Instituion, SUM(st.Shifts) AS ShiftsTotal, SUM(st.Hours) AS HoursTotal "
    		+ "FROM ShiftTracking st, Application ap, UserInfo ui, Institution it "
    		+ "WHERE ap.ApplicationID = st.ApplicationID " 
    		+ "AND ap.Email = ui.Email "
    		+ "AND ap.InstitutionID = it.InstitutionID "  
    		+ "GROUP BY ap.ApplicationID, ui.FirstName, ui.LastName, ap.Email, ap.SupervisorName, it.Name_EN "
    		+ "ORDER BY ap.ApplicationID ASC";

    private String GET_SHIFTS_BY_DATE_SQL =
            "SELECT YearValue, Month, SUM(Shifts) AS ShiftsTotal, SUM(Hours) AS HoursTotal "
            + "FROM ShiftTracking "
            + "GROUP BY YearValue, Month "
            + "ORDER BY YearValue, Month ASC";

    private String email;
    
    public ShiftTrackingDAO() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ShiftTrackingDAO" );
    }

    public HashSet<ShiftCalendarData> getCalendarInfo( String email, String year ) {
        logger.debugMethod( "getCalendarInfo" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        HashSet<ShiftCalendarData> hSet = new HashSet<>();
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_CALENDAR_INFO_SQL );
            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, year );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String month = resultSet.getString( "Month" );
                int shifts = resultSet.getInt( "Shifts" );
                int hours = resultSet.getInt( "Hours" );

                ShiftCalendarData shiftData = new ShiftCalendarData();

                shiftData.setMonth( month );
                shiftData.setShifts( shifts );
                shiftData.setHours( hours );

                hSet.add( shiftData );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return hSet;
    }

    public List<ShiftTrackingData> getShiftTrackingListing( String email, String year, String month ) {
        logger.debugMethod( "getShiftTrackingListing" );
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        Connection connection = null;
        List<ShiftTrackingData> shiftTrackingList = new ArrayList<>();
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_APP_LIST_INFO_P1_SQL );
            preparedStatement.setString( 1, getEmail() );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String numberOfShifts = EMPTY_STRING;
                String numberOfHours = EMPTY_STRING;

                String applicationID = resultSet.getString( "ApplicationID" );
                String institution = resultSet.getString( "InstName" );
                String serviceType = resultSet.getString( "ServName" );

                preparedStatement2 = connection.prepareStatement( GET_APP_LIST_INFO_P2_SQL );
                preparedStatement2.setString( 1, applicationID );
                preparedStatement2.setString( 2, year );
                preparedStatement2.setString( 3, month );

                resultSet2 = preparedStatement2.executeQuery();

                while( resultSet2.next() ) {
                    numberOfShifts = EMPTY_STRING + resultSet2.getInt( "Shifts" );
                    numberOfHours = EMPTY_STRING + resultSet2.getInt( "Hours" );
                }

                if( numberOfShifts == null || numberOfShifts.trim().equals( EMPTY_STRING ) ) {
                    numberOfShifts = "0";
                }
                if( numberOfHours == null || numberOfHours.trim().equals( EMPTY_STRING ) ) {
                    numberOfHours = "0";
                }

                shiftTrackingList.add( new ShiftTrackingData( applicationID, institution, serviceType, numberOfShifts, numberOfHours ) );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( resultSet2 != null ) {
                    resultSet2.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( preparedStatement2 != null ) {
                    preparedStatement2.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return shiftTrackingList;
    }

    public String[] getListingHeader( String email, String year, String month ) {
        logger.debugMethod( "getListingHeader" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String[] hdrInfo = new String[2];
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_LIST_HEADER_SQL );
            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, year );
            preparedStatement.setString( 3, month );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                int shifts = resultSet.getInt( "Shifts" );
                int hours = resultSet.getInt( "Hours" );

                hdrInfo[0] = EMPTY_STRING + shifts;
                hdrInfo[1] = EMPTY_STRING + hours;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return hdrInfo;
    }

    public ShiftFormData getShiftFormData( String applicationID, String year, String month ) {
        logger.debugMethod( "getShiftFormData" );
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        Connection connection = null;

        ShiftFormData shiftFormData = null;

        String institution = EMPTY_STRING;
        String serviceType = EMPTY_STRING;
        String shifts = "0";
        String hours = "0";
        boolean weekday = false;
        boolean weeknight = false;
        boolean weekendDay = false;
        boolean weekendNight = false;
        boolean locumWeek = false;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_FORM_HEADER_SQL );
            preparedStatement.setString( 1, applicationID );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                institution = resultSet.getString( "InstName" );
                serviceType = resultSet.getString( "ServName" );
            }

            preparedStatement2 = connection.prepareStatement( GET_FORM_DATA_SQL );
            preparedStatement2.setString( 1, applicationID );
            preparedStatement2.setString( 2, year );
            preparedStatement2.setString( 3, month );

            resultSet2 = preparedStatement2.executeQuery();

            while( resultSet2.next() ) {
                shifts = EMPTY_STRING + resultSet2.getInt( "Shifts" );
                hours = EMPTY_STRING + resultSet2.getInt( "Hours" );
                weekday = resultSet2.getBoolean( "Weekday" );
                weeknight = resultSet2.getBoolean( "Weeknight" );
                weekendDay = resultSet2.getBoolean( "WeekendDay" );
                weekendNight = resultSet2.getBoolean( "WeekendNight" );
                locumWeek = resultSet2.getBoolean( "LocumWeek" );
            }

            String yearMonth = numberToMonth( month ) + " " + getFromSession( SHIFT_TRACK_YEAR );

            shiftFormData = new ShiftFormData( yearMonth, applicationID, institution, serviceType, shifts, hours, weekday, weeknight, weekendDay, weekendNight, locumWeek );
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( resultSet2 != null ) {
                    resultSet2.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( preparedStatement2 != null ) {
                    preparedStatement2.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return shiftFormData;
    }

    public boolean formDataExists( String applicationID, String year, String month ) {
        logger.debugMethod( "formDataExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean alreadyExists = true;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_FORM_DATA_SQL );
            preparedStatement.setString( 1, applicationID );
            preparedStatement.setString( 2, year );
            preparedStatement.setString( 3, month );

            resultSet = preparedStatement.executeQuery();

            if( resultSet.next() ) {
                int shiftCount = resultSet.getInt( "ShiftCount" );

                if( shiftCount == 0 ) {
                    alreadyExists = false;
                }
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return alreadyExists;
    }

    public boolean summaryDataExists( String email, String year, String month ) {
        logger.debugMethod( "summaryDataExists" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        boolean alreadyExists = true;
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( CHECK_SUMMARY_DATA_SQL );
            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, year );
            preparedStatement.setString( 3, month );

            resultSet = preparedStatement.executeQuery();

            if( resultSet.next() ) {
                int shiftCount = resultSet.getInt( "ShiftCount" );

                if( shiftCount == 0 ) {
                    alreadyExists = false;
                }
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return alreadyExists;
    }

    public boolean formDataUpdate( String applicationID, String year, String month, String shifts, boolean weekday, boolean weeknight, boolean weekendDay, boolean weekendNight, String hours, boolean locumWeek ) {
        logger.debugMethod( "formDataUpdate" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountAP = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( UPDATE_FORM_DATA_SQL );

            preparedStatement.setString( 1, shifts );
            preparedStatement.setBoolean( 2, weekday );
            preparedStatement.setBoolean( 3, weeknight );
            preparedStatement.setBoolean( 4, weekendDay );
            preparedStatement.setBoolean( 5, weekendNight );
            preparedStatement.setString( 6, hours );
            preparedStatement.setBoolean( 7, locumWeek );
            preparedStatement.setString( 8, applicationID );
            preparedStatement.setString( 9, year );
            preparedStatement.setString( 10, month );

            updateCountAP = preparedStatement.executeUpdate();

            if( updateCountAP != 1 ) {
                logger.error( "Update failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public boolean summaryDataUpdate( String email, String year, String month, String shifts, String hours ) {
        logger.debugMethod( "summaryDataUpdate" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int updateCountAP = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( UPDATE_SUMMARY_DATA_SQL );

            preparedStatement.setString( 1, shifts );
            preparedStatement.setString( 2, hours );
            preparedStatement.setString( 3, getEmail() );
            preparedStatement.setString( 4, year );
            preparedStatement.setString( 5, month );

            updateCountAP = preparedStatement.executeUpdate();

            if( updateCountAP != 1 ) {
                logger.error( "Update failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();
                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public boolean formDataAdd( String applicationID, String year, String month, String shifts, boolean weekday, boolean weeknight, boolean weekendDay, boolean weekendNight, String hours, boolean locumWeek ) {
        logger.debugMethod( "formDataAdd" );
        boolean returnedValue = false;

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountST = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_FORM_DATA_SQL );

            preparedStatement.setString( 1, applicationID );
            preparedStatement.setString( 2, year );
            preparedStatement.setString( 3, month );
            preparedStatement.setString( 4, shifts );
            preparedStatement.setBoolean( 5, weekday );
            preparedStatement.setBoolean( 6, weeknight );
            preparedStatement.setBoolean( 7, weekendDay );
            preparedStatement.setBoolean( 8, weekendNight );
            preparedStatement.setString( 9, hours );
            preparedStatement.setBoolean( 10, locumWeek );

            insertCountST = preparedStatement.executeUpdate();

            if( insertCountST != 1 ) {
                logger.error( "Add failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();

                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public boolean summaryDataAdd( String email, String year, String month, String shifts, String hours ) {
        logger.debugMethod( "summaryDataAdd" );
        boolean returnedValue = false;
        setEmail( email );

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Savepoint savePoint = null;

        try {
            int insertCountST = 0;

            connection = getConnection();

            connection.setAutoCommit( false );
            savePoint = connection.setSavepoint();

            preparedStatement = connection.prepareStatement( ADD_SUMMARY_DATA_SQL );

            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, year );
            preparedStatement.setString( 3, month );
            preparedStatement.setString( 4, shifts );
            preparedStatement.setString( 5, hours );

            insertCountST = preparedStatement.executeUpdate();

            if( insertCountST != 1 ) {
                logger.error( "Add failed" );
                connection.rollback( savePoint );
            }
            else {
                connection.commit();

                returnedValue = true;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );

            try {
                connection.rollback( savePoint );
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }
        finally {
            try {
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( Exception ex ) {
                logger.exception( ex );
            }
        }

        return returnedValue;
    }

    public String[] getTotalByDate( String email, String year, String month ) {
        logger.debugMethod( "getTotalByDate" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String[] hdrInfo = new String[2];
        setEmail( email );

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement( GET_TOTAL_BY_DATE_SQL );
            preparedStatement.setString( 1, getEmail() );
            preparedStatement.setString( 2, year );
            preparedStatement.setString( 3, month );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                int shifts = resultSet.getInt( "SumShifts" );
                int hours = resultSet.getInt( "SumHours" );

                hdrInfo[0] = EMPTY_STRING + shifts;
                hdrInfo[1] = EMPTY_STRING + hours;
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return hdrInfo;
    }

    public List<ReportShiftsByServTypeData> getShiftsByServiceType() {
        logger.debugMethod( "getShiftsByServiceType" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<ReportShiftsByServTypeData> shiftTrackingList = new ArrayList<>();

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement( GET_SHIFTS_BY_SERVICE_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String serviceTypeID = resultSet.getString( "ServiceTypeID" );
                String serviceTypeDesc = resultSet.getString( "Name_EN" );
                String numberOfShifts = resultSet.getString( "ShiftsTotal" );
                String numberOfHours = resultSet.getString( "HoursTotal" );

                shiftTrackingList.add( new ReportShiftsByServTypeData( serviceTypeID, serviceTypeDesc, numberOfShifts, numberOfHours ) );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return shiftTrackingList;
    }

    public List<ReportShiftsByInstitutionData> getShiftsByInstitution() {
        logger.debugMethod( "getShiftsByInstitution" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<ReportShiftsByInstitutionData> shiftTrackingList = new ArrayList<>();

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement( GET_SHIFTS_BY_INSTITUTION_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String institutionID = resultSet.getString( "InstitutionID" );
                String institutionName = resultSet.getString( "Name_EN" );
                String numberOfShifts = resultSet.getString( "ShiftsTotal" );
                String numberOfHours = resultSet.getString( "HoursTotal" );

                shiftTrackingList.add( new ReportShiftsByInstitutionData( institutionID, institutionName, numberOfShifts, numberOfHours ) );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return shiftTrackingList;
    }

    public int getWeekCount( String shiftType ) {
        logger.debugMethod( "getWeekCount" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        int weekCount = 0;

        try {
            connection = getConnection();

            if( shiftType.equals( SHIFT_TYPE_WEEK_DAY ) ) {
                preparedStatement = connection.prepareStatement( GET_WEEKDAY_SHIFTS_HOURS_SQL );
            }
            else {
                if( shiftType.equals( SHIFT_TYPE_WEEK_NIGHT ) ) {
                    preparedStatement = connection.prepareStatement( GET_WEEKNIGHT_SHIFTS_HOURS_SQL );
                }
                else {
                    if( shiftType.equals( SHIFT_TYPE_WEEKEND_DAY ) ) {
                        preparedStatement = connection.prepareStatement( GET_WEEKENDDAY_SHIFTS_HOURS_SQL );
                    }
                    else {
                        if( shiftType.equals( SHIFT_TYPE_WEEKEND_NIGHT ) ) {
                            preparedStatement = connection.prepareStatement( GET_WEEKENDNIGHT_SHIFTS_HOURS_SQL );
                        }
                        else {
                            if( shiftType.equals( SHIFT_TYPE_LOCUM_WEEK ) ) {
                                preparedStatement = connection.prepareStatement( GET_LOCUMWEEK_SHIFTS_HOURS_SQL );
                            }
                        }
                    }
                }
            }

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                weekCount = resultSet.getInt( "WeekCount" );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return weekCount;
    }

    //TODO
    public List<ReportShiftsByApplicationData> getShiftsByApplication() {
        logger.debugMethod( "getShiftsByApplication" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<ReportShiftsByApplicationData> shiftTrackingList = new ArrayList<>();

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement( GET_SHIFTS_BY_APPLICATION_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String applicationID = resultSet.getString( "ApplicationID" );
                String firstName = resultSet.getString( "FirstName" );
                String lastName = resultSet.getString( "LastName" );
                String names = firstName.trim() + " " + lastName.trim();
                String email = resultSet.getString( "Email" );
                String supervisorName = resultSet.getString( "SupervisorName" );
                String instituion = resultSet.getString( "Instituion" );
                String numberOfShifts = resultSet.getString( "ShiftsTotal" );
                String numberOfHours = resultSet.getString( "HoursTotal" );

                shiftTrackingList.add( new ReportShiftsByApplicationData( applicationID, names, email, instituion, supervisorName, numberOfShifts, numberOfHours ) );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return shiftTrackingList;
    }

    public List<ReportShiftsByDateData> getShiftsByDate() {
        logger.debugMethod( "getShiftsByDate" );
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<ReportShiftsByDateData> shiftTrackingList = new ArrayList<>();

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement( GET_SHIFTS_BY_DATE_SQL );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ) {
                String year = resultSet.getString( "YearValue" );
                String month = resultSet.getString( "Month" );
                String numberOfShifts = resultSet.getString( "ShiftsTotal" );
                String numberOfHours = resultSet.getString( "HoursTotal" );

                shiftTrackingList.add( new ReportShiftsByDateData( year, month, numberOfShifts, numberOfHours ) );
            }
        }
        catch( Exception ex ) {
            logger.exception( ex );
        }
        finally {
            try {
                if( resultSet != null ) {
                    resultSet.close();
                }
                if( preparedStatement != null ) {
                    preparedStatement.close();
                }
                if( connection != null ) {
                    connection.close();
                }
            }
            catch( SQLException sex ) {
                logger.exception( sex );
            }
        }

        return shiftTrackingList;
    }
    
    private String getEmail() {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            return EMPTY_STRING;
        }
        else {
            return email.trim().toLowerCase();
        }
    }

    private void setEmail(String email) {
        if ( email == null || email.trim().equals( EMPTY_STRING ) ) {
            this.email = EMPTY_STRING;
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }
}
