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
      <h:form id="form_adminCPSOapprove" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_adminCPSOapprove:button_back' ); return false; }; return true;">
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
            <div id="titlepage1"> CPSO Approval
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" immediate="true" /> &gt; CPSO Approval</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">&nbsp;</td>
                  <td>
                    <h1>Has the following Application been approved by the CPSO?</h1>
                  </td>
                </tr>
              </table>
              <br />
              <table cellpadding="4" cellspacing="1" width="740" border="0">
                <tr>
                  <td width="150">Application ID</td>
                  <td class="capData"><h:outputText id="output_applicationid" value="#{adminCPSOapprovalRQ.applicationID}" /></td>
                </tr>
                <tr>
                  <td width="150">Resident Name</td>
                  <td class="capData"><h:outputText id="output_residentName" value="#{adminCPSOapprovalRQ.residentName}" /></td>
                </tr>
                <tr>
                  <td width="150">University</td>
                  <td class="capData"><h:outputText id="output_university" value="#{adminCPSOapprovalRQ.university}" /></td>
                </tr>
                <tr>
                  <td width="150">Program</td>
                  <td class="capData"><h:outputText id="output_program" value="#{adminCPSOapprovalRQ.program}" /></td>
                </tr>
                <tr>
                  <td width="150">Institution</td>
                  <td class="capData"><h:outputText id="output_institution" value="#{adminCPSOapprovalRQ.institution}" /></td>
                </tr>
                <tr>
                  <td width="150">Service Type</td>
                  <td class="capData"><h:outputText id="output_serviceType" value="#{adminCPSOapprovalRQ.serviceType}" /></td>
                </tr>
                <tr>
                  <td id="hrline" colspan="2">&nbsp;</td>
                </tr>
                <tr>
                  <td>
                    <h:commandButton id="button_back" action="#{adminCPSOsearchRQ.open}" value="Back" styleClass="btn btnbluePr btn100" />
                  </td>
                  <td style="text-align:left;">
                    <h:commandButton id="button_approve" action="#{adminCPSOapprovalRQ.update}" value="Yes, Approve" styleClass="btn btnorange btn14s0" />
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
