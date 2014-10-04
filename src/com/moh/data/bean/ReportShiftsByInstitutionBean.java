package com.moh.data.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.moh.common.AbstractBean;
import com.moh.data.dao.ShiftTrackingDAO;

public class ReportShiftsByInstitutionBean extends AbstractBean {

	@SuppressWarnings( "rawtypes" )
    DataModel shiftDM = new ListDataModel();

    public ReportShiftsByInstitutionBean() {
        ShiftTrackingDAO shiftTrackingDAO = new ShiftTrackingDAO();

        List<ReportShiftsByInstitutionData> reportList = shiftTrackingDAO.getShiftsByInstitution();

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
