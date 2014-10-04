<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>Restricted Registration</title>
    <script type="text/javascript" src="/js/online.js"></script>
    <link href="/css/styles.css" rel="stylesheet" type="text/css" />
  </head>
  <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
  <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

  <body>
    <f:view>
      <h:form id="form_mainAdmin" >
        <div id="container">
          <div id="topstatus">
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
              <tr>
                <td width="90%" >&nbsp;</td>
                <td id="divider" width="40%" nowrap>Logged in as <strong><h:outputText value="#{sessionScope.email}" /></strong></td>
                <td width="40%"><h:commandLink id="link_logout" action="#{logout.execute}">Logout</h:commandLink></td>
              </tr>
            </table>
          </div>
          <h:messages id="app_messages" layout="table" errorClass="msgs_error" infoClass="msgs_info" warnClass="msgs_warning" />
          <div id="separ2">&nbsp;</div>
          <div id="glocontainer1">
            <div id="titlepage1"> System Administrator
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb">Home</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top" width="95%">
                    <table width="100%" cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td>
                          <div id="topcontent1">
                            <h1>Main Page</h1>
                            <br />
                            Available options for the System Administrator:<br />
                            <br />
                            <center>
                              <table cellpadding="3" cellspacing="3">
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h2>Reviewer's Profile Maintenance</h2>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>
                                          <ul>
                                            <li><h:commandLink id="link_addProfile" action="#{adminPersonalInfoRq.open}">Create a New Profile</h:commandLink></li>
                                            <li><h:commandLink id="link_addMultiRole" action="#{adminSearchRq.open}">Add Second Role to an Existing Profile</h:commandLink></li>
                                            <li><h:commandLink id="link_changePassword" action="changePassword" immediate="true">Change my Password</h:commandLink></li>
                                          </ul>
                                        </td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h2>Application-Form Maintenance</h2>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>
                                          <ul>
                                            <li><h:commandLink id="link_cpsoApproval" action="#{adminCPSOsearchRQ.open}">Approve Applications by CPSO</h:commandLink></li>
                                          </ul>
                                        </td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h2>Listings</h2>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>
                                          <ul>
                                            <li><h:commandLink id="link_appListByUniversity" action="#{adminAppListByUniversitySs.applicationList}">Applications by University: Filtered by Status</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_appListByUniversity2" action="#{adminAppListByUniversitySs2.applicationList}">Applications by University: Filtered by Reviewer Confirmation</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_userListByRole" action="#{adminUserListByRoleSs.userList}">Users Filtered by Role</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_cpsoAppListByUniversity" action="#{adminCPSOapprovedAppListSs.applicationList}">Applications Approved By CPSO: Filtered by University</h:commandLink></li>
                                          </ul>
                                        </td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h2>Reports</h2>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>
                                          <ul>
                                            <li><h:commandLink id="link_adminSiteStatusPrint" action="#{AdminStatusPrintRQ.open}">Applications, Users and other Entities</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_reportAppsByUniversity" action="#{reportAppsByUnivRQ.open}">Applications by University</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_reportAppsByProgram" action="#{reportAppsByProgRQ.open}">Applications by Program</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_reportAppsByInstitution" action="#{reportAppsByInstRQ.open}">Applications by Institution</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_reportAppsByServType" action="#{reportAppsByServRQ.open}">Applications by Service Type</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li>Shift Tracking
                                              <ul>
                                                <li><h:commandLink id="link_reportShiftsByServType" action="#{adminShiftsByServTypeReportRQ.shiftTrackingList}">Shifts By Service Type</h:commandLink></li>
                                              </ul>
                                              <ul>
                                                <li><h:commandLink id="link_reportShiftsByInstitution" action="#{adminShiftsByInstitutionReportRQ.shiftTrackingList}">Shifts By Institution</h:commandLink></li>
                                              </ul>
                                              <ul>
                                                <li><h:commandLink id="link_reportShiftsByShiftType" action="#{adminShiftsByShiftTypeReportRQ.shiftTrackingList}">Applications By Type of Shift (Week)</h:commandLink></li>
                                              </ul>
                                              <ul>
                                                <li><h:commandLink id="link_reportShiftsByApplication" action="#{adminShiftsByApplicationReportRQ.shiftTrackingList}">Shifts By Application ID</h:commandLink></li>
                                              </ul>
                                              <ul>
                                                <li><h:commandLink id="link_reportShiftsByDate" action="#{adminShiftsByDateReportRQ.shiftTrackingList}">Shifts By Date</h:commandLink></li>
                                              </ul>
                                            </li>
                                          </ul>
                                        </td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h2>Database Maintenance</h2>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>
                                          <%--
                                          <ul>
                                              <li><h:commandLink id="link_initTables" action="adminDataLoad">Initialize Tables</h:commandLink></li>
                                          </ul>
                                          --%>
                                          <ul>
                                            <li><h:commandLink id="link_university" action="#{adminUniversitySs.universityList}">Maintain University Information</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_program" action="#{adminProgramSs.programList}">Maintain Program Information</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_institution" action="#{adminInstitutionSs.institutionList}">Maintain Institution Information</h:commandLink></li>
                                          </ul>
                                          <ul>
                                            <li><h:commandLink id="link_serviceType" action="#{adminServiceTypeSs.serviceTypeList}">Maintain Service Type Information</h:commandLink></li>
                                          </ul>
                                          <%--
                                          <ul>
                                              <li><h:commandLink id="link_backup" action="#{adminDbUtils.backup}">Create Backup Files</h:commandLink></li>
                                          </ul>
                                          <ul>
                                              <li><h:commandLink id="link_restore" action="#{adminDbUtils.restore}">Restore Data from Backed-up Files</h:commandLink></li>
                                          </ul>
                                          --%>
                                        </td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                              </table>
                            </center>
                            <br />
                            <br />
                            <br />
                          </div>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </div>
            <jsp:include page="/jsp/footer.inc" />
          </div>
        </div>
      </h:form>
    </f:view>
  </body>
</html>
