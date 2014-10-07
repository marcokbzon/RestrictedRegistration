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
      <h:form id="form_appForm03" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_appForm03:button_next' ); return false; }; return true;">
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
                  <td class="mbb"></td>
                  <td class="bl">&nbsp;Education&nbsp;</td>
                  <td class="mby"></td>
                  <td class="yl">&nbsp;Clinical&nbsp;</td>
                  <td class="myg"></td>
                  <td class="gr">&nbsp;Employer Info&nbsp;</td>
                  <td class="mgg"></td>
                  <td class="gr">&nbsp;Agreement&nbsp;</td>
                  <td class="bg"></td>
                </tr>
              </table>
              <br />
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">3</td>
                  <td>
                    <h1>Clinical Rotations Completed To Date</h1>
                  </td>
                </tr>
              </table>
              <table cellpadding="2" cellspacing="1" width="740" border="0">
                <tr>
                  <td id="hrline">&nbsp;</td>
                </tr>
              </table>

              <h:dataTable id="table_rotations" value="#{rotationBean.rotationDM}" var="rList"
                           styleClass="tbl_main" headerClass="tbl_hdr"
                           rowClasses="tbl_oddRow, tbl_evenRow" columnClasses="tbl_col_420 , tbl_col_136, tbl_col_136">
                <h:column>
                  <f:facet name="header"><h:outputText value="Service Type" /></f:facet>
                  <h:outputText value="#{rList.serviceType}"/>
                </h:column>

                <h:column>
                  <f:facet name="header">
                    <h:panelGroup>
                      <h:outputText value="Rotations:" />
                      <br />
                      <h:outputText value="# of rotations" />
                    </h:panelGroup>
                  </f:facet>
                  <h:outputText value="#{rList.rotations}"/>
                </h:column>

                <h:column>
                  <f:facet name="header">
                    <h:panelGroup>
                      <h:outputText value="Duration:" />
                      <br />
                      <h:outputText value="total # of weeks" />
                    </h:panelGroup>
                  </f:facet>
                  <h:outputText value="#{rList.weeksTotal}"/>
                </h:column>
              </h:dataTable>

              <table cellpadding="2" cellspacing="1" width="740" border="0">
                <tr>
                  <td id="hrline" colspan="2">&nbsp;</td>
                </tr>
                <tr>
                  <td width="306">
                    <h:commandButton id="button_previous" action="#{educationalInfoRq.view}" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td width="424" style="text-align:right;">
                    <h:commandButton id="button_edit" action="#{personalInfoSs.updateProfile}" value="Edit Profile" styleClass="btn btnorange btn120" />
                    &nbsp;
                    <h:commandButton id="button_next" action="#{employerInfoSs.view}" value="Next" styleClass="btn btnblueNx btn100" />
                  </td>
                </tr>
                <tr>
                  <td colspan="2"><br />
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
