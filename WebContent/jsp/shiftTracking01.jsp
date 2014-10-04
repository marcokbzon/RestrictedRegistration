<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <title>Restricted Registration</title>
    <script type="text/javascript" src="/js/online.js"></script>
    <link href="/css/styles.css" rel="stylesheet" type="text/css" />
  </head>
  <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
  <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

  <body>
    <f:view>
      <h:form id="form_shiftTracking01" onkeypress="if (event.keyCode == 13) { javascript:submitForm( 'form_shiftTracking01:button_back' ); return false; }; return true;">
        <div id="container">
          <div id="topstatus">
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
              <tr>
                <td width="90%" >&nbsp;</td>
                <td id="divider" width="40%" nowrap>Logged in as <strong><h:outputText value="#{sessionScope.email}" /></strong></td>
                <td width="40%"><h:commandLink id="link_logout" immediate="true" action="#{logout.execute}">Logout</h:commandLink></td>
              </tr>
            </table>
          </div>
          <h:messages id="app_messages" layout="table" errorClass="msgs_error" infoClass="msgs_info" warnClass="msgs_warning" />
          <div id="separ2"></div>
          <div id="glocontainer1">
            <div id="titlepage1"> Shift Tracking
              <div id="hlp1">&nbsp;</div>
            </div>
            <div id="breadcrumb"><h:commandLink value="Home" action="#{mainPage.view}" immediate="true" /> &gt; Shift Tracking</div>
            <hr noshade color="#c8cbd0" size="1" />
            <div id="topcontent1">
              <table cellpadding="2" cellspacing="1" width="740">
                <tr>
                  <td> Months prior to the launch of the SHIFT TRACKING application will show as N/A (Not Available). Months greater than the current calendar months will show as
                    TBD (To Be Determined)<br />
                    <br />
                    Click on the Shift Tracking information of the month you want to edit:<br />
                  </td>
                </tr>
              </table>
            </div>
            <div id="navinside">
              <ul>
                <%--li id="chk"><h:commandLink id="link_2008" value=" 2008 " action="#{shiftTrackCalendarSS.view2008}" rendered="#{shiftTrackCalendarSS.render2008}" /></li--%>
                <li id="chk"><h:commandLink id="link_2009" value=" 2009 " action="#{shiftTrackCalendarSS.view2009}" rendered="#{shiftTrackCalendarSS.render2009}" /></li>
                <li id="chk"><h:commandLink id="link_2010" value=" 2010 " action="#{shiftTrackCalendarSS.view2010}" rendered="#{shiftTrackCalendarSS.render2010}" /></li>
                <li id="chk"><h:commandLink id="link_2011" value=" 2011 " action="#{shiftTrackCalendarSS.view2011}" rendered="#{shiftTrackCalendarSS.render2011}" /></li>
                <li id="chk"><h:commandLink id="link_2012" value=" 2012 " action="#{shiftTrackCalendarSS.view2012}" rendered="#{shiftTrackCalendarSS.render2012}" /></li>
                <li id="chk"><h:commandLink id="link_2013" value=" 2013 " action="#{shiftTrackCalendarSS.view2013}" rendered="#{shiftTrackCalendarSS.render2013}" /></li>
                <li id="chk"><h:commandLink id="link_2014" value=" 2014 " action="#{shiftTrackCalendarSS.view2014}" rendered="#{shiftTrackCalendarSS.render2014}" /></li>
              </ul>
            </div>
            <div id="tblform">
              <table width="740" border="0" cellpadding="0" cellspacing="1">
                <tr><td class="cal_trkTitle"><h:outputText value="#{shiftTrackCalendarSS.selectedYear}" /></td></tr>
                <tr>
                  <td>
                    <div id="tblscroll2">
                      <table width="700" cellpadding="3" cellspacing="0" class="shiftTrackTable">
                        <tr>
                          <td width="110" class="shiftTrackTableHeader">Jan</td>
                          <td width="110" class="shiftTrackTableHeader">Feb</td>
                          <td width="110" class="shiftTrackTableHeader">Mar</td>
                          <td width="110" class="shiftTrackTableHeader">Apr</td>
                          <td width="110" class="shiftTrackTableHeader">May</td>
                          <td width="110" class="shiftTrackTableHeader">Jun</td>
                        </tr>
                        <tr>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_janMsg" value="#{shiftTrackCalendarSS.janMessage}" rendered="#{not shiftTrackCalendarSS.janRenderAction}"/>
                            <h:commandLink id="link_janShifts" value="#{shiftTrackCalendarSS.janDataShifts}" action="#{shiftTrackCalendarSS.janAction}" rendered="#{shiftTrackCalendarSS.janRenderAction}" />
                            <br />
                            <h:commandLink id="link_janHours" value="#{shiftTrackCalendarSS.janDataHours}" action="#{shiftTrackCalendarSS.janAction}" rendered="#{shiftTrackCalendarSS.janRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_febMsg" value="#{shiftTrackCalendarSS.febMessage}" rendered="#{not shiftTrackCalendarSS.febRenderAction}"/>
                            <h:commandLink id="link_febShifts" value="#{shiftTrackCalendarSS.febDataShifts}" action="#{shiftTrackCalendarSS.febAction}" rendered="#{shiftTrackCalendarSS.febRenderAction}" />
                            <br />
                            <h:commandLink id="link_febHours" value="#{shiftTrackCalendarSS.febDataHours}" action="#{shiftTrackCalendarSS.febAction}" rendered="#{shiftTrackCalendarSS.febRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_marMsg" value="#{shiftTrackCalendarSS.marMessage}" rendered="#{not shiftTrackCalendarSS.marRenderAction}"/>
                            <h:commandLink id="link_marShifts" value="#{shiftTrackCalendarSS.marDataShifts}" action="#{shiftTrackCalendarSS.marAction}" rendered="#{shiftTrackCalendarSS.marRenderAction}" />
                            <br />
                            <h:commandLink id="link_marHours" value="#{shiftTrackCalendarSS.marDataHours}" action="#{shiftTrackCalendarSS.marAction}" rendered="#{shiftTrackCalendarSS.marRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_aprMsg" value="#{shiftTrackCalendarSS.aprMessage}" rendered="#{not shiftTrackCalendarSS.aprRenderAction}"/>
                            <h:commandLink id="link_aprShifts" value="#{shiftTrackCalendarSS.aprDataShifts}" action="#{shiftTrackCalendarSS.aprAction}" rendered="#{shiftTrackCalendarSS.aprRenderAction}" />
                            <br />
                            <h:commandLink id="link_aprHours" value="#{shiftTrackCalendarSS.aprDataHours}" action="#{shiftTrackCalendarSS.aprAction}" rendered="#{shiftTrackCalendarSS.aprRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_mayMsg" value="#{shiftTrackCalendarSS.mayMessage}" rendered="#{not shiftTrackCalendarSS.mayRenderAction}"/>
                            <h:commandLink id="link_mayShifts" value="#{shiftTrackCalendarSS.mayDataShifts}" action="#{shiftTrackCalendarSS.mayAction}" rendered="#{shiftTrackCalendarSS.mayRenderAction}" />
                            <br />
                            <h:commandLink id="link_mayHours" value="#{shiftTrackCalendarSS.mayDataHours}" action="#{shiftTrackCalendarSS.mayAction}" rendered="#{shiftTrackCalendarSS.mayRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_junMsg" value="#{shiftTrackCalendarSS.junMessage}" rendered="#{not shiftTrackCalendarSS.junRenderAction}"/>
                            <h:commandLink id="link_junShifts" value="#{shiftTrackCalendarSS.junDataShifts}" action="#{shiftTrackCalendarSS.junAction}" rendered="#{shiftTrackCalendarSS.junRenderAction}" />
                            <br />
                            <h:commandLink id="link_junHours" value="#{shiftTrackCalendarSS.junDataHours}" action="#{shiftTrackCalendarSS.junAction}" rendered="#{shiftTrackCalendarSS.junRenderAction}" />
                          </td>
                        </tr>
                      </table>
                      <br />
                      <br />
                      <table width="700" cellpadding="3" cellspacing="0" class="shiftTrackTable">
                        <tr>
                          <td width="110" class="shiftTrackTableHeader">Jul</td>
                          <td width="110" class="shiftTrackTableHeader">Aug</td>
                          <td width="110" class="shiftTrackTableHeader">Sep</td>
                          <td width="110" class="shiftTrackTableHeader">Oct</td>
                          <td width="110" class="shiftTrackTableHeader">Nov</td>
                          <td width="110" class="shiftTrackTableHeader">Dec</td>
                        </tr>
                        <tr>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_julMsg" value="#{shiftTrackCalendarSS.julMessage}" rendered="#{not shiftTrackCalendarSS.julRenderAction}"/>
                            <h:commandLink id="link_julShifts" value="#{shiftTrackCalendarSS.julDataShifts}" action="#{shiftTrackCalendarSS.julAction}" rendered="#{shiftTrackCalendarSS.julRenderAction}" />
                            <br />
                            <h:commandLink id="link_julHours" value="#{shiftTrackCalendarSS.julDataHours}" action="#{shiftTrackCalendarSS.julAction}" rendered="#{shiftTrackCalendarSS.julRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_augMsg" value="#{shiftTrackCalendarSS.augMessage}" rendered="#{not shiftTrackCalendarSS.augRenderAction}"/>
                            <h:commandLink id="link_augShifts" value="#{shiftTrackCalendarSS.augDataShifts}" action="#{shiftTrackCalendarSS.augAction}" rendered="#{shiftTrackCalendarSS.augRenderAction}" />
                            <br />
                            <h:commandLink id="link_augHours" value="#{shiftTrackCalendarSS.augDataHours}" action="#{shiftTrackCalendarSS.augAction}" rendered="#{shiftTrackCalendarSS.augRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_sepMsg" value="#{shiftTrackCalendarSS.sepMessage}" rendered="#{not shiftTrackCalendarSS.sepRenderAction}"/>
                            <h:commandLink id="link_sepShifts" value="#{shiftTrackCalendarSS.sepDataShifts}" action="#{shiftTrackCalendarSS.sepAction}" rendered="#{shiftTrackCalendarSS.sepRenderAction}" />
                            <br />
                            <h:commandLink id="link_sepHours" value="#{shiftTrackCalendarSS.sepDataHours}" action="#{shiftTrackCalendarSS.sepAction}" rendered="#{shiftTrackCalendarSS.sepRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_octMsg" value="#{shiftTrackCalendarSS.octMessage}" rendered="#{not shiftTrackCalendarSS.octRenderAction}"/>
                            <h:commandLink id="link_octShifts" value="#{shiftTrackCalendarSS.octDataShifts}" action="#{shiftTrackCalendarSS.octAction}" rendered="#{shiftTrackCalendarSS.octRenderAction}" />
                            <br />
                            <h:commandLink id="link_octHours" value="#{shiftTrackCalendarSS.octDataHours}" action="#{shiftTrackCalendarSS.octAction}" rendered="#{shiftTrackCalendarSS.octRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_novMsg" value="#{shiftTrackCalendarSS.novMessage}" rendered="#{not shiftTrackCalendarSS.novRenderAction}"/>
                            <h:commandLink id="link_novShifts" value="#{shiftTrackCalendarSS.novDataShifts}" action="#{shiftTrackCalendarSS.novAction}" rendered="#{shiftTrackCalendarSS.novRenderAction}" />
                            <br />
                            <h:commandLink id="link_novHours" value="#{shiftTrackCalendarSS.novDataHours}" action="#{shiftTrackCalendarSS.novAction}" rendered="#{shiftTrackCalendarSS.novRenderAction}" />
                          </td>
                          <td class="shiftTrackTableData">
                            <h:outputText id="output_decMsg" value="#{shiftTrackCalendarSS.decMessage}" rendered="#{not shiftTrackCalendarSS.decRenderAction}"/>
                            <h:commandLink id="link_decShifts" value="#{shiftTrackCalendarSS.decDataShifts}" action="#{shiftTrackCalendarSS.decAction}" rendered="#{shiftTrackCalendarSS.decRenderAction}" />
                            <br />
                            <h:commandLink id="link_decHours" value="#{shiftTrackCalendarSS.decDataHours}" action="#{shiftTrackCalendarSS.decAction}" rendered="#{shiftTrackCalendarSS.decRenderAction}" />
                          </td>
                        </tr>
                      </table>
                    </div>
                  </td>
                </tr>
              </table>
            </div>
            <div id="tbl1b" style="width:740px;margin-left:auto; margin-right:auto;">
              <div id="tbl2b"><img src="../images/spacer.gif" height="10" border="0" alt="" /></div>
            </div>
            <div align="center"> <br />
              <table width="740" border="0" cellpadding="0" cellspacing="1">
                <tr>
                  <td style="padding:3px 0px 1px 10px;">
                    <h:commandButton id="button_back" action="#{mainPage.view}" value="Back to Main" styleClass="btn btnbluePr btn140" />
                  </td>
                  <td style="text-align:right;padding:3px 10px 1px 0px;">&nbsp;</td>
                </tr>
              </table>
            </div>
            <br />
            <jsp:include page="/jsp/footer.inc" />
          </div>
        </div>
      </h:form>
    </f:view>
  </body>
</html>
