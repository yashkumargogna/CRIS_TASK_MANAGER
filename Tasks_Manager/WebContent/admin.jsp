
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="common.CommonDetails"%>
<%@page import="model.UserDet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page	import="java.text.DateFormat, java.text.SimpleDateFormat, java.util.Date, java.io.*,javax.servlet.http.*"%>
<%UserDet ud=(UserDet)session.getAttribute("UserDet");%>
<!DOCTYPE HTML>
<html>
<HEAD>
  <title>TASK MANAGER</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="icon" href="images/project-management-tools.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="images/project-management-tools.ico" type="image/x-icon"/>	
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=text], input[type=text], input[type=text],input[type=text],input[type=text], {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
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
          document.getElementById("module").options.length = 1;
         for(key in jsonObj)
        	 {
        	 console.log(key,jsonObj[key]);
        	 if(key==="0")		
        		 {
        		 document.getElementById("module").options.length = 1;
        		 }
        	 else
        	{	 
        	 document.getElementById("module").options.add(new Option(jsonObj[key],key,true));
        	} 
        	 
        	 }
          
     
       }
    }
	
    http_request.open("GET",data_file, true);
    http_request.send();
 }
 
 
 
/*
 
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
*/

</SCRIPT>


</HEAD>
<body>
<%try
{
%>
<%@include file="include/NavBar.html" %>

   <div id="id01" class="modal">
  
 <form class="modal-content animate" id="createform" method="post" action="registerTask" >
			<table id="createtable">
				
				<tr>
					<td>Task  Name<font color="red" size=2>*</font> :</td>
					<td><input id="taskname" name="taskname" size=30 required autofocus /></td>
				</tr>
                <tr>
                 <td> <iframe src="k.jsp"><input type="file" name="pic" accept="image/*">></td>
                </tr>
              <tr>
                <td><input type="hidden" id="EmpId" name="EmpId" size=50 required autofocus /></td>
                </tr>
				<tr>
					<td>Task Description<font color="red" size=2>*</font></td>
					<td><textarea id="taskDesc" name="taskDesc" cols="30" rows="4" size=30></textarea></td>
				</tr>
				
				<tr>
					<td>Start Date<font color="red" size=2>*</font> : </td>
					<td><input type="date" name="startDate" />
				</tr>
				<tr>
					<td>Target Date<font color="red" size=2>*</font> : </td> 
					<td><input type="date" name="targetDate" /></td>
				</tr>
			
				<tr>
					<td>Type Task <font color="red" size=2>
                    <td><input id="taskname" name="taskname" size=30 required autofocus /></td>
                    
                    </font>
						<input type="hidden" name="task_type" value="task"/>
					</td>
				</tr>
				
				<tr>
					<select id=project name=project onchange="loadJSON(this.value)" >
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
                    
				<script>setTimeout(function(){loadJSON(document.getElementById('project').value);} ,1);</script>
				
					<select id="module" name="module">
							<option value="">-- Select --</option>
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
			<TABLE><tr><TD align="center" width=500><input type="button" value="Submit" /></TD></TR></TABLE>
            </div>
		</form>
</div>

<div id="id02" class="modal">
  
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container">
      
      
        <label for="uname"><b>Project Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="uname" required>
      
        <label for="uname"><b>Project Discription</b></label>
      <input type="text" placeholder="Enter Project Discription" name="uname" required>
      

    
        
      <button type="submit">SUBMIT</button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
    </form>
    <div id="id02" class="modal">
  
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container">
      
      
        <label for="uname"><b>Module Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="uname" required>
      
        <label for="uname"><b>Module Description</b></label>
      <input type="text" placeholder="Enter module Discription" name="uname" required>
        
      <button type="submit">SUBMIT</button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
    </form>
    
    <div id="id04" class="modal">
  
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id04').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container">
     
        <label for="uname"><b>Sprint Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="uname" required>
      
        <label for="uname"><b>Sprint Description</b></label>
      <input type="text" placeholder="Enter Project Discription" name="uname" required>
      

    
        
      <button type="submit">SUBMIT</button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
    </form>
    <div id="id02" class="modal">
  
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container">
      
      
        <label for="uname"><b>Daily Scrum Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="uname" required>
      
        <label for="uname"><b>Daily Scrum Description</b></label>
      <input type="text" placeholder="Enter Project Discription" name="uname" required>
      

    
        
      <button type="submit">SUBMIT</button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Remember me
      </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
   

<%}catch(Exception e){e.printStackTrace();}%>

</body>


</html>