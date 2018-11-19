<%@page import="common.IcmsadminCacheData"%>
<%@page import="DTO.ICMSTeamMember"%>
<%@page import="DTO.TeamMeetingsDTO"%>

<html>
<head>
<title>:: EDIT TEAM MEETINGS FORM ::</title>
<jsp:useBean id="IcmsadminMessage" class="common.IcmsadminMessage" scope="request"></jsp:useBean>
<jsp:useBean id="IcmsadminFilter" class="common.IcmsadminFilter" scope="request"></jsp:useBean>
<jsp:useBean id="TeamMeetingsDTO" class="DTO.TeamMeetingsDTO" scope="request"></jsp:useBean>
<jsp:useBean id="vRecordList" class ="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="vRecordList1" class ="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:include page="/include/head_tags.html" flush="true"/>
<jsp:include page="/TeamMeetings/Menu.jsp" flush="true"/>
</head>
<style>
 #createtable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#createtable td, #createtable th {
	border: 1px solid #ddd;
	text-align: left;
	font-size: 14px;
	padding: 4px;
}

#special tr {
	text-align: center;
}

#createtable tr:nth-child(even) {
	background-color: #f2f2f2
}

#createtable tr:hover {
	background-color: #ddd;
}

#createtable th {
	padding-top: 12px;
	padding-bottom: 12px;
	background-color: grey;
	color: basic;
} 
</style>

<SCRIPT>

function checkForm()
{
var frm = document.frm;

	   if(frm.fromDate.value == "") {
		
			alert("Please Enter From Date !");
			return;
		}
		else if(frm.toDate.value == "") {
		
			alert("Please Enter To Date !");
			return;
		}
	
	frm.action="IcmsadminServlet?action=MicsUtilites&subAction=TeamMeeting&event=editList";
	
	frm.submit(); 
	lockBackContent();
	fnPleaseWait();
}

</SCRIPT>

<SCRIPT>

function showRecords(meetingId){
var frm = document.frm1;

	frm.action="IcmsadminServlet?action=MicsUtilites&subAction=TeamMeeting&event=editListReport&meetingId="+meetingId;
	
	frm.submit(); 
	lockBackContent();
	fnPleaseWait();
}

</SCRIPT>

<SCRIPT>

function submitConfirmed(meetingId)
{
var frm = document.frm2;
	
	frm.action="IcmsadminServlet?action=MicsUtilites&subAction=TeamMeeting&event=editListReportConfirmed&meetingId="+meetingId;
	
	frm.submit(); 
	lockBackContent();
	fnPleaseWait();
}
</SCRIPT>
<body>
<form id="createform" method="post" name="frm" >
<jsp:include page="/include/messages_app.jsp" flush="true"/>
<IMG height=1 src="images/grey.gif" width="100%" align="top">

<div id=headReport>
<FIELDSET><legend><span class="blueRow12NR">:: Edit Meetings ::</span></legend>
<TABLE class=style800 >
	<THEAD>
    	<TR class="lightGrey" height="25">
		<TD width="35%" nowrap><jsp:include page="/include/dateInput2.jsp" flush="true"/></TD>
		<TD nowrap><input type=submit value="Display"onClick="checkForm();"></TD>
		</TR>
      	</THEAD>
</TABLE>
</div>
</form>
<br>

