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

  <body>
    <f:view>
      <h:form id="form_appForm05" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_appForm05:button_previous' ); return false; }; return true;">
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
              Application Form : <i><h:outputText value="#{agreementInfoSs.applicationID}" /></i>
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" /> &gt; Application Form</div>
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
                  <td class="mbb"></td>
                  <td class="bl">&nbsp;Employer Info&nbsp;</td>
                  <td class="mby"></td>
                  <td class="yl">&nbsp;Agreement&nbsp;</td>
                  <td class="by"></td>
                </tr>
              </table>
              <br />
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">5</td>
                  <td>
                    <h1>Agreement / Undertaking</h1>
                  </td>
                </tr>
              </table>
              <table cellpadding="2" cellspacing="1" width="700" border="0">
                <tr>
                  <td>
                    <br />
                    Please place a check mark beside each statement below to indicate that you have read and agree to each.<br />
                    <br />
                  </td>
                </tr>
              </table>

              <table class="tbl_main">
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_ptcValue" value="#{agreementInfoSs.ptcValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.ptcDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_nccValue" value="#{agreementInfoSs.nccValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.nccDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_rasValue" value="#{agreementInfoSs.rasValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.rasDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_rriValue" value="#{agreementInfoSs.rriValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.rriDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_pwsValue" value="#{agreementInfoSs.pwsValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.pwsDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_ftcValue" value="#{agreementInfoSs.ftcValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.ftcDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_atiValue" value="#{agreementInfoSs.atiValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.atiDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_rtwValue" value="#{agreementInfoSs.rtwValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.rtwDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_pwtValue" value="#{agreementInfoSs.pwtValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.pwtDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_rucValue" value="#{agreementInfoSs.rucValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.rucDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_terValue" value="#{agreementInfoSs.terValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.terDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_nlaValue" value="#{agreementInfoSs.nlaValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.nlaDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_pidValue" value="#{agreementInfoSs.pidValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.pidDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">
                    <h:selectBooleanCheckbox id="input_iapValue" value="#{agreementInfoSs.iapValue}" />
                  </td>
                  <td class="tbl_col_663">
                    <h:outputText value="#{agreementInfoSs.iapDescription}"/>
                  </td>
                </tr>
                <tr class="tbl_oddAndEvenRow">
                  <td class="tbl_col_26">&nbsp;</td>
                  <td class="tbl_col_663">
                    Comments / Notes: <br/>
                    <h:inputTextarea id="textarea_comments" rows="3" cols="100" style="width:550px;" styleClass="fldtxt" value="#{agreementInfoSs.comments}" />
                  </td>
                </tr>
              </table>

              <table cellpadding="2" cellspacing="1" width="700" border="0">
                <!-- tr>
                    <td colspan="2">By signing, I hereby confirm that the information I have provided above is accurate and that I agree to the terms and conditions of the application.</td>
                </tr-->
                <tr>
                  <td id="hrline" colspan="2">&nbsp;</td>
                </tr>
                <tr>
                  <td width="344">
                    <h:commandButton id="button_previous" action="#{employerInfoSs.view}" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td width="345" style="text-align:right;">
                    <h:commandButton id="button_submit" action="#{agreementInfoSs.submit}" value="Submit" styleClass="btn btnorange btn120" />
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

