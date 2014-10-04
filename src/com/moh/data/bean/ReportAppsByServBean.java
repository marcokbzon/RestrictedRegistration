package com.moh.data.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ReportStatsDAO;

public class ReportAppsByServBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel reportDM = new ListDataModel();

    public ReportAppsByServBean() {
        ReportStatsDAO reportStatsDAO = new ReportStatsDAO();

        List<ReportAppsByServData> reportList = reportStatsDAO.getAppsByServType();

        reportDM.setWrappedData( reportList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getReportDM() {
        return reportDM;
    }

    public void setReportDM( Object data ) {
        reportDM.setWrappedData( data );
    }
}
