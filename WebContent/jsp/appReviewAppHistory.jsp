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
      <h:form id="form_appReviewAppHistory">
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
            <div id="titlepage1">
              Application Listing
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" immediate="true" action="#{mainPage.view}" /> &gt; Application Listing</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="720" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top" width="95%">
                    <table width="100%" cellpadding="0" cellspacing="1" border="0">
                      <tr>
                        <td>
                          <div id="topcontent1">
                            <h1>PAST APPLICATIONS</h1>
                            <br />
                            Please select a University to retrieve information: <br />
                            <br />
                            <div id="tbl1t">
                              <div id="tbl2t"><img src="/images/spacer.gif" height="10" alt="" /></div>
                            </div>
                            <div id="tbl">
                              <table width="400" border="0" cellspacing="0" cellpadding="3">
                                <tr>
                                  <td>
                                    &nbsp;
                                    <h:selectOneMenu id="input_university" value="#{appReviewAppHistorySs.universityID}"
                                                     immediate="true" valueChangeListener="#{appReviewAppHistorySs.changeUniversity}"
                                                     onchange="this.form.submit( );" >
                                      <f:selectItem itemValue="" itemLabel="-Select a University-"/>
                                      <f:selectItems value="#{schoolBean.schoolList}" />
                                    </h:selectOneMenu>
                                    &nbsp;
                                    <h:commandButton id="button_go" action="#{appReviewAppHistorySs.go}" value="Go" styleClass="btn btnorange btn80" />
                                  </td>
                                </tr>
                              </table>
                              <table width="400" border="0" cellspacing="0" cellpadding="3">
                                <tr>
                                  <td>
                                    <h3>
                                      &nbsp;&nbsp;Number of Applications: <b><h:outputText value="#{appReviewAppHistorySs.listCount}" /></b>
                                    </h3>
                                  </td>
                                </tr>
                              </table>
                              <br />
                              <table cellpadding="0" cellspacing="0" border="0" bgcolor="#999999" align="center">
                                <tr>
                                  <td>
                                    <h:dataTable id="table_applicationsA" value="#{appReviewAppHistoryBean.applicationDM}" var="aList"
                                                 styleClass="tbl_mainListing" headerClass="tbl_hdr"
                                                 rowClasses="tbl_oddRow, tbl_evenRow" columnClasses="tbl_col_26, tbl_col_110, tbl_col_278, tbl_col_310">
                                      <h:column>
                                        <f:facet name="header"><h:outputText value="" /></f:facet>
                                        <i><h:outputText value="#{aList.position}"/></i>
                                      </h:column>

                                      <h:column>
                                        <f:facet name="header"><h:outputText value="Application ID" /></f:facet>
                                        <h:commandLink action="#{appReviewPrint.open}" immediate="true" value="#{aList.applicationID}">
                                          <f:param name="appID" value="#{aList.applicationID}" />
                                        </h:commandLink>
                                      </h:column>

                                      <h:column>
                                        <f:facet name="header"><h:outputText value="Resident Name / University" /></f:facet>
                                        <h:panelGroup>
                                          <strong><h:outputText value="#{aList.firstName}"/>&nbsp;<h:outputText value="#{aList.lastName}"/></strong>
                                          <br />
                                          <h:outputText value="#{aList.universityName}"/>
                                        </h:panelGroup>
                                      </h:column>

                                      <h:column>
                                        <f:facet name="header"><h:outputText value="Institution / Service Type" /></f:facet>
                                        <h:panelGroup>
                                          <h:outputText value="#{aList.institutionName}"/>
                                          <br />
                                          <h:outputText value="#{aList.serviceTypeName}"/>
                                        </h:panelGroup>
                                      </h:column>
                                    </h:dataTable>
                                  </td>
                                </tr>
                              </table>
                              <br />
                            </div>
                            <div id="tbl1b">
                              <div id="tbl2b"><img src="/images/spacer.gif" height="10" alt="" /></div>
                            </div>
                          </div>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </div>
            <br />
            <jsp:include page="/jsp/footer.inc" />
          </div>
        </div>
      </h:form>
    </f:view>
  </body>
</html>
