package com.moh.data.bean;

public class ReportShiftsByApplicationData {

    private String applicationID;
    private String residentNames;
    private String residentEmail;
    private String institutionName;
    private String supervisorName;
    private String numberOfShifts;
    private String numberOfHours;

    public ReportShiftsByApplicationData( String applicationID, String residentNames, String residentEmail, String institutionName, String supervisorName, String numberOfShifts, String numberOfHours ) {
        this.applicationID = applicationID;
        this.residentNames = residentNames;
        this.residentEmail = residentEmail;
        this.institutionName = institutionName;
        this.supervisorName = supervisorName;
        this.numberOfShifts = numberOfShifts;
        this.numberOfHours = numberOfHours;
    }

    public String getNumberOfShifts() {
        return numberOfShifts;
    }

    public void setNumberOfShifts( String numberOfShifts ) {
        this.numberOfShifts = numberOfShifts;
    }

    public String getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours( String numberOfHours ) {
        this.numberOfHours = numberOfHours;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID( String applicationID ) {
        this.applicationID = applicationID;
    }

	public String getResidentNames() {
		return residentNames;
	}

	public void setResidentNames( String residentNames ) {
		this.residentNames = residentNames;
	}

	public String getResidentEmail() {
		return residentEmail;
	}

	public void setResidentEmail( String residentEmail ) {
		this.residentEmail = residentEmail;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName( String institutionName ) {
		this.institutionName = institutionName;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName( String supervisorName ) {
		this.supervisorName = supervisorName;
	}
}