<%
try{
if(vRecordList.size() > 0) {%>
<form id="createform1" method="post" name="frm1">
<FIELDSET><legend><span class="blueRow12NR">:: Detail of Meetings ::</span></legend>

<TABLE class=tableBorder800>
	<TR class=lightBlue12NC>
		<td>Sr.</td>
		<td>From Date</td>
		<td>To Date</td>
		<td>Meeting ID</td>
		<td>Meeting Titles</td>
		<td>Meeting Place</td>
		<td>Meeting Chaired By</td>
	 </TR>
<%for(int i=0;i<vRecordList.size();i++) {
TeamMeetingsDTO = (TeamMeetingsDTO)vRecordList.get(i);
int count=i+1;%>
<TR>
	<td ><%=count%></td>
	<td ><%=TeamMeetingsDTO.getFromDate1()%></td>
	<td ><%=TeamMeetingsDTO.getToDate1()%></td>
	<td ><a href="javascript:showRecords('<%=TeamMeetingsDTO.getMeetingId()%>')"><%=TeamMeetingsDTO.getMeetingId()%></a></td>
	<td ><%=TeamMeetingsDTO.getMeetingTitle()%></td>
	<td ><%=TeamMeetingsDTO.getMeetingPlace()%></td>
	<td><%=TeamMeetingsDTO.getMeetingCalledBy()%></td>
</TR>
<%}}%>
</TABLE>
</FIELDSET>
</form>
<%
if(vRecordList1.size() > 0){
for(int i=0;i<vRecordList1.size();i++) {
TeamMeetingsDTO = (TeamMeetingsDTO)vRecordList1.get(i);
int count=i+1;%>
<form id="createform2" method="post" name="frm2">
<!-- <div id="divReportContent"> -->
<FIELDSET><legend><span class="blueRow12NR">:: Edit Detail of Meetings ::</span></legend>
<TABLE id="createtable" class=tableBorder800>
	<%-- <TR><TD>Sr.</TD><TD><%=count%></TD><TD></TD></TR> --%>
	<TR ><TD class=lightBlue12NC align="left">From Date</TD><TD align="left"><%=TeamMeetingsDTO.getFromDate1()%></TD><TD></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">To Date</TD><TD align="left"><%=TeamMeetingsDTO.getToDate1()%></TD><TD></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Meeting ID</TD><TD align="left"><%=TeamMeetingsDTO.getMeetingId()%></TD><TD></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Meeting Titles</TD><TD align="left"><%=TeamMeetingsDTO.getMeetingTitle()%></TD><TD align="left"><Input id="meetingTitle" name="meetingTitle" size=100 required autofocus /></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Meeting Place</TD><TD align="left"><%=TeamMeetingsDTO.getMeetingPlace()%></TD><TD align="left">
	<select name="meetingPlace">
							<option value="RB">RB - RAILWAY BOARD</option>
							<option value="GM">GM - GM ROOM</option>
							<option value="ICMS">ICMS - ICMS HALL</option>
							<option value="CRISHQ">CRISHQ - CRIS HEAD QUARTER</option>
							</select></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Meeting Chaired By</TD><TD align="left"><%=TeamMeetingsDTO.getMeetingCalledBy()%></TD><TD align="left">
	<select name=meetingCalledBy required>
							<option selected value="">--select--</option>
							<option value="RB">RB - RAILWAY BOARD</option>
							<option value="CRISHQ">CRISHQ - CRIS HEAD QUARTER</option>
							<option value="ITPI">ITPI - ITPI, ITO</option>
						</select></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Meeting Called By Person</TD><TD align="left"><%=TeamMeetingsDTO.getMeetingCalledByPerson()%></TD><TD align="left"><input name="meetingCalledByPerson" size=50 required/></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Attended By</TD><TD align="left"><%=TeamMeetingsDTO.getAttendedBy()%></TD><TD align="left"><select name="attendedICMSErName" required multiple="multiple" size="5"><%for(ICMSTeamMember itm:IcmsadminCacheData.getvICMSTeamMember()){%><option value="<%=itm.getEmployeeId()%>"><%=itm.getNameTitle()%> <%=itm.getEmployeeName()%> <%=itm.getDesignation() == null ? "" : itm.getDesignation()%></option><%}%></select></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Other Person</TD><TD align="left"><%=TeamMeetingsDTO.getOtherPerson()%></TD><TD align="left"><input name="otherPerson" size=50 required /></TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Meeting Required</TD><TD align="left"><%=TeamMeetingsDTO.getMeetingReq()%></TD><TD align="left"><input type=radio name="meetingReq" value="1"/>Yes<input type=radio name="meetingReq" value="0"/>No</TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Utilized Time</TD><TD align="left"><%=TeamMeetingsDTO.getUtilizedTime()%></TD><TD align="left">
	<select name=mUtilizedTimeHr required>
						<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						 <!--
                 		for(i=10;i<24;i++)
				 		{
                    		document.write("<option value=\""+i+"\">"+i+"</option>");  
				 		}
                 		//-->
						</select>Hr.:
									<select name=mUtilizedTimeMn>
									<option value="01" selected>01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<SCRIPT>
                					 <!--
                					 for(i=10;i<60;i++)
				 					{
                    				document.write("<option value=\""+i+"\">"+i+"</option>");  
				 					}
                 					//-->
                 					document.forms[0].toMI.selectedIndex = 59;
                					</SCRIPT></select>Mn.
	</TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Wasted Time</TD><TD align="left"><%=TeamMeetingsDTO.getWastedTime()%></TD><TD align="left">
	<select name=mWastedTimeHr >
					<option value="00">00</option>
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						 <!--
                 		for(i=10;i<24;i++)
				 		{
                    		document.write("<option value=\""+i+"\">"+i+"</option>");  
				 		}
                 		//-->
						</select>Hr.:
					<select name=mWastedTimeMn>
					<option value="01" selected>01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<SCRIPT>
                					 <!--
                					 for(i=10;i<60;i++)
				 					{
                    				document.write("<option value=\""+i+"\">"+i+"</option>");  
				 					}
                 					//-->
                 					document.forms[0].toMI.selectedIndex = 59;
                					</SCRIPT></select>Mn.
	</TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Travel Time</TD><TD align="left"><%=TeamMeetingsDTO.getTravelTime()%></TD><TD align="left">
	<select name="fromHH">
					<option value="00" selected>00</option>
					<option value="01">01</option>
					<option value="02">02</option>
					<option value="03">03</option>
					<option value="04">04</option>
					<option value="05">05</option>
					<option value="06">06</option>
					<option value="07">07</option>
					<option value="08">08</option>
					<option value="09">09</option>
				<SCRIPT>
                 <!--
                 for(i=10;i<24;i++)
				 {
                    document.write("<option value=\""+i+"\">"+i+"</option>");  
				 }
                 //-->
                </SCRIPT></select>Hr.:
				<select name="fromMI">
					<option value="01" selected>01</option>
					<option value="02">02</option>
					<option value="03">03</option>
					<option value="04">04</option>
					<option value="05">05</option>
					<option value="06">06</option>
					<option value="07">07</option>
					<option value="08">08</option>
					<option value="09">09</option>
					<option value="10">10</option>
				<SCRIPT>
                 <!--
                 for(i=10;i<60;i++)
				 {
                    document.write("<option value=\""+i+"\">"+i+"</option>");  
				 }
                 //-->
                </SCRIPT></select>Mn.
	</TD></TR>
	<TR ><TD class=lightBlue12NC align="left">Description</TD><TD align="left"><%=TeamMeetingsDTO.getDescription()%></TD>
	<TD align="left"><input name="meetingDesc" size=100 required /></TD></TR>
<%}%>
</TABLE>
<br>
<TABLE><tr><td align="center" width=1000><input type="button" value="Submit" onClick="submitConfirmed('<%=TeamMeetingsDTO.getMeetingId()%>')"/></TD></TR></TABLE>
</FIELDSET>
</div>
</form>
</FIELDSET>
<%}vRecordList.clear();}catch(Exception e){}%>
<jsp:include page="/include/footer.jsp" flush="true"/>
</body>
</html>