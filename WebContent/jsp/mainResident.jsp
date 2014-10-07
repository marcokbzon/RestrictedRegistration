<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
      <h:form id="form_mainResident" >
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
            <div id="titlepage1">
              Resident
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

                            <!-- TEMPORARY NOTIFICATION:START -->
                            <br />
                            <table class="table_notification">
                              <tr>
                                <td>
                                  <!--
                                  <strong><p><span class="bigText1">Notice to Residents applying for a Certificate of Restricted Registration</span></p></strong>

                                  <p align="left">Applications for the current academic year (up until June 30, 2009) are no longer being accepted.</p>

                                  <p align="left">If you are planning to submit an application to coincide with the beginning of the new academic year (July 1, 2009 &#8211; June 30, 2010) you may apply at any time. Your completed application will be sent to the CPSO at the earliest opportunity. If you are reviewed at the April, May or June CPSO meetings, your certificate will be valid and applicable for the next academic year.</p>

                                  <strong><p>SOME DATES TO CONSIDER:</p></strong>

                                  <p align="left">
                                          <strong>April 8th</strong> &#8211; recommended last day to create a new application on the Restricted Registration portal if you want your application to be reviewed before July 1st, 2009.<br/>
                                          <strong>April 27th</strong> &#8211; Restricted Registration Office deadline to accept applications that have passed the academic review.<br/>
                                          <strong>April 29th</strong> &#8211; Academically-reviewed applications sent to the CPSO<br/>
                                          <strong>June 24th </strong> &#8211; CPSO Registration Committee meeting.<br/>
                                  </p>

                                  <ul type="circle">
                                          <li>If you are planning to <strong>RENEW</strong> an existing certificate, please contact the RR Office.</li>
                                          <li>Please be reminded that you must meet all your Program's stated guidelines, and obtain permission to apply from your potential Supervising Physician BEFORE completing a new application online.</li>
                                          <li>Residents can apply at any time of the year for an RR Certificate, but only applications that meet the above deadlines have the possibility of being issued at the commencement of the next academic year (July).</li>
                                  </ul>
                                  <br />
                                  -->
                                  <p align="left">For questions and queries about your Restricted Registration application, please contact the Restricted Registration Office, at 416-597-3650.</p>
                                </td>
                              </tr>
                            </table>
                            <!-- TEMPORARY NOTIFICATION:END -->

                            <br />
                            Available options for Residents:<br />
                            <br />
                            <center>
                              <table cellpadding="3" cellspacing="3">
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td><h:commandLink id="link_editProfile" action="#{personalInfoSs.updateProfile}">Edit Profile</h:commandLink></td>
                                      </tr>
                                      <tr>
                                        <td>Update your personal and educational information, and maintain your rotation history</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td><h:commandLink id="link_appFormPrint" action="#{profilePrintRq.print}">Printable Profile</h:commandLink></td>
                                      </tr>
                                      <tr>
                                        <td>Print your personal profile ( including education and rotation information )</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>

                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h:commandLink id="link_createApplication" action="#{personalInfoRq.view}">Create an Application</h:commandLink>
                                          <%-- A HREF="#" onclick="javascript:alert('MESSAGE GOES HERE...');">Create an Application</A --%>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>Creates a new application form</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>

                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h:commandLink id="link_listApplications" action="#{appFormList.view}">List Applications</h:commandLink>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>Display a list of all my applications and their status</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h:commandLink id="link_shiftTrack" action="#{shiftTrackCalendarSS.open}">Shift Tracking</h:commandLink>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>Keep track of your Shifts and Hours ( only for Applications already approved by the CPSO )</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h:commandLink id="link_changePassword" action="changePassword" immediate="true">Change my Password</h:commandLink>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>You will need to enter your existing password</td>
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
