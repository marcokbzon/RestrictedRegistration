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

  <body onload="setFocusOn( 'form_createProfile:input_firstName' )">
    <f:view>
      <h:form id="form_createProfile" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_createProfile:button_createProfile' ); return false; }; return true;">
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
              Create Profile
              <div id="hlp1">&nbsp;</div>
            </div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top">
                    <table cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td width="136" class="cap"><span class="mand">+</span> First Name</td>
                        <td width="294">
                          <h:inputText id="input_firstName"
                                       label="First Name"
                                       styleClass="fld250"
                                       value="#{personalInfoRq.firstName}"
                                       maxlength="20"
                                       required="true"
                                       requiredMessage="#{appmsg.error_firstname_required}" />
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Last Name</td>
                        <td>
                          <h:inputText id="input_lastName"
                                       label="Last Name"
                                       styleClass="fld250"
                                       value="#{personalInfoRq.lastName}"
                                       maxlength="20"
                                       required="true"
                                       requiredMessage="#{appmsg.error_lastname_required}" />
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Email</td>
                        <td>
                          <h:inputText id="input_email"
                                       label="Email"
                                       styleClass="fld250"
                                       value="#{personalInfoRq.email}"
                                       maxlength="50"
                                       required="true"
                                       requiredMessage="#{appmsg.error_email_required}"
                                       validatorMessage="#{appmsg.error_email_invalid}">
                            <f:validator validatorId="emailValidator" />
                          </h:inputText>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Enter Password</td>
                        <td>
                          <h:inputSecret id="input_password"
                                         label="Password"
                                         styleClass="fld100"
                                         value="#{personalInfoRq.password}"
                                         maxlength="10"
                                         required="true"
                                         requiredMessage="#{appmsg.error_password_required}" />
                          8 characters minimum
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Confirm Password</td>
                        <td>
                          <h:inputSecret id="input_passwordConfirm"
                                         label="Confirm Password"
                                         styleClass="fld100"
                                         value="#{personalInfoRq.passwordConfirm}"
                                         maxlength="10"
                                         required="true"
                                         requiredMessage="#{appmsg.error_passwordconfirm_required}" />
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Gender</td>
                        <td>
                          <h:selectOneRadio id="gender" required="true" value="#{personalInfoRq.gender}" requiredMessage="#{appmsg.error_gender_required}">
                            <f:selectItem itemValue="M" itemLabel="Male"/>
                            <f:selectItem itemValue="F" itemLabel="Female"/>
                          </h:selectOneRadio>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Year of Birth</td>
                        <td>
                          <h:selectOneMenu id="input_yearOfBirth" value="#{personalInfoRq.yearOfBirth}" required="true" requiredMessage="#{appmsg.error_yob_required}">
                            <f:selectItem itemValue="" itemLabel="YYYY"/>
                            <f:selectItems value="#{yobBean.yearList}" />
                          </h:selectOneMenu>
                        </td>
                      </tr>
                    </table>
                  </td>
                  <td><img src="images/spacer.gif" width="20" height="1" alt="" /></td>
                  <td valign="top">
                    <div id="notes">
                      <h4>Note:</h4>
                      <span class="mand">+</span> Mandatory field </div>
                  </td>
                </tr>
                <tr>
                  <td colspan="3">
                    <table cellpadding="2" cellspacing="1" width="738" border="0">
                      <tr>
                        <td id="hrline" colspan="6"><span id="txtline">Home Address</span></td>
                      </tr>
                      <tr>
                        <td width="136" class="cap"><span class="mand">+</span> Street #</td>
                        <td width="226">
                          <h:inputText id="input_streetNumber"
                                       label="Street Number"
                                       styleClass="fld100"
                                       value="#{personalInfoRq.streetNumber}"
                                       maxlength="5"
                                       required="true"
                                       requiredMessage="#{appmsg.error_streetnumber_required}" />
                        </td>
                        <td width="122" class="cap"><span class="mand">+</span> Street Name</td>
                        <td colspan="3">
                          <h:inputText id="input_streetName"
                                       label="Street Name"
                                       styleClass="fld200"
                                       value="#{personalInfoRq.streetName}"
                                       maxlength="40"
                                       required="true"
                                       requiredMessage="#{appmsg.error_streetname_required}" />
                        </td>
                      </tr>
                      <tr>
                        <td class="cap">Apt. #</td>
                        <td>
                          <h:inputText id="input_aptNumber"
                                       label="Apartment Number"
                                       styleClass="fld100"
                                       maxlength="5"
                                       value="#{personalInfoRq.aptNumber}" />
                        </td>
                        <td class="cap">Country</td>
                        <td class="capData" width="51">
                          Canada
                          <h:inputHidden id="input_countryCode" value="#{personalInfoRq.countryCode}" />
                        </td>
                        <td width="85" class="cap">Province</td>
                        <td class="capData" width="87">
                          Ontario
                          <h:inputHidden id="input_provStateCode" value="#{personalInfoRq.provStateCode}" />
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> City</td>
                        <td>
                          <h:inputText id="input_city"
                                       label="City"
                                       styleClass="fld100"
                                       value="#{personalInfoRq.city}"
                                       maxlength="30"
                                       required="true"
                                       requiredMessage="#{appmsg.error_city_required}" />
                        </td>
                        <td class="cap"><span class="mand">+</span> Postal Code</td>
                        <td colspan="3">
                          <h:inputText id="input_postalCode"
                                       label="Postal Code"
                                       styleClass="fld100"
                                       value="#{personalInfoRq.postalCode}"
                                       maxlength="7"
                                       required="true"
                                       requiredMessage="#{appmsg.error_postalcode_required}" />
                        </td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="6"><span id="txtline">Phone</span></td>
                      </tr>
                      <tr>
                        <td class="cap">Home</td>
                        <td>
                          <h:inputText id="input_homePhone"
                                       label="Home Phone"
                                       styleClass="fld150"
                                       value="#{personalInfoRq.homePhone}"
                                       maxlength="12"
                                       validatorMessage="#{appmsg.error_homephone_invalid}">
                            <f:validator validatorId="phoneValidator" />
                          </h:inputText>
                          <br /><span class="smallText">999-999-9999</span>
                        </td>
                        <td class="cap">Office</td>
                        <td colspan="3">
                          <h:inputText id="input_officePhone"
                                       label="Office Phone"
                                       styleClass="fld150"
                                       value="#{personalInfoRq.officePhone}"
                                       maxlength="12"
                                       validatorMessage="#{appmsg.error_officephone_invalid}">
                            <f:validator validatorId="phoneValidator" />
                          </h:inputText>
                          <br /><span class="smallText">999-999-9999</span>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap">Mobile</td>
                        <td>
                          <h:inputText id="input_cellPhone"
                                       label="Cellular Phone"
                                       styleClass="fld150"
                                       value="#{personalInfoRq.cellPhone}"
                                       maxlength="12"
                                       validatorMessage="#{appmsg.error_cellphone_invalid}">
                            <f:validator validatorId="phoneValidator" />
                          </h:inputText>
                          <br /><span class="smallText">999-999-9999</span>
                        </td>
                        <td class="cap">Pager</td>
                        <td colspan="3">
                          <h:inputText id="input_pagerNumber"
                                       label="Pager Number"
                                       styleClass="fld150"
                                       value="#{personalInfoRq.pagerNumber}"
                                       maxlength="12"
                                       validatorMessage="#{appmsg.error_pagernumber_invalid}">
                            <f:validator validatorId="phoneValidator" />
                          </h:inputText>
                          <br /><span class="smallText">999-999-9999</span>
                        </td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="6">&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="1">
                          <h:commandButton id="button_back" action="login" immediate="true" value="Back" styleClass="btn btnbluePr btn100" />
                        </td>
                        <td colspan="5">
                          <h:commandButton id="button_createProfile" action="#{personalInfoRq.create}" value="Create" styleClass="btn btnorange btn100" />
                        </td>
                      </tr>
                      <tr>
                        <td colspan="6"><br />
                          You are now at the point where you can create your profile. Fill in your email address and your password. Use a password that is easy for you to remember. Click [Create]. You will now be directed to the SignIn Page. </td>
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
