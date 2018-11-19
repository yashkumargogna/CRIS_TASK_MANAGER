<%@page import="icmsadmin.DTO.ICMSTeamMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="icmsadmin.common.IcmsadminCacheData"%>
<%@page	import="java.text.DateFormat, java.text.SimpleDateFormat, java.util.Date, java.io.*"%>

<!DOCTYPE HTML>
<HTML>
<HEAD>
<jsp:useBean id="UserInfo" class="icmsadmin.common.UserInfo" scope="session"></jsp:useBean>
<jsp:useBean id="IcmsadminFilter" class="icmsadmin.common.IcmsadminFilter" scope="request"></jsp:useBean>
<jsp:useBean id="TeamMeetingsDTO" class="TeamMeetingsDTO" scope="request"></jsp:useBean>
<jsp:useBean id="IcmsadminMessage" class="icmsadmin.common.IcmsadminMessage" scope="request"></jsp:useBean>
<jsp:include page="/include/head_tags.html" flush="true"/>
<jsp:include page="/TeamMeetings/Menu.jsp" flush="true"/>
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
var frm= document.frm;
var radios = frm.meetingReq;

 	  if(frm.meetingTitle.value == "") {
		
    		alert("Please Enter Meeting Title!");
    	 	return;
		}
		else if(frm.meetingTitle.value.length > 100) {
		
			alert("Please Enter Meeting Title with In range of 100 Characters !");
			return;
		}
		else if(frm.meetingCalledBy.value == "") {
		
			alert("Please Select Meeting Called by Name !");
			return;
		}
		else if(frm.meetingCalledByPerson.value == "") {
		
			alert("Please Enter Meeting Called by Person Name !");
			return;
		}
		else if(frm.meetingCalledByPerson.value.length > 50) {
		
			alert("Please Enter Meeting Called by Person Name with in Ragne of 50!");
			return;
		}
		else if(!radios[0].checked) {
		
			alert("Please Select Meeting Required or Not !");
			return;
		}
		else if(frm.fromDate.value == "") {
		
			alert("Please Enter From Date !");
			return;
		}
		else if(frm.toDate.value == "") {
		
			alert("Please Enter To Date !");
			return;
		}
		else if(frm.fromHH.value == "00") {
		
			alert("Please Enter Time From!");
			return;
		}
		
		else if(frm.toHH.value == "00") {
		
			alert("Please Enter Time To!");
			return;
		}
	frm.action="IcmsadminServlet?action=MicsUtilites&subAction=TeamMeeting&event=addConfirm";
	
	frm.submit(); 
	lockBackContent();
	fnPleaseWait();
}
</SCRIPT>
<jsp:include page="/include/messages_app.jsp" flush="true"/>
<IMG height=1 src="images/grey.gif" width="100%" align="top"/>
</head>
<body>
<%try{%>
<div id="divReportContent">
<FIELDSET><legend><span class="blueRow12NR">:: Add Meetings ::</span></legend>
<form name="frm" id="createform" method="post" >
			<table id="createtable">
				<tr>
					<td>Meeting Title<font color="red" size=2>*</font> :</td>
					<td><input id="meetingTitle" name="meetingTitle" size=100 required autofocus /></td>
				</tr>
				<tr>
					<td>Meeting Description<font color="red" size=2>*</font> :</td>
					<td><textarea id="meetingDesc" name="meetingDesc" cols="80" rows="4"></textarea></td>
				</tr>
				<tr>
					<td>Meeting Place<font color="red" size=2>*</font> :</td>
					<td><select name="meetingPlace">
							<option value="RB">RB - RAILWAY BOARD</option>
							<option value="GM">GM - GM ROOM</option>
							<option value="ICMS">ICMS - ICMS HALL</option>
							<option value="CRISHQ">CRISHQ - CRIS HEAD QUARTER</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Attended By ICMS Engineer Name<font color="red" size=2>*</font> :</td>
					<td><select name="attendedICMSErName" required multiple="multiple" size="5">
							<%for(ICMSTeamMember itm:IcmsadminCacheData.getvICMSTeamMember()){%>
							<option value="<%=itm.getEmployeeId()%>"><%=itm.getNameTitle()%> <%=itm.getEmployeeName()%> <%=itm.getDesignation() == null ? "" : itm.getDesignation()%></option>
						<%}%>
						</select>
					</td>
					
				</tr>

				<tr>
					<td>Other Person :</td>
					<td><input name="otherPerson" size=50 required /></td>
				</tr>

				<tr>
					<td>Meeting Called By<font color="red" size=2>*</font> :</td><td>
						<select name=meetingCalledBy required>
							<option selected value="">--select--</option>
							<option value="RB">RB - RAILWAY BOARD</option>
							<option value="CRISHQ">CRISHQ - CRIS HEAD QUARTER</option>
							<option value="ITPI">ITPI - ITPI, ITO</option>
						</select>
					</td>
				</tr>
					
				<tr>
					<td>Meeting Called By Person Name <font color="red" size=2>*</font> :</td>
					<td><input name="meetingCalledByPerson" size=50 required/></td>
				</tr>

				<tr>
					<td>Is Requirement of Meeting Justified ? <font color="red" size=2>*</font> :</td>
					<td><input type=radio name="meetingReq" value="1"/>Yes
					    <input type=radio name="meetingReq" value="0"/>No
					</td>
				</tr>

				<tr>
					<td>Meeting Date<font color="red" size=2>*</font> :</td>
					<td><jsp:include page="/include/dateInput2.jsp" flush="true"/></td>
				</tr>
				<tr>
			<td>Time From:</td> 
			<td align="left">	
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
                </SCRIPT></select>Mn. to 
				<select name="toHH">
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
				<SCRIPT>
                 <!--
                 for(i=10;i<24;i++)
				 {
                    document.write("<option value=\""+i+"\">"+i+"</option>");  
				 }
                 //-->
                document.forms[0].toHH.selectedIndex = 23;
                </SCRIPT></select>Hr.:
				<select name="toMI">
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
			</td>
		</tr>
				<tr>
					<td>Meeting Utilized Time<font color="red" size=2>*</font> :</td>
					<td><select name=mUtilizedTimeHr required>
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
					</td>
				</tr>

				<tr>
					<td>Wasted Time<font color="red" size=2>*</font> :</td>
			<td><select name=mWastedTimeHr >
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
					</td>
				</tr>
				
				<tr>
					<td>Travel Time<font color="red" size=2>*</font> :</td>
					<td>	
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
					</td>
				</tr>
				<tr>
					<td>Meeting Description<font color="red" size=2>*</font> :</td>
					<td><input name="meetingDesc" size=100 required /></td>
				</tr>
			</table>
			<br>
			<TABLE><tr><TD align="center" width=1000><input type="button" value="Submit" onClick="checkForm();"/></TD></TR></TABLE>
		</form>
		</FIELDSET>
</div>
<%}catch(Exception e){e.getStackTrace();}%>
<jsp:include page="/include/footer.jsp" flush="true"/>
</body>
</html>