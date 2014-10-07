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
  <%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

  <body onLoad="setFocusOn( 'form_fileUpload:input_filename' )">
    <f:view>
      <h:form id="form_fileUpload" enctype="multipart/form-data">
        <div id="container">
          <div id="topstatus">
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
              <tr>
                <td width="90%" >&nbsp;</td>
                <td id="divider" width="40%" nowrap>Logged in as <strong><h:outputText value="#{sessionScope.email}" /></strong></td>
                <td width="40%"><h:commandLink id="link_logout" action="#{logout.execute}" immediate="true">Logout</h:commandLink></td>
              </tr>
            </table>
          </div>
          <h:messages id="app_messages" layout="table" errorClass="msgs_error" infoClass="msgs_info" warnClass="msgs_warning" />
          <div id="separ2">&nbsp;</div>
          <div id="glocontainer1">
            <div id="titlepage1"> Upload Document
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" immediate="true" /> &gt; Upload Document</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td valign="top" width="95%">
                    <table width="100%" cellpadding="2" cellspacing="1" border="0">
                      <tr>
                        <td>
                          <div id="topcontent1">
                            <h1>Upload Supporting File</h1>
                            <br />
                            Upload files that can  help support your approval/rejection decision or help further explain some of the rules in the decision making process. These files can be Word documents or Excel spreadsheets.<br />
                            <br />
                            You can create links to the files you have uploaded here, from within any Application-review page, by refering to their &quot;short name&quot;. It's important to chose a &quot;short name&quot; that will clearly identify the content of your documents.<br />
                            <br />
                            <br />
                            <center>
                              <table cellpadding="3" border="0">
                                <tr>
                                  <td width="100" class="cap"><span class="mand">+</span> Physical File: </td>
                                  <td width="500">
                                    <t:inputFileUpload id="input_filename" value="#{uploadFileRq.supportFile}" storage="file" size="55" />
                                  </td>
                                </tr>
                                <tr>
                                  <td class="cap">&nbsp;</td>
                                  <td>
                                    <h:selectBooleanCheckbox id="input_override" value="#{uploadFileRq.overrideFile}" />
                                    Override existing file
                                  </td>
                                </tr>
                                <tr>
                                  <td class="cap"><span class="mand">+</span> Descriptive file Name:</td>
                                  <td>
                                    <h:inputText id="input_delineativeName"
                                                 label="Delineative Name"
                                                 styleClass="fld150"
                                                 maxlength="25"
                                                 value="#{uploadFileRq.delineativeName}" />
                                  </td>
                                </tr>
                                <tr>
                                  <td class="cap">Description:</td>
                                  <td>
                                    <h:inputTextarea id="input_description" rows="6" cols="55" style="width:400px;" styleClass="fldtxt" value="#{uploadFileRq.description}"/>
                                  </td>
                                </tr>
                                <tr>
                                  <td id="hrline" colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                  <td>
                                    <h:commandButton id="button_back" action="#{uploadFileListSs.uploadFileList}" value="Back" styleClass="btn btnbluePr btn100" />
                                  </td>
                                  <td>
                                    <h:commandButton id="button_upload" action="#{uploadFileRq.submit}" value="Upload" styleClass="btn btnorange btn100" />
                                  </td>
                                </tr>
                              </table>
                            </center>
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

