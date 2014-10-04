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
      <h:form id="form_appReviewPrint">
        <table class="print_main">
          <tr>
            <td>
              <table width="649" border="0" cellspacing="0" cellpadding="2">
                <tr>
                  <td height="45px" colspan="4" class="print_title">&nbsp;Restricted Registration Application : <h:outputText id="output_applicationID" value="#{appReviewPrint.applicationID}" /></td>
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
                  <td width="110" valign="top"><span class="print_label">First Name</span></td>
                  <td width="458" valign="top"><span class="print_data"><strong><h:outputText id="output_firstName" value="#{appReviewPrint.firstName}" /></strong></span></td>
                  <td width="37">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Last Name</span></td>
                  <td valign="top"><span class="print_data"><strong><h:outputText id="output_lastName" value="#{appReviewPrint.lastName}" /></strong></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Address</span></td>
                  <td valign="top"><span class="print_data">
                      <h:outputText id="output_aptNumber" value="#{appReviewPrint.aptNumber}" /><h:outputText id="output_aptSeparator" value=" - " rendered="#{appReviewPrint.aptSeparator}" /><h:outputText id="output_streetNumber" value="#{appReviewPrint.streetNumber}" />&nbsp;<h:outputText id="output_streetName" value="#{appReviewPrint.streetName}" /><br />
                      <h:outputText id="output_city" value="#{appReviewPrint.city}" />, <h:outputText id="output_provStateCode" value="#{appReviewPrint.provStateCode}" />, <h:outputText id="output_postalCode" value="#{appReviewPrint.postalCode}" /><br />
                      <h:outputText id="output_countryCode" value="#{appReviewPrint.countryCode}" />
                    </span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Office Phone</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_officePhone" value="#{appReviewPrint.officePhone}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Home Phone</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_homePhone" value="#{appReviewPrint.homePhone}"/></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Cell Phone</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_cellPhone" value="#{appReviewPrint.cellPhone}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Pager Number</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_pagerNumber" value="#{appReviewPrint.pagerNumber}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Email</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_email" value="#{appReviewPrint.email}" /></span></td>
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
                  <td valign="top"><span class="print_data"><h:outputText id="output_postGradYear" value="#{appReviewPrint.postGradYear}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">School of Residency</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_schoolResidency" value="#{appReviewPrint.schoolResidency}" /> <h:outputText id="output_northernStream" value="#{appReviewPrint.northernStream}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">P.G. Program</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_pgProgram" value="#{appReviewPrint.pgProgram}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Program Director</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_programDirector" value="#{appReviewPrint.programDirector}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Medical School</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_medSchool" value="#{appReviewPrint.medSchool}" /><br />
                      <h:outputText id="output_edCountryCode" value="#{appReviewPrint.edCountryName}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">ATLS Exam</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_atls" value="#{appReviewPrint.atls}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">ACLS Exam</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_acls" value="#{appReviewPrint.acls}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">PALS Exam</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_pals" value="#{appReviewPrint.pals}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">NALS/NRP Exam</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_nals" value="#{appReviewPrint.nals}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">MCCQE1</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_mccqe1" value="#{appReviewPrint.mccqe1}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">MCCQE2</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_mccqe2" value="#{appReviewPrint.mccqe2}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Current CPSO #</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_cpsoNumber" value="#{appReviewPrint.cpsoNumber}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">CMPA #</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_cmpaNumber" value="#{appReviewPrint.cmpaNumber}" /></span></td>
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
                  <td>&nbsp;</td>
                  <td colspan="3"><span class="print_label">Successfully completed rotations:<br/><br/></span></td>
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
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><br /><span class="print_label">Rotation history was last updated on: </span><span class="print_data"> <h:outputText id="output_rotationUpdatedOn" value="#{appReviewPrint.rotationUpdatedOn}" /></span><br /><br /></td>
                  <td valign="top">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;4. Employer Information</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">LHIN</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_lhinName" value="#{appReviewPrint.lhinName}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Institution</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_institutionName" value="#{appReviewPrint.institutionName}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Service Type</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_serviceTypeName" value="#{appReviewPrint.serviceTypeName}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Supervisor Name</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_supervisorName" value="#{appReviewPrint.supervisorName}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Supervisor Email</span></td>
                  <td valign="top"><span class="print_data"><h:outputText id="output_supervisorEmail" value="#{appReviewPrint.supervisorEmail}" /></span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top"><span class="print_label">Supervisor Phone</span></td>
                  <td valign="top">
                    <span class="print_data">
                      <h:outputText id="output_supervisorPhone" value="#{appReviewPrint.supervisorPhone}" />&nbsp;<h:outputText id="output_supervisorExtension" value="#{appReviewPrint.supervisorExtension}" />
                    </span>
                  </td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;5. Resident Agreement/Undertaking</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.ptcDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_ptcValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.ptcValue}" />
                      <h:graphicImage id="graphic_ptcValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.ptcValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.nccDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_nccValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.nccValue}" />
                      <h:graphicImage id="graphic_nccValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.nccValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.rasDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_rasValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.rasValue}" />
                      <h:graphicImage id="graphic_rasValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.rasValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.rriDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_rriValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.rriValue}" />
                      <h:graphicImage id="graphic_rriValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.rriValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.pwsDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_pwsValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.pwsValue}" />
                      <h:graphicImage id="graphic_pwsValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.pwsValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.ftcDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_ftcValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.ftcValue}" />
                      <h:graphicImage id="graphic_ftcValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.ftcValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.atiDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_atiValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.atiValue}" />
                      <h:graphicImage id="graphic_atiValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.atiValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.rtwDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_rtwValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.rtwValue}" />
                      <h:graphicImage id="graphic_rtwValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.rtwValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.pwtDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_pwtValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.pwtValue}" />
                      <h:graphicImage id="graphic_pwtValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.pwtValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.rucDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_rucValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.rucValue}" />
                      <h:graphicImage id="graphic_rucValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.rucValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.terDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_terValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.terValue}" />
                      <h:graphicImage id="graphic_terValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.terValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.nlaDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_nlaValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.nlaValue}" />
                      <h:graphicImage id="graphic_nlaValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.nlaValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><h:outputText value="#{appReviewPrint.iapDescription}"/></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_iapValueYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.iapValue}" />
                      <h:graphicImage id="graphic_iapValueNo" url="/images/checkedNO.gif" rendered="#{not appReviewPrint.iapValue}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top">
                    <span class="print_label">Comments about this application:</span><br />
                    <span class="print_data"><h:outputText id="output_umrComments" value="#{appReviewPrint.umrComments}" /></span><br />
                    <br />
                  </td>
                  <td valign="top"></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">By submitting this application form, I ( <strong><h:outputText id="output_umrFirstName" value="#{appReviewPrint.firstName}" />&nbsp;<h:outputText id="output_umrLastName" value="#{appReviewPrint.lastName}" /></strong> ) on <h:outputText id="output_appSubmittedOn" value="#{appReviewPrint.applicationSubmittedOn}" />, hereby confirm that the information I have provided above is accurate and that I agree to the terms and conditions of the application</span></td>
                  <td valign="top">
                    <div align="center"><img src="/images/checkedYES.gif" border="0" alt="" /></div></td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;6. Supervisor Agreement/Undertaking</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I hereby confirm that ( <h:outputText value="#{appReviewPrint.firstName}" />&nbsp;<h:outputText value="#{appReviewPrint.lastName}" /> ) has applied and been hired, pending receipt of a Restricted Registration from the College, to provide the services in the following domain of practice</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvConfirmAppliedYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvConfirmApplied}" />
                      <h:graphicImage id="graphic_isvConfirmAppliedNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvConfirmApplied) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I hereby attest that we have verified the Resident&#39;s credentials and are satisfied that he/she has the skills to perform the above activities</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvAttestCredentialsYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvAttestCredentials}" />
                      <h:graphicImage id="graphic_isvAttestCredentialsNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvAttestCredentials) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I hereby attest that the Resident&#39;s activities will be restricted to the scope of services and sites listed above and authorized by the CPSO</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvAttestActivitiesYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvAttestActivities}" />
                      <h:graphicImage id="graphic_isvAttestActivitiesNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvAttestActivities) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I hereby agree to abide by the PAIRO/CAHO Collective Agreement</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvAbideToPairoYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvAbideToPairo}" />
                      <h:graphicImage id="graphic_isvAbideToPairoNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvAbideToPairo) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I hereby attest that the Resident will be working under supervision levels required by the registration.</span><br />
                    <br />
                  </td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvAttestSupervisionYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvAttestSupervision}" />
                      <h:graphicImage id="graphic_isvAttestSupervisionNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvAttestSupervision) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I hereby agree to inform the CPSO ( and the Resident&#39;s Program Director ) of any unprofessional conduct or failure to abide by the terms of the Restricted Registration on the part of the Resident</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvInformCpsoYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvInformCpso}" />
                      <h:graphicImage id="graphic_isvInformCpsoNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvInformCpso) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I understand that the Resident may not begin the employment until the Resident has been issued a Restricted Certificate by the CPSO for this employment.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvIssueCertificateYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvIssueCertificate}" />
                      <h:graphicImage id="graphic_isvIssueCertificateNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvIssueCertificate) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I agree that before the Resident starts his/her employment the hospital will confirm with the CPSO that the Resident has been issued a Restricted Certificate of Registration for this purpose.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvConfirmCertificateYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvConfirmCertificate}" />
                      <h:graphicImage id="graphic_isvConfirmCertificateNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvConfirmCertificate) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I hereby attest that the Resident will not be the Most Responsible Physician ( MRP ).</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvNotBeMRPYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvNotBeMRP}" />
                      <h:graphicImage id="graphic_isvNotBeMRPNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvNotBeMRP) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">For purposes of tracking and evaluating the program on Restricted Registration for Residents, I agree to provide information and data as required by the program</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvProvideInformationYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvProvideInformation}" />
                      <h:graphicImage id="graphic_isvProvideInformationNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvProvideInformation) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I agree to inform appropriate personnel within the healthcare site of the planned Restricted Registration activities.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvInformActivitiesYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvInformActivities}" />
                      <h:graphicImage id="graphic_isvInformActivitiesNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvInformActivities) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top">
                    <span class="print_label">Duties the Resident may be performing in my employment</span><br />
                    <span class="print_data"><h:outputText id="output_dutiesDescription" value="#{appReviewPrint.dutiesDescription}" /></span><br /><br />
                    <span class="print_label">The supervision will be provided by:</span><br />
                  </td>
                  <td valign="top"></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td valign="top">
                    <span class="print_label">
                      Doctor's Name<br />
                      Phone<br />
                      Email<br />
                    </span>
                    <br />
                  </td>
                  <td valign="top">
                    <span class="print_data">
                      <h:outputText id="output_supervisedByName" value="#{appReviewPrint.supervisedByName}" /><br />
                      <h:outputText id="output_supervisedByPhone" value="#{appReviewPrint.supervisedByPhone}" /><br />
                      <h:outputText id="output_supervisedByEmail" value="#{appReviewPrint.supervisedByEmail}" /><br />
                    </span>
                  </td>
                  <td valign="top"></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top">
                    <span class="print_label">Type of supervision that will be provided</span><br />
                    <span class="print_data"><h:outputText id="output_supervisionDescription" value="#{appReviewPrint.supervisionDescription}" /></span><br />
                    <br />
                  </td>
                  <td valign="top"></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><strong><h:outputText value="I, #{appReviewPrint.supervisorFullname}, have confirmed the application on #{appReviewPrint.supervisorApprovedOn}" rendered="#{appReviewPrint.isvEntryExists}" /></strong></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_isvApplicationConfirmationYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.isvApplicationConfirmation}" />
                      <h:graphicImage id="graphic_isvApplicationConfirmationNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.isvApplicationConfirmation) and (appReviewPrint.isvEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;7. Program Director Agreement/Undertaking</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I attest that the information provided by the Resident, ( <h:outputText value="#{appReviewPrint.firstName}" />&nbsp;<h:outputText value="#{appReviewPrint.lastName}" /> ), on his/her training in the attached application is correct.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_updCorrectInformationYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.updCorrectInformation}" />
                      <h:graphicImage id="graphic_updCorrectInformationNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.updCorrectInformation) and (appReviewPrint.updEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I attest that the Resident is currently in good academic standing.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_updGoodAcademicYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.updGoodAcademic}" />
                      <h:graphicImage id="graphic_updGoodAcademicNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.updGoodAcademic) and (appReviewPrint.updEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I attest that the Resident has successfully completed the rotations required to work in the settings outlined in Section A(5) in which s/he is registered.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_updAttestEligibilityYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.updAttestEligibility}" />
                      <h:graphicImage id="graphic_updAttestEligibilityNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.updAttestEligibility) and (appReviewPrint.updEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">I agree that should the activities engaged in by the Resident under the Restricted Registration conflict with the requirements of the residency program, or should the activities negatively impact the Resident&#39;s academic/clinical obligations, or should the Resident experience any problems with performance generally, whether or not related to the Restricted Registration, I will withdraw my approval for the Restricted Registration in which case, the Resident&#39;s Restricted Registration Certificate will automatically expire.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_updNotifyCpsoYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.updNotifyCpso}" />
                      <h:graphicImage id="graphic_updNotifyCpsoNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.updNotifyCpso) and (appReviewPrint.updEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">For purposes of tracking and evaluating the program on Restricted Registration for Residents, I agree to provide information and data as required by the program.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_updProvideInformationYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.updProvideInformation}" />
                      <h:graphicImage id="graphic_updProvideInformationNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.updProvideInformation) and (appReviewPrint.updEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top">
                    <span class="print_label">Comments about this application:</span><br />
                    <span class="print_data"><h:outputText id="output_updComments" value="#{appReviewPrint.updComments}" /></span><br />
                    <br />
                  </td>
                  <td valign="top"></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><strong><h:outputText value="I, #{appReviewPrint.directorFullname}, have confirmed the application on #{appReviewPrint.directorApprovedOn}" rendered="#{appReviewPrint.updEntryExists}" /></strong></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_updApplicationConfirmationYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.updApplicationConfirmation}" />
                      <h:graphicImage id="graphic_updApplicationConfirmationNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.updApplicationConfirmation) and (appReviewPrint.updEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;8. PGME Dean Agreement/Undertaking</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">Based on the information provided above by the Resident applicant and the Program Director, I hereby give approval for the Resident, <h:outputText value="#{appReviewPrint.firstName}" />&nbsp;<h:outputText value="#{appReviewPrint.lastName}" />, to apply for a Restricted Registration to provide clinical services outside his/her program area.</span><br /><br /></td>
                  <td valign="top"><div align="center">&nbsp;</div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top">
                    <span class="print_label">Comments about this application:</span><br />
                    <span class="print_data"><h:outputText id="output_udnComments" value="#{appReviewPrint.udnComments}" /></span><br />
                    <br />
                  </td>
                  <td valign="top"></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><strong><h:outputText value="I, #{appReviewPrint.deanFullname}, have confirmed the application on #{appReviewPrint.deanApprovedOn}" rendered="#{appReviewPrint.udnEntryExists}" /></strong></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_udnApplicationConfirmationYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.udnApplicationConfirmation}" />
                      <h:graphicImage id="graphic_udnApplicationConfirmationNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.udnApplicationConfirmation) and (appReviewPrint.udnEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;9. R.R. Committee Agreement/Undertaking</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data">The Committee has reviewed the application and attests that it is complete and meets the requirements of the program.</span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_umcAttestRequirementsYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.umcAttestRequirements}" />
                      <h:graphicImage id="graphic_umcAttestRequirementsNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.umcAttestRequirements) and (appReviewPrint.umcEntryExists)}" />
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top">
                    <span class="print_label">Comments about this application:</span><br />
                    <span class="print_data"><h:outputText id="output_umcComments" value="#{appReviewPrint.umcComments}" /></span><br />
                    <br />
                  </td>
                  <td valign="top"></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td colspan="2" valign="top"><span class="print_data"><strong><h:outputText value="I, #{appReviewPrint.committeeFullname}, have confirmed the application on #{appReviewPrint.committeeApprovedOn}" rendered="#{appReviewPrint.umcEntryExists}" /></strong></span><br /><br /></td>
                  <td valign="top"><div align="center">
                      <h:graphicImage id="graphic_umcApplicationConfirmationYes" url="/images/checkedYES.gif" rendered="#{appReviewPrint.umcApplicationConfirmation}" />
                      <h:graphicImage id="graphic_umcApplicationConfirmationNo" url="/images/checkedNO.gif" rendered="#{(not appReviewPrint.umcApplicationConfirmation) and (appReviewPrint.umcEntryExists)}" />
                    </div></td>
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
