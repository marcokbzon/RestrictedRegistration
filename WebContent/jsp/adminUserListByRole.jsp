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
      <h:form id="form_adminUserListByRole">
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
              User Listing
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" immediate="true" action="#{mainPage.view}" /> &gt; User Listing</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="720" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top" width="95%">
                    <table width="100%" cellpadding="0" cellspacing="1" border="0">
                      <tr>
                        <td>
                          <div id="topcontent1">
                            <h1>USERS BY ROLE</h1>
                            <br />
                            Please select a Role and press "Go" to retrieve information:
                            <br />
                            <br />
                            <div id="tbl1t">
                              <div id="tbl2t"><img src="/images/spacer.gif" height="10" alt="" /></div>
                            </div>
                            <div id="tbl">
                              <br/>
                              <table width="600" border="0" cellspacing="0" cellpadding="3">
                                <tr>
                                  <td>
                                    <h:selectOneRadio id="input_role" value="#{adminUserListByRoleSs.selectedRole}"
                                                      immediate="true" valueChangeListener="#{adminUserListByRoleSs.changeRole}"
                                                      onclick="this.form.submit( );" >
                                      <f:selectItem itemValue="ALL" itemLabel="All"/>
                                      <f:selectItem itemValue="UMR" itemLabel="Residents"/>
                                      <f:selectItem itemValue="ISV" itemLabel="Supervisors"/>
                                      <f:selectItem itemValue="UPD" itemLabel="Directors"/>
                                      <f:selectItem itemValue="UDN" itemLabel="Deans"/>
                                      <f:selectItem itemValue="UCM" itemLabel="Committees"/>
                                      <f:selectItem itemValue="OTH" itemLabel="Other"/>
                                    </h:selectOneRadio>
                                    &nbsp;
                                    <h:commandButton id="button_go" action="#{adminUserListByRoleSs.go}" value="Go" styleClass="btn btnorange btn80" />
                                  </td>
                                </tr>
                              </table>
                              <table width="400" border="0" cellspacing="0" cellpadding="3">
                                <tr>
                                  <td>
                                    <h3>
                                      &nbsp;&nbsp;Number of Users: <b><h:outputText value="#{adminUserListByRoleSs.listCount}" /></b>
                                    </h3>
                                  </td>
                                </tr>
                              </table>
                              <table cellpadding="0" cellspacing="0" border="0" bgcolor="#999999" align="center">
                                <tr>
                                  <td>
                                    <h:dataTable id="table_users" value="#{adminUserListByRoleBean.userDM}" var="uList"
                                                 styleClass="tbl_mainListing" headerClass="tbl_hdr"
                                                 rowClasses="tbl_oddRow, tbl_evenRow" columnClasses="tbl_col_26, tbl_col_160, tbl_col_410, tbl_col_118">
                                      <h:column>
                                        <f:facet name="header"><h:outputText value="" /></f:facet>
                                        <i><h:outputText value="#{uList.position}"/></i>
                                      </h:column>

                                      <h:column>
                                        <f:facet name="header"><h:outputText value="User" /></f:facet>
                                        <h:panelGroup>
                                          <strong><h:outputText value="#{uList.lastName}"/>,&nbsp;<h:outputText value="#{uList.firstName}"/></strong>
                                          <br />
                                          <h:outputText value="#{uList.email}"/>
                                        </h:panelGroup>
                                      </h:column>

                                      <h:column>
                                        <f:facet name="header"><h:outputText value="Other Information" /></f:facet>
                                        <h:panelGroup>
                                          <h:outputText value="#{uList.info1}"/>
                                          <br />
                                          <h:outputText value="#{uList.info2}"/>
                                        </h:panelGroup>
                                      </h:column>

                                      <h:column>
                                        <f:facet name="header"><h:outputText value="Role" /></f:facet>
                                        <h:outputText value="#{uList.role}"/>
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
