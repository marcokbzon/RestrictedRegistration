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
      <h:form id="form_adminDataLoad">
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
              Initial Database Reset and Load
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" /> &gt; Database Maintenance</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <br />

              <table width="700" border="1" cellspacing="0" cellpadding="3">
                <tr>
                  <td>
                    <table class="tbl_main3">
                      <tr>
                        <th class="tbl_hdr">Table</th>
                        <th class="tbl_hdr">Action</th>
                        <th class="tbl_hdr">Record Count</th>
                      </tr>
                      <tr class="tbl_oddRow">
                        <td class="tbl_col_344">Country</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadCountry" action="#{adminDataLoadRq.loadCountry}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.countryCount}" /></td>
                      </tr>
                      <tr class="tbl_evenRow">
                        <td class="tbl_col_344">Province / State</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadProvinceState" action="#{adminDataLoadRq.loadProvinceState}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.provinceStateCount}" /></td>
                      </tr>

                      <tr class="tbl_oddRow">
                        <td class="tbl_col_344">University</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadUniversity" action="#{adminDataLoadRq.loadUniversity}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.universityCount}" /></td>
                      </tr>
                      <tr class="tbl_evenRow">
                        <td class="tbl_col_344">Program</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadProgram" action="#{adminDataLoadRq.loadProgram}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.programCount}" /></td>
                      </tr>
                      <%--
                      <tr class="tbl_oddRow">
                          <td class="tbl_col_344">Role</td>
                          <td class="tbl_col_172">
                              <h:commandButton id="button_loadRole" action="#{adminDataLoadRq.loadRole}" value="Reset & Load" styleClass="btn btnorange btn140" />
                          </td>
                          <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.roleCount}" /></td>
                      </tr>
                      --%>
                      <tr class="tbl_evenRow">
                        <td class="tbl_col_344">Code</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadCode" action="#{adminDataLoadRq.loadCode}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.codeCount}" /></td>
                      </tr>

                      <tr class="tbl_oddRow">
                        <td class="tbl_col_344">Institution</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadInstitution" action="#{adminDataLoadRq.loadInstitution}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.institutionCount}" /></td>
                      </tr>
                      <tr class="tbl_evenRow">
                        <td class="tbl_col_344">Service Type</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadServiceType" action="#{adminDataLoadRq.loadServiceType}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.serviceTypeCount}" /></td>
                      </tr>

                      <tr class="tbl_oddRow">
                        <td class="tbl_col_344">LHIN</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadLHIN" action="#{adminDataLoadRq.loadLHIN}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.lhinCount}" /></td>
                      </tr>
                      <tr class="tbl_evenRow">
                        <td class="tbl_col_344">Classification</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadClassification" action="#{adminDataLoadRq.loadClassification}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.classificationCount}" /></td>
                      </tr>

                      <tr class="tbl_oddRow">
                        <td class="tbl_col_344">Agreement</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadAgreement" action="#{adminDataLoadRq.loadAgreement}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.agreementCount}" /></td>
                      </tr>
                      <tr class="tbl_evenRow">
                        <td class="tbl_col_344">Institution &amp; LHIN</td>
                        <td class="tbl_col_172">
                          <h:commandButton id="button_loadInstLHIN" action="#{adminDataLoadRq.loadInstLHIN}" value="Reset & Load" styleClass="btn btnorange btn140" />
                        </td>
                        <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.instLhinCount}" /></td>
                      </tr>
                      <%--
                      <tr class="tbl_oddRow">
                          <td class="tbl_col_344">Institution & Classification</td>
                          <td class="tbl_col_172">
                              <h:commandButton id="button_loadInstClassification" action="#{adminDataLoadRq.loadInstClassification}" value="Reset & Load" styleClass="btn btnorange btn140" />
                          </td>
                          <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.instClassificationCount}" /></td>
                      </tr>
                      <tr class="tbl_evenRow">
                          <td class="tbl_col_344">Institution & Service Type</td>
                          <td class="tbl_col_172">
                              <h:commandButton id="button_loadInstServiceType" action="#{adminDataLoadRq.loadInstServiceType}" value="Reset & Load" styleClass="btn btnorange btn140" />
                          </td>
                          <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.instServiceTypeCount}" /></td>
                      </tr>

                                            <tr class="tbl_oddRow">
                                                <td class="tbl_col_344">User Info</td>
                                                <td class="tbl_col_172">
                                                    <h:commandButton id="button_loadUserInfo" action="#{adminDataLoadRq.loadUserInfo}" value="Reset & Load" styleClass="btn btnorange btn140" />
                                                </td>
                                                <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.userInfoCount}" /></td>
                                            </tr>
                                            <tr class="tbl_evenRow">
                                                <td class="tbl_col_344">User Info & Role</td>
                                                <td class="tbl_col_172">
                                                    <h:commandButton id="button_loadUserRole" action="#{adminDataLoadRq.loadUserRole}" value="Reset & Load" styleClass="btn btnorange btn140" />
                                                </td>
                                                <td class="tbl_col_158"><h:outputText value="#{databaseCountBean.userRoleCount}" /></td>
                                            </tr>
                      --%>
                    </table>
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
