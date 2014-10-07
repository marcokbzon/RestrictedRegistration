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
      <h:form id="form_appReview01" >
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
            <div id="titlepage1">
              Agreement / Undertaking
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
                <li id="chk"><h:commandLink id="link_review01" value="Application" action="#{appReviewInfo.view}" /></li>
                <li><h:commandLink id="link_review02" value="Supervisor" action="#{appReviewSupervisor.open}" rendered="#{tabNavigation.showTabSupervisor}" /></li>
                <li><h:commandLink id="link_review03" value="Program Director" action="#{appReviewDirector.open}" rendered="#{tabNavigation.showTabProgDirector}" /></li>
                <li><h:commandLink id="link_review04" value="PGME Dean" action="#{appReviewDean.open}" rendered="#{tabNavigation.showTabPgmeDean}" /></li>
                <li><h:commandLink id="link_review05" value="RR Academic Review Committee" action="#{appReviewCommittee.open}" rendered="#{tabNavigation.showTabCommittee}" /></li>
              </ul>
            </div>
            <div id="tblform">
              <table width="740" cellpadding="0" cellspacing="1">
                <tr>
                  <td>
                    <div id="tblscroll">
                      <h4>Personal Info</h4>
                      <strong><h:outputText value="#{reviewHeaderInfo.applicantName}" /></strong><br />
                      <h:outputText value="#{appReviewInfo.address}" /><br />
                      <h:outputText value="#{appReviewInfo.city}" />, <h:outputText value="#{appReviewInfo.province}" />, <h:outputText value="#{appReviewInfo.postalCode}" /><br />
                      <h:outputText value="#{appReviewInfo.country}" /><br />
                      <br />
                      Email: <a href="mailto:<h:outputText value='#{appReviewInfo.email}' />"><h:outputText value="#{appReviewInfo.email}" /></a><br />
                      Phone: <h:outputText value="#{appReviewInfo.homePhone}" /><br />
                      Pager: <h:outputText value="#{appReviewInfo.pagerNumber}" /><br />
                      <br />
                      <h4>Educational Background</h4>
                      <table cellpadding="4" cellspacing="1" width="90%" style="border:0px;">
                        <tr>
                          <td colspan="3"><em><strong>Postgraduate year:</strong></em><br />
                          </td>
                        </tr>
                        <tr>
                          <td>PGY<h:outputText value="#{appReviewInfo.pgYear}" /></td>
                          <td>ATLS: <h:outputText value="#{appReviewInfo.atlsIndicator}" /></td>
                          <td>Date obtained: <h:outputText value="#{appReviewInfo.atlsYear}" /></td>
                        </tr>
                        <tr>
                          <td colspan="3"><em><strong>School of Residency:</strong></em><br /></td>
                        </tr>
                        <tr>
                          <td><h:outputText value="#{reviewHeaderInfo.university}" /></td>
                          <td>ACLS: <h:outputText value="#{appReviewInfo.aclsIndicator}" /></td>
                          <td>Date obtained: <h:outputText value="#{appReviewInfo.aclsYear}" /></td>
                        </tr>
                        <tr>
                          <td>Northern Stream: <h:outputText value="#{appReviewInfo.northernStream}" /></td>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td colspan="3"><em><strong>Postgraduate Program:</strong></em><br /></td>
                        </tr>
                        <tr>
                          <td><h:outputText value="#{appReviewInfo.pgProgram}" /></td>
                          <td>PALS: <h:outputText value="#{appReviewInfo.palsIndicator}" /></td>
                          <td>Date obtained: <h:outputText value="#{appReviewInfo.palsYear}" /></td>
                        </tr>
                        <tr>
                          <td colspan="3"><em><strong>Medical School:</strong></em><br /></td>
                        </tr>
                        <tr>
                          <td><h:outputText value="#{appReviewInfo.medicalSchool}" /></td>
                          <td>NALS/NRP: <h:outputText value="#{appReviewInfo.nalsIndicator}" /></td>
                          <td>Date obtained: <h:outputText value="#{appReviewInfo.nalsYear}" /></td>
                        </tr>
                        <tr>
                          <td colspan="3"><em><strong>Program Director:</strong></em><br /></td>
                        </tr>
                        <tr>
                          <td colspan="3"><h:outputText value="#{appReviewInfo.progDirector}" />
                            <br /><a href="mailto:<h:outputText value='#{appReviewInfo.progDirEmail}' />"><h:outputText value="#{appReviewInfo.progDirEmail}" /></a>
                          </td>
                        </tr>
                        <tr>
                          <td colspan="3">
                            <hr size="1" color="#808080" noshade />
                            For a summary of the goals and objectives of the Resident's rotations by specialty and school, <a href="http://www.restrictedregistrationontario.ca/Programs/Home%20Page" target="_blank">click here</a>.
                          </td>
                        </tr>
                      </table>
                      <br />
                      <br />
                      <h4>Eligibility</h4>
                      <table width="30%" style="border:0px;">
                        <tr>
                          <td>MCCQE1:</td>
                          <td><h:outputText value="#{appReviewInfo.mccqe1Date}" /></td>
                        </tr>
                        <tr>
                          <td>MCCQE2:</td>
                          <td><h:outputText value="#{appReviewInfo.mccqe2Date}" /></td>
                        </tr>
                        <tr>
                          <td>Current CPSO #:</td>
                          <td><h:outputText value="#{appReviewInfo.cpsoNumber}" /></td>
                        </tr>
                        <tr>
                          <td>CMPA #:</td>
                          <td><h:outputText value="#{appReviewInfo.cmpaNumber}" /></td>
                        </tr>
                      </table>
                      <br />
                      <br />
                      <h4>Rotation History</h4>
                      <table style="margin-left:0px;border-collapse: collapse;border-left:0px;border-right:0px;">
                        <tr>
                          <td>
                            <h:dataTable id="table_rotations" value="#{rotationBean.rotationDM}" var="rList"
                                         styleClass="tbl_main4" headerClass="tbl_hdr"
                                         rowClasses="tbl_oddRow, tbl_evenRow" columnClasses="tbl_col_238 , tbl_col_120, tbl_col_120">
                              <h:column>
                                <f:facet name="header"><h:outputText value="Service Type" /></f:facet>
                                <h:outputText value="#{rList.serviceType}"/>
                              </h:column>

                              <h:column>
                                <f:facet name="header">
                                  <h:panelGroup>
                                    <h:outputText value="Number" />
                                    <br />
                                    <h:outputText value="of Rotations" />
                                  </h:panelGroup>
                                </f:facet>
                                <h:outputText value="#{rList.rotations}"/>
                              </h:column>

                              <h:column>
                                <f:facet name="header">
                                  <h:panelGroup>
                                    <h:outputText value="Total" />
                                    <br />
                                    <h:outputText value="# of weeks" />
                                  </h:panelGroup>
                                </f:facet>
                                <h:outputText value="#{rList.weeksTotal}"/>
                              </h:column>
                            </h:dataTable>
                          </td>
                        </tr>
                      </table>
                      <br />
                      <br />
                      <h4>Employer Info</h4>
                      <table width="400" style="border:0px;0px;margin-left:0px;">
                        <tr>
                          <td width="120">Institution:</td>
                          <td width="268"><h:outputText value="#{reviewHeaderInfo.institution}" /></td>
                        </tr>
                        <tr>
                          <td>Service:</td>
                          <td><h:outputText value="#{reviewHeaderInfo.serviceType}" /></td>
                        </tr>
                        <tr>
                          <td>Supervisor:</td>
                          <td><h:outputText value="#{reviewHeaderInfo.supervisorName}" /></td>
                        </tr>
                      </table>
                      <br />
                      <br />
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
                    <h:commandButton id="button_back" action="#{appReviewList.view}" value="Back to Main" styleClass="btn btnbluePr btn140" />
                  </td>
                  <td style="text-align:right;padding:3px 10px 1px 0px;">
                    <h:commandButton id="button_print" action="#{appReviewPrint.print}" value="Print" styleClass="btn btnorange btn100" />
                    &nbsp;
                    <h:commandButton id="button_next" action="#{appReviewSupervisor.open}" value="Next" styleClass="btn btnblueNx btn100" />
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