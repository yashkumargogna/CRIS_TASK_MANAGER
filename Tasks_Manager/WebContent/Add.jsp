
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="common.CommonDetails"%>
<%@page import="model.UserDet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page	import="java.text.DateFormat, java.text.SimpleDateFormat, java.util.Date, java.io.*,javax.servlet.http.*"%>

<!DOCTYPE HTML>
<html>
<HEAD>
<style>
 #createtable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 70%;
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

function loadJSON(str){
    var data_file = "getModule?p_id="+str;
    console.log(data_file);
    var http_request = new XMLHttpRequest();
    try{
       // Opera 8.0+, Firefox, Chrome, Safari
       http_request = new XMLHttpRequest();
    }catch (e){
       // Internet Explorer Browsers
       try{
          http_request = new ActiveXObject("Msxml2.XMLHTTP");
			
       }catch (e) {
		
          try{
             http_request = new ActiveXObject("Microsoft.XMLHTTP");
          }catch (e){
             // Something went wrong
             alert("Your browser broke!");
             return false;
          }
			
       }
    }
	
    http_request.onreadystatechange = function()
    {
	
       if (http_request.readyState == 4  ){
          // Javascript function JSON.parse to parse JSON data
          var jsonObj = JSON.parse(http_request.responseText);

          // jsonObj variable now contains the data structure and can
          // be accessed as jsonObj.Module 
       
          for(key in jsonObj)
        	 {
        	 	console.log(key,jsonObj[key]);	
        	 		document.getElementByName("module").options.add(new Option(key,jsonObj[key],true));
        	 
        	 
        	 }
     
       }
    }
	
    http_request.open("GET", data_file, true);
    http_request.send();
 }
 
 
 
<!-- 
 
var timepicker = new TimePicker('time', {
  lang: 'en',
  theme: 'dark'
});
timepicker.on('change', function(evt) {
  
  var value = (evt.hour || '00') + ':' + (evt.minute || '00');
  evt.element.value = value;

})

  
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
	frm.action="registerTask";
	
	frm.submit(); 
	lockBackContent();
	fnPleaseWait();
}
-->

</SCRIPT>

<IMG height=1 src="images/grey.gif" width="100%" align="top"/>
</head>
<body>
<%try{
UserDet ud=(UserDet)session.getAttribute("UserDet");
%>
<div id="divReportContent">
<FIELDSET><legend><span class="blueRow12NR">:: Task ::</span></legend>
<form name="frm" id="createform" method="post" action="">
			<table id="createtable">
				
				<tr>
					<td>Task Name<font color="red" size=2>*</font> :</td>
					<td><input id="taskname" name="taskname" size=100 required autofocus /></td>
				</tr>
				<tr>
					<td>Task Description<font color="red" size=2>*</font> :</td>
					<td><textarea id="taskDesc" name="taskDesc" cols="80" rows="4"></textarea></td>
				</tr>
				
				<tr>
					<td>Start Date<font color="red" size=2>*</font> : </td>
					<td><input type="date" name="startDate" />In Time<font color="red" size=2>*</font> <input type="time" name="inTime" >
				</tr>
				<tr>
					<td>Target Date<font color="red" size=2>*</font> : </td> 
					<td><input type="date" name="targetDate" />Out Time<font color="red" size=2>*</font> <input type="time" name="inTime"></td>
				</tr>
				

				<tr>
					<td>Time Duration<font color="red" size=2>*</font> :</td>
					<td><input name="meetingDesc" size=100 required /></td>
				</tr>

				<tr>
							
							
							
							
							
							
							
				<tr>
					<td>Task Type <font color="red" size=2>*</font> :</td>
					<td>
						<input type="hidden" name="task_type" value="task"/>
					</td>
				</tr>
				<tr>
					<select name="project" onselect=loadJSON(this.value) >
					<%		
						HashMap<Integer,String> proj_det=CommonDetails.dep_proj.get(ud.getDept());
						Set<Integer> proj_id_set=proj_det.keySet();
						for(Integer i:proj_id_set)
						{	
					%>
					
							<option value=<%=i%>> <%=(String)proj_det.get(i)%> </option>
					<%
						}					
					%>	

					</select>
				
				
					<select name="module">
					
					
					</select>
				
				
				
				</tr>
				<tr>
				
					<td>Assign Work<font color="red" size=2>*</font> :</td>
					<td>
					<select name="assignwork" multiple>
					<%		
						HashMap<Integer,String> emp_det=CommonDetails.dep_emp.get(ud.getDept());
						Set<Integer> emp_id_set=emp_det.keySet();
						for(Integer i:emp_id_set)
						{	
					%>
					
							<option value=<%=i%>> <%=(String)emp_det.get(i)%> </option>
					<%
						}					
					%>	

						</select>
					</td>
				</tr>
				
			</table>
			<br>
			<TABLE><tr><TD align="center" width=500><input type="button" value="Submit" onClick="checkForm();"/></TD></TR></TABLE>
			<input type="submit" value="submit"/>
		</form>
		</FIELDSET>
</div>
<%}catch(Exception e){e.getStackTrace();}%>

</body>


</html>