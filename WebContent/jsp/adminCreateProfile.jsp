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

  <body onload="setFocusOn( 'form_adminCreateProfile:input_firstName' );">
    <f:view>
      <h:form id="form_adminCreateProfile" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_adminCreateProfile:button_back' ); return false; }; return true;">
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
            <div id="titlepage1"> Create Reviewer Profile
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" immediate="true" /> &gt; Profile Maintenance</div>
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
                                       value="#{adminPersonalInfoRq.firstName}"
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
                                       value="#{adminPersonalInfoRq.lastName}"
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
                                       value="#{adminPersonalInfoRq.email}"
                                       required="true"
                                       maxlength="50"
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
                                         value="#{adminPersonalInfoRq.password}"
                                         maxlength="10"
                                         required="true"
                                         requiredMessage="#{appmsg.error_password_required}" redisplay="true"/>
                          6 characters minimum</td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Confirm Password</td>
                        <td>
                          <h:inputSecret id="input_passwordConfirm"
                                         label="Confirm Password"
                                         styleClass="fld100"
                                         value="#{adminPersonalInfoRq.passwordConfirm}"
                                         maxlength="10"
                                         required="true"
                                         requiredMessage="#{appmsg.error_passwordconfirm_required}" redisplay="true"/>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Gender</td>
                        <td>
                          <h:selectOneRadio id="gender" value="#{adminPersonalInfoRq.gender}"
                                            required="true" requiredMessage="#{appmsg.error_gender_required}">
                            <f:selectItem itemValue="M" itemLabel="Male"/>
                            <f:selectItem itemValue="F" itemLabel="Female"/>
                          </h:selectOneRadio>
                        </td>
                      </tr>
                      <%--- PAIRO ---%>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Role</td>
                        <td>
                          <h:selectOneMenu id="input_roleId" immediate="true" value="#{adminPersonalInfoRq.roleID}"
                                           valueChangeListener="#{adminPersonalInfoRq.changeRole}"
                                           onchange="javascript:simpleSubmit( 'form_adminCreateProfile' ); return false;">
                            <f:selectItem itemValue="000" itemLabel="-- Select a Role --"/>
                            <f:selectItem itemValue="ISV" itemLabel="Supervisor"/>
                            <f:selectItem itemValue="UPD" itemLabel="Program Director"/>
                            <f:selectItem itemValue="UDN" itemLabel="PGME Dean"/>
                            <f:selectItem itemValue="UMC" itemLabel="Committee Member"/>
                            <f:selectItem itemValue="ADM" itemLabel="System Administrator"/>
                          </h:selectOneMenu>
                        </td>
                      </tr>
                    </table>
                  </td>
                  <td><img src="/images/spacer.gif" width="20" height="1" alt="" /></td>
                  <td valign="top">
                    <div id="notes">
                      <h4>Note:</h4>
                      <span class="mand">+</span> Mandatory field
                    </div>
                  </td>
                </tr>
                <tr>
                  <td colspan="3">
                    <table cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td width="134"><img src="/images/spacer.gif" width="136" height="20" alt="" /></td>
                        <td width="589"><img src="/images/spacer.gif" width="590" height="20" alt="" /></td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="2"><span id="txtline">Work</span></td>
                      </tr>
                      <tr>
                        <td class="cap">
                          <span class="mand">+</span> University</td>
                        <td>
                          <h:selectOneMenu id="input_universityId" value="#{adminPersonalInfoRq.universityID}"
                                           required="true" requiredMessage="#{appmsg.error_university_required}"
                                           disabled="#{adminPersonalInfoRq.enableUniversity}">
                            <f:selectItem itemValue="" itemLabel="-- Select a University --"/>
                            <f:selectItems value="#{schoolBean.schoolList}" />
                          </h:selectOneMenu>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Program</td>
                        <td>
                          <h:selectOneMenu id="input_programId" value="#{adminPersonalInfoRq.programID}"
                                           required="true" requiredMessage="#{appmsg.error_program_required}"
                                           disabled="#{adminPersonalInfoRq.enableProgram}">
                            <f:selectItem itemValue="" itemLabel="-- Select a Program --"/>
                            <f:selectItems value="#{programBean.programList}" />
                          </h:selectOneMenu>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Institution</td>
                        <td>
                          <h:selectOneMenu id="input_institutionId" value="#{adminPersonalInfoRq.institutionID}"
                                           required="true" requiredMessage="#{appmsg.error_institution_required}"
                                           disabled="#{adminPersonalInfoRq.enableInstitution}">
                            <f:selectItem itemValue="" itemLabel="-- Select an Institution --"/>
                            <f:selectItems value="#{institutionBean.institutionList}" />
                          </h:selectOneMenu>
                        </td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><h:selectBooleanCheckbox id="input_overrideInd" value="#{adminPersonalInfoRq.overrideIndicator}" /> Override Existing Role Assignments (only for University Reviewers)</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="2"><span id="txtline">Phone</span></td>
                      </tr>
                      <tr>
                        <td class="cap">Office</td>
                        <td>
                          <h:inputText id="input_officePhone"
                                       label="Office Phone"
                                       styleClass="fld150"
                                       maxlength="12"
                                       value="#{adminPersonalInfoRq.officePhone}"
                                       validatorMessage="#{appmsg.error_officephone_invalid}">
                            <f:validator validatorId="phoneValidator" />
                          </h:inputText>
                          <br />
                          <span class="smallText">999-999-9999</span></td>
                      </tr>
                      <tr>
                        <td class="cap">Pager</td>
                        <td>
                          <h:inputText id="input_pagerNumber"
                                       label="Pager Number"
                                       styleClass="fld150"
                                       maxlength="12"
                                       value="#{adminPersonalInfoRq.pagerNumber}"
                                       validatorMessage="#{appmsg.error_pagernumber_invalid}">
                            <f:validator validatorId="phoneValidator" />
                          </h:inputText>
                          <br />
                          <span class="smallText">999-999-9999</span></td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="2">&nbsp;</td>
                      </tr>
                      <tr>
                        <td>
                          <h:commandButton id="button_back" action="#{mainPage.view}" immediate="true" value="Back" styleClass="btn btnbluePr btn100" />
                        </td>
                        <td>
                          <h:commandButton id="button_createProfile" action="#{adminPersonalInfoRq.create}" value="Create" styleClass="btn btnorange btn100" />
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2"><br />
                          When creating a Program Director: Universities and Programs will be automatically associated. Residents will only have access to the
                          Programs that have been assigned a Program Director.
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
