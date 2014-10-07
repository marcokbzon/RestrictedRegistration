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
      <h:form id="form_appReview03">
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
          <div id="separ2"></div>
          <div id="glocontainer1">
            <div id="titlepage1"> Agreement / Undertaking
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" /> &gt; Application Review</div>
            <hr noshade color="#c8cbd0" size="1" />
            <table cellpadding="2" cellspacing="1" width="740">
              <tr>
                <td width="97" class="cap">Resident: </td>
                <td width="218" class="capData"><h:outputText value="#{reviewHeaderInfo.applicantName}" /></td>
                <td width="94" class="cap">App.Date: </td>
                <td width="308" class="capData"><h:outputText value="#{reviewHeaderInfo.applicationDate}" /></td>
              </tr>
              <tr>
                <td class="cap">Institution: </td>
                <td class="capData"><h:outputText value="#{reviewHeaderInfo.university}" /></td>
                <td class="cap">Employer: </td>
                <td class="capData"><h:outputText value="#{reviewHeaderInfo.institution}" /></td>
              </tr>
              <tr>
                <td class="cap">&nbsp;</td>
                <td class="capData">&nbsp;</td>
                <td class="cap">Service: </td>
                <td class="capData"><h:outputText value="#{reviewHeaderInfo.serviceType}" /></td>
              </tr>
            </table>
            <div id="navinside">
              <ul>
                <li><h:commandLink id="link_review01" value="Application" action="#{appReviewInfo.view}" /></li>
                <li><h:commandLink id="link_review02" value="Supervisor" action="#{appReviewSupervisor.open}" rendered="#{tabNavigation.showTabSupervisor}" /></li>
                <li id="chk"><h:commandLink id="link_review03" value="Program Director" action="#{appReviewDirector.open}" rendered="#{tabNavigation.showTabProgDirector}" /></li>
                <li><h:commandLink id="link_review04" value="PGME Dean" action="#{appReviewDean.open}" rendered="#{tabNavigation.showTabPgmeDean}" /></li>
                <li><h:commandLink id="link_review05" value="RR Academic Review Committee" action="#{appReviewCommittee.open}" rendered="#{tabNavigation.showTabCommittee}" /></li>
              </ul>
            </div>
            <div id="tblform">
              <table width="740" cellpadding="0" cellspacing="1">
                <tr>
                  <td width="700" style="padding:10px;">
                    <table cellpadding="2" cellspacing="1" width="100%" style="border:0px;">
                      <tr>
                        <td class="baseline" style="text-align:right;">
                          <h:graphicImage id="graphic_correctInformationYes" url="/images/checkedYES.gif" rendered="#{appReviewDirector.correctInformation}" />
                          <h:graphicImage id="graphic_correctInformationNo" url="/images/checkedNO.gif" rendered="#{not appReviewDirector.correctInformation}" />
                        </td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">I attest that the information provided by the Resident, <strong><h:outputText value="#{reviewHeaderInfo.applicantName}" /></strong>, on his/her training in the attached application is correct.<br />
                          <br />
                        </td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">
                          <h:graphicImage id="graphic_goodAcademicYes" url="/images/checkedYES.gif" rendered="#{appReviewDirector.goodAcademic}" />
                          <h:graphicImage id="graphic_goodAcademicNo" url="/images/checkedNO.gif" rendered="#{not appReviewDirector.goodAcademic}" />
                        </td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">I attest that the Resident is currently in good academic standing.<br />
                          <br />
                        </td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">
                          <h:graphicImage id="graphic_attestEligibilityYes" url="/images/checkedYES.gif" rendered="#{appReviewDirector.attestEligibility}" />
                          <h:graphicImage id="graphic_attestEligibilityNo" url="/images/checkedNO.gif" rendered="#{not appReviewDirector.attestEligibility}" />
                        </td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">I attest that the Resident has successfully completed the rotations required to work in the settings outlined in Section A(5) in which s/he is registered.<br />
                          <br />
                        </td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">
                          <h:graphicImage id="graphic_notifyCpsoYes" url="/images/checkedYES.gif" rendered="#{appReviewDirector.notifyCpso}" />
                          <h:graphicImage id="graphic_notifyCpsoNo" url="/images/checkedNO.gif" rendered="#{not appReviewDirector.notifyCpso}" />
                        </td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">I agree that should the activities engaged in by the Resident under the Restricted Registration conflict with the requirements of the residency program, or should the activities negatively impact the Resident's academic/clinical obligations, or should the Resident experience any problems with performance generally, whether or not related to the Restricted Registration, I will withdraw my approval for the Restricted Registration in which case, the Resident's Restricted Registration Certificate will automatically expire.<br />
                          <br />
                        </td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">
                          <h:graphicImage id="graphic_provideInformationYes" url="/images/checkedYES.gif" rendered="#{appReviewDirector.provideInformation}" />
                          <h:graphicImage id="graphic_provideInformationNo" url="/images/checkedNO.gif" rendered="#{not appReviewDirector.provideInformation}" />
                        </td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">For purposes of tracking and evaluating the program on Restricted Registration for Residents, I agree to provide information and data as required by the program.<br />
                          <br />
                        </td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">&nbsp;</td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">Comments about this application:</td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">&nbsp;</td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;" class="capDataWrap"><h:outputText id="textarea_comments" value="#{appReviewDirector.comments}" /></td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">&nbsp;</td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">Supporting documentation:</td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">&nbsp;</td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;" class="capDataWrap">
                          <%
                                if (request.getSession().getAttribute("DocsAvailable").equals("Y")) {
                                  out.write((String) request.getSession().getAttribute("DocLinks"));
                                } else {
                                  out.print("No documents have been attached to this Application");
                                }
                          %>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="6" style="text-align:center;">
                          <strong>
                            <h:outputText id="text_applicationConfirmationYes" value="I, #{appReviewDirector.approverName}, Confirm The Application" rendered="#{appReviewDirector.booleanApplicationConfirmation}" />&nbsp;
                            <h:outputText id="text_applicationConfirmationNo" value="I, #{appReviewDirector.approverName}, Don't Confirm The Application" rendered="#{not appReviewDirector.booleanApplicationConfirmation}" />&nbsp;
                            <h:graphicImage id="graphic_applicationConfirmationYes" url="/images/checkedYES.gif" rendered="#{appReviewDirector.booleanApplicationConfirmation}" />
                            <h:graphicImage id="graphic_applicationConfirmationNo" url="/images/checkedNO.gif" rendered="#{not appReviewDirector.booleanApplicationConfirmation}" />
                          </strong>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
              <table width="740" cellpadding="0" cellspacing="1">
                <tr>
                  <td id="hrline20" colspan="6">&nbsp;</td>
                </tr>
                <tr>
                  <td style="padding:3px 0px 1px 10px;">
                    <h:commandButton action="#{appReviewSupervisor.open}" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td style="text-align:right;padding:3px 10px 1px 0px;">
                    <h:commandButton id="button_next" action="#{appReviewDean.open}" value="Next" styleClass="btn btnblueNx btn100" rendered="#{appReviewDirector.nextButton}" />
                  </td>
                </tr>
              </table>
            </div>
            <div id="tbl1b" style="width:740px;margin-left:auto; margin-right:auto;">
              <div id="tbl2b"><img src="/images/spacer.gif" height="10" border="0" alt="" /></div>
            </div>
            <br />
            <jsp:include page="/jsp/footer.inc" />
          </div>
        </div>
      </h:form>
    </f:view>
  </body>
</html>
