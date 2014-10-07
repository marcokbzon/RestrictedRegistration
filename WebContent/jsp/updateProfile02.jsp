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

  <body onload="setFocusOn( 'form_updateProfile02:input_postGradYear' )">
    <f:view>
      <h:form id="form_updateProfile02" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_updateProfile02:button_next' ); return false; }; return true;">
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
                  <td class="mby"></td>
                  <td class="yl">&nbsp;Education&nbsp;</td>
                  <td class="myg"></td>
                  <td class="gr">&nbsp;Clinical&nbsp;</td>
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
                  <td class="cap">Postgraduate Year</td>
                  <td colspan="3">
                    <h:selectOneMenu id="input_postGradYear" value="#{educationalInfoSs.postGradYear}">
                      <f:selectItem itemValue="0" itemLabel="-- Select PG Year"/>
                      <f:selectItem itemValue="2" itemLabel="PGY2"/>
                      <f:selectItem itemValue="3" itemLabel="PGY3"/>
                      <f:selectItem itemValue="4" itemLabel="PGY4"/>
                      <f:selectItem itemValue="5" itemLabel="PGY5"/>
                      <f:selectItem itemValue="6" itemLabel="PGY6"/>
                      <f:selectItem itemValue="7" itemLabel="PGY7"/>
                      <f:selectItem itemValue="8" itemLabel="PGY8"/>
                      <f:selectItem itemValue="9" itemLabel="PGY9"/>
                    </h:selectOneMenu>
                  </td>
                </tr>
                <tr>
                  <td class="cap"><img src="/images/icoAlert.gif" alt="" /></td>
                  <td colspan="3">
                    Make sure you review the
                    <a href="http://www.restrictedregistrationontario.ca/deadlines.html" target="_blank">Prerequisites and Guidelines</a>
                    <br />for your specific school and program
                  </td>
                </tr>
                <tr>
                  <td class="cap">School of Residency</td>
                  <td colspan="3">
                    <h:selectOneMenu id="input_schoolResidency" immediate="true"
                                     value="#{educationalInfoSs.schoolResidency}" valueChangeListener="#{educationalInfoSs.changeSchool}"
                                     onchange="javascript:submitAndClear( 'form_updateProfile02','form_updateProfile02:input_pgProgram' ); return false;">
                      <f:selectItem itemValue="" itemLabel="-Select School-"/>
                      <f:selectItems value="#{schoolBean.schoolList}" />
                    </h:selectOneMenu>
                  </td>
                </tr>
                <tr>
                  <td class="cap">
                    <h:selectBooleanCheckbox id="input_northernStreamInd" value="#{educationalInfoSs.northernStreamIndicator}" />
                  </td>
                  <td colspan="3">
                    I am part of the Northern stream
                  </td>
                </tr>
                <tr>
                  <td class="cap">Postgraduate Program</td>
                  <td colspan="3">
                    <h:selectOneMenu id="input_pgProgram" value="#{educationalInfoSs.pgProgram}">
                      <f:selectItem itemValue="00000" itemLabel="-Select Program-"/>
                      <f:selectItems value="#{customProgramBean.programList}" />
                    </h:selectOneMenu>
                  </td>
                </tr>
                <tr>
                  <td class="cap">Undergrad. Medical School</td>
                  <td colspan="3">
                    <h:inputText id="input_medSchool"
                                 label="Medical School"
                                 styleClass="fld200"
                                 maxlength="40"
                                 value="#{educationalInfoSs.medSchool}" />
                  </td>
                </tr>
                <tr>
                  <td class="cap">&nbsp;</td>
                  <td colspan="3">
                    <h:selectOneMenu id="input_countryCode" value="#{educationalInfoSs.countryCode}">
                      <f:selectItem itemValue="000000" itemLabel="-Select Country-"/>
                      <f:selectItems value="#{countryBean.countryList}" />
                    </h:selectOneMenu>
                    <!--
                    &nbsp;
                    <h:selectOneMenu id="input_provStateCode" value="#{educationalInfoSs.provStateCode}">
                      <f:selectItem itemValue="00000" itemLabel="-Canadian Province-"/>
                      <f:selectItems value="#{provStateBean.provStateList}" />
                    </h:selectOneMenu>
                       	-->
                  </td>
                </tr>
                <tr>
                  <td id="hrline" colspan="4"><span id="txtline">Completed Exams / Certifications</span></td>
                </tr>
                <tr>
                  <td class="cap">ATLS</td>
                  <td width="160">
                    <h:selectBooleanCheckbox id="input_atlsInd" value="#{educationalInfoSs.atlsIndicator}" />
                    &nbsp;
                    <h:selectOneMenu id="input_atlsYear" value="#{educationalInfoSs.atlsYear}">
                      <f:selectItem itemValue="0" itemLabel="YYYY"/>
                      <f:selectItems value="#{examYearBean.yearList}" />
                    </h:selectOneMenu>
                  </td>
                  <td width="159" class="cap">ACLS</td>
                  <td width="256">
                    <h:selectBooleanCheckbox id="input_aclsInd" value="#{educationalInfoSs.aclsIndicator}" />
                    &nbsp;
                    <h:selectOneMenu id="input_aclsYear" value="#{educationalInfoSs.aclsYear}">
                      <f:selectItem itemValue="0" itemLabel="YYYY"/>
                      <f:selectItems value="#{examYearBean.yearList}" />
                    </h:selectOneMenu>
                  </td>
                </tr>
                <tr>
                  <td class="cap">PALS</td>
                  <td>
                    <h:selectBooleanCheckbox id="input_palsInd" value="#{educationalInfoSs.palsIndicator}" />
                    &nbsp;
                    <h:selectOneMenu id="input_palsYear" value="#{educationalInfoSs.palsYear}">
                      <f:selectItem itemValue="0" itemLabel="YYYY"/>
                      <f:selectItems value="#{examYearBean.yearList}" />
                    </h:selectOneMenu>
                  </td>
                  <td class="cap">NALS/NRP</td>
                  <td>
                    <h:selectBooleanCheckbox id="input_nalsInd" value="#{educationalInfoSs.nalsIndicator}" />
                    &nbsp;
                    <h:selectOneMenu id="input_nalsYear" value="#{educationalInfoSs.nalsYear}">
                      <f:selectItem itemValue="0" itemLabel="YYYY"/>
                      <f:selectItems value="#{examYearBean.yearList}" />
                    </h:selectOneMenu>
                  </td>
                </tr>
                <tr>
                  <td id="hrline" colspan="4"><span id="txtline">Eligibility</span></td>
                </tr>
                <tr>
                  <td class="cap">MCCQE1</td>
                  <td>
                    <h:selectOneMenu id="input_mccqe1Month" value="#{educationalInfoSs.mccqe1Month}">
                      <f:selectItem itemValue="00" itemLabel="MMM"/>
                      <f:selectItem itemValue="01" itemLabel="Jan"/>
                      <f:selectItem itemValue="02" itemLabel="Feb"/>
                      <f:selectItem itemValue="03" itemLabel="Mar"/>
                      <f:selectItem itemValue="04" itemLabel="Apr"/>
                      <f:selectItem itemValue="05" itemLabel="May"/>
                      <f:selectItem itemValue="06" itemLabel="Jun"/>
                      <f:selectItem itemValue="07" itemLabel="Jul"/>
                      <f:selectItem itemValue="08" itemLabel="Aug"/>
                      <f:selectItem itemValue="09" itemLabel="Sep"/>
                      <f:selectItem itemValue="10" itemLabel="Oct"/>
                      <f:selectItem itemValue="11" itemLabel="Nov"/>
                      <f:selectItem itemValue="12" itemLabel="Dec"/>
                    </h:selectOneMenu>
                       	-
                    <h:selectOneMenu id="input_mccqe1Year" value="#{educationalInfoSs.mccqe1Year}">
                      <f:selectItem itemValue="0000" itemLabel="YYYY"/>
                      <f:selectItems value="#{examYearBean.yearList}" />
                    </h:selectOneMenu>
                  </td>
                  <td class="cap">Current CPSO #</td>
                  <td>
                    <h:inputText id="input_cpsoNumber"
                                 label="CPSO Number"
                                 styleClass="fld100"
                                 maxlength="6"
                                 value="#{educationalInfoSs.cpsoNumber}" />
                  </td>
                </tr>
                <tr>
                  <td class="cap">MCCQE2</td>
                  <td>
                    <h:selectOneMenu id="input_mccqe2Month" value="#{educationalInfoSs.mccqe2Month}">
                      <f:selectItem itemValue="00" itemLabel="MMM"/>
                      <f:selectItem itemValue="01" itemLabel="Jan"/>
                      <f:selectItem itemValue="02" itemLabel="Feb"/>
                      <f:selectItem itemValue="03" itemLabel="Mar"/>
                      <f:selectItem itemValue="04" itemLabel="Apr"/>
                      <f:selectItem itemValue="05" itemLabel="May"/>
                      <f:selectItem itemValue="06" itemLabel="Jun"/>
                      <f:selectItem itemValue="07" itemLabel="Jul"/>
                      <f:selectItem itemValue="08" itemLabel="Aug"/>
                      <f:selectItem itemValue="09" itemLabel="Sep"/>
                      <f:selectItem itemValue="10" itemLabel="Oct"/>
                      <f:selectItem itemValue="11" itemLabel="Nov"/>
                      <f:selectItem itemValue="12" itemLabel="Dec"/>
                    </h:selectOneMenu>
                       	-
                    <h:selectOneMenu id="input_mccqe2Year" value="#{educationalInfoSs.mccqe2Year}">
                      <f:selectItem itemValue="0000" itemLabel="YYYY"/>
                      <f:selectItems value="#{examYearBean.yearList}" />
                    </h:selectOneMenu>
                  </td>
                  <td class="cap">CMPA #</td>
                  <td>
                    <h:inputText id="input_cmpaNumber"
                                 label="CMPA Number"
                                 styleClass="fld100"
                                 maxlength="8"
                                 value="#{educationalInfoSs.cmpaNumber}" />
                  </td>
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
                    <h:commandButton id="button_previous" action="#{personalInfoSs.updateProfile}" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td colspan="3" style="text-align:right;">
                    <h:commandButton id="button_save" action="#{educationalInfoSs.update}" value="Save" styleClass="btn btnorange btn100" />
                    &nbsp;
                    <h:commandButton id="button_next" action="#{clinicalInfoSs.updateProfile}" value="Next" styleClass="btn btnblueNx btn100" />
                  </td>
                </tr>
                <tr>
                  <td colspan="4"><br />
                    To keep any changes made to your Educational Information, you must press &quot;<strong>Save</strong>&quot; before continuing.
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
