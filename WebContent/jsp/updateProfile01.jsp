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

  <body onload="setFocusOn( 'form_updateProfile01:input_firstName' )">
    <f:view>
      <h:form id="form_updateProfile01" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_updateProfile01:button_save' ); return false; }; return true;">
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
                  <td class="ty"></td>
                  <td class="yl">&nbsp;Personal Info&nbsp;</td>
                  <td class="myg"></td>
                  <td class="gr">&nbsp;Education&nbsp;</td>
                  <td class="mgg"></td>
                  <td class="gr">&nbsp;Clinical&nbsp;</td>
                  <td class="bg"></td>
                </tr>
              </table>
              <br />
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">1</td>
                  <td>
                    <h1>Personal Information</h1>
                  </td>
                </tr>
              </table>
              <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top">
                    <table cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td width="139" class="cap"><span class="mand">+</span> First Name</td>
                        <td width="280">
                          <h:inputText id="input_firstName"
                                       label="First Name"
                                       styleClass="fld250"
                                       value="#{personalInfoSs.firstName}"
                                       required="true"
                                       maxlength="20"
                                       requiredMessage="#{appmsg.error_firstname_required}" />
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Last Name</td>
                        <td>
                          <h:inputText id="input_lastName"
                                       label="Last Name"
                                       styleClass="fld250"
                                       value="#{personalInfoSs.lastName}"
                                       maxlength="20"
                                       required="true"
                                       requiredMessage="#{appmsg.error_lastname_required}" />
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Gender</td>
                        <td>
                          <h:selectOneRadio id="gender" required="true" value="#{personalInfoSs.gender}" requiredMessage="#{appmsg.error_gender_required}">
                            <f:selectItem itemValue="M" itemLabel="Male"/>
                            <f:selectItem itemValue="F" itemLabel="Female"/>
                          </h:selectOneRadio>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Year of Birth</td>
                        <td>
                          <h:selectOneMenu id="input_yearOfBirth" value="#{personalInfoSs.yearOfBirth}" required="true" requiredMessage="#{appmsg.error_yob_required}">
                            <f:selectItem itemValue="0" itemLabel="YYYY"/>
                            <f:selectItems value="#{yobBean.yearList}" />
                          </h:selectOneMenu>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> I am a VISA Resident</td>
                        <td>
                          <h:selectOneRadio id="input_visaStudentInd" value="#{personalInfoSs.visaStudentIndicator}">
                            <f:selectItem itemValue="1" itemLabel="Yes"/>
                            <f:selectItem itemValue="0" itemLabel="No"/>
                          </h:selectOneRadio>
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
                        <td width="207">
                          <h:inputText id="input_streetNumber"
                                       label="Street Number"
                                       styleClass="fld100"
                                       value="#{personalInfoSs.streetNumber}"
                                       maxlength="5"
                                       required="true"
                                       requiredMessage="#{appmsg.error_streetnumber_required}" />
                        </td>
                        <td width="143" class="cap"><span class="mand">+</span> Street Name</td>
                        <td colspan="3">
                          <h:inputText id="input_streetName"
                                       label="Street Name"
                                       styleClass="fld200"
                                       value="#{personalInfoSs.streetName}"
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
                                       value="#{personalInfoSs.aptNumber}" />
                        </td>
                        <td class="cap">Country</td>
                        <td class="capData" width="51">Canada</td>
                        <td width="83" class="cap">Province</td>
                        <td class="capData" width="87">Ontario</td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> City</td>
                        <td>
                          <h:inputText id="input_city"
                                       label="City"
                                       styleClass="fld100"
                                       value="#{personalInfoSs.city}"
                                       maxlength="30"
                                       required="true"
                                       requiredMessage="#{appmsg.error_city_required}" />
                        </td>
                        <td class="cap"><span class="mand">+</span> Postal Code</td>
                        <td colspan="3">
                          <h:inputText id="input_postalCode"
                                       label="Postal Code"
                                       styleClass="fld100"
                                       value="#{personalInfoSs.postalCode}"
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
                                       value="#{personalInfoSs.homePhone}"
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
                                       value="#{personalInfoSs.officePhone}"
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
                                       value="#{personalInfoSs.cellPhone}"
                                       maxlength="12"
                                       validatorMessage="#{appmsg.error_cellphone_invalid}">
                            <f:validator validatorId="phoneValidator" />
                          </h:inputText>
                          <br /><span class="smallText">999-999-9999</span>
                        </td>
                        <td class="cap">Pagers</td>
                        <td colspan="3">
                          <h:inputText id="input_pagerNumber"
                                       label="Pager Number"
                                       styleClass="fld150"
                                       value="#{personalInfoSs.pagerNumber}"
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
                          <h:commandButton id="button_back" action="#{mainPage.view}" value="Back" styleClass="btn btnbluePr btn100" />
                        </td>
                        <td colspan="5" style="text-align:right;">
                          <h:commandButton id="button_save" action="#{personalInfoSs.update}" value="Save" styleClass="btn btnorange btn100" />
                          &nbsp;
                          <h:commandButton id="button_next" action="#{educationalInfoSs.updateProfile}" value="Next" styleClass="btn btnblueNx btn100" />
                        </td>
                      </tr>
                      <tr>
                        <td colspan="6"><br />
                          To keep any changes made to your Personal Information, you must press &quot;<strong>Save</strong>&quot; before continuing.
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
