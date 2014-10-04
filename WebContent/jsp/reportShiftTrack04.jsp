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
      <h:form id="form_reportShiftTracking">
        <table class="print_main_wide">
          <tr>
            <td>
              <table width="798" border="0" cellspacing="0" cellpadding="2">
                <tr>
                  <td height="45px" colspan="4" class="print_title">&nbsp;Restricted Registration: Shift Tracking</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;Shifts By Application</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr class="center">
                  <td colspan="4" class="center" width="700">
                    <h:dataTable id="table_shifts" value="#{shiftByApplicationBean.shiftDM}" var="aList"
                                 styleClass="tbl_main3" headerClass="tbl_hdr" footerClass="tbl_hdr"
                                 rowClasses="tbl_oddRow, tbl_evenRow" columnClasses="tbl_col_500, tbl_col_90, tbl_col_90">
                      <h:column>
                        <f:facet name="header"><h:outputText value="Application ID" /></f:facet>
                        <h:outputText value="#{aList.applicationID}"/>
                        <f:facet name="footer"><h:outputText value="" /></f:facet>
                      </h:column>

                      <h:column>
                        <f:facet name="header"><h:outputText value="Shifts" /></f:facet>
                        <h:outputText value="#{aList.numberOfShifts}"/>
                        <f:facet name="footer"><h:outputText value="#{adminShiftsByApplicationReportRQ.totalShifts}" /></f:facet>
                      </h:column>

                      <h:column>
                        <f:facet name="header"><h:outputText value="Hours" /></f:facet>
                        <h:outputText value="#{aList.numberOfHours}"/>
                        <f:facet name="footer"><h:outputText value="#{adminShiftsByApplicationReportRQ.totalHours}" /></f:facet>
                      </h:column>
                    </h:dataTable>
                  </td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
            <td>
              <jsp:include page="/jsp/footer.inc" />
            </td>
          </tr>
        </table>
      </h:form>
    </f:view>
  </body>
</html>
