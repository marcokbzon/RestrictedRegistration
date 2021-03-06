package com.moh.data.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ShiftTrackingDAO;

public class ReportShiftsByServTypeBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel shiftDM = new ListDataModel();

    public ReportShiftsByServTypeBean() {
        ShiftTrackingDAO shiftTrackingDAO = new ShiftTrackingDAO();

        List<ReportShiftsByServTypeData> reportList = shiftTrackingDAO.getShiftsByServiceType();

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
