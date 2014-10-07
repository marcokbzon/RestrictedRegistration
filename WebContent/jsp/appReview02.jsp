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
      <h:form id="form_appReview02e" >
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
                <li id="chk"><h:commandLink id="link_review02" value="Supervisor" action="#{appReviewSupervisor.open}" rendered="#{tabNavigation.showTabSupervisor}" /></li>
                <li><h:commandLink id="link_review03" value="Program Director" action="#{appReviewDirector.open}" rendered="#{tabNavigation.showTabProgDirector}" /></li>
                <li><h:commandLink id="link_review04" value="PGME Dean" action="#{appReviewDean.open}" rendered="#{tabNavigation.showTabPgmeDean}" /></li>
                <li><h:commandLink id="link_review05" value="RR Academic Review Committee" action="#{appReviewCommittee.open}" rendered="#{tabNavigation.showTabCommittee}" /></li>
              </ul>
            </div>
            <div id="tblform">
              <table width="740" cellpadding="0" cellspacing="1">
                <tr>
                  <td width="740">
                    <div id="tblscroll">
                      <table cellpadding="2" cellspacing="1" width="100%" style="border:1px;">
                        <tr>
                          <td width="8%" class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_confirmAppliedYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.confirmApplied}" />
                            <h:graphicImage id="graphic_confirmAppliedNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.confirmApplied}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I hereby confirm that ( <strong><h:outputText value="#{reviewHeaderInfo.applicantName}" /></strong> ) has applied and been hired, pending receipt of a Restricted Registration from the College, to provide the services in the following domain of practice.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td colspan="5"> For more information on CPSO Supervision levels, <a href="http://www.cpso.on.ca/CPSO/media/documents/CPGs/Other/Supervision-Guidelines.pdf">click here</a>.<br />
                            For a summary of the goals and objectives of the Resident's rotations by specialty and school, <a href="http://www.restrictedregistrationontario.ca/Programs/Home%20Page">click here</a>.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td colspan="5">Please describe the types of duties the Resident may be performing in your employment:</td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td colspan="5" class="capDataWrap" >
                            <h:outputText id="text_dutiesDescription" value="#{appReviewSupervisor.dutiesDescription}" />
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_attestCredentialsYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.attestCredentials}" />
                            <h:graphicImage id="graphic_attestCredentialsNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.attestCredentials}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I hereby attest that we have verified the Residents credentials and are satisfied that he/she has the skills to perform the above activities.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_attestActivitiesYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.attestActivities}" />
                            <h:graphicImage id="graphic_attestActivitiesNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.attestActivities}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I hereby attest that the Residents activities will be restricted to the scope of services and sites listed above and authorized by the CPSO.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_abideToPairoYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.abideToPairo}" />
                            <h:graphicImage id="graphic_abideToPairoNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.abideToPairo}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I hereby agree to abide by the PAIRO/CAHO Collective Agreement.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_attestSupervisionYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.attestSupervision}" />
                            <h:graphicImage id="graphic_attestSupervisionNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.attestSupervision}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I hereby attest that the Resident will be working under supervision levels required by the registration. The supervision will be provided by 
                          </td>
                        </tr>
                        <tr>
                          <td class="cap">Dr.</td>
                          <td colspan="5" class="capData">
                            <h:outputText id="text_supervisorName" value="#{appReviewSupervisor.supervisorName}" />
                          </td>
                        </tr>
                        <tr>
                          <td class="cap">Phone</td>
                          <td width="34%" class="capData">
                            <h:outputText id="text_supervisorPhone" value="#{appReviewSupervisor.supervisorPhone}" />
                          </td>
                          <td width="13%" class="cap">Email</td>
                          <td width="45%" colspan="3" class="capData">
                            <h:outputText id="text_supervisorEmail" value="#{appReviewSupervisor.supervisorEmail}" />
                          </td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td colspan="5">Please describe in detail the type of supervision that will be provided:</td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td colspan="5" class="capDataWrap">
                            <h:outputText id="text_supervisionDescription" value="#{appReviewSupervisor.supervisionDescription}" />
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td colspan="6"><br /></td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_informCpsoYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.informCpso}" />
                            <h:graphicImage id="graphic_informCpsoNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.informCpso}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I hereby agree to inform the CPSO ( and the Residents Program Director ) of any unprofessional conduct or failure to abide by the terms of the Restricted Registration on the part of the Resident.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_issueCertificateYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.issueCertificate}" />
                            <h:graphicImage id="graphic_issueCertificateNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.issueCertificate}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I understand that the Resident may not begin the employment until the Resident has been issued a Restricted Certificate by the CPSO for this employment.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_confirmCertificateYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.confirmCertificate}" />
                            <h:graphicImage id="graphic_confirmCertificateNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.confirmCertificate}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I agree that before the Resident starts his/her employment the hospital will confirm with the CPSO that the Resident has been issued a Restricted Certificate of Registration for this purpose.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_notBeMRPYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.notBeMRP}" />
                            <h:graphicImage id="graphic_notBeMRPNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.notBeMRP}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I hereby attest that the Resident will not be the Most Responsible Physician ( MRP ).
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_provideInformationYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.provideInformation}" />
                            <h:graphicImage id="graphic_provideInformationNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.provideInformation}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	For purposes of tracking and evaluating the program on Restricted Registration for Residents, I agree to provide information and data as required by the program.
                            <br /><br />
                          </td>
                        </tr>
                        <tr>
                          <td class="baseline" style="text-align:right;">
                            <h:graphicImage id="graphic_informActivitiesYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.informActivities}" />
                            <h:graphicImage id="graphic_informActivitiesNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.informActivities}" />
                          </td>
                          <td colspan="5" style="padding:5px 0px 0px 0px;">
                                        	I agree to inform appropriate personnel within the healthcare site of the planned Restricted Registration activities.
                            <br /><br />
                          </td>
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
                          <td style="text-align:center;" colspan="6"><br />
                            <strong>
                              <h:outputText id="text_applicationConfirmationYes" value="I, #{appReviewSupervisor.approverName}, Confirm The Application" rendered="#{appReviewSupervisor.booleanApplicationConfirmation}" />&nbsp;
                              <h:outputText id="text_applicationConfirmationNo" value="I, #{appReviewSupervisor.approverName}, Don't Confirm The Application" rendered="#{not appReviewSupervisor.booleanApplicationConfirmation}" />&nbsp;
                              <h:graphicImage id="graphic_applicationConfirmationYes" url="/images/checkedYES.gif" rendered="#{appReviewSupervisor.booleanApplicationConfirmation}" />
                              <h:graphicImage id="graphic_applicationConfirmationNo" url="/images/checkedNO.gif" rendered="#{not appReviewSupervisor.booleanApplicationConfirmation}" />
                            </strong>
                          </td>
                        </tr>
                      </table>
                    </div>
                  </td>
                </tr>
              </table>
              <table width="740" cellpadding="0" cellspacing="1">
                <tr>
                  <td id="hrline20" colspan="6">&nbsp;</td>
                </tr>
                <tr>
                  <td style="padding:3px 0px 1px 10px;">
                    <h:commandButton id="button_previous" action="appReview01" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td style="text-align:right;padding:3px 10px 1px 0px;">
                    <h:commandButton id="button_next" action="#{appReviewDirector.open}" value="Next" styleClass="btn btnblueNx btn100" rendered="#{appReviewSupervisor.nextButton}" />
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
