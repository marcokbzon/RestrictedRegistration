package com.moh.common;

/**
 * <p>
 * Manifest constants for the Restricted-Registration application.
 * </p>
 */
public interface Constants {

    public static final String WEBMASTER_EMAIL = "info@restrictedregistrationontario.ca";
    public static final String DEFAULT_COUNTRY = "CAN";
    public static final String DEFAULT_PROVINCE = "ON";
    public static final String DEFAULT_ALL = "ALL";
    public static final String TO_BE_DETERMINED = "TBD";
    public static final String NOT_APPLICABLE = "N/A";
    public static final String EMPTY_STRING = "";
    public static final int COL_LENGTH_UNIVPROGID = 10;
    public static final int COL_LENGTH_UNIVERSITYID = 5;
    public static final int COL_LENGTH_PROGRAMID = 5;
    public static final int COL_LENGTH_LHINID = 2;
    public static final int COL_LENGTH_INSTITUTIONID = 4;
    public static final int COL_LENGTH_SERVICETYPEID = 3;
    public static final int COL_LENGTH_COUNTRYCODE = 3;
    public static final int COL_LENGTH_PROVSTATECODE = 2;
    public static final int COL_SORT_LENGTH = 3;
    // --- JDBC ----
    public static final String JDBC_JNDI_DATASOURCE = "RR_JNDI_JDBC_DATASOURCE";
    public static final String FIELD_SEPARATOR = "|";
    // --- EMAIL ----
    public static final String EMAIL_JNDI_SESSION = "RR_JNDI_MAILSESSION"; // Non-Secured
    //public static final String EMAIL_JNDI_SESSION = "RR_JNDI_MAILSESSION_SSL"; // Secured (SSL)
    //public static final String EMAIL_JNDI_SESSION = "GMAIL_MAILSESSION"; // Secured (SSL)
    // --- Role Types ---
    public static final String ROLE_ADMIN = "ADM";
    public static final String ROLE_ADMIN_COMMITTEE = "AMC";
    public static final String ROLE_SUPERVISOR = "ISV";
    public static final String ROLE_RESIDENT = "UMR";
    public static final String ROLE_DIRECTOR = "UPD";
    public static final String ROLE_DEAN = "UDN";
    public static final String ROLE_COMMITTEE = "UMC";
    // --- Session keys ---
    public static final String SESSION_EMAIL = "email";
    public static final String SESSION_FIRSTNAME = "firstname";
    public static final String SESSION_LASTNAME = "lastname";
    public static final String SESSION_ROLEID = "roleid";
    public static final String SESSION_ROLEDESC = "roledesc";
    public static final String SESSION_ROLES = "roles";
    public static final String SESSION_APPLICATIONID = "applicationid";
    public static final String SESSION_LIST_COUNT = "listcount";
    public static final String SESSION_LIST_COUNT_APPROVED = "listcountapproved";
    public static final String SESSION_LIST_COUNT_REJECTED = "listcountrejected";
    public static final String SESSION_SELECTED_ROLEID = "selectedroleid";
    public static final String SESSION_VISA_RESIDENT = "visaResident";
    public static final String SESSION_LHINID = "lhinid";
    public static final String SESSION_INSTITUTIONID = "institutionid";
    public static final String SESSION_UNIVERSITYID = "universityid";
    public static final String SESSION_UNIVERSITYID_PAST = "universityidpast";
    public static final String SESSION_TODAYSDATE = "todaysdate";
    public static final String SESSION_CODEID = "codeid";
    //public static final String SESSION_DATAMAP = "dataMap";     // It was replaced by 2 separate constants to avoid casting issues
    public static final String SESSION_DATAMAP_CPSO = "dataMapCpso";
    public static final String SESSION_DATAMAP_REVIEWER = "dataMapReviewer";
    
    public static final String SESSION_YEAROFBIRTH_MIN = "YEAR_OF_BIRTH_MIN";
    public static final String SESSION_YEAROFBIRTH_MAX = "YEAR_OF_BIRTH_MAX";
    public static final String SESSION_YEAROFEXAM_MIN = "YEAR_OF_EXAM_MIN";
    public static final String SESSION_YEAROFEXAM_MAX = "YEAR_OF_EXAM_MAX";
    public static final String SESSION_FILE_SEPARATOR = "RESOURCE_SEPARATOR";
    public static final String SESSION_RRDOCS_PATH = "RRDOCS_FOLDER_PATH";
    public static final String SESSION_TBLXTR_PATH = "TBLXTR_FOLDER_PATH";
    
