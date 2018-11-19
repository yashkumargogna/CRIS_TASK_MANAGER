<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="icmsadmin.DAO.TeamMeetingsDAO"%>
<%@page import="icmsadmin.DTO.TeamMeetingsDTO"%>

<html>
<head>
<title>:: TEAM MEETING REPORT FORM ::</title>
<jsp:include page="/include/head_tags.html" flush="true"/>
<jsp:include page="/TeamMeetings/Menu.jsp" flush="true"/>
<jsp:useBean id="IcmsadminFilter" class="icmsadmin.common.IcmsadminFilter" scope="request"></jsp:useBean>
<jsp:useBean id="TeamMeetingsDTO" class="icmsadmin.DTO.TeamMeetingsDTO" scope="request"></jsp:useBean>
<jsp:useBean id="vRecordList" class ="java.util.ArrayList" scope="request"></jsp:useBean>
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
	
	frm.action="IcmsadminServlet?action=MicsUtilites&subAction=MeetingReport&event=show";
	
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
<FIELDSET><legend><span class="blueRow12NR">:: Report of Meeting ::</span></legend>
<TABLE class=style800 >
	<THEAD>
    	<TR class="lightGrey" height="25">
		<TD width="35%" nowrap><jsp:include page="/include/dateInput2.jsp" flush="true"/></TD>
		<TD nowrap><input type=submit value="Display"onClick="checkForm();"></TD>
		</TR>
      	</THEAD>
</TABLE>
</FIELDSET>
</div>
</form>
<br>
<%
try{
if(vRecordList.size() > 0) {%>
<div id="divReportContent">
<FIELDSET><legend><span class="blueRow12NR">:: Detail of Meetings ::</span></legend>
<TABLE class=tableBorder800>
	<TR class=lightBlue12NC>
		<td>Sr.</td>
		<td>From Date</td>
		<td>To Date</td>
		<td>Meeting Require</td>
		<td>Meeting ID</td>
		<td>Meeting Titles</td>
		<td>Meeting Place</td>
		<td>Meeting Chaired By</td>
		<td>Meeting Chaired By Person</td>
		<td>Meeting Attended By Er.</td>
        <td>Other Person</td>
        <td>Utilized Time</td> 
        <td>Wasted Time</td>  
        <td>Travel Time</td>  
        <td>Update Time</td> 
        <td>Description</td>             
	 </TR>
<%
TeamMeetingsDTO tmdto =null;
ArrayList<String> attendedBy = null;
String meetingReq = "";
int count=0;

for(int i=0;i<vRecordList.size();i++) {
tmdto = (TeamMeetingsDTO)vRecordList.get(i);
count = i+1;
 if(TeamMeetingsDTO.getMeetingReq() != null)
	meetingReq = "Yes";
else
	meetingReq = "No"; 
%>

<TR>
	<td ><%=count%></td>
	<td nowrap><%=tmdto.getFromDate1()%></td>
	<td nowrap><%=tmdto.getToDate1()%></td>
	<td ><%=meetingReq%></td>
	
	<td ><%=tmdto.getMeetingId()%></td>
	<td nowrap><%=tmdto.getMeetingTitle()%></td>
	<td ><%=tmdto.getMeetingPlace()%></td>
	<td><%=tmdto.getMeetingCalledBy()%></td>
	
	<td><%=tmdto.getMeetingCalledByPerson()%></td>
	<td><%=tmdto.getAttendedBy()%></td>
	<td nowrap><%=tmdto.getOtherPerson()%></td>
	<td nowrap><%=tmdto.getUtilizedTime()%></td>
	
	<td nowrap><%=tmdto.getWastedTime()%></td>
	<td nowrap><%=tmdto.getTravelTime()%></td>
	<td nowrap><%=tmdto.getUpdateTime()%></td>
	<td nowrap><%=tmdto.getDescription()%></td>
	
</TR>
<%}%>
</TABLE>
</FIELDSET>
</div>
<%}}catch(Exception e){}%>
<br>
<jsp:include page="/include/footer.jsp" flush="true"/>
</body>
</html>