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
      <h:form id="form_appFormPrint">
        <table class="print_main">
          <tr>
            <td>
              <table width="649" border="0" cellspacing="0" cellpadding="2">
                <tr>
                  <td height="45px" colspan="4" class="print_title">&nbsp;Restricted Registration: Resident Profile</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;1. Personal Information</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td width="28">&nbsp;</td>
                  <td width="109" valign="top"><span class="print_label">First Name</span></td>
                  <td width="409" valign="top"><span class="print_data"><strong><h:outputText id="output_firstName" value="#{profilePrintRq.firstName}" /></strong></span></td>
                  <td width="79">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Last Name</span></td>
                  <td valign="top"><span class="print_data"><strong><h:outputText id="output_lastName" value="#{profilePrintRq.lastName}" /></strong></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">VISA Resident</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_visaResident" value="#{profilePrintRq.visaStudentIndicator}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Address</span></td>
                  <td valign="top"><span class="print_data">
                      <h:outputText id="output_aptNumber" value="#{profilePrintRq.aptNumber}" /><h:outputText id="output_aptSeparator" value=" - " rendered="#{profilePrintRq.aptSeparator}" /><h:outputText id="output_streetNumber" value="#{profilePrintRq.streetNumber}" />&nbsp;<h:outputText id="output_streetName" value="#{profilePrintRq.streetName}" /><br />
                      <h:outputText id="output_city" value="#{profilePrintRq.city}" />, <h:outputText id="output_provStateCode" value="#{profilePrintRq.provStateCode}" />, <h:outputText id="output_postalCode" value="#{profilePrintRq.postalCode}" /><br />
                      <h:outputText id="output_countryCode" value="#{profilePrintRq.countryCode}" />
                    </span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Office Phone</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_officePhone" value="#{profilePrintRq.officePhone}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Home Phone</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_homePhone" value="#{profilePrintRq.homePhone}"/></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Cell Phone</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_cellPhone" value="#{profilePrintRq.cellPhone}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Pager Number</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_pagerNumber" value="#{profilePrintRq.pagerNumber}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;2. Educational Information</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">P.G. Year</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_postGradYear" value="#{profilePrintRq.postGradYear}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">School of Residency</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_schoolResidency" value="#{profilePrintRq.schoolResidency}" /> <h:outputText id="output_northernStream" value="#{profilePrintRq.northernStream}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">P.G. Program</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_pgProgram" value="#{profilePrintRq.pgProgram}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Program Director</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_programDirector" value="#{profilePrintRq.programDirector}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Medical School</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_medSchool" value="#{profilePrintRq.medSchool}" /><br />
                      <h:outputText id="output_edCountryCode" value="#{profilePrintRq.edCountryName}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">ATLS Exam</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_atls" value="#{profilePrintRq.atls}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">ACLS Exam</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_acls" value="#{profilePrintRq.acls}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">PALS Exam</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_pals" value="#{profilePrintRq.pals}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">NALS/NRP Exam</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_nals" value="#{profilePrintRq.nals}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">MCCQE1</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_mccqe1" value="#{profilePrintRq.mccqe1}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">MCCQE2</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_mccqe2" value="#{profilePrintRq.mccqe2}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Current CPSO #</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_cpsoNumber" value="#{profilePrintRq.cpsoNumber}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">CMPA #</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_cmpaNumber" value="#{profilePrintRq.cmpaNumber}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;3. Clinical Rotations</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4">
                    <h:dataTable id="table_rotations" value="#{rotationBean.rotationDM}" var="rList"
                                 columnClasses="tbl_col_25 , tbl_col_119 print_label, tbl_col_399 print_data, tbl_col_76">
                      <h:column>
                        <h:outputText value=""/>
                      </h:column>
                      <h:column>
                        <h:outputText value="#{rList.serviceType}"/>
                      </h:column>
                      <h:column>
                        <h:outputText value="#{rList.rotations}"/> rotation(s) for a period of <h:outputText value="#{rList.weeksTotal}"/> week(s)
                      </h:column>
                      <h:column>
                        <h:outputText value=""/>
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
