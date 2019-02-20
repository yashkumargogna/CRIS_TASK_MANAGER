<%@page import="com.google.gson.Gson"%>
<%@page import="works.Tasks"%>
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
  <link rel="stylesheet" href="css/style.css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="icon" href="images/project-management-tools.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="images/project-management-tools.ico" type="image/x-icon"/>	
<style>
body {font-family: Arial, Helvetica, sans-serif;}
/* Full-width input fields */
input[type=text], input[type=text], input[type=text],input[type=text],input[type=text],input[type=text] {
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
/* Modal Content/Box              this is for modal file*/
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
function addProjectToSelect(resp)
{
	var sel_mod_proj=document.getElementById('mod_proj');
	var sel_proj_proj=document.getElementById('task_project');
	var opt_proj=document.createElement('OPTION');
	opt_proj.value=resp["project"]["p_id"];
	opt_proj.text=resp["project"]["p_name"];
	sel_proj_proj.add(opt_proj);
	
	opt_proj=document.createElement('OPTION');
	opt_proj.value=resp["project"]["p_id"];
	opt_proj.text=resp["project"]["p_name"];
	sel_mod_proj.add(opt_proj);
	
	}
	
	
function showProjectAddResp(resp) {
	if(resp.success) {
		if(resp["inserted"]==="project")
			{	
				addProjectToSelect(resp);
				alert("successfully added:-"+resp["project"]["p_id"]+"&"+resp["project"]["p_name"]);	
				loadJSON(document.getElementById('task_project').value);
			}
		else if(resp["inserted"]==="module")
			{
				alert("successfully added:-"+resp["module"]["mod_id"]+"&"+resp["module"]["mod_name"]);
			}
		else if(resp["inserted"]==="task")
		{
			var div_to_write=document.getElementById(resp["task"]["status"]);
			//creating a table
			var create_table=document.createElement("table");
					create_table.id=resp["task"]["work_id"];
					
					console.log(create_table.id);
			var tbl_row=document.createElement("tr");
			tbl_row.className = "task";
					tbl_row.insertCell(0).appendChild(document.createTextNode(resp["task"]["work_id"]));
					tbl_row.insertCell(1).appendChild(document.createTextNode(resp["task"]["workname"]));
					tbl_row.insertCell(2).appendChild(document.createTextNode(resp["task"]["desp"]));
					tbl_row.insertCell(3).appendChild(document.createTextNode(resp["task"]["module"]));
					tbl_row.insertCell(4).appendChild(document.createTextNode(resp["task"]["project"]));
					tbl_row.insertCell(5).appendChild(document.createTextNode(resp["task"]["dept"]));
					tbl_row.insertCell(6).appendChild(document.createTextNode(resp["task"]["st_date"]));
					tbl_row.insertCell(7).appendChild(document.createTextNode(resp["task"]["tg_date"]));
					tbl_row.insertCell(8).appendChild(document.createTextNode(resp["task"]["remarks"]));
					tbl_row.insertCell(9).appendChild(document.createTextNode(resp["task"]["incharge"]));
					tbl_row.insertCell(10).appendChild(document.createTextNode(resp["task"]["assign_to"]));
					tbl_row.insertCell(11).appendChild(document.createTextNode(resp["task"]["type"]));
			create_table.appendChild(tbl_row);		
			div_to_write.appendChild(create_table);
			tasks_details[resp["task"]["work_id"]]=resp["task"];
			//alert(JSON.stringify(tasks_details[resp["task"]["work_id"]]));
			alert("successfully added:-"+resp["task"]["work_id"]+"&"+resp["task"]["workname"]);
		}
	}	
	else {
		alert(resp.err);
	}
	
}
function access(){ 
	
	<%
	HashMap<String, HashMap<String, Tasks>> hm_all=(HashMap<String, HashMap<String, Tasks>>)application.getAttribute("dep_tasks");
	String json_tasks="";
	if(hm_all.containsKey(ud.getDept()))
	{
		
		HashMap<String, Tasks> hm_tasks=hm_all.get(ud.getDept());
		json_tasks=new Gson().toJson(hm_tasks,hm_tasks.getClass().getGenericSuperclass());
	}
	else
	{
		json_tasks="{\" \":\" \"}";
	}
	%>
		return <%=json_tasks%>;
		

 
	   	   
	} 
var tasks_details=access();
function loadJSON(str){
    if(str!="")
    {	
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
				
			       if (http_request.readyState == 4){
			          // Javascript function JSON.parse to parse JSON data
			          console.log(http_request.responseText);
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
}
function arrangeJson()
{
	for(key in tasks_details)
	{
		if(key!=0)
		{	
				var div_to_write=document.getElementById(tasks_details[key]["status"]);
				//creating a table
				var create_table=document.createElement("table");
						create_table.id=key;
						
						console.log(create_table.id);
				var tbl_row=document.createElement("tr");
				tbl_row.className = "task";
						tbl_row.insertCell(0).appendChild(document.createTextNode(key));
						tbl_row.insertCell(1).appendChild(document.createTextNode(tasks_details[key]["workname"]));
						tbl_row.insertCell(2).appendChild(document.createTextNode(tasks_details[key]["desp"]));
						tbl_row.insertCell(3).appendChild(document.createTextNode(tasks_details[key]["module"]));
						tbl_row.insertCell(4).appendChild(document.createTextNode(tasks_details[key]["project"]));
						tbl_row.insertCell(5).appendChild(document.createTextNode(tasks_details[key]["dept"]));
						tbl_row.insertCell(6).appendChild(document.createTextNode(tasks_details[key]["st_date"]));
						tbl_row.insertCell(7).appendChild(document.createTextNode(tasks_details[key]["tg_date"]));
						tbl_row.insertCell(8).appendChild(document.createTextNode(tasks_details[key]["remarks"]));
						tbl_row.insertCell(9).appendChild(document.createTextNode(tasks_details[key]["incharge"]));
						tbl_row.insertCell(10).appendChild(document.createTextNode(tasks_details[key]["assign_to"]));
						tbl_row.insertCell(11).appendChild(document.createTextNode(tasks_details[key]["type"]));
				create_table.appendChild(tbl_row);		
			var scr=tasks_details[key]["task_scr"];
						for(sid in scr)
						{
									
									let scr_row=document.createElement("tr");
									scr_row.className = "Scr";
									scr_row.insertCell(0).appendChild(document.createTextNode(sid));
									scr_row.insertCell(1).appendChild(document.createTextNode(scr[sid]["workname"]));
									scr_row.insertCell(2).appendChild(document.createTextNode(scr[sid]["desp"]));
									scr_row.insertCell(3).appendChild(document.createTextNode(scr[sid]["module"]));
									scr_row.insertCell(4).appendChild(document.createTextNode(scr[sid]["project"]));
									scr_row.insertCell(5).appendChild(document.createTextNode(scr[sid]["dept"]));
									scr_row.insertCell(6).appendChild(document.createTextNode(scr[sid]["st_date"]));
									scr_row.insertCell(7).appendChild(document.createTextNode(scr[sid]["tg_date"]));
									scr_row.insertCell(8).appendChild(document.createTextNode(scr[sid]["remarks"]));
									scr_row.insertCell(9).appendChild(document.createTextNode(scr[sid]["assign_to"]));
									scr_row.insertCell(10).appendChild(document.createTextNode(scr[sid]["type"]+" of TASK :- "+tasks_details[key]["workname"]));
									create_table.appendChild(scr_row);				
						}
						
					var spr=tasks_details[key]["task_spr"];
					for(spr_id in spr)
					{
							let spr_row=document.createElement("tr");
							spr_row.className = "Spr";
							spr_row.insertCell(0).appendChild(document.createTextNode(spr_id));
							spr_row.insertCell(1).appendChild(document.createTextNode(spr[spr_id]["workname"]));
									spr_row.insertCell(2).appendChild(document.createTextNode(spr[spr_id]["desp"]));
									spr_row.insertCell(3).appendChild(document.createTextNode(spr[spr_id]["module"]));
									spr_row.insertCell(4).appendChild(document.createTextNode(spr[spr_id]["project"]));
									spr_row.insertCell(5).appendChild(document.createTextNode(spr[spr_id]["dept"]));
									spr_row.insertCell(6).appendChild(document.createTextNode(spr[spr_id]["st_date"]));
									spr_row.insertCell(7).appendChild(document.createTextNode(spr[spr_id]["tg_date"]));
									spr_row.insertCell(8).appendChild(document.createTextNode(spr[spr_id]["remarks"]));
									spr_row.insertCell(9).appendChild(document.createTextNode(spr[spr_id]["assign_to"]));
									spr_row.insertCell(10).appendChild(document.createTextNode(spr[spr_id]["type"]+"  of TASK :- "+tasks_details[key]["workname"]));
									create_table.appendChild(spr_row);	
							let spr_sc=spr[spr_id]["spr_scr"];
									for(spr_sc_id in spr_sc)
									{
											let spr_sc_row=document.createElement("tr");
									spr_sc_row.insertCell(0).appendChild(document.createTextNode(spr_sc_id));
									spr_sc_row.insertCell(1).appendChild(document.createTextNode(spr_sc[spr_sc_id]["workname"]));
									spr_sc_row.insertCell(2).appendChild(document.createTextNode(spr_sc[spr_sc_id]["desp"]));
									spr_sc_row.insertCell(3).appendChild(document.createTextNode(spr_sc[spr_sc_id]["module"]));
									spr_sc_row.insertCell(4).appendChild(document.createTextNode(spr_sc[spr_sc_id]["project"]));
									spr_sc_row.insertCell(5).appendChild(document.createTextNode(spr_sc[spr_sc_id]["dept"]));
									spr_sc_row.insertCell(6).appendChild(document.createTextNode(spr_sc[spr_sc_id]["st_date"]));
									spr_sc_row.insertCell(7).appendChild(document.createTextNode(spr_sc[spr_sc_id]["tg_date"]));
									spr_sc_row.insertCell(8).appendChild(document.createTextNode(spr_sc[spr_sc_id]["remarks"]));
									spr_sc_row.insertCell(9).appendChild(document.createTextNode(spr_sc[spr_sc_id]["assign_to"]));
									spr_sc_row.insertCell(10).appendChild(document.createTextNode(spr_sc[spr_sc_id]["type"]+" of Sprint:- "+spr[spr_id]["workname"]+" --OF TASK :-"+tasks_details[key]["workname"]));
									create_table.appendChild(spr_sc_row);				
		
									}
					}			
					div_to_write.appendChild(create_table);	
		}	
			
		
		
	}
}

 </SCRIPT>
</HEAD>
<body onload="arrangeJson()">
<iframe name="insert_frame" style="display:none;"></iframe>
<%try
{
%>
<%@include file="include/NavBar.html" %>

   <div id="id01" class="modal" style="display:none;">
  
 <form class="modal-content animate" id="createform" method="get" action="insertData" target="insert_frame">

			<div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>
					
				<br>	
			 <div class="container">
			<input type="hidden" value="TASK" name="action"/>	
			Task  Name<font color="red" size=2>*</font> : <input id="taskname" name="taskname" size=30 required autofocus />
				<br>
                
               <!-- <input type="file" name="pic" accept="image/*" /> -->  
              		
              
                <input type="hidden" id="EmpId" name="empID" value=<%=ud.getEid()%> size=30 required autofocus />
                <br>
				
					Task Description : <font color="red" size=2>*</font>
					<textarea id="taskDesc" name="taskDesc" cols="30" rows="4" size=30 required autofocus></textarea>
			
				<br>
			
					Start Date<font color="red" size=2>*</font> : <input type="date" name="startDate" />
					<br>
					Target Date<font color="red" size=2>*</font> : <input type="date" name="targetDate" />
				<br>
					<input type="hidden" name="work_type" value="TASK"/>
					
				<br>
				   <select id="task_project" name="project" onchange="loadJSON(this.value)" >
					<%		
						if(CommonDetails.dep_proj.containsKey(ud.getDept()))
						{	
							HashMap<String,String> proj_det=CommonDetails.dep_proj.get(ud.getDept());
							Set<String> proj_id_set=proj_det.keySet();
							for(String i:proj_id_set)
							{	
						%>							
								<option value=<%=i%>> <%=(String)proj_det.get(i)%> </option>
						<%
							}
						}	
						%>	
	
					</select>
                    
				<script>setTimeout(function(){loadJSON(document.getElementById('task_project').value);} ,1);</script>
				<br>
					<select id="module" name="module">
							<option value="">-- Select --</option>
					</select>
					<br>
					<br>
					
					<br>
				INCHARGE : <font color="red" size=2>*</font> :<select name="incharge" multiple>
					<%		
						if(CommonDetails.dep_emp.containsKey(ud.getDept()))
						{	
								HashMap<Integer,String> emp_det=CommonDetails.dep_emp.get(ud.getDept());
								Set<Integer> emp_id_set=emp_det.keySet();
								for(Integer i:emp_id_set) 
								{	
							%>
									<option value=<%=i%>> <%=(String)emp_det.get(i)%> </option>
							<%
								}
						}		
							%>	

						</select>
						<br>			
				<br>
				<br>
				Assign Work<font color="red" size=2>*</font> :<select name="assign_to" multiple>
					<%		
						if(CommonDetails.dep_emp.containsKey(ud.getDept()))
						{	
								HashMap<Integer,String> emp_det=CommonDetails.dep_emp.get(ud.getDept());
								Set<Integer> emp_id_set=emp_det.keySet();
								for(Integer i:emp_id_set) 
								{	
							%>
									<option value=<%=i%>> <%=(String)emp_det.get(i)%> </option>
							<%
								}
						}		
							%>	

						</select>
				<br>
				<br>
				<br>
					<select name="work_catg">
						<option value="DB WORK">DB WORK</option>
						<option value="DEVELOPMENT">DEVELOPMENT</option>
						<option value="DOCUMENTATION">DOCUMENTATION</option>
						<option value="OTHERS">OTHERS</option>
					</select>		
				<br>
				Status:<input type="text" name="status" value="todo" />
				<br>
				REMARKS:<input type="text" name="remarks" placeholder="remarks" />
				<br>		
				<input type="hidden" value=<%=ud.getDept() %>  name="dept" />
				<br>	
					
				
			<input type="submit" value="CREATE TASK" />
		</form>
</div>
</div>
<div id="id02" class="modal">
  
  <form class="modal-content animate" action="insertData" method="get"  target="insert_frame">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container" >
      
      
        <label for="uname"><b>Project Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="project_name" size=30  required autofocus/>
      
        <label for="uname"><b>Project Description</b></label>
      <input type="text" placeholder="Enter Project Description" name="project_desc" size=30  required />
      <input type="hidden" name="dept"value=<%=ud.getDept()%> />
      <input type="hidden" name="action" value="PROJECT"/>
      <input type="submit" value="ADD PROJECT"/>
      </div>

    </form>
    </div>
 
 <div id="id03" class="modal">
  
  <form class="modal-content animate" action="insertData" method="get"  target="insert_frame">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container">
      
      
        <label for="uname"><b>Module Name</b></label>
      <input type="text" placeholder="Enter Module Name" name="mod_name" size=30  required autofocus/>
      
        <label for="uname"><b>Module Description</b></label>
      <input type="text" placeholder="Enter module Description" name="mod_desp" size=30  required autofocus/></br>
      				<input type="hidden" name="action" value="MODULE"/>
					<select id="mod_proj" name="project">
					<%		
						if(CommonDetails.dep_proj.containsKey(ud.getDept()))
						{	
							HashMap<String,String> proj_det=CommonDetails.dep_proj.get(ud.getDept());
							Set<String> proj_id_set=proj_det.keySet();
							for(String i:proj_id_set)
							{	
						%>							
								<option value=<%=i%>> <%=(String)proj_det.get(i)%> </option>
						<%
							}
						}	
						%>	
	
					</select>
      <input type="submit" value="add module"/>
    
    </form>
    
   </div> 
 </div>  
    <div id="id04" class="modal">
  
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id04').style.display='none'" class="close" title="Close Modal">&times;</span>
    </div>

    <div class="container">
     
        <label for="uname"><b>Sprint Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="uname" size=30  required autofocus/>
      
        <label for="uname"><b>Sprint Description</b></label>
      <input type="text" placeholder="Enter Project Discription" name="uname" size=30  required autofocus/>
      

    
        
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
    </div>
    <div id="id05" class="modal">
  
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id05').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container">
      
      
        <label for="uname"><b>Daily Scrums Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="uname" size=30 required>
      
        <label for="uname"><b>Daily Scrums Description</b></label>
      <input type="text" placeholder="Enter Project Discription" name="uname" size=30  required>
      

    
        
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
   </div>

<%}catch(Exception e){e.printStackTrace();}%>
<center><div id="todo"><hr>TO-DO
<hr>
</div></center>
<center>*****************************</center>
<br>
<br>
<br>
<center><div id="pending"><hr>PENDING
<hr>
</div></center>
<center>*****************************</center>
<br>
<br>
<br>
<center><div id="inprogress"><hr>IN-PROGRESS
<hr>
</div></center>
<center>*****************************</center>
<br>
<br>
<br>
<center><div id="testing"><hr>TESTING
<hr>
</div></center>
<center>*****************************</center>
<br>
<br>
<br>
<center><div id="completed"><hr>COMPLETED
<hr>
</div></center>
<center>*****************************</center>
<br>
<br>
<br>

</body>


</html>
