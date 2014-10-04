<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>Restricted Registration</title>
    <script type="text/javascript" src="/js/online.js"></script>
    <!--TO BE REMOVED:START-->
    <link href="/css/styles.css" rel="stylesheet" type="text/css" />
    <!--TO BE REMOVED:END-->
  </head>
  <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
  <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

  <body>
    <f:view>
      <div id="container">
        <div id="topstatus">
          <table cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
              <td width="100%">&nbsp;</td>
            </tr>
          </table>
        </div>
        <div id="separ2">&nbsp;</div>
        <div id="glocontainer1">
          <div id="titlepage1">Sign Out</div>
          <hr noshade color="#c8cbd0" size="1" />
          <div id="contentpage">
            <table width="740" cellpadding="0" cellspacing="0" border="0">
              <tr>
                <td valign="top" width="95%">
                  <table width="100%" cellpadding="2" cellspacing="1" border="0">
                    <tr>
                      <td>
                        <div id="topcontent1">
                          <h1>Sign Out</h1>
                          <br />
                          You have successfully terminated your session with the Ontario Restricted Registration site.<br />
                          <br />
                          Click the following links, if you wish to:
                          <br /><br />
                          <center>
                            <table cellpadding="3">
                              <tr>
                                <td width="11">1.</td>
                                <td width="435">
                                  <h:outputLink value="login.jsp">Go back to the Restricted Registration SIGN-IN page</h:outputLink>
                                </td>
                              </tr>
                              <tr>
                                <td>2.</td>
                                <td>
                                  <h:outputLink value="http://www.restrictedregistrationontario.ca/index.html">Go to the Restricted Registration web site</h:outputLink>
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
    </f:view>
  </body>
</html>
