<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>Restricted Registration</title>
    <script type="text/javascript" src="/js/online.js"></script>
    <link href="/css/styles.css" rel="stylesheet" type="text/css" />
  </head>
  <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
  <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

  <body onLoad="setFocusOn( 'form_shiftTracking03:input_numberOfShifts' )">
    <f:view>
      <h:form id="form_shiftTracking03" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_shiftTracking03:button_back' ); return false; }; return true;">
        <div id="container">
          <div id="topstatus">
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
              <tr>
                <td width="90%" >&nbsp;</td>
                <td id="divider" width="40%" nowrap>Logged in as <strong><h:outputText value="#{sessionScope.email}" /></strong></td>
                <td width="40%"><h:commandLink id="link_logout" immediate="true" action="#{logout.execute}">Logout</h:commandLink></td>
              </tr>
            </table>
          </div>
          <h:messages id="app_messages" layout="table" errorClass="msgs_error" infoClass="msgs_info" warnClass="msgs_warning" />
          <div id="separ2">&nbsp;</div>
          <div id="glocontainer1">
            <div id="titlepage1"> Shift Tracking
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" immediate="true" /> &gt; Shift Tracking</div>
            <hr noshade color="#c8cbd0" size="1" />
            <span class="bigText1"><h:outputText id="output_yearMonth" value="#{shiftTrackFormRQ.yearMonth}" /></span>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top">
                    <table cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td width="140" class="cap">Application ID</td>
                        <td width="286" class="capData"><h:outputText id="output_applicationId" value="#{shiftTrackFormRQ.applicationId}" /></td>
                      </tr>
                      <tr>
                        <td class="cap">Institution</td>
                        <td class="capData"><h:outputText id="output_institution" value="#{shiftTrackFormRQ.institution}" /></td>
                      </tr>
                      <tr>
                        <td class="cap">Service Type</td>
                        <td class="capData"><h:outputText id="output_serviceType" value="#{shiftTrackFormRQ.serviceType}" /></td>
                      </tr>
                    </table>
                  </td>
                  <td><img src="../images/spacer.gif" width="20" height="1" alt="" /></td>
                  <td valign="top">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="3">
                    <table cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td width="139"><img src="../images/spacer.gif" width="136" height="20" alt="" /></td>
                        <td width="590"><img src="../images/spacer.gif" width="590" height="20" alt="" /></td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="2"><span id="txtline">Shifts</span></td>
                      </tr>
                      <tr>
                        <td class="cap">Total Number of Shifts </td>
                        <td>
                          <h:inputText id="input_numberOfShifts"
                                       label="Number Of Shifts"
                                       styleClass="fld100"
                                       value="#{shiftTrackFormRQ.numberOfShifts}"
                                       maxlength="5" />
                        </td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td class="cap">Were any of these Shifts?</td>
                        <td>
                          <h:selectBooleanCheckbox id="input_weekday" value="#{shiftTrackFormRQ.weekday}" />
                          Weekday&nbsp;&nbsp;
                          <h:selectBooleanCheckbox id="input_weeknight" value="#{shiftTrackFormRQ.weeknight}" />
                          Weeknight&nbsp;&nbsp;
                          <h:selectBooleanCheckbox id="input_weekend_day" value="#{shiftTrackFormRQ.weekend_day}" />
                          Weekend day&nbsp;&nbsp;
                          <h:selectBooleanCheckbox id="input_weekend_night" value="#{shiftTrackFormRQ.weekend_night}" />
                          Weekend night&nbsp;&nbsp; </td>
                      </tr>
                      <tr>
                        <td class="cap">&nbsp;</td>
                        <td>( Check all that apply )</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="2"><span id="txtline">Hours</span></td>
                      </tr>
                      <tr>
                        <td colspan="2">&nbsp;&nbsp;Please add the hours of all the shifts you recorded above, and enter the total number here:</td>
                      </tr>
                      <tr>
                        <td class="cap"> Total Number of Hours</td>
                        <td>
                          <h:inputText id="input_numberOfHours"
                                       label="Number Of Hours"
                                       styleClass="fld100"
                                       value="#{shiftTrackFormRQ.numberOfHours}"
                                       maxlength="5" />
                        </td>
                      </tr>
                      <tr>
                        <td class="cap">&nbsp;</td>
                        <td>
                          <h:selectBooleanCheckbox id="input_week_locum" value="#{shiftTrackFormRQ.week_locum}" />
                          Was there a 1 week locum included in this month?</td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="2">&nbsp;</td>
                      </tr>
                      <tr>
                        <td>
                          <h:commandButton id="button_back" action="#{shiftTrackListRQ.open}" value="Back" styleClass="btn btnbluePr btn100" />
                        </td>
                        <td>
                          <h:commandButton id="button_update" action="#{shiftTrackFormRQ.update}" value="Update" styleClass="btn btnorange btn100" />
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2"><br />
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
