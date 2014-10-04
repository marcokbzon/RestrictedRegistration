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

  <body onLoad="setFocusOn( 'form_appForm04:input_lhin' );">
    <f:view>
      <h:form id="form_appForm04" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_appForm04:button_next' ); return false; }; return true;">
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
              Application Form : <i><h:outputText value="#{employerInfoSs.applicationID}" /></i>
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" immediate="true" action="#{mainPage.view}" /> &gt; Application Form</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="tb"></td>
                  <td class="bl">&nbsp;Personal Info&nbsp;</td>
                  <td class="mbb"></td>
                  <td class="bl">&nbsp;Education&nbsp;</td>
                  <td class="mbb"></td>
                  <td class="bl">&nbsp;Clinical&nbsp;</td>
                  <td class="mby"></td>
                  <td class="yl">&nbsp;Employer Info&nbsp;</td>
                  <td class="myg"></td>
                  <td class="gr">&nbsp;Agreement&nbsp;</td>
                  <td class="bg"></td>
                </tr>
              </table>
              <br />
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">4</td>
                  <td>
                    <h1>Employer Contact Info</h1>
                  </td>
                </tr>
              </table>
              <br />
              <table class="table_warning">
                <tr>
                  <td>You must <b>only create an application</b>, if you have previously contacted/engaged the Supervisor of the institution you are applying to and you have a conditional offer of employment ( includes an understanding of credentialing and remuneration )</td>
                </tr>
              </table>
              <br />
              <table cellpadding="2" cellspacing="1" width="740" border="0">
                <tr>
                  <td id="hrline" colspan="3"><span id="txtline">Employer Information</span></td>
                </tr>
                <tr>
                  <td colspan="3"> In order to process this application, the employing institution where you will be providing service under the Restricted Registration must provide certain information and assurances. Please provide the name and contact information of the appropriate individual at the employing institution to whom the request
                    should be sent.<br />
                    <br />
                  </td>
                </tr>
                <tr>
                  <td class="cap"><span class="mand">+</span> LHIN</td>
                  <td colspan="2">
                   <h:selectOneMenu id="input_lhin" immediate="true"
                                     value="#{employerInfoSs.lhinID}" valueChangeListener="#{employerInfoSs.changeLHIN}"
                                     onchange="javascript:submitAndReset( 'form_appForm04','form_appForm04:input_institution' ); return false;">
                      <f:selectItem itemValue="00" itemLabel="-- Select Geographic Area --"/>
                      <f:selectItems value="#{lhinBean.lhinList}" />
                    </h:selectOneMenu>
                  </td>
                </tr>
                <tr>
                  <td class="cap"><span class="mand">+</span> Institution</td>
                  <td colspan="2">
                    <h:selectOneMenu id="input_institution" immediate="false" value="#{employerInfoSs.institutionID}"
                                     valueChangeListener="#{employerInfoSs.changeInstitution}" onchange="this.form.submit();">
                      <f:selectItem itemValue="0000" itemLabel="-- Select Institution --"/>
                      <f:selectItems value="#{institutionBean.institutionList}" />
                    </h:selectOneMenu>
                  </td>
                </tr>
                <tr>
                  <td class="cap"><span class="mand">+</span> Service or Dept.</td>
                  <td colspan="2">
                    <h:selectOneMenu id="input_serviceType" value="#{employerInfoSs.serviceTypeID}">
                      <f:selectItem itemValue="000" itemLabel="-- Select Service Type --"/>
                      <f:selectItems value="#{serviceTypeBean.serviceTypeList}" />
                    </h:selectOneMenu>
                  </td>
                </tr>
                <tr>
                  <td class="cap"><span class="mand">+</span> Supervisor Name</td>
                  <td colspan="2">
                    <h:inputText id="input_supervisorName"
                                 label="Supervisor Name"
                                 styleClass="fld300"
                                 maxlength="30"
                                 value="#{employerInfoSs.supervisorName}" />
                  </td>
                </tr>
                <tr>
                  <td class="cap"><span class="mand">+</span> Supervisor Email</td>
                  <td colspan="2">
                    <h:inputText id="input_supervisorEmail"
                                 label="Supervisor Email"
                                 styleClass="fld300"
                                 maxlength="50"
                                 validatorMessage="#{appmsg.error_email_invalid}"
                                 value="#{employerInfoSs.supervisorEmail}">
                      <%---f:validator validatorId="emailValidator" /---%>
                    </h:inputText>
                  </td>
                </tr>
                <tr>
                  <td width="200" class="cap"><span class="mand">+</span> Supervisor Phone</td>
                  <td width="160">
                    <h:inputText id="input_supervisorPhone"
                                 label="Supervisor Phone"
                                 styleClass="fld150"
                                 maxlength="12"
                                 validatorMessage="#{appmsg.error_officephone_invalid}"
                                 value="#{employerInfoSs.supervisorPhone}">
                      <f:validator validatorId="phoneValidator" />
                    </h:inputText>
                  </td>
                  <td width="380">
                    <h:inputText id="input_supervisorExtension"
                                 label="Supervisor Extension"
                                 styleClass="fld75"
                                 maxlength="6"
                                 value="#{employerInfoSs.supervisorExtension}" />
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td><span class="smallText">999-999-9999</span></td>
                  <td><span class="smallText">Extension</span></td>
                </tr>
                <tr>
                  <td id="hrline" colspan="6">&nbsp;</td>
                </tr>
                <tr>
                  <td>
                    <h:commandButton id="button_previous" action="#{clinicalInfoRq.view}" immediate="true" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td colspan="2" style="text-align:right;">
                    <h:commandButton id="button_save" action="#{employerInfoSs.save}" value="Save" styleClass="btn btnorange btn100" />
                    &nbsp;
                    <h:commandButton id="button_next" action="#{agreementInfoSs.view}" value="Next" styleClass="btn btnblueNx btn100" />
                  </td>
                </tr>
                <tr>
                  <td colspan="3"><br />
                    To keep any changes made to your Employer Information, you must press &quot;<strong>Save</strong>&quot; before continuing.
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
