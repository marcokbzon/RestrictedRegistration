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
      <h:form id="form_adminSiteStatusPrint">
        <table class="print_main">
          <tr>
            <td>
              <table width="649" border="0" cellspacing="0" cellpadding="2">
                <tr>
                  <td height="45px" colspan="4" class="print_title">&nbsp;Restricted Registration: Status Report</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;1. Users and Applications</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td width="28">&nbsp;</td>
                  <td width="320" valign="top"><span class="print_label"> Number of Applications</span></td>
                  <td width="80" valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberApplicationsAll}" /></span></td>
                  <td width="200">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label"> Number of Residents</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberResidentsAll}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">&nbsp;&nbsp;&nbsp;&nbsp;- Who have applied at least once</span></td>
                  <td valign="bottom"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberResidentsApplied}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Number of Supervisors</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberSupervisorsAll}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Number of Program Directors</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberProgramDirectorsAll}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Number of PGME Deans</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberPGMEDeansAll}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Number of Committee Members</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberCommitteeMembersAll}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;2. Universities and Institutions</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Number of Universities</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberUniversities}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">&nbsp;&nbsp;&nbsp;&nbsp;- Referenced at least by 1 application</span></td>
                  <td valign="bottom"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberUniversitiesApplied}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Number of Specialties</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberPrograms}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">&nbsp;&nbsp;&nbsp;&nbsp;- Referenced at least by 1 application</span></td>
                  <td valign="bottom"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberProgramsApplied}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Number of Institutions</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberInstitutions}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">&nbsp;&nbsp;&nbsp;&nbsp;- Referenced at least by 1 application</span></td>
                  <td valign="bottom"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberInstitutionsApplied}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Number of Service Types</span></td>
                  <td valign="top"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberServiceTypes}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">&nbsp;&nbsp;&nbsp;&nbsp;- Referenced at least by 1 application</span></td>
                  <td valign="bottom"><span class="print_data"><h:outputText value="#{AdminStatusPrintRQ.numberServiceTypesApplied}" /></span></td>
                  <td>&nbsp;</td>
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
