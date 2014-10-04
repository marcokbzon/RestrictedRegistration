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

  <body onload="setFocusOn( 'form_forgotPassword:input_email' );">
    <f:view>
      <h:form id="form_forgotPassword" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_forgotPassword:button_forgotPassword' ); return false; }; return true;">
        <div id="container">
          <div id="topstatus">
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
              <tr>
                <td width="100%">&nbsp;</td>
              </tr>
            </table>
          </div>
          <h:messages id="app_messages" layout="table" errorClass="msgs_error" infoClass="msgs_info" warnClass="msgs_warning" />
          <div id="separ2">&nbsp;</div>
          <div id="glocontainer1">
            <div id="titlepage1">
              Forgot Password
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
                            <h1>Forgot your Password</h1>
                            <br />
                            Your password will be sent  to you via e-mail:  <%-- *** R-140521 *** Please respond all the following questions: --%><br />
                            <br />
                            <br />
                            <center>
                              <table cellpadding="3">
                                <tr>
                                  <td width="140" class="cap"><span class="mand">+</span> Email: </td>
                                  <td width="259">
                                    <h:inputText id="input_email"
                                                 label="Email"
                                                 value="#{forgotPassword.email}"
                                                 maxlength="50"
                                                 required="true"
                                                 styleClass="fld250"
                                                 requiredMessage="#{appmsg.error_email_required}"/>
                                  </td>
                                </tr>
                                <%--   *** R-140521 ***
                                <tr>
                                  <td class="cap"><span class="mand">+</span> Year of Birth:</td>
                                  <td>
                                    <h:inputText id="input_yearOfBirth"
                                                 label="Year of Birth"
                                                 value="#{forgotPassword.yearOfBirth}"
                                                 maxlength="4"
                                                 required="true"
                                                 styleClass="fld50"
                                                 requiredMessage="#{appmsg.error_yob_required}" />
                                  </td>
                                </tr>
                                <tr>
                                  <td class="cap"><span class="mand">+</span> Home Postal Code:</td>
                                  <td>
                                    <h:inputText id="input_postalCode"
                                                 label="Postal Code"
                                                 value="#{forgotPassword.postalCode}"
                                                 maxlength="6"
                                                 required="true"
                                                 styleClass="fld100"
                                                 requiredMessage="#{appmsg.error_postalcode_required}"/>
                                  </td>
                                </tr>
                                --%>
                                <tr>
                                  <td id="hrline" colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                  <td>&nbsp;</td>
                                  <td>
                                    <h:commandButton id="button_forgotPassword" action="#{forgotPassword.execute}" value="Get Password" styleClass="btn btnorange btn140" />
                                  </td>
                                </tr>
                              </table>
                            </center>
                            <br />
                            <br />
                            <h:commandButton id="button_back" action="login" immediate="true" value="Back" styleClass="btn btnbluePr btn100" />
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
