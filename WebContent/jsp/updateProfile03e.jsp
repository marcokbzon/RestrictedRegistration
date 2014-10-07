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

  <body onload="setFocusOn( 'form_updateProfile03e:button_back' )">
    <f:view>
      <h:form id="form_updateProfile03e" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_updateProfile03e:button_back' ); return false; }; return true;">
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
              Edit Profile
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" /> &gt; Edit Profile</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="tb"></td>
                  <td class="bl">&nbsp;Personal Info&nbsp;</td>
                  <td class="mbb"></td>
                  <td class="bl">&nbsp;Education&nbsp;</td>
                  <td class="mby"></td>
                  <td class="yl">&nbsp;Clinical&nbsp;</td>
                  <td class="by"></td>
                </tr>
              </table>
              <br />
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">3</td>
                  <td>
                    <h1>Edit a Rotation</h1>
                  </td>
                </tr>
              </table>

              <table cellpadding="4" cellspacing="1" width="740" border="0">
                <tr>
                  <td width="429"><span class="mand">+</span> Service Type</td>
                  <td width="120">Rotations:<br /># of rotations</td>
                  <td width="120">Duration:<br />total # of weeks</td>
                </tr>
                <tr>
                  <td class="capData">
                    <h:inputText id="input_serviceType"
                                 label="Service Type"
                                 styleClass="fld300"
                                 maxlength="60"
                                 value="#{clinicalInfoSs.serviceType}" />
                  </td>
                  <td>
                    <h:inputText id="input_rotations"
                                 label="Number of Rotations"
                                 styleClass="fld50"
                                 maxlength="3"
                                 value="#{clinicalInfoSs.rotations}" />
                  </td>
                  <td>
                    <h:inputText id="input_weeksTotal"
                                 label="Total Number of Weeks"
                                 styleClass="fld50"
                                 maxlength="3"
                                 value="#{clinicalInfoSs.weeksTotal}" />
                  </td>
                </tr>
                <tr>
                  <td id="hrline" colspan="3">&nbsp;</td>
                </tr>
                <tr>
                  <td>
                    <h:commandButton id="button_back" action="#{clinicalInfoSs.updateProfile}" value="Back" styleClass="btn btnbluePr btn100" />
                  </td>
                  <td colspan="2" style="text-align:left;">
                    <h:commandButton id="button_save" action="#{clinicalInfoSs.update}" value="Save" styleClass="btn btnorange btn100" />
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