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

  <body onLoad="setFocusOn( 'form_adminSearchEmail:button_search' )">
    <f:view>
      <h:form id="form_adminSearchEmail" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_adminSearchEmail:button_search' ); return false; }; return true;">
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
              Maintain Program
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" /> &gt; Search</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">&nbsp;</td>
                  <td>
                    <h1>Search Email</h1>
                  </td>
                </tr>
              </table>
              <br />
              <table cellpadding="4" cellspacing="1" width="740" border="0">
                <tr>
                  <td width="150"><span class="mand">+</span> Email</td>
                  <td>
                    <h:inputText id="input_email"
                                 label="Email"
                                 styleClass="fld300"
                                 maxlength="60"
                                 value="#{adminSearchRq.email}" />
                  </td>
                </tr>
                <tr>
                  <td id="hrline" colspan="2">&nbsp;</td>
                </tr>
                <tr>
                  <td>
                    <h:commandButton id="button_back" action="#{mainPage.view}" immediate="true" value="Back" styleClass="btn btnbluePr btn100" />
                  </td>
                  <td style="text-align:left;">
                    <h:commandButton id="button_search" action="#{adminSearchRq.submit}" value="Search" styleClass="btn btnorange btn100" />
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
