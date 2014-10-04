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
      <h:form id="form_appReview05">
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
                <li><h:commandLink id="link_review03" value="Program Director" action="#{appReviewDirector.open}" rendered="#{tabNavigation.showTabProgDirector}" /></li>
                <li><h:commandLink id="link_review04" value="PGME Dean" action="#{appReviewDean.open}" rendered="#{tabNavigation.showTabPgmeDean}" /></li>
                <li id="chk"><h:commandLink id="link_review05" value="RR Academic Review Committee" action="#{appReviewCommittee.open}" rendered="#{tabNavigation.showTabCommittee}" /></li>
              </ul>
            </div>
            <div id="tblform">
              <table width="740" cellpadding="0" cellspacing="1">
                <tr>
                  <td width="700" style="padding:10px;">
                    <table cellpadding="2" cellspacing="1" width="100%" style="border:0px;">
                      <tr>
                        <td class="baseline" style="text-align:right;">
                          <h:graphicImage id="graphic_attestRequirementsYes" url="/images/checkedYES.gif" rendered="#{appReviewCommittee.attestRequirements}" />
                          <h:graphicImage id="graphic_attestRequirementsNo" url="/images/checkedNO.gif" rendered="#{not appReviewCommittee.attestRequirements}" />
                        </td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">The Committee has reviewed the application and attests that it is complete and meets the requirements of the program.<br />
                          <br />
                        </td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">&nbsp;</td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;">Comments about this application:</td>
                      </tr>
                      <tr>
                        <td class="baseline" style="text-align:right;">&nbsp;</td>
                        <td colspan="5" style="padding:5px 0px 0px 0px;" class="capDataWrap"><h:outputText id="textarea_comments" value="#{appReviewCommittee.comments}" /></td>
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
                            <h:outputText id="text_applicationConfirmationYes" value="I, #{appReviewCommittee.approverName}, Confirm The Application" rendered="#{appReviewCommittee.booleanApplicationConfirmation}" />&nbsp;
                            <h:outputText id="text_applicationConfirmationNo" value="I, #{appReviewCommittee.approverName}, Don't Confirm The Application" rendered="#{not appReviewCommittee.booleanApplicationConfirmation}" />&nbsp;
                            <h:graphicImage id="graphic_applicationConfirmationYes" url="/images/checkedYES.gif" rendered="#{appReviewCommittee.booleanApplicationConfirmation}" />
                            <h:graphicImage id="graphic_applicationConfirmationNo" url="/images/checkedNO.gif" rendered="#{not appReviewCommittee.booleanApplicationConfirmation}" />
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
                    <h:commandButton action="#{appReviewDean.open}" value="Previous" styleClass="btn btnbluePr btn120" />
                  </td>
                  <td style="text-align:right;padding:3px 10px 1px 0px;">
                    <h:commandButton id="button_next" action="appReviewList" value="Back To List" styleClass="btn btnblueNx btn140" />
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
