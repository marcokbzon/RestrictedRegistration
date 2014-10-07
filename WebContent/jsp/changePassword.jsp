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

  <body onload="setFocusOn( 'form_changePassword:input_oldPassword' )">
    <f:view>
      <h:form id="form_changePassword" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_changePassword:button_change' ); return false; }; return true;">
        <div id="container">
          <div id="topstatus">
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
              <tr>
                <td width="90%" >&nbsp;</td>
                <td id="divider" width="40%" nowrap>Logged in as <strong><h:outputText value="#{sessionScope.email}" /></strong></td>
                <td width="40%"><h:commandLink id="link_logout" action="#{logout.execute}" immediate="true">Logout</h:commandLink></td>
              </tr>
            </table>
          </div>
          <h:messages id="app_messages" layout="table" errorClass="msgs_error" infoClass="msgs_info" warnClass="msgs_warning" />
          <div id="separ2">&nbsp;</div>
          <div id="glocontainer1">
            <div id="titlepage1"> Change Password
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" immediate="true" /> &gt; Change Password</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top" width="95%">
                    <table width="100%" cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td>
                          <div id="topcontent1">
                            <h1>Change your Password</h1>
                            <br />
                            A strong password helps prevent unauthorized access to your personal account.<br />
                            <br />
                            <br />
                            <center>
                              <table cellpadding="3">
                                <tr>
                                  <td width="140" class="cap"><span class="mand">+</span> Old password: </td>
                                  <td width="222">
                                    <h:inputSecret id="input_oldPassword"
                                                   label="Old Password"
                                                   value="#{changePassword.oldPassword}"
                                                   required="true"
                                                   maxlength="10"
                                                   requiredMessage="#{appmsg.error_oldpassword_required}"
                                                   validatorMessage="#{appmsg.error_password_length_invalid}"
                                                   styleClass="fld150"
                                                   tabindex="1">
                                      <f:validateLength minimum="6" maximum="10" />
                                    </h:inputSecret>
                                  </td>
                                </tr>
                                <tr>
                                  <td class="cap"><span class="mand">+</span> Type new password:</td>
                                  <td>
                                    <h:inputSecret id="input_newPassword"
                                                   label="New Password"
                                                   value="#{changePassword.newPassword}"
                                                   required="true"
                                                   maxlength="10"
                                                   requiredMessage="#{appmsg.error_newpassword_required}"
                                                   validatorMessage="#{appmsg.error_password_length_invalid}"
                                                   styleClass="fld150"
                                                   tabindex="2">
                                      <f:validateLength minimum="6" maximum="10" />
                                    </h:inputSecret>
                                  </td>
                                </tr>
                                <tr>
                                  <td class="cap"><span class="mand">+</span> Re-type new password:</td>
                                  <td>
                                    <h:inputSecret id="input_newPasswordConfirm"
                                                   label="New Password Confirm"
                                                   value="#{changePassword.newPasswordConfirm}"
                                                   required="true"
                                                   maxlength="10"
                                                   requiredMessage="#{appmsg.error_newpasswordconfirm_required}"
                                                   validatorMessage="#{appmsg.error_password_length_invalid}"
                                                   styleClass="fld150"
                                                   tabindex="3">
                                      <f:validateLength minimum="6" maximum="10" />
                                    </h:inputSecret>
                                  </td>
                                </tr>
                                <tr>
                                  <td id="hrline" colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                  <td>&nbsp;</td>
                                  <td>
                                    <h:commandButton id="button_change" action="#{changePassword.execute}" value="Change" styleClass="btn btnorange btn100" />
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

