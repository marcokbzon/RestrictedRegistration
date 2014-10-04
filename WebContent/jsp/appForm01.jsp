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
      <h:form id="form_appForm01" >
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
                  <td class="ty"></td>
                  <td class="yl">&nbsp;Personal Info&nbsp;</td>
                  <td class="myg"></td>
                  <td class="gr">&nbsp;Education&nbsp;</td>
                  <td class="mgg"></td>
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
                  <td class="steps">1</td>
                  <td>
                    <h1>Personal Information</h1>
                  </td>
                </tr>
              </table>
              <table cellpadding="0" cellspacing="6" width="740" border="0">
                <tr>
                  <td class="cap">First Name</td>
                  <td class="capData"><h:outputText id="output_firstName" value="#{personalInfoRq.firstName}" /></td>
                  <td class="cap">Last Name</td>
                  <td colspan="3" class="capData"><h:outputText id="output_lastName" value="#{personalInfoRq.lastName}" /></td>
                </tr>
                <tr>
                  <td class="cap">Gender</td>
                  <td class="capData"><h:outputText id="output_gender" value="#{personalInfoRq.genderDesc}" /></td>
                </tr>
                <tr>
                  <td class="cap">Year of Birth</td>
                  <td class="capData"><h:outputText id="output_yob" value="#{personalInfoRq.yearOfBirth}" /></td>
                </tr>
                <tr>
                  <td class="cap">VISA Resident</td>
                  <td class="capData"><h:outputText id="output_visaResident" value="#{personalInfoRq.visaStudentIndicator}" /></td>
                </tr>
                <tr>
                  <td id="hrline" colspan="6"><span id="txtline">Home Address</span></td>
                </tr>
                <tr>
                  <td class="cap">Street #</td>
                  <td class="capData"><h:outputText id="output_streetNumber" value="#{personalInfoRq.streetNumber}" /></td>
                  <td class="cap">Street Name</td>
                  <td  class="capData" colspan="3"><h:outputText id="output_streetName" value="#{personalInfoRq.streetName}" /></td>
                </tr>
                <tr>
                  <td class="cap">Apt. #</td>
                  <td class="capData"><h:outputText id="output_aptNumber" value="#{personalInfoRq.aptNumber}" /></td>
                  <td class="cap">Country</td>
                  <td class="capData"><h:outputText id="output_countryCode" value="#{personalInfoRq.countryCode}" /></td>
                  <td class="cap">Province</td>
                  <td class="capData"><h:outputText id="output_provStateCode" value="#{personalInfoRq.provStateCode}" /></td>
                </tr>
                <tr>
                  <td class="cap">City</td>
                  <td class="capData"><h:outputText id="output_city" value="#{personalInfoRq.city}" /></td>
                  <td class="cap">Postal Code</td>
                  <td class="capData" colspan="3"><h:outputText id="output_postalCode" value="#{personalInfoRq.postalCode}" /></td>
                </tr>
                <tr>
                  <td id="hrline" colspan="6"><span id="txtline">Phone</span></td>
                </tr>
                <tr>
                  <td class="cap">Home</td>
                  <td class="capData"><h:outputText id="output_homePhone" value="#{personalInfoRq.homePhone}"/></td>
                  <td class="cap">Office</td>
                  <td class="capData" colspan="3"><h:outputText id="output_officePhone" value="#{personalInfoRq.officePhone}" /></td>
                </tr>
                <tr>
                  <td class="cap">Mobile</td>
                  <td class="capData"><h:outputText id="output_cellPhone" value="#{personalInfoRq.cellPhone}" /></td>
                  <td class="cap">Pager</td>
                  <td class="capData" colspan="3"><h:outputText id="output_pagerNumber" value="#{personalInfoRq.pagerNumber}" /></td>
                </tr>
                <tr>
                  <td id="hrline" colspan="6">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="2">
                    <h:commandButton id="button_previous" action="#{mainPage.view}" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td colspan="4" style="text-align:right;">
                    <h:commandButton id="button_edit" action="#{personalInfoSs.updateProfile}" value="Edit Profile" styleClass="btn btnorange btn120" />
                    &nbsp;
                    <h:commandButton id="button_next" action="#{educationalInfoRq.view}" value="Next" styleClass="btn btnblueNx btn100" />
                  </td>
                </tr>
                <tr>
                  <td colspan="6"><br />
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