    public static final String AGREEMENT_PTC = "PTC";
    public static final String AGREEMENT_NCC = "NCC";
    public static final String AGREEMENT_RAS = "RAS";
    public static final String AGREEMENT_RRI = "RRI";
    public static final String AGREEMENT_PWS = "PWS";
    public static final String AGREEMENT_FTC = "FTC";
    public static final String AGREEMENT_ATI = "ATI";
    public static final String AGREEMENT_RTW = "RTW";
    public static final String AGREEMENT_PWT = "PWT";
    public static final String AGREEMENT_RUC = "RUC";
    public static final String AGREEMENT_TER = "TER";
    public static final String AGREEMENT_NLA = "NLA";
    public static final String AGREEMENT_PID = "PID";
    public static final String AGREEMENT_IAP = "IAP";
    public static final String STATUS_SAVED = "SAV";  // by med residents
    public static final String STATUS_SUBMITTED = "SBM";  // by med residents
    public static final String STATUS_NOTIFIED = "NTF";  // by any other
    public static final String STATUS_APPROVED = "APR";  // by any other
    public static final String STATUS_REJECTED = "RJT";  // by any other
    public static final String STATUS_CANCELLED = "CAN";  // by administrator
    public static final String PHASE_NEW = "NEW";  // by med residents
    public static final String PHASE_INPROGRESS = "PRG";  // by any other
    public static final String PHASE_COMPLETED = "CMP";  // by committee if approved
    // Independent tables
    public static final String DATABASE_SCHEMA = "RRDB.";  // not use for now
    
    public static final String TABLE_COUNTRY = "Country";
    public static final String TABLE_PROVINCESTATE = "ProvinceState";
    public static final String TABLE_UNIVERSITY = "University";
    public static final String TABLE_PROGRAM = "Program";
    public static final String TABLE_ROLE = "Role";
    public static final String TABLE_CODE = "Code";
    public static final String TABLE_INSTITUTION = "Institution";
    public static final String TABLE_SERVICETYPE = "ServiceType";
    public static final String TABLE_LHIN = "LHIN";
    public static final String TABLE_CLASSIFICATION = "Classification";
    //public static final String TABLE_LOCATION = "Location";
    public static final String TABLE_AGREEMENT = "Agreement";
    // Mixed tables
    public static final String TABLE_INSTLHIN = "InstLHIN";
    public static final String TABLE_INSTCLASSIFICATION = "InstClassification";
    public static final String TABLE_INSTSERVICETYPE = "InstServiceType";
    public static final String TABLE_USERINFO = "UserInfo";
    public static final String TABLE_USERROLE = "UserRole";
    public static final String MONTH_JAN_NN = "01";
    public static final String MONTH_FEB_NN = "02";
    public static final String MONTH_MAR_NN = "03";
    public static final String MONTH_APR_NN = "04";
    public static final String MONTH_MAY_NN = "05";
    public static final String MONTH_JUN_NN = "06";
    public static final String MONTH_JUL_NN = "07";
    public static final String MONTH_AUG_NN = "08";
    public static final String MONTH_SEP_NN = "09";
    public static final String MONTH_OCT_NN = "10";
    public static final String MONTH_NOV_NN = "11";
    public static final String MONTH_DEC_NN = "12";
    public static final String MONTH_JAN_FULL = "January";
    public static final String MONTH_FEB_FULL = "February";
    public static final String MONTH_MAR_FULL = "March";
    public static final String MONTH_APR_FULL = "April";
    public static final String MONTH_MAY_FULL = "May";
    public static final String MONTH_JUN_FULL = "June";
    public static final String MONTH_JUL_FULL = "July";
    public static final String MONTH_AUG_FULL = "August";
    public static final String MONTH_SEP_FULL = "September";
    public static final String MONTH_OCT_FULL = "October";
    public static final String MONTH_NOV_FULL = "November";
    public static final String MONTH_DEC_FULL = "December";
    public static final String SHIFT_TRACK_YEAR = "ShiftTrackYear";
    public static final String SHIFT_TRACK_MONTH = "ShiftTrackMonth";
    public static final String SHIFT_TYPE_WEEK_DAY = "Weekday";
    public static final String SHIFT_TYPE_WEEK_NIGHT = "Weeknight";
    public static final String SHIFT_TYPE_WEEKEND_DAY = "WeekendDay";
    public static final String SHIFT_TYPE_WEEKEND_NIGHT = "WeekendNight";
    public static final String SHIFT_TYPE_LOCUM_WEEK = "LocumWeek";
}
