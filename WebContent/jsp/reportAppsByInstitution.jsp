<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  	<% request.setCharacterEncoding("UTF-8"); %>
    <f:view>
      <h:form id="form_reportAppsByInstitution">
        <table class="print_main_wide">
          <tr>
            <td>
              <table width="798" border="0" cellspacing="0" cellpadding="2">
                <tr>
                  <td height="45px" colspan="4" class="print_title">&nbsp;Restricted Registration: Statistics</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="25px" colspan="4" class="print_section">&nbsp;Applications By Institution</td>
                </tr>
                <tr>
                  <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="4" class="center">
                    <%---
                    <h:graphicImage value="http://chart.googleapis.com/chart?chs=800x240&cht=p&chco=333333&chds=0,#{reportAppsByInstRQ.pieChartMaxValue}&chd=t:#{reportAppsByInstRQ.pieChartParts}&chdlp=b&chp=4.72&chl=#{reportAppsByInstRQ.pieChartLabels}&chma=5,5,5,5|0,30" />
                    ---%>
                    No image available: Number of parameters exceeds chart capacity
                  </td>
                </tr>
                <tr>
                  <td colspan="4" class="print_divider">&nbsp;</td>
                </tr>
                <tr class="center">
                  <td colspan="4" class="center">
                    <h:dataTable id="table_rotations" value="#{reportAppsByInstBean.reportDM}" var="rList"
                                 styleClass="tableB" width="460"
                                 rowClasses="tbl_oddRow, tbl_evenRow" columnClasses="tbl_col_410 , tbl_col_83">
                      <h:column>
                        <h:outputText value="#{rList.entityName}" styleClass="print_label"/>
                      </h:column>

                      <h:column>
                        <h:outputText value="#{rList.entityCount}" styleClass="print_data"/>
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
