<%@page import="com.google.gson.Gson"%>
<%@page import="works.TaskDB"%>
<%@page import="works.Sprint"%>
<%@page import="works.Tasks"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MY TASKS</title>
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
<%
HashMap<String,Tasks> tasks_incharge= (HashMap<String,Tasks>)request.getAttribute("tasks_incharge");//(task_id,tasks object)
HashMap<String,Sprint> sprints_incharge=(HashMap<String,Sprint>)request.getAttribute("sprints_incharge");//(sprint _id(incharge of sprint),sprint object)
HashMap<String,TaskDB> all_tasks=(HashMap<String,TaskDB>)request.getAttribute("all_tasks");//(all work id assigned.taskDB object)

%>
<SCRIPT>
function emptyDivs()
{
	var tables = document.getElementsByTagName("TABLE");
	for (var i=tables.length-1; i>=0;i-=1)
	   if (tables[i]) tables[i].parentNode.removeChild(tables[i]);}
function task()
{
	return <%=new Gson().toJson(tasks_incharge)%>;	
}
function sprint()
{
	return <%=new Gson().toJson(sprints_incharge)%>;	
}
function all()
{
	return <%=new Gson().toJson(all_tasks)%>;	
}
var tasks_incharge=task();
var sprints_incharge=sprint();
var all_tasks=all();

