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
                  <td height="25px" colspan="4" class="print_section">&nbsp;Shifts By Type (week)</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr class="center">
                  <td colspan="4" class="center" width="700">
                    <table id="table_shifts" class="tableB" width="700">
                      <thead>
                        <tr>
                          <th class="tbl_hdr" scope="col">Week Type</th>
                          <th class="tbl_hdr" scope="col">Applications</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr class="tbl_oddRow">
                          <td class="tbl_col_560"><span class="print_label">Weekday</span></td>
                          <td class="tbl_col_120"><span class="print_data"><h:outputText value="#{adminShiftsByShiftTypeReportRQ.weekdayCount}" /></span></td>
                        </tr>
                        <tr class="tbl_evenRow">
                          <td><span class="print_label">Weeknight</span></td>
                          <td><span class="print_data"><h:outputText value="#{adminShiftsByShiftTypeReportRQ.weeknightCount}" /></span></td>
                        </tr>
                        <tr class="tbl_oddRow">
                          <td><span class="print_label">Weekend Day</span></td>
                          <td><span class="print_data"><h:outputText value="#{adminShiftsByShiftTypeReportRQ.weekendDayCount}" /></span></td>
                        </tr>
                        <tr class="tbl_evenRow">
                          <td><span class="print_label">Weekend Night</span></td>
                          <td><span class="print_data"><h:outputText value="#{adminShiftsByShiftTypeReportRQ.weekendNightCount}" /></span></td>
                        </tr>
                        <tr class="tbl_oddRow">
                          <td><span class="print_label">Locum Week</span></td>
                          <td><span class="print_data"><h:outputText value="#{adminShiftsByShiftTypeReportRQ.locumWeekCount}" /></span></td>
                        </tr>
                      </tbody>
                    </table>
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
