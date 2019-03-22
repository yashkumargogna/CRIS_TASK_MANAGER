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
var i=0;
function emptyDivs()
{
	var tables = document.getElementsByTagName("TABLE");
	for (var i=tables.length-1; i>=0;i-=1)
	{  if (tables[i]) tables[i].parentNode.removeChild(tables[i]);}
	var arr=document.getElementsByTagName("BR");
	for (var i=arr.length-1; i>=0;i-=1)
	{ if (arr[i]) arr[i].parentNode.removeChild(arr[i]);}

		}

function showTaskById(id_to_show)
{
	emptyDivs();
	arrangeJson();
	var a=id_to_show.trim();
	var tables = document.getElementsByTagName("TABLE");
	for (var i=tables.length-1; i>=0;i-=1)
	{ if (tables[i].id===a) 
		{}
	   else  
		 {tables[i].style.display="none";}  
	}
	var arr=document.getElementsByTagName("BR");
	for (var i=arr.length-1; i>=0;i-=1)
	{ if (arr[i]) arr[i].parentNode.removeChild(arr[i]);}
}	
function scrumOfSprintForm()
{
	var scofspformaction=document.getElementById("insertDataAction");
	scofspformaction.value="SCRUMOFSPRINT";
	var scofspformsubmitscrum=document.getElementById("submitscrum");
	scofspformsubmitscrum.value="CREATE SCRUM FOR SPRINT"
		var scofspformid_rel_to=document.getElementById("id_rel_to");
	scofspformid_rel_to.placeholder="SPRINT - ID";
	console.log(scofspformaction.value);
}