function incharge()
{
	emptyDivs();
	for(key in tasks_incharge)
	{
		if(key!=0)
		{	
				var div_to_write=document.getElementById(tasks_incharge[key]["status"]);
				//creating a table
				var create_table=document.createElement("table");
						create_table.id=key;
						
						console.log(create_table.id);
				var tbl_row=document.createElement("tr");
				tbl_row.className = "task";
						tbl_row.insertCell(0).appendChild(document.createTextNode(key));
						tbl_row.insertCell(1).appendChild(document.createTextNode(tasks_incharge[key]["workname"]));
						tbl_row.insertCell(2).appendChild(document.createTextNode(tasks_incharge[key]["desp"]));
						tbl_row.insertCell(3).appendChild(document.createTextNode(tasks_incharge[key]["module"]));
						tbl_row.insertCell(4).appendChild(document.createTextNode(tasks_incharge[key]["project"]));
						tbl_row.insertCell(5).appendChild(document.createTextNode(tasks_incharge[key]["dept"]));
						tbl_row.insertCell(6).appendChild(document.createTextNode(tasks_incharge[key]["st_date"]));
						tbl_row.insertCell(7).appendChild(document.createTextNode(tasks_incharge[key]["tg_date"]));
						tbl_row.insertCell(8).appendChild(document.createTextNode(tasks_incharge[key]["remarks"]));
						tbl_row.insertCell(9).appendChild(document.createTextNode(tasks_incharge[key]["incharge"]));
						tbl_row.insertCell(10).appendChild(document.createTextNode(tasks_incharge[key]["assign_to"]));
						tbl_row.insertCell(11).appendChild(document.createTextNode(tasks_incharge[key]["type"]));
				create_table.appendChild(tbl_row);		
			var scr=tasks_incharge[key]["task_scr"];
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
									scr_row.insertCell(10).appendChild(document.createTextNode(scr[sid]["type"]+" of TASK :- "+tasks_incharge[key]["workname"]));
									create_table.appendChild(scr_row);				
						}
						
					var spr=tasks_incharge[key]["task_spr"];
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
									spr_row.insertCell(10).appendChild(document.createTextNode(spr[spr_id]["type"]+"  of TASK :- "+tasks_incharge[key]["workname"]));
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
									spr_sc_row.insertCell(10).appendChild(document.createTextNode(spr_sc[spr_sc_id]["type"]+" of Sprint:- "+spr[spr_id]["workname"]+" --OF TASK :-"+tasks_incharge[key]["workname"]));
									create_table.appendChild(spr_sc_row);				
		
									}
					}			
					div_to_write.appendChild(create_table);	
		}	
			
		
		
	}
	
		
	sprint_incharge();
	
	
}
function sprint_incharge()
{
	
					for(spr_id in sprints_incharge)
					{
						if(spr_id!=0)
						{	
							var div_to_write=document.getElementById(sprints_incharge[spr_id]["status"]);
							//creating a table
							var create_table=document.createElement("table");
									create_table.id=spr_id;
									
									console.log(create_table.id);
													
							let spr_row=document.createElement("tr");
							spr_row.className = "Spr";
							spr_row.insertCell(0).appendChild(document.createTextNode(spr_id));
							spr_row.insertCell(1).appendChild(document.createTextNode(sprints_incharge[spr_id]["workname"]));
									spr_row.insertCell(2).appendChild(document.createTextNode(sprints_incharge[spr_id]["desp"]));
									spr_row.insertCell(3).appendChild(document.createTextNode(sprints_incharge[spr_id]["module"]));
									spr_row.insertCell(4).appendChild(document.createTextNode(sprints_incharge[spr_id]["project"]));
									spr_row.insertCell(5).appendChild(document.createTextNode(sprints_incharge[spr_id]["dept"]));
									spr_row.insertCell(6).appendChild(document.createTextNode(sprints_incharge[spr_id]["st_date"]));
									spr_row.insertCell(7).appendChild(document.createTextNode(sprints_incharge[spr_id]["tg_date"]));
									spr_row.insertCell(8).appendChild(document.createTextNode(sprints_incharge[spr_id]["remarks"]));
									spr_row.insertCell(9).appendChild(document.createTextNode(sprints_incharge[spr_id]["assign_to"]));
									spr_row.insertCell(10).appendChild(document.createTextNode(sprints_incharge[spr_id]["type"]));
									create_table.appendChild(spr_row);	
							let spr_sc=sprints_incharge[spr_id]["spr_scr"];
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
									spr_sc_row.insertCell(10).appendChild(document.createTextNode(spr_sc[spr_sc_id]["type"]));
									create_table.appendChild(spr_sc_row);				
				
									}
							
					div_to_write.appendChild(create_table);	
					}
				}
				
}
function all_tasks_create()
{
	emptyDivs();
	for(key in all_tasks)
	{
		if(key!=0)
		{	
			var div_to_write=document.getElementById(all_tasks[key]["status"]);
			//creating a table
			var create_table=document.createElement("table");
					create_table.id=key;
					
					console.log(create_table.id);
									
			let spr_row=document.createElement("tr");
			spr_row.className = "Spr";
			spr_row.insertCell(0).appendChild(document.createTextNode(key));
			spr_row.insertCell(1).appendChild(document.createTextNode(all_tasks[key]["workname"]));
					spr_row.insertCell(2).appendChild(document.createTextNode(all_tasks[key]["desp"]));
					spr_row.insertCell(3).appendChild(document.createTextNode(all_tasks[key]["module"]));
					spr_row.insertCell(4).appendChild(document.createTextNode(all_tasks[key]["project"]));
					spr_row.insertCell(5).appendChild(document.createTextNode(all_tasks[key]["dept"]));
					spr_row.insertCell(6).appendChild(document.createTextNode(all_tasks[key]["st_date"]));
					spr_row.insertCell(7).appendChild(document.createTextNode(all_tasks[key]["tg_date"]));
					spr_row.insertCell(8).appendChild(document.createTextNode(all_tasks[key]["remarks"]));
					spr_row.insertCell(9).appendChild(document.createTextNode(all_tasks[key]["assign_to"]));
					spr_row.insertCell(10).appendChild(document.createTextNode(all_tasks[key]["type"]));
					create_table.appendChild(spr_row);	
					div_to_write.appendChild(create_table);
		}
		
	}	
				
	
}

</SCRIPT>
</head>
<body onload="all_tasks_create()">

<button onclick="incharge()">INCHARGE VIEW</button>
<button onclick="all_tasks_create()">ALL TASKS TO BE DONE</button>
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