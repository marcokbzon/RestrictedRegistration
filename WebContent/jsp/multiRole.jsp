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
      <h:form id="form_multiRole">
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
              Multiple Role Account
              <div id="hlp1">&nbsp;</div>
            </div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top" width="95%">
                    <table width="100%" cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td>
                          <div id="topcontent1">
                            <h1>Select A Role</h1>
                            <br />
                            Your account is linked to more than one role. Please select from the follwoing list the role you want to work with:<br />
                            <br />
                            <br />
                            <center>
                              <table cellpadding="3">
                                <tr>
                                  <td>
                                    <h:selectOneRadio id="input_role" required="true"
                                                      value="#{multiRole.selectedRole}" layout="pageDirection"
                                                      requiredMessage="#{appmsg.error_role_required}">
                                      <f:selectItems value="#{multiRole.roleList}" />
                                    </h:selectOneRadio>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <br />
                                    <h:commandButton id="button_multiRole" action="#{multiRole.execute}" value="Select a Role" styleClass="btn btnblue btn140" />
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
