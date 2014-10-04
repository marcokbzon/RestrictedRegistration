package com.moh.admin;

import java.util.Iterator;
import java.util.List;

import com.moh.common.AbstractBean;
import com.moh.data.bean.ReportAppsByUnivData;
import com.moh.data.dao.ReportStatsDAO;

import com.moh.utils.Logger;

public class ReportAppsByUnivRQHelper extends AbstractBean {

    private String pieChartParts;
    private String pieChartLabels;
    private String pieChartMaxValue;

    public ReportAppsByUnivRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReportAppsByUnivRQHelper" );
    }

    public String open() {

        return newPrint();
    }

    public String print() {
        logger.debugMethod( "print" );

        String parts = EMPTY_STRING;
        String labels = EMPTY_STRING;

        ReportStatsDAO reportStatsDAO = new ReportStatsDAO();
        List<ReportAppsByUnivData> reportList = reportStatsDAO.getAppsByUniversity();

        Iterator<ReportAppsByUnivData> reportIter = reportList.iterator();

        while( reportIter.hasNext() ) {
            ReportAppsByUnivData data = reportIter.next();

            String entityName = data.getEntityName();
            String entityCount = data.getEntityCount();

            parts = parts + entityCount + ",";

            entityName = entityName.replaceAll( " ", "+" );
            labels = labels + entityName + ":" + "+" + entityCount + "|";
        }

        int partsSize = parts.length();
        parts = parts.substring( 0, partsSize - 1 );

        int labelsSize = labels.length();
        labels = labels.substring( 0, labelsSize - 1 );

        setPieChartParts( parts );
        setPieChartLabels( labels );

        logger.debugPage( "/jsp/reportAppsByUniversity.jsp" );
        return "reportAppsByUniversity";
    }
    
    public String newPrint() {
        logger.debugMethod( "newPrint" );

        String parts = EMPTY_STRING;
        String labels = EMPTY_STRING;
        int volEntity = 0;
        int maxEntity = 0;

        ReportStatsDAO reportStatsDAO = new ReportStatsDAO();
        List<ReportAppsByUnivData> reportList = reportStatsDAO.getAppsByUniversity();

        Iterator<ReportAppsByUnivData> reportIter = reportList.iterator();
        
        while( reportIter.hasNext() ) {
            ReportAppsByUnivData data = reportIter.next();

            String entityName = data.getEntityName();           // University names
            
            entityName = entityName.replaceAll( "&", "and" );
            
            String entityCount = data.getEntityCount();         // Number of Applications per Univeristy

            parts = parts + entityCount + ",";        // 40,15,30
            
            volEntity = Integer.parseInt( entityCount );
            
            if ( volEntity > maxEntity ) {
                maxEntity = volEntity;
            }

            entityName = entityName.replaceAll( " ", "+" );        // University of Toronto = Univeristy+of+Toronto
            labels = labels + entityName + ":" + "+" + entityCount + "|";    // Univeristy+of+Toronto:+40|University+of+Ottawa:+15|...
        }

        int partsSize = parts.length();
        parts = parts.substring( 0, partsSize - 1 );   // to remove the last comma 

        int labelsSize = labels.length();
        labels = labels.substring( 0, labelsSize - 1 );   // to remove the last pipe

        setPieChartParts( parts );
        setPieChartLabels( labels );
        setPieChartMaxValue( "" + maxEntity );

        logger.debugPage( "/jsp/reportAppsByUniversity.jsp" );
        return "reportAppsByUniversity";
    }

    public String getPieChartParts() {
        return pieChartParts;
    }

    public void setPieChartParts( String pieChartParts ) {
        this.pieChartParts = pieChartParts;
    }

    public String getPieChartLabels() {
        return pieChartLabels;
    }

    public void setPieChartLabels( String pieChartLabels ) {
        this.pieChartLabels = pieChartLabels;
    }
    
    public String getPieChartMaxValue() {
        return pieChartMaxValue;
    }

    public void setPieChartMaxValue( String pieChartMaxValue ) {
        this.pieChartMaxValue = pieChartMaxValue;
    }
}
