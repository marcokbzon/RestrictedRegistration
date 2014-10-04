package com.moh.data.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ReportStatsDAO;

public class ReportAppsByProgBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel reportDM = new ListDataModel();

    public ReportAppsByProgBean() {
        ReportStatsDAO reportStatsDAO = new ReportStatsDAO();

        List<ReportAppsByProgData> reportList = reportStatsDAO.getAppsByProgram();

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
