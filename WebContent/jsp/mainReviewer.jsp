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
      <h:form id="form_mainReviewer" >
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
              <h:outputText value="#{sessionScope.roledesc}" />
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb">Home</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top" width="95%">
                    <table width="100%" cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td>
                          <div id="topcontent1">
                            <h1>Main Page</h1>
                            <br />
                            Available options for Reviewers / Approvers:<br />
                            <br />
                            <center>
                              <table cellpadding="3" cellspacing="3">
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td><h:commandLink id="link_editProfile" action="#{reviewerPersonalInfoSs.updateProfile}">Edit Profile</h:commandLink></td>
                                      </tr>
                                      <tr>
                                        <td>Update personal information</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h:commandLink id="link_listApplications" action="#{appReviewList.view}">List Pending Applications</h:commandLink>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>Display a list of applications requiring my attention</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h:commandLink id="link_uploadfileList" action="#{uploadFileListSs.uploadFileList}">Upload Document List</h:commandLink>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>Upload supporting documentation</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h:commandLink id="link_listApplicationHistory" action="#{appReviewAppHistorySs.applicationList}">List Past Applications</h:commandLink>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>Display a list of applications I have already reviewed</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <table cellpadding="0" cellspacing="2" border="0" width="500">
                                      <tr>
                                        <td>
                                          <h:commandLink id="link_changePassword" action="changePassword" immediate="true">Change my Password</h:commandLink>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td>You will need to enter your existing password</td>
                                      </tr>
                                    </table>
                                  </td>
                                </tr>
                              </table>
                            </center>
                            <br />
                            <br />
                            <br />
                          </div>
                        </td>
                      </tr>
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
