package com.moh.admin;

import java.util.Iterator;
import java.util.List;

import com.moh.common.AbstractBean;
import com.moh.data.bean.ReportAppsByInstData;
import com.moh.data.dao.ReportStatsDAO;

import com.moh.utils.Logger;

public class ReportAppsByInstRQHelper extends AbstractBean {

    private String pieChartParts;
    private String pieChartLabels;
    private String pieChartMaxValue;

    public ReportAppsByInstRQHelper() {
        logger = new Logger( this.getClass() );
        logger.debugMethod( "ReportAppsByInstRQHelper" );
    }

    public String open() {

        return newPrint();
    }

    public String print() {
        logger.debugMethod( "print" );

        String parts = EMPTY_STRING;
        String labels = EMPTY_STRING;

        ReportStatsDAO reportStatsDAO = new ReportStatsDAO();
        List<ReportAppsByInstData> reportList = reportStatsDAO.getAppsByInstitution();

        Iterator<ReportAppsByInstData> reportIter = reportList.iterator();

        while( reportIter.hasNext() ) {
            ReportAppsByInstData data = reportIter.next();

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

        logger.debugPage( "/jsp/reportAppsByInstitution.jsp" );
        return "reportAppsByInstitution";
    }

    public String newPrint() {
        logger.debugMethod( "newPrint" );

        String parts = EMPTY_STRING;
        String labels = EMPTY_STRING;
        int volEntity = 0;
        int maxEntity = 0;

        ReportStatsDAO reportStatsDAO = new ReportStatsDAO();
        List<ReportAppsByInstData> reportList = reportStatsDAO.getAppsByInstitution();

        Iterator<ReportAppsByInstData> reportIter = reportList.iterator();

        while( reportIter.hasNext() ) {
            ReportAppsByInstData data = reportIter.next();

            String entityName = data.getEntityName();
            
            if ( entityName.length() > 36 ) {
                entityName = entityName.substring( 0, 36 );
            }
            
            entityName = entityName.replaceAll( "&", "and" );
            entityName = entityName.replaceAll( "/", "-" );
            
            String entityCount = data.getEntityCount();

            parts = parts + entityCount + ",";

            volEntity = Integer.parseInt( entityCount );
            
            if ( volEntity > maxEntity ) {
                maxEntity = volEntity;
            }
            
            entityName = entityName.replaceAll( " ", "+" );
            
            labels = labels + entityName + ":" + "+" + entityCount + "|";
        }

        int partsSize = parts.length();
        parts = parts.substring( 0, partsSize - 1 );

        int labelsSize = labels.length();
        labels = labels.substring( 0, labelsSize - 1 );

        setPieChartParts( parts );
        setPieChartLabels( labels );
        setPieChartMaxValue( "" + maxEntity );

        logger.debugPage( "/jsp/reportAppsByInstitution.jsp" );
        return "reportAppsByInstitution";
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
