package com.moh.shift.track;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import com.moh.common.AbstractBean;
import com.moh.data.bean.ShiftCalendarData;
import com.moh.data.dao.ShiftTrackingDAO;
import com.moh.utils.Logger;

public class ShiftTrackingCalendarSSHelper extends AbstractBean {

    private boolean render2008;
    private boolean render2009;
    private boolean render2010;
    private boolean render2011;
    private boolean render2012;
    private boolean render2013;
    private boolean render2014;
    
    private String janDataShifts;
    private String janDataHours;
    private String janMessage;
    private boolean janRenderAction;
    private String febDataShifts;
    private String febDataHours;
    private String febMessage;
    private boolean febRenderAction;
    private String marDataShifts;
    private String marDataHours;
    private String marMessage;
    private boolean marRenderAction;
    private String aprDataShifts;
    private String aprDataHours;
    private String aprMessage;
    private boolean aprRenderAction;
    private String mayDataShifts;
    private String mayDataHours;
    private String mayMessage;
    private boolean mayRenderAction;
    private String junDataShifts;
    private String junDataHours;
    private String junMessage;
    private boolean junRenderAction;
    private String julDataShifts;
    private String julDataHours;
    private String julMessage;
    private boolean julRenderAction;
    private String augDataShifts;
    private String augDataHours;
    private String augMessage;
    private boolean augRenderAction;
    private String sepDataShifts;
    private String sepDataHours;
    private String sepMessage;
    private boolean sepRenderAction;
    private String octDataShifts;
    private String octDataHours;
    private String octMessage;
    private boolean octRenderAction;
    private String novDataShifts;
    private String novDataHours;
    private String novMessage;
    private boolean novRenderAction;
    private String decDataShifts;
    private String decDataHours;
    private String decMessage;
    private boolean decRenderAction;
    private int selectedYear;
    private final String stringShifts = " shift(s)";
    private final String stringHours = " hour(s)";
    private String currentYear;
    private String currentMonth;
    private String email;

    public ShiftTrackingCalendarSSHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ShiftTrackingCalendarSSHelper" );
    }

    public String open() {
        logger.debugMethod( "open" );

        setRender2008( false );
        setRender2009( false );
        setRender2010( false );
        setRender2011( false );
        setRender2012( false );
        setRender2013( false );
        setRender2014( false );

        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM" );

        String currentYearMonth = dateFormat.format( new Date() );

        currentYear = currentYearMonth.substring( 0, 4 );
        currentMonth = currentYearMonth.substring( 5, 7 );
        selectedYear = Integer.parseInt( currentYear );

        if( Integer.parseInt( currentYear ) >= 2008 ) {
            setRender2008( true );
        }
        if( Integer.parseInt( currentYear ) >= 2009 ) {
            setRender2009( true );
        }
        if( Integer.parseInt( currentYear ) >= 2010 ) {
            setRender2010( true );
        }
        if( Integer.parseInt( currentYear ) >= 2011 ) {
            setRender2011( true );
        }
        if( Integer.parseInt( currentYear ) >= 2012 ) {
            setRender2012( true );
        }
        if( Integer.parseInt( currentYear ) >= 2013 ) {
            setRender2013( true );
        }
        if( Integer.parseInt( currentYear ) >= 2014 ) {
            setRender2014( true );
        }

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        ShiftTrackingDAO shiftDAO = new ShiftTrackingDAO();
        HashSet<ShiftCalendarData> calendarSet = shiftDAO.getCalendarInfo( getEmail(), currentYear );
        Iterator<ShiftCalendarData> calendarIterator = calendarSet.iterator();
        ShiftCalendarData shiftData = new ShiftCalendarData();

        resetValues();

        initValues( calendarIterator, shiftData );

        logger.debugPage( "/jsp/shiftTracking01.jsp" );
        return "shiftTracking01";
    }

    private void resetValues() {
        setJanDataShifts( "0" + stringShifts );
        setJanDataHours( "0" + stringHours );
        setJanMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setJanRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 1 ) {
                setJanRenderAction( true );
            }
            else {
                setJanRenderAction( false );
            }
        }

        setFebDataShifts( "0" + stringShifts );
        setFebDataHours( "0" + stringHours );
        setFebMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setFebRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 2 ) {
                setFebRenderAction( true );
            }
            else {
                setFebRenderAction( false );
            }
        }

        setMarDataShifts( "0" + stringShifts );
        setMarDataHours( "0" + stringHours );
        setMarMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setMarRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 3 ) {
                setMarRenderAction( true );
            }
            else {
                setMarRenderAction( false );
            }
        }

        setAprDataShifts( "0" + stringShifts );
        setAprDataHours( "0" + stringHours );
        setAprMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setAprRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 4 ) {
                setAprRenderAction( true );
            }
            else {
                setAprRenderAction( false );
            }
        }

        setMayDataShifts( "0" + stringShifts );
        setMayDataHours( "0" + stringHours );
        setMayMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setMayRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 5 ) {
                setMayRenderAction( true );
            }
            else {
                setMayRenderAction( false );
            }
        }

        setJunDataShifts( "0" + stringShifts );
        setJunDataHours( "0" + stringHours );
        setJunMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setJunRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 6 ) {
                setJunRenderAction( true );
            }
            else {
                setJunRenderAction( false );
            }
        }

        setJulDataShifts( "0" + stringShifts );
        setJulDataHours( "0" + stringHours );
        setJulMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setJulRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 7 ) {
                setJulRenderAction( true );
            }
            else {
                setJulRenderAction( false );
            }
        }

        setAugDataShifts( "0" + stringShifts );
        setAugDataHours( "0" + stringHours );
        setAugMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setAugRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 8 ) {
                setAugRenderAction( true );
            }
            else {
                setAugRenderAction( false );
            }
        }

        setSepDataShifts( "0" + stringShifts );
        setSepDataHours( "0" + stringHours );
        setSepMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setSepRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 9 ) {
                setSepRenderAction( true );
            }
            else {
                setSepRenderAction( false );
            }
        }

        setOctDataShifts( "0" + stringShifts );
        setOctDataHours( "0" + stringHours );
        setOctMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setOctRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 10 ) {
                setOctRenderAction( true );
            }
            else {
                setOctRenderAction( false );
            }
        }

        setNovDataShifts( "0" + stringShifts );
        setNovDataHours( "0" + stringHours );
        setNovMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setNovRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) >= selectedYear & Integer.parseInt( currentMonth ) >= 11 ) {
                setNovRenderAction( true );
            }
            else {
                setNovRenderAction( false );
            }
        }

        setDecDataShifts( "0" + stringShifts );
        setDecDataHours( "0" + stringHours );
        setDecMessage( NOT_APPLICABLE );
        if( Integer.parseInt( currentYear ) > selectedYear ) {
            setDecRenderAction( true );
        }
        else {
            if( Integer.parseInt( currentYear ) == selectedYear & Integer.parseInt( currentMonth ) >= 12 ) {
                setDecRenderAction( true );
            }
            else {
                setDecRenderAction( false );
            }
        }
    }

    private void initValues( Iterator<ShiftCalendarData> calendarIterator, ShiftCalendarData shiftData ) {
        while( calendarIterator.hasNext() ) {
            shiftData = calendarIterator.next();

            if( shiftData.getMonth().equals( MONTH_JAN_NN ) ) {
                setJanDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setJanDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setJanRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_FEB_NN ) ) {
                setFebDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setFebDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setFebRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_MAR_NN ) ) {
                setMarDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setMarDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setMarRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_APR_NN ) ) {
                setAprDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setAprDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setAprRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_MAY_NN ) ) {
                setMayDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setMayDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setMayRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_JUN_NN ) ) {
                setJunDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setJunDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setJunRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_JUL_NN ) ) {
                setJulDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setJulDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setJulRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_AUG_NN ) ) {
                setAugDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setAugDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setAugRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_SEP_NN ) ) {
                setSepDataShifts( EMPTY_STRING + shiftData.getShifts() );
                setSepDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setSepRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_OCT_NN ) ) {
                setOctDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setOctDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setOctRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_NOV_NN ) ) {
                setNovDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setNovDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setNovRenderAction( true );
            }

            if( shiftData.getMonth().equals( MONTH_DEC_NN ) ) {
                setDecDataShifts( EMPTY_STRING + shiftData.getShifts() + stringShifts );
                setDecDataHours( EMPTY_STRING + shiftData.getHours() + stringHours );
                setDecRenderAction( true );
            }
        }
    }

    public String view2008() {
        logger.debugMethod( "view2008" );

        viewYear( "2008" );

        logger.debugPage( "/jsp/shiftTracking01.jsp" );
        return "shiftTracking01";
    }

    public String view2009() {
        logger.debugMethod( "view2009" );

        viewYear( "2009" );

        logger.debugPage( "/jsp/shiftTracking01.jsp" );
        return "shiftTracking01";
    }

    public String view2010() {
        logger.debugMethod( "view2010" );

        viewYear( "2010" );

        logger.debugPage( "/jsp/shiftTracking01.jsp" );
        return "shiftTracking01";
    }

    public String view2011() {
        logger.debugMethod( "view2011" );

        viewYear( "2011" );

        logger.debugPage( "/jsp/shiftTracking01.jsp" );
        return "shiftTracking01";
    }

    public String view2012() {
        logger.debugMethod( "view2012" );

        viewYear( "2012" );

        logger.debugPage( "/jsp/shiftTracking01.jsp" );
        return "shiftTracking01";
    }

    public String view2013() {
        logger.debugMethod( "view2013" );

        viewYear( "2013" );

        logger.debugPage( "/jsp/shiftTracking01.jsp" );
        return "shiftTracking01";
    }

    public String view2014() {
        logger.debugMethod( "view2014" );

        viewYear( "2014" );

        logger.debugPage( "/jsp/shiftTracking01.jsp" );
        return "shiftTracking01";
    }
    
    private void viewYear( String vYear ) {
        selectedYear = Integer.parseInt( vYear );

        setEmail( ( String ) getFromSession( SESSION_EMAIL ) );

        ShiftTrackingDAO shiftDAO = new ShiftTrackingDAO();
        HashSet<ShiftCalendarData> calendarSet = shiftDAO.getCalendarInfo( getEmail(), vYear );
        Iterator<ShiftCalendarData> calendarIterator = calendarSet.iterator();
        ShiftCalendarData shiftData = new ShiftCalendarData();

        resetValues();

        initValues( calendarIterator, shiftData );
    }

    public String janAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_JAN_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String febAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_FEB_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String marAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_MAR_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String aprAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_APR_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String mayAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_MAY_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String junAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_JUN_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String julAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_JUL_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String augAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_AUG_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String sepAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_SEP_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String octAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_OCT_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String novAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_NOV_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public String decAction() {
        addToSession( SHIFT_TRACK_YEAR, EMPTY_STRING + selectedYear );
        addToSession( SHIFT_TRACK_MONTH, MONTH_DEC_NN );

        logger.debugPage( "/jsp/shiftTracking02.jsp" );
        return "shiftTracking02";
    }

    public boolean getRender2008() {
        return render2008;
    }

    public void setRender2008( boolean render2008 ) {
        this.render2008 = render2008;
    }

    public boolean getRender2009() {
        return render2009;
    }

    public void setRender2009( boolean render2009 ) {
        this.render2009 = render2009;
    }

    public boolean getRender2010() {
        return render2010;
    }

    public void setRender2010( boolean render2010 ) {
        this.render2010 = render2010;
    }

    public boolean getRender2011() {
        return render2011;
    }

    public void setRender2011( boolean render2011 ) {
        this.render2011 = render2011;
    }

    public boolean getRender2012() {
        return render2012;
    }

    public void setRender2012( boolean render2012 ) {
        this.render2012 = render2012;
    }

    public boolean getRender2013() {
        return render2013;
    }

    public void setRender2013( boolean render2013 ) {
        this.render2013 = render2013;
    }

    public boolean getRender2014() {
        return render2014;
    }

    public void setRender2014( boolean render2014 ) {
        this.render2014 = render2014;
    }
    
    public boolean getJanRenderAction() {
        return janRenderAction;
    }

    public void setJanRenderAction( boolean janRenderAction ) {
        this.janRenderAction = janRenderAction;
    }

    public String getJanDataShifts() {
        return janDataShifts;
    }

    public void setJanDataShifts( String janDataShifts ) {
        this.janDataShifts = janDataShifts;
    }

    public String getJanDataHours() {
        return janDataHours;
    }

    public void setJanDataHours( String janDataHours ) {
        this.janDataHours = janDataHours;
    }

    public String getJanMessage() {
        return janMessage;
    }

    public void setJanMessage( String janMessage ) {
        this.janMessage = janMessage;
    }

    public String getFebDataShifts() {
        return febDataShifts;
    }

    public void setFebDataShifts( String febDataShifts ) {
        this.febDataShifts = febDataShifts;
    }

    public String getFebDataHours() {
        return febDataHours;
    }

    public void setFebDataHours( String febDataHours ) {
        this.febDataHours = febDataHours;
    }

    public String getFebMessage() {
        return febMessage;
    }

    public void setFebMessage( String febMessage ) {
        this.febMessage = febMessage;
    }

    public boolean getFebRenderAction() {
        return febRenderAction;
    }

    public void setFebRenderAction( boolean febRenderAction ) {
        this.febRenderAction = febRenderAction;
    }

    public String getMarDataShifts() {
        return marDataShifts;
    }

    public void setMarDataShifts( String marDataShifts ) {
        this.marDataShifts = marDataShifts;
    }

    public String getMarDataHours() {
        return marDataHours;
    }

    public void setMarDataHours( String marDataHours ) {
        this.marDataHours = marDataHours;
    }

    public String getMarMessage() {
        return marMessage;
    }

    public void setMarMessage( String marMessage ) {
        this.marMessage = marMessage;
    }

    public boolean getMarRenderAction() {
        return marRenderAction;
    }

    public void setMarRenderAction( boolean marRenderAction ) {
        this.marRenderAction = marRenderAction;
    }

    public String getAprDataShifts() {
        return aprDataShifts;
    }

    public void setAprDataShifts( String aprDataShifts ) {
        this.aprDataShifts = aprDataShifts;
    }

    public String getAprDataHours() {
        return aprDataHours;
    }

    public void setAprDataHours( String aprDataHours ) {
        this.aprDataHours = aprDataHours;
    }

    public String getAprMessage() {
        return aprMessage;
    }

    public void setAprMessage( String aprMessage ) {
        this.aprMessage = aprMessage;
    }

    public boolean getAprRenderAction() {
        return aprRenderAction;
    }

    public void setAprRenderAction( boolean aprRenderAction ) {
        this.aprRenderAction = aprRenderAction;
    }

    public String getMayDataShifts() {
        return mayDataShifts;
    }

    public void setMayDataShifts( String mayDataShifts ) {
        this.mayDataShifts = mayDataShifts;
    }

    public String getMayDataHours() {
        return mayDataHours;
    }

    public void setMayDataHours( String mayDataHours ) {
        this.mayDataHours = mayDataHours;
    }

    public String getMayMessage() {
        return mayMessage;
    }

    public void setMayMessage( String mayMessage ) {
        this.mayMessage = mayMessage;
    }

    public boolean getMayRenderAction() {
        return mayRenderAction;
    }

    public void setMayRenderAction( boolean mayRenderAction ) {
        this.mayRenderAction = mayRenderAction;
    }

    public String getJunDataShifts() {
        return junDataShifts;
    }

    public void setJunDataShifts( String junDataShifts ) {
        this.junDataShifts = junDataShifts;
    }

    public String getJunDataHours() {
        return junDataHours;
    }

    public void setJunDataHours( String junDataHours ) {
        this.junDataHours = junDataHours;
    }

    public String getJunMessage() {
        return junMessage;
    }

    public void setJunMessage( String junMessage ) {
        this.junMessage = junMessage;
    }

    public boolean getJunRenderAction() {
        return junRenderAction;
    }

    public void setJunRenderAction( boolean junRenderAction ) {
        this.junRenderAction = junRenderAction;
    }

    public String getJulDataShifts() {
        return julDataShifts;
    }

    public void setJulDataShifts( String julDataShifts ) {
        this.julDataShifts = julDataShifts;
    }

    public String getJulDataHours() {
        return julDataHours;
    }

    public void setJulDataHours( String julDataHours ) {
        this.julDataHours = julDataHours;
    }

    public String getJulMessage() {
        return julMessage;
    }

    public void setJulMessage( String julMessage ) {
        this.julMessage = julMessage;
    }

    public boolean getJulRenderAction() {
        return julRenderAction;
    }

    public void setJulRenderAction( boolean julRenderAction ) {
        this.julRenderAction = julRenderAction;
    }

    public String getAugDataShifts() {
        return augDataShifts;
    }

    public void setAugDataShifts( String augDataShifts ) {
        this.augDataShifts = augDataShifts;
    }

    public String getAugDataHours() {
        return augDataHours;
    }

    public void setAugDataHours( String augDataHours ) {
        this.augDataHours = augDataHours;
    }

    public String getAugMessage() {
        return augMessage;
    }

    public void setAugMessage( String augMessage ) {
        this.augMessage = augMessage;
    }

    public boolean getAugRenderAction() {
        return augRenderAction;
    }

    public void setAugRenderAction( boolean augRenderAction ) {
        this.augRenderAction = augRenderAction;
    }

    public String getSepDataShifts() {
        return sepDataShifts;
    }

    public void setSepDataShifts( String sepDataShifts ) {
        this.sepDataShifts = sepDataShifts;
    }

    public String getSepDataHours() {
        return sepDataHours;
    }

    public void setSepDataHours( String sepDataHours ) {
        this.sepDataHours = sepDataHours;
    }

    public String getSepMessage() {
        return sepMessage;
    }

    public void setSepMessage( String sepMessage ) {
        this.sepMessage = sepMessage;
    }

    public boolean getSepRenderAction() {
        return sepRenderAction;
    }

    public void setSepRenderAction( boolean sepRenderAction ) {
        this.sepRenderAction = sepRenderAction;
    }

    public String getOctDataShifts() {
        return octDataShifts;
    }

    public void setOctDataShifts( String octDataShifts ) {
        this.octDataShifts = octDataShifts;
    }

    public String getOctDataHours() {
        return octDataHours;
    }

    public void setOctDataHours( String octDataHours ) {
        this.octDataHours = octDataHours;
    }

    public String getOctMessage() {
        return octMessage;
    }

    public void setOctMessage( String octMessage ) {
        this.octMessage = octMessage;
    }

    public boolean getOctRenderAction() {
        return octRenderAction;
    }

    public void setOctRenderAction( boolean octRenderAction ) {
        this.octRenderAction = octRenderAction;
    }

    public String getNovDataShifts() {
        return novDataShifts;
    }

    public void setNovDataShifts( String novDataShifts ) {
        this.novDataShifts = novDataShifts;
    }

    public String getNovDataHours() {
        return novDataHours;
    }

    public void setNovDataHours( String novDataHours ) {
        this.novDataHours = novDataHours;
    }

    public String getNovMessage() {
        return novMessage;
    }

    public void setNovMessage( String novMessage ) {
        this.novMessage = novMessage;
    }

    public boolean getNovRenderAction() {
        return novRenderAction;
    }

    public void setNovRenderAction( boolean novRenderAction ) {
        this.novRenderAction = novRenderAction;
    }

    public String getDecDataShifts() {
        return decDataShifts;
    }

    public void setDecDataShifts( String decDataShifts ) {
        this.decDataShifts = decDataShifts;
    }

    public String getDecDataHours() {
        return decDataHours;
    }

    public void setDecDataHours( String decDataHours ) {
        this.decDataHours = decDataHours;
    }

    public String getDecMessage() {
        return decMessage;
    }

    public void setDecMessage( String decMessage ) {
        this.decMessage = decMessage;
    }

    public boolean getDecRenderAction() {
        return decRenderAction;
    }

    public void setDecRenderAction( boolean decRenderAction ) {
        this.decRenderAction = decRenderAction;
    }

    public int getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear( int selectedYear ) {
        this.selectedYear = selectedYear;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear( String currentYear ) {
        this.currentYear = currentYear;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth( String currentMonth ) {
        this.currentMonth = currentMonth;
    }

    public String getStringShifts() {
        return stringShifts;
    }

    public String getStringHours() {
        return stringHours;
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
