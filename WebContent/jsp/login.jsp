<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>Restricted Registration</title>
    <meta name="author" content="Headlong Media">
    <script type="text/javascript" src="/js/online.js"></script>
    <link href="/css/styles.css" rel="stylesheet" type="text/css" />
  </head>
  <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
  <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

  <body onLoad="setFocusOn( 'form_login:input_email' )">
    <f:view >
      <h:form id="form_login" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_login:button_signIn' ); return false; }; return true;">
        <div id="container">
          <div id="topstatus">
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
              <tr>
                <td width="100%">&nbsp;</td>
              </tr>
            </table>
          </div>
          <h:messages id="app_messages" layout="table" errorClass="msgs_error" infoClass="msgs_info" warnClass="msgs_warning" />
          <div id="bkgcontainer2">
            <div id="separ1">&nbsp;</div>
            <div id="cont">
              <div id="forgotpass">
                <table cellpadding="0" cellspacing="0" align="right" border="0">
                  <tr>
                    <td>[&nbsp;<h:commandLink id="link_forgotPassword" action="forgotPassword" value="Forgot Password?" immediate="true" />&nbsp;]&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td><div id="hlp1">&nbsp;</div></td>
                  </tr>
                </table>
              </div>
              <div id="welcome">
                <table cellpadding="0" cellspacing="0" border="0">
                  <tr>
                    <td width="460"><br />
                      <!-- h1>ONLINE APPLICATION FORM FOR RESTRICTED REGISTRATION FOR RESIDENTS</h1-->
                      <h1>This is the sign-in page for Residents, Supervisors, Program Directors and PG Deans</h1>
                      <br />
                      <em>Information required includes your previous rotations, email addresses of your Program Director and potential hospital Supervisor, as well as details of the proposed contracted hospital work.</em><br />
                    </td>
                    <td width="25"><img src="/images/spacer.gif" width="32" height="1" alt="" /></td>
                    <td align="right" width="230" style="padding-top:14px;">
                      <table cellpadding="0" cellspacing="3" border="0">
                        <tr>
                          <td class="cap" width="80">Email</td>
                          <td width="160">
                            <h:inputText id="input_email"
                                         label="Email"
                                         value="#{login.email}"
                                         required="true"
                                         maxlength="50"
                                         requiredMessage="#{appmsg.error_email_required}"
                                         validatorMessage="#{appmsg.error_email_invalid}"
                                         tabindex="1">
                              <f:validator validatorId="emailValidator" />
                            </h:inputText>
                          </td>
                        </tr>
                        <tr>
                          <td class="cap">Password</td>
                          <td>
                            <h:inputSecret id="input_password"
                                           label="Password"
                                           value="#{login.password}"
                                           required="true"
                                           maxlength="10"
                                           requiredMessage="#{appmsg.error_password_required}"
                                           validatorMessage="#{appmsg.error_password_length_invalid}"
                                           tabindex="2">
                              <f:validateLength minimum="6" maximum="10" />
                            </h:inputSecret>
                          </td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td>
                            <div>
                              <h:commandButton id="button_signIn" action="#{login.execute}" value="Sign In" styleClass="btn btnorange btn80" tabindex="3" />
                            </div>
                          </td>
                        </tr>
                      </table>
                      <div id="crt1">Residents may create a profile by clicking below. All other users must contact the <a href="mailto:info@restrictedregistrationontario.ca">Restricted Registration Office</a><br /><br />
                        <h:commandButton id="button_createProfile" action="createProfile" immediate="true" value="Create Profile!" styleClass="btn btnblue btn140" tabindex="4" />
                      </div>
                    </td>
                  </tr>
                </table>
              </div>
              <br />
              <div id="welcomeBody">
                <table cellpadding="0" cellspacing="0" border="0">
                  <tr>
                    <td colspan="2">
                      <br /><br />
                      <strong>APPLICATION PROCESS:</strong>
                      <ul type="square">
                        <li>The Resident creates a profile and applies online through the portal.</li>
                        <li>An automatic email is sent to the Resident's Supervising Physician (Credentialing, PIC, Supervising Housestaff) once the online application is complete. It is important that the Resident provides the correct contact information of the Supervising Physician. In the case where more than one physician will be supervising, a main contact (Most Responsible Physician) is necessary.</li>
                        <li>Upon successful confirmation by the Supervising Physician, the application is automatically forwarded to the Program Director.</li>
                        <li>Upon successful review by the Program Director, the application is automatically forwarded to the PG Dean for approval.</li>
                        <li>The application is then handled by the Restricted Registration office and sent to the College of Physicians and Surgeons of Ontario (CPSO). The application is prepared by the CPSO for the next available Registration Committee meeting. The Resident will be asked to complete additional forms and documentation.</li>
                        <li>The CPSO informs the Resident of the Committee decision, and issues a Restricted Certificate for the period of work outside postgraduate training. Please note, you may not begin practice until you have received confirmation from the College that you are registered.</li>
                      </ul>
                      <br />
                    </td>
                  </tr>
                </table>
              </div>
              <br />
              <div>
                <table cellpadding="0" cellspacing="0" border="0">
                  <tr>
                    <td width="2%" align="right" valign="top">*</td>
                    <td>
                        	There are no appeals to this process. The Program Directors, PG Deans and Registration Committee at CPSO all have the right to refuse or withdraw a Resident's participation in this program at any time.
                      <br /><br />
                    </td>
                  </tr>
                  <tr>
                    <td align="right" valign="top">**</td>
                    <td>
                        	The onus is on the Resident to contact the Canadian Medical Protective Association (CMPA) to request that their coverage be changed to TOW Code 14. There is no additional fee, and you may do this at any time. However, you MUST have proof of this change in status by the time you begin your Restricted Registration activity.
                      <br /><br />
                    </td>
                  </tr>
                </table>
              </div>
            </div>
            <jsp:include page="/jsp/footer.inc" />
          </div>
        </div>
      </h:form>
    </f:view>
  </body>
</html>
