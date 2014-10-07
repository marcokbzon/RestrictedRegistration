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

  <body>
    <f:view>
      <h:form id="form_appForm02" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_appForm02:button_next' ); return false; }; return true;">
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
              Application Form
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" /> &gt; Application Form</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="tb"></td>
                  <td class="bl">&nbsp;Personal Info&nbsp;</td>
                  <td class="mby"></td>
                  <td class="yl">&nbsp;Education&nbsp;</td>
                  <td class="myg"></td>
                  <td class="gr">&nbsp;Clinical&nbsp;</td>
                  <td class="mgg"></td>
                  <td class="gr">&nbsp;Employer Info&nbsp;</td>
                  <td class="mgg"></td>
                  <td class="gr">&nbsp;Agreement&nbsp;</td>
                  <td class="bg"></td>
                </tr>
              </table>
              <br />
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">2</td>
                  <td>
                    <h1>Education</h1>
                  </td>
                </tr>
              </table>
              <table cellpadding="1" cellspacing="5" width="740" border="0">
                <tr>
                  <td id="hrline" colspan="4"><span id="txtline">Educational Background</span></td>
                </tr>
                <tr>
                  <td class="cap">Postgraduate Year<br />
                    ( at RR start date )</td>
                  <td colspan="3" class="capData"><h:outputText id="output_postGradYear" value="#{educationalInfoRq.postGradYear}" /></td>
                </tr>
                <tr>
                  <td class="cap">School Residency</td>
                  <td colspan="3" class="capData"><h:outputText id="output_schoolResidency" value="#{educationalInfoRq.schoolResidency}" /></td>
                </tr>
                <tr>
                  <td class="cap">Northern Stream</td>
                  <td colspan="3" class="capData"><h:outputText id="output_northernStreamInd" value="#{educationalInfoRq.northernStreamIndicator}" /></td>
                </tr>
                <tr>
                  <td class="cap">Postgraduate Program</td>
                  <td colspan="3" class="capData"><h:outputText id="output_pgProgram" value="#{educationalInfoRq.pgProgram}" /></td>
                </tr>
                <tr>
                  <td class="cap">Program Director</td>
                  <td colspan="3" class="capData"><h:outputText id="output_programDirector" value="#{educationalInfoRq.programDirector}" /></td>
                </tr>
                <tr>
                  <td class="cap">Medical School</td>
                  <td colspan="3" class="capData">
                    <h:outputText id="output_medSchool" value="#{educationalInfoRq.medSchool}" />
                    <br />
                    <h:outputText id="output_countryCode" value="#{educationalInfoRq.countryName}" />
                  </td>
                </tr>
                <tr>
                  <td id="hrline" colspan="4"><span id="txtline">Completed Exams / Certifications</span></td>
                </tr>
                <tr>
                  <td class="cap">ATLS</td>
                  <td width="138" class="capData">
                    <h:outputText id="output_atlsInd" value="#{educationalInfoRq.atlsIndicator}" />
                    <h:outputText id="output_atlsYear" value="#{educationalInfoRq.atlsYear}" />
                  </td>
                  <td width="153" class="cap">ACLS</td>
                  <td width="284" class="capData">
                    <h:outputText id="output_aclsInd" value="#{educationalInfoRq.aclsIndicator}" />
                    <h:outputText id="output_aclsYear" value="#{educationalInfoRq.aclsYear}" />
                  </td>
                </tr>
                <tr>
                  <td class="cap">PALS</td>
                  <td class="capData">
                    <h:outputText id="output_palsInd" value="#{educationalInfoRq.palsIndicator}" />
                    <h:outputText id="output_palsYear" value="#{educationalInfoRq.palsYear}" />
                  </td>
                  <td class="cap">NALS/NRP</td>
                  <td class="capData">
                    <h:outputText id="output_nalsInd" value="#{educationalInfoRq.nalsIndicator}" />
                    <h:outputText id="output_nalsYear" value="#{educationalInfoRq.nalsYear}" />
                  </td>
                </tr>
                <tr>
                  <td id="hrline" colspan="4"><span id="txtline">Eligibility</span></td>
                </tr>
                <tr>
                  <td class="cap">MCCQE1</td>
                  <td class="capData"><h:outputText id="output_mccqe1Month" value="#{educationalInfoRq.mccqe1Month}" />&nbsp;<h:outputText id="output_mccqe1Year" value="#{educationalInfoRq.mccqe1Year}" /></td>
                  <td class="cap">Current CPSO #</td>
                  <td class="capData"><h:outputText id="output_cpsoNumber" value="#{educationalInfoRq.cpsoNumber}" /></td>
                </tr>
                <tr>
                  <td class="cap">MCCQE2</td>
                  <td class="capData"><h:outputText id="output_mccqe2Month" value="#{educationalInfoRq.mccqe2Month}" />&nbsp;<h:outputText id="output_mccqe2Year" value="#{educationalInfoRq.mccqe2Year}" /></td>
                  <td class="cap">CMPA #</td>
                  <td class="capData"><h:outputText id="output_cmpaNumber" value="#{educationalInfoRq.cmpaNumber}" /></td>
                </tr>
                <tr>
                  <td colspan="4">
                    <br />
                    <table>
                      <tr>
                        <td width="1%" valign="top">*</td>
                        <td>
                          Once approved, you will be required to obtain a TOW code 14 (trainee with moonlighting) with the The Canadian Medical Protective Association (CMPA) before being eligible to work                                </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td id="hrline" colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td width="132">
                    <h:commandButton id="button_previous" action="#{personalInfoRq.view}" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td colspan="3" style="text-align:right;">
                    <h:commandButton id="button_edit" action="#{personalInfoSs.updateProfile}" value="Edit Profile" styleClass="btn btnorange btn120" />
                    &nbsp;
                    <h:commandButton id="button_next" action="#{clinicalInfoRq.view}" value="Next" styleClass="btn btnblueNx btn100" />
                  </td>
                </tr>
                <tr>
                  <td colspan="4"><br />
                    To modify the information displayed in this page, you must press &quot;<strong>Edit Profile</strong>&quot;
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
