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

  <body onLoad="setFocusOn( 'form_adminCreateMultiRole:input_roleId' )">
    <f:view>
      <h:form id="form_adminCreateMultiRole" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_adminCreateMultiRole:button_back' ); return false; }; return true;">
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
            <div id="titlepage1"> Create Reviewer Multi-Role
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
                        <td width="136" class="cap">Fullname</td>
                        <td width="294" class="capData"><h:outputText id="output_fullname" value="#{adminPersonalInfoMrRq.fullname}" /></td>
                      </tr>
                      <tr>
                        <td class="cap">Email</td>
                        <td class="capData"><h:outputText id="output_email" value="#{adminPersonalInfoMrRq.email}" /></td>
                      </tr>
                      <tr>
                        <td class="cap">Current Role</td>
                        <td class="capData"><h:outputText id="output_roleDescription" value="#{adminPersonalInfoMrRq.roleDescription}" /></td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> New Role</td>
                        <td>
                          <h:selectOneMenu id="input_roleId" immediate="true" value="#{adminPersonalInfoMrRq.roleID}"
                                           valueChangeListener="#{adminPersonalInfoMrRq.changeRole}"
                                           onchange="javascript:simpleSubmit( 'form_adminCreateMultiRole' ); return false;">
                            <f:selectItem itemValue="" itemLabel="-- Select a Role --"/>
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
                      <span class="mand">+</span> Mandatory field <br />
                      Two roles maximum per profile<br />
                      New role must be different from current role
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
                          <h:selectOneMenu id="input_universityId" value="#{adminPersonalInfoMrRq.universityID}"
                                           required="true" requiredMessage="#{appmsg.error_university_required}"
                                           disabled="#{adminPersonalInfoMrRq.enableUniversity}">
                            <f:selectItem itemValue="" itemLabel="-- Select a University --"/>
                            <f:selectItems value="#{schoolBean.schoolList}" />
                          </h:selectOneMenu>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Program</td>
                        <td>
                          <h:selectOneMenu id="input_programId" value="#{adminPersonalInfoMrRq.programID}"
                                           required="true" requiredMessage="#{appmsg.error_program_required}"
                                           disabled="#{adminPersonalInfoMrRq.enableProgram}">
                            <f:selectItem itemValue="" itemLabel="-- Select a Program --"/>
                            <f:selectItems value="#{programBean.programList}" />
                          </h:selectOneMenu>
                        </td>
                      </tr>
                      <tr>
                        <td class="cap"><span class="mand">+</span> Institution</td>
                        <td>
                          <h:selectOneMenu id="input_institutionId" value="#{adminPersonalInfoMrRq.institutionID}"
                                           required="true" requiredMessage="#{appmsg.error_institution_required}"
                                           disabled="#{adminPersonalInfoMrRq.enableInstitution}">
                            <f:selectItem itemValue="" itemLabel="-- Select an Institution --"/>
                            <f:selectItems value="#{institutionBean.institutionList}" />
                          </h:selectOneMenu>
                        </td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td id="hrline" colspan="2">&nbsp;</td>
                      </tr>
                      <tr>
                        <td>
                          <h:commandButton id="button_back" action="#{mainPage.view}" immediate="true" value="Back to Main" styleClass="btn btnbluePr btn300" />
                        </td>
                        <td>
                          <h:commandButton id="button_createProfile" action="#{adminPersonalInfoMrRq.create}" value="Create" styleClass="btn btnorange btn100" />
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
