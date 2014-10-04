<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>Restricted Registration</title>
    <script type="text/javascript" src="/js/online.js"></script>
    <link href="/css/styles.css" rel="stylesheet" type="text/css" />
  </head>
  <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
  <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

  <body>
    <f:view>
      <h:form id="form_shiftTracking02" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_shiftTracking02:button_back' ); return false; }; return true;">
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
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">&nbsp;</td>
                  <td>
                    <h1>Application List</h1>
                  </td>
                </tr>
              </table>
              <br />
              <table cellpadding="2" cellspacing="1" width="740" border="0">
                <tr>
                  <td width="363">
                    <h:commandButton id="button_edit" action="#{shiftTrackListRQ.editInfo}" value="Edit" styleClass="btn btnorange btn100" />
                    &nbsp; </td>
                  <td  width="363" style="text-align:right;">&nbsp;</td>
                </tr>
              </table>
              <table width="740" cellpadding="0" cellspacing="0" border="0" bgcolor="#999999">
                <tr>
                  <td width="40">
                    <table>
                      <tr>
                        <th height="45">&nbsp;</th>
                      </tr>
                    </table>
                    <h:selectOneRadio id="input_shiftTrackId"
                                      value="#{shiftTrackListRQ.shiftTrackId}" layout="pageDirection"
                                      styleClass="tbl_main5">
                      <f:selectItems value="#{shiftTrackingBean.applicationIdList}" />
                    </h:selectOneRadio>
                  </td>
                  <td width="700">
                    <h:dataTable id="table_shiftTrack" value="#{shiftTrackingBean.shiftTrackDM}" var="sList"
                                 styleClass="tbl_main3" headerClass="tbl_hdr"
                                 rowClasses="tbl_oddRow, tbl_evenRow" columnClasses="tbl_col_408, tbl_col_136, tbl_col_136">
                      <h:column>
                        <f:facet name="header">
                          <h:panelGroup>
                            <br />
                            <h:outputText value="Application Information" />
                          </h:panelGroup>
                        </f:facet>
                        <h:outputText value="#{sList.applicationID}"/><br />
                        <h:outputText value="#{sList.institution}"/><br />
                        <h:outputText value="#{sList.serviceType}"/>
                      </h:column>

                      <h:column>
                        <f:facet name="header">
                          <h:panelGroup>
                            <br />
                            <h:outputText value="# of Shifts" />
                          </h:panelGroup>
                        </f:facet>
                        <h:outputText value="#{sList.numberOfShifts}"/>
                      </h:column>

                      <h:column>
                        <f:facet name="header">
                          <h:panelGroup>
                            <br />
                            <h:outputText value="# of Hours" />
                          </h:panelGroup>
                        </f:facet>
                        <h:outputText value="#{sList.numberOfHours}"/>
                      </h:column>
                    </h:dataTable>
                  </td>
                </tr>
              </table>
              <table cellpadding="2" cellspacing="1" width="740" border="0">
                <tr>
                  <td id="hrline" colspan="2">&nbsp;</td>
                </tr>
                <tr>
                  <td width="363">
                    <h:commandButton id="button_back" action="#{shiftTrackCalendarSS.open}" value="Back" styleClass="btn btnbluePr btn100" />
                  </td>
                  <td width="363" style="text-align:right;">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="2"><br />
                    To &quot;<strong>Edit</strong>&quot; number of Shifts or Hours, you must first select an Application from the list. </td>
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
