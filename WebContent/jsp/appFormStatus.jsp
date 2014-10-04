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
      <h:form id="form_appFormStatus">
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
              Application Form
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" /> &gt; <h:commandLink value="Application Listing" action="#{appFormList.view}" /> &gt; Application Status</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="720" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top" width="95%">
                    <table width="100%" cellpadding="0" cellspacing="1" border="0">
                      <tr>
                        <td>
                          <div id="topcontent1">
                            <h1>APPLICATION STATUS</h1>
                            <br />
                            <div id="tbl1t">
                              <div id="tbl2t"><img src="/images/spacer.gif" height="10" alt="" /></div>
                            </div>
                            <div id="tbl">
                              <table class="tbl_mainListing">
                                <tr>
                                  <td style="background:#fff; padding-left: 10px;">
                                    <h4>Application : <strong><h:outputText value="#{appFormStatus.applicationID}"/></strong></h4> <br />
                                    Institution: <span class="capData"><h:outputText value="#{appFormStatus.institution}"/></span> <br />
                                    Service Type: <span class="capData"><h:outputText value="#{appFormStatus.serviceType}"/></span><br />
                                    <br />
                                  </td>
                                </tr>
                              </table>
                              <table cellpadding="0" cellspacing="0" border="0" bgcolor="#999999" align="center">
                                <tr>
                                  <td>
                                    <table class="tbl_mainListing">
                                      <tr>
                                        <th class="tbl_hdr tbl_col_83">Date</th>
                                        <th class="tbl_hdr tbl_col_500">Contact</th>
                                        <th class="tbl_hdr tbl_col_118">Status</th>
                                      </tr>
                                    </table>
                                    <h:dataTable id="tbl_mainListing" value="#{appFormStatusBean.statusDM}" var="sList"
                                                 styleClass="tbl_mainListing"
                                                 rowClasses="tbl_oddRow, tbl_evenRow"
                                                 columnClasses="tbl_col_88, tbl_col_328, tbl_col_168, tbl_col_118">
                                      <h:column>
                                        <h:outputText value="#{sList.updatedOn}"/>
                                      </h:column>

                                      <h:column>
                                        <h:outputText value="#{sList.roleDesc}"/>
                                      </h:column>

                                      <h:column>
                                        <h:outputText value="#{sList.firstName}"/>&nbsp;<h:outputText value="#{sList.lastName}"/>
                                      </h:column>

                                      <h:column>
                                        <h:outputText value="#{sList.codeDesc}" styleClass="#{sList.iconName}"/>
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
