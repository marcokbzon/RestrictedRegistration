package com.moh.data.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ShiftTrackingDAO;

public class ReportShiftsByApplicationBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel shiftDM = new ListDataModel();

    public ReportShiftsByApplicationBean() {
        ShiftTrackingDAO shiftTrackingDAO = new ShiftTrackingDAO();

        List<ReportShiftsByApplicationData> reportList = shiftTrackingDAO.getShiftsByApplication();

        shiftDM.setWrappedData( reportList );
    }

    @SuppressWarnings( "rawtypes" )
    public DataModel getShiftDM() {
        return shiftDM;
    }

    public void setShiftDM( Object data ) {
        shiftDM.setWrappedData( data );
    }
}