var page_type="admin";
var socketURI="ws://localhost:8686/Tasks_Manager/Notify?eid=<%=ud.getEid()%>&dept=<%=ud.getDept()%>&page_type=admin";
var socket = new WebSocket(socketURI);
socket.onmessage=received;
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
						//alert(JSON.stringify(tasks_details[resp["task"]["work_id"]]));
			notify(resp["inserted"],resp["task"]);
			alert("successfully added:-"+resp["task"]["work_id"]+"&"+resp["task"]["workname"]);
		}
		else if(resp["inserted"]==="scrumofsprint")
		{
						//alert(JSON.stringify(tasks_details[resp["task"]["work_id"]]));
			notify(resp["inserted"],resp["scrum"]);
			alert("successfully added:-"+resp["scrum"]["work_id"]+"&"+resp["scrum"]["workname"]);
		}
		else if(resp["inserted"]==="scrumoftask")
		{
						//alert(JSON.stringify(tasks_details[resp["task"]["work_id"]]));
			notify(resp["inserted"],resp["scrum"]);
			alert("successfully added:-"+resp["scrum"]["work_id"]+"&"+resp["scrum"]["workname"]);
		}
		else if(resp["inserted"]==="sprint")
		{
						//alert(JSON.stringify(tasks_details[resp["task"]["work_id"]]));
			notify(resp["inserted"],resp["sprint"]);
			alert("successfully added:-"+resp["sprint"]["work_id"]+"&"+resp["sprint"]["workname"]);
		}
	}	
	else {
		alert(resp.err);
	}
	
}
function notify(resp_inserted,resp_task)
{
	if(resp_inserted==="task")
	{	
		var task_to_notify={"action":"CREATE TASK","task":resp_task};
		socket.send(JSON.stringify(task_to_notify));	
	}
	else if(resp_inserted==="scrumofsprint")
	{

		var scofsp_to_notify={"action":"CREATE SCRUMOFSPRINT","scrum":resp_task};
		socket.send(JSON.stringify(scofsp_to_notify));	
	}
	else if(resp_inserted==="scrumoftask")
	{

		var scoft_to_notify={"action":"CREATE SCRUMOFTASK","scrum":resp_task};
		socket.send(JSON.stringify(scoft_to_notify));	
	}
	else if(resp_inserted==="sprint")
	{

		var sprint_to_notify={"action":"CREATE SPRINT","sprint":resp_task};
		socket.send(JSON.stringify(sprint_to_notify));	
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
function received(event)//HANDLE THE WEB SOCKET MESSAGE RECEIVED
{
	var rec_event=event.data;
	var resp=JSON.parse(rec_event);
	console.log(JSON.stringify(resp));
	if(resp["action"]==="CREATE TASK")
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
					var fst_tab=div_to_write.getElementsByTagName('table')[0];
					div_to_write.insertBefore(create_table,fst_tab);
			
			tasks_details[resp["task"]["work_id"]]=resp["task"];
	}
	else if(resp["action"]==="CREATE SCRUMOFTASK")
	{
		var task_table=document.getElementById(resp["scrum"]["task_of"]);
		var task_row=task_table.getElementsByTagName('tr')[0];
		
		var sc_of_t=resp["scrum"];
		var tbl_row=task_table.insertRow(task_row.rowIndex+1);
		tbl_row.id=resp["scrum"]["work_id"];
				tbl_row.insertCell(0).appendChild(document.createTextNode(resp["scrum"]["work_id"]));
				tbl_row.insertCell(1).appendChild(document.createTextNode(resp["scrum"]["workname"]));
				tbl_row.insertCell(2).appendChild(document.createTextNode(resp["scrum"]["desp"]));
				tbl_row.insertCell(3).appendChild(document.createTextNode(resp["scrum"]["module"]));
				tbl_row.insertCell(4).appendChild(document.createTextNode(resp["scrum"]["project"]));
				tbl_row.insertCell(5).appendChild(document.createTextNode(resp["scrum"]["dept"]));
				tbl_row.insertCell(6).appendChild(document.createTextNode(resp["scrum"]["st_date"]));
				tbl_row.insertCell(7).appendChild(document.createTextNode(resp["scrum"]["tg_date"]));
				tbl_row.insertCell(8).appendChild(document.createTextNode(resp["scrum"]["remarks"]));
				tbl_row.insertCell(9).appendChild(document.createTextNode(" "));
				tbl_row.insertCell(10).appendChild(document.createTextNode(resp["scrum"]["assign_to"]));
				tbl_row.insertCell(11).appendChild(document.createTextNode(resp["scrum"]["type"]));
		tasks_details[resp["scrum"]["task_of"]]["task_scr"][resp["scrum"]["work_id"]]=resp["scrum"]; 
		
	
	}
	else if(resp["action"]==="CREATE SCRUMOFSPRINT")
	{
		var sc_of_sp=resp["scrum"];
		var task_table=document.getElementById(resp["scrum"]["task_of"]);
		var sprint_row=document.getElementById(resp["scrum"]["id_rel_to"]);
		var sprint_rowindex=sprint_row.rowIndex;
		var tbl_row=task_table.insertRow(sprint_rowindex+1);
		tbl_row.id=resp["scrum"]["work_id"];
	
				tbl_row.insertCell(0).appendChild(document.createTextNode(resp["scrum"]["work_id"]));
				tbl_row.insertCell(1).appendChild(document.createTextNode(resp["scrum"]["workname"]));
				tbl_row.insertCell(2).appendChild(document.createTextNode(resp["scrum"]["desp"]));
				tbl_row.insertCell(3).appendChild(document.createTextNode(resp["scrum"]["module"]));
				tbl_row.insertCell(4).appendChild(document.createTextNode(resp["scrum"]["project"]));
				tbl_row.insertCell(5).appendChild(document.createTextNode(resp["scrum"]["dept"]));
				tbl_row.insertCell(6).appendChild(document.createTextNode(resp["scrum"]["st_date"]));
				tbl_row.insertCell(7).appendChild(document.createTextNode(resp["scrum"]["tg_date"]));
				tbl_row.insertCell(8).appendChild(document.createTextNode(resp["scrum"]["remarks"]));
				tbl_row.insertCell(9).appendChild(document.createTextNode(" "));
				tbl_row.insertCell(10).appendChild(document.createTextNode(resp["scrum"]["assign_to"]));
				tbl_row.insertCell(11).appendChild(document.createTextNode(resp["scrum"]["type"]));
		tasks_details[resp["scrum"]["task_of"]]["task_spr"][resp["scrum"]["id_rel_to"]]["spr_scr"][resp["scrum"]["work_id"]]=resp["scrum"]; 
		

	}
	else if(resp["action"]==="CREATE SPRINT")
	{
		var sp_of_t=resp["sprint"];
		//get table using task id (task_of)
		//and append sprint row to last
		var task_table=document.getElementById(resp["sprint"]["task_of"]);
		var tbl_row=document.createElement('tr');
		tbl_row.className = "Spr";
		tbl_row.id=resp["sprint"]["work_id"];
		tbl_row.insertCell(0).appendChild(document.createTextNode(resp["sprint"]["work_id"]));
		tbl_row.insertCell(1).appendChild(document.createTextNode(resp["sprint"]["workname"]));
		tbl_row.insertCell(2).appendChild(document.createTextNode(resp["sprint"]["desp"]));
		tbl_row.insertCell(3).appendChild(document.createTextNode(resp["sprint"]["module"]));
		tbl_row.insertCell(4).appendChild(document.createTextNode(resp["sprint"]["project"]));
		tbl_row.insertCell(5).appendChild(document.createTextNode(resp["sprint"]["dept"]));
		tbl_row.insertCell(6).appendChild(document.createTextNode(resp["sprint"]["st_date"]));
		tbl_row.insertCell(7).appendChild(document.createTextNode(resp["sprint"]["tg_date"]));
		tbl_row.insertCell(8).appendChild(document.createTextNode(resp["sprint"]["remarks"]));
		tbl_row.insertCell(9).appendChild(document.createTextNode(resp["sprint"]["incharge"]));
		tbl_row.insertCell(10).appendChild(document.createTextNode(resp["sprint"]["assign_to"]));
		tbl_row.insertCell(11).appendChild(document.createTextNode(resp["sprint"]["type"]));
		task_table.appendChild(tbl_row);
		tasks_details[resp["sprint"]["task_of"]]["task_spr"][resp["sprint"]["work_id"]]=resp["sprint"];
	}
	
	}


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
			          document.getElementById("module_for_scrum").options.length = 1;
			          document.getElementById("module_for_sprint").options.length=1;	
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
			        	 document.getElementById("module_for_scrum").options.add(new Option(jsonObj[key],key,true));
			        	 document.getElementById("module_for_sprint").options.add(new Option(jsonObj[key],key,true));
			        	 
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
	
	document.getElementById("search_by_id").options.length = 1;
	for(key in tasks_details)
	{
		if(key!=0)
		{	
			var tab_br=document.createElement("br");
			document.getElementById("search_by_id").options.add(new Option(key+"   "+tasks_details[key]["workname"]+","+tasks_details[key]["module"]+","+tasks_details[key]["project"],key,true));
				var div_to_write=document.getElementById(tasks_details[key]["status"]);
				//creating a table
				var create_table=document.createElement("table");
						create_table.id=key;
						var st_ch_btn;
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
						st_ch_btn=document.createElement('button');
						st_ch_btn.innerHTML="CHANGE STATUS";
						
						st_ch_btn.addEventListener('click',function(){showStChForm(this);});

						
						tbl_row.insertCell(12).appendChild(st_ch_btn);
				create_table.appendChild(tbl_row);	
				var tbl_row_index=tbl_row.rowIndex;
			var scr=tasks_details[key]["task_scr"];
						for(sid in scr)
						{
									
									let scr_row=create_table.insertRow(tbl_row_index+1);
									scr_row.id=sid;
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
									scr_row.insertCell(9).appendChild(document.createTextNode(" "));
									scr_row.insertCell(10).appendChild(document.createTextNode(scr[sid]["assign_to"]));
									scr_row.insertCell(11).appendChild(document.createTextNode(scr[sid]["type"]+" of TASK :- "+tasks_details[key]["workname"]));
											
						}
						
					var spr=tasks_details[key]["task_spr"];
					for(spr_id in spr)
					{
							var spr_row=document.createElement("tr");
							spr_row.id=spr_id;
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
									spr_row.insertCell(9).appendChild(document.createTextNode(spr[spr_id]["incharge"]));
									spr_row.insertCell(10).appendChild(document.createTextNode(spr[spr_id]["assign_to"]));
									spr_row.insertCell(11).appendChild(document.createTextNode(spr[spr_id]["type"]+"  of TASK :- "+tasks_details[key]["workname"]));
									create_table.appendChild(spr_row);
									var spr_row_rowIndex=spr_row.rowIndex;
							var spr_sc=spr[spr_id]["spr_scr"];
									for(spr_sc_id in spr_sc)
									{
											var spr_sc_row=create_table.insertRow(spr_row_rowIndex+1);
											spr_sc_row.id=spr_sc_id;
									spr_sc_row.insertCell(0).appendChild(document.createTextNode(spr_sc_id));
									spr_sc_row.insertCell(1).appendChild(document.createTextNode(spr_sc[spr_sc_id]["workname"]));
									spr_sc_row.insertCell(2).appendChild(document.createTextNode(spr_sc[spr_sc_id]["desp"]));
									spr_sc_row.insertCell(3).appendChild(document.createTextNode(spr_sc[spr_sc_id]["module"]));
									spr_sc_row.insertCell(4).appendChild(document.createTextNode(spr_sc[spr_sc_id]["project"]));
									spr_sc_row.insertCell(5).appendChild(document.createTextNode(spr_sc[spr_sc_id]["dept"]));
									spr_sc_row.insertCell(6).appendChild(document.createTextNode(spr_sc[spr_sc_id]["st_date"]));
									spr_sc_row.insertCell(7).appendChild(document.createTextNode(spr_sc[spr_sc_id]["tg_date"]));
									spr_sc_row.insertCell(8).appendChild(document.createTextNode(spr_sc[spr_sc_id]["remarks"]));
									spr_sc_row.insertCell(9).appendChild(document.createTextNode(" "));
									spr_sc_row.insertCell(10).appendChild(document.createTextNode(spr_sc[spr_sc_id]["assign_to"]));
									spr_sc_row.insertCell(11).appendChild(document.createTextNode(spr_sc[spr_sc_id]["type"]+" of Sprint:- "+spr[spr_id]["workname"]+" --OF TASK :-"+tasks_details[key]["workname"]));
													
		
									}
					}		
					var firstNode = div_to_write.getElementsByTagName('table')[0];
					div_to_write.insertBefore(create_table,firstNode);
					
					div_to_write.insertBefore(tab_br,create_table.nextSibling);			
						
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
  
 <form class="modal-content animate" id="createtaskform" method="get" action="insertData" target="insert_frame">

			<div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>
					
				<br>	
			 <div class="container">
			<input type="hidden" value="TASK" name="doaction"/>	
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
  
  <form class="modal-content animate" id="createprojectform"action="insertData" method="get"  target="insert_frame">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container" >
      
      
        <label for="uname"><b>Project Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="project_name" size=30  required autofocus/>
      
        <label for="uname"><b>Project Description</b></label>
      <input type="text" placeholder="Enter Project Description" name="project_desc" size=30  required />
      <input type="hidden" name="dept"value=<%=ud.getDept()%> />
      <input type="hidden" name="doaction" value="PROJECT"/>
      <input type="submit" value="ADD PROJECT"/>
      </div>

    </form>
    </div>
 
 <div id="id03" class="modal">
  
  <form class="modal-content animate" id="createmoduleform"action="insertData" method="get"  target="insert_frame">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container">
      
      
        <label for="uname"><b>Module Name</b></label>
      <input type="text" placeholder="Enter Module Name" name="mod_name" size=30  required autofocus/>
      
        <label for="uname"><b>Module Description</b></label>
      <input type="text" placeholder="Enter module Description" name="mod_desp" size=30  required autofocus/></br>
      				<input type="hidden" name="doaction" value="MODULE"/>
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
    	
    	<form class="modal-content animate" id="createsprintform" method="get" action="insertData" target="insert_frame">

			<div class="imgcontainer">
      <span onclick="document.getElementById('id04').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>
					
				<br>	
			 <div class="container">
			<input type="hidden" value="SPRINT" name="doaction"/>	
			SPRINT Name<font color="red" size=2>*</font> : <input id="taskname" name="taskname" size=30 required autofocus />
				<br>
                
               <!-- <input type="file" name="pic" accept="image/*" /> -->  
              		
              
                <input type="hidden" name="empID" value=<%=ud.getEid()%> size=30 required autofocus />
                <br>
					
					SPRINT Description : <font color="red" size=2>*</font>
					<textarea id="taskDesc" name="taskDesc" cols="30" rows="4" size=30 required autofocus></textarea>
						<br>
					TASK ID RELATED TO :-<input type="text" name="id_rel_to" placeholder="TASK-ID"/>	
				<br>
			
					Start Date<font color="red" size=2>*</font> : <input type="date" name="startDate" />
					<br>
					Target Date<font color="red" size=2>*</font> : <input type="date" name="targetDate" />
				<br>
					<input type="hidden" name="work_type" value="SPRINT"/>
					
				<br>
				   <select id="task_project_sprint" name="project" onchange="loadJSON(this.value)" >
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
                    
			
				<br>
					<select id="module_for_sprint" name="module">
							<option value="">-- Select --</option>
					</select>
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
					
				
			<input type="submit" value="CREATE SPRINT FOR TASK" />
		</form>
    	</div>
    </div>
    <div id="id05" class="modal">
    <!-- DAILY SCRUMS FORM -->	
  	<form class="modal-content animate" id="createscrumform" method="get" action="insertData" target="insert_frame">

			<div class="imgcontainer">
      <span onclick="document.getElementById('id05').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>
					
				<br>	
			 <div class="container">
			 <input type="button" onclick=scrumOfSprintForm() value="CHANGE FORM TO SCRUM OF SPRINT" />
			<input id="insertDataAction" type="hidden" value="SCRUMOFTASK" name="doaction"/>	
			SCRUM Name<font color="red" size=2>*</font> : <input id="taskname" name="taskname" size=30 required autofocus />
				<br>
                
               <!-- <input type="file" name="pic" accept="image/*" /> -->  
              		
              
                <input type="hidden" name="empID" value=<%=ud.getEid()%> size=30 required autofocus />
                <br>
					
					Scrum Description : <font color="red" size=2>*</font>
					<textarea id="taskDesc" name="taskDesc" cols="30" rows="4" size=30 required autofocus></textarea>
					<br>
					TASK ID RELATED TO :-<input type="text" name="id_rel_to" id="id_rel_to" />	
				<br>
			
					Start Date<font color="red" size=2>*</font> : <input type="date" name="startDate" />
					<br>
					Target Date<font color="red" size=2>*</font> : <input type="date" name="targetDate" />
				<br>
					<input type="hidden" name="work_type" value="SCRUM"/>
					
				<br>
				   <select id="task_project_scrum" name="project" onchange="loadJSON(this.value)" >
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
                    
			
				<br>
					<select id="module_for_scrum" name="module">
							<option value="">-- Select --</option>
					</select>
					<br>
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
					
				
			<input id="submitscrum" type="submit" value="CREATE SCRUM FOR TASK" />
		</form>
  				
     </div>
   </div>  

<%}catch(Exception e){e.printStackTrace();}%>
<select id="search_by_id" onChange=showTaskById(this.value)>
	<option value="">--SELECT--</option>
</select>
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
<script>
function changeStatus()
{
	var formch=document.forms.namedItem("changeStatus"); 
	var statusFormData=new FormData(formch);
	var statusData=new URLSearchParams(statusFormData);
		var http_request_new; 
	http_request_new= new XMLHttpRequest();
	    try{
	       // Opera 8.0+, Firefox, Chrome, Safari
	       http_request_new = new XMLHttpRequest();
	    }catch (e){
	       // Internet Explorer Browsers
	       try{ 
	          http_request_new = new ActiveXObject("Msxml2.XMLHTTP");
				
	       }catch (e) {
			
	          try{
	             http_request_new = new ActiveXObject("Microsoft.XMLHTTP");
	          }catch (e){
	             // Something went wrong
	             alert("Your browser broke!");
	             return false;
	          }
				
	       }
	    }
	    
		
	    http_request_new.onreadystatechange = function()
	    {
		
	       if (http_request_new.readyState == 4)
	       {
	          // Javascript function JSON.parse to parse JSON data
	          console.log(http_request_new.responseText);
	          var st_ch_res=JSON.parse(http_request_new.responseText);
	          if(st_ch_res.success)
	         	{	  
	          		socket.send(JSON.stringify(st_ch_res));  
	        	}    
	          // jsonObj variable now contains the data structure and can
	          // be accessed as jsonObj.Module 
	           
	        }
	     }
	 	
http_request_new.open('POST',"insertData",true);
http_request_new.send(statusData);

	
	}
function showStChForm(work_id)
{
	var w_id;
	if(work_id.parentNode.parentNode.id==="")
	{
		if(work_id.parentNode.parentNode.parentNode.tagName==="TABLE")
		{
			w_id=work_id.parentNode.parentNode.parentNode.id;
		}
		else
		{	
			if(work_id.parentNode.parentNode.parentNode.parentNode.tagName==="TABLE")
			{	w_id=work_id.parentNode.parentNode.parentNode.parentNode.id;}
		}	
	}
	else
	{
		w_id=work_id.parentNode.parentNode.id;
	}	
	
	var id_to_be_ch_st=document.getElementById('st_ch_id');
	id_to_be_ch_st.value=w_id.trim();
	var st_ch_fm=document.getElementById('status_change_form').style.display='block';
	
	/*button*/	console.log("  "+work_id.tagName+" "+work_id.id);
	/*td*/	console.log(work_id.parentNode.tagName+" "+work_id.parentNode.id);
	/*tr*/	console.log(work_id.parentNode.parentNode.tagName+" "+work_id.parentNode.parentNode.id);
	/*table or tbody*/	console.log(work_id.parentNode.parentNode.parentNode.tagName+"  "+work_id.parentNode.parentNode.parentNode.id);
	/*div*/	console.log(work_id.parentNode.parentNode.parentNode.parentNode.tagName+"   "+work_id.parentNode.parentNode.parentNode.parentNode.id);
	
}
	
</script>
<div id="status_change_form" class="modal"  style="display:none">
<form id="changeStatus" class="modal-content animate">
<div class="imgcontainer">
      <span onclick="document.getElementById('status_change_form').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

WORK-ID(Whose status is to be changed) : <input type="text" id="st_ch_id" name="work_id" value=""/>&nbsp&nbsp
<input type="hidden" name="doaction" value="changeStatus"/>
CHANGED STATUS	:<select name="status">
	<option value="todo">TO-DO</option>
	<option value="pending">PENDING</option>
	<option value="inprogress">IN-PROGRESS</option>
	<option value="testing">TESTING</option>
	<option value="completed">COMPLETED</option>	

</select>&nbsp&nbsp
REMARKS	:- <textarea name=remarks></textarea>	
&nbsp&nbsp&nbsp&nbsp<input type="button" value="CHANGE STATUS" onclick="changeStatus()" />

</form> 
</div>
</body>


</html>
