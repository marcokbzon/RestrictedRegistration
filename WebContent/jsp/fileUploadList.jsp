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

  <body onLoad="setFocusOn( 'form_fileUploadList:button_back' )">
    <f:view>
      <h:form id="form_fileUploadList" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_fileUploadList:button_back' ); return false; }; return true;">
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
        		Upload File
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" /> &gt; Upload File List</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="contentpage">
              <br />
              <table cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td class="steps">&nbsp;</td>
                  <td>
                    <h1>List of Uploaded files</h1>
                  </td>
                </tr>
              </table>
              <br />
              <table cellpadding="2" cellspacing="1" width="740" border="0">
                <tr>
                  <td width="363">
                    <h:commandButton id="button_delete" action="#{uploadFileListSs.deleteUploadFile}" value="Delete" styleClass="btn btnorange btn100" />
                  </td>
                  <td  width="363" style="text-align:right;">
                    <h:commandButton id="button_add" action="#{uploadFileListSs.addUploadFile}" value="Add" styleClass="btn btnorange btn100" />
                  </td>
                </tr>
              </table>

              <table width="740" cellpadding="0" cellspacing="0" border="0" bgcolor="#999999">
                <tr>
                  <td width="40">
                    <table width="100%" height="40">
                      <tr>
                        <td>&nbsp;</td>
                      </tr>
                    </table>
                    <h:selectOneRadio id="input_uploadFileList"
                                      value="#{uploadFileListSs.selectedFileID}" layout="pageDirection"
                                      styleClass="tbl_main2">
                      <f:selectItems value="#{uploadFileBean.fileIDList}" />
                    </h:selectOneRadio>
                  </td>
                  <td width="700">
                    <h:dataTable id="table_uploadFiles" value="#{uploadFileBean.uploadFileDM}" var="uList"
                                 styleClass="tbl_main3" headerClass="tbl_hdr"
                                 rowClasses="tbl_oddRow, tbl_evenRow" columnClasses="tbl_col_580, tbl_col_100">
                      <h:column>
                        <f:facet name="header"><h:outputText value="Descriptive file Name" /></f:facet>
                        <b><h:outputText value="#{uList.delineativeName}" title="#{uList.description}"/></b>
                      </h:column>

                      <h:column>
                        <f:facet name="header"><h:outputText value="File" /></f:facet>
                        <h:outputLink value="/#{uList.fileDirectory}/#{uList.fileName}" title="#{uList.fileName}"><img src="/images/icoReports.gif" border="0" alt="" /></h:outputLink>
                      </h:column>
                    </h:dataTable>
                  </td>
                </tr>
              </table>

              <table cellpadding="2" cellspacing="1" width="740" border="0">
                <tr>
                  <td id="hrline" colspan="2">&nbsp;</td>
                </tr>
                <tr>
                  <td width="363">
                    <h:commandButton id="button_back" action="#{mainPage.view}" value="Go to Main" styleClass="btn btnbluePr btn140" />
                  </td>
                  <td width="363" style="text-align:right;">
                    &nbsp;
                  </td>
                </tr>
                <tr>
                  <td colspan="2"><br />
                    To &quot;<strong>Delete</strong>&quot; any of the files you have uploaded, you must first select one from the list, then click on DELETE.
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