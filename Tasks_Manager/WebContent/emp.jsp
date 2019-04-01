<%@page import="common.CommonDetails"%>
<%@page import="model.UserDet"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="works.TaskDB"%>
<%@page import="works.Sprint"%>
<%@page import="works.Tasks"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%UserDet ud=(UserDet)session.getAttribute("UserDet");%>
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
function eid_to_name(eid_arr)
{
	var emp_names="";
		for(eid_no in eid_arr)
		{
			if(eid_no==0)
			{
				emp_names=dep_emp[eid_arr[eid_no]];	
			}
			else
			{
				emp_names=emp_names+","+dep_emp[eid_arr[eid_no]];
			}	
			
		}
		return emp_names;
}

function access_emp()
{
	
	<%
	String json_emp="";
	if(CommonDetails.dep_emp.containsKey(ud.getDept()))
	{
		
		HashMap<Integer,String> hm_emp=CommonDetails.dep_emp.get(ud.getDept());
		json_emp=new Gson().toJson(hm_emp,hm_emp.getClass().getGenericSuperclass());
	}
	else
	{
		json_emp="{\" \":\" \"}";
	}
	%>
		return <%=json_emp%>;  	   
	
}
var dep_emp=access_emp();

var selected_view="all";
var page_type="employee";
var socketURI="ws://localhost:8686/Tasks_Manager/Notify?eid=<%=ud.getEid()%>&dept=<%=ud.getDept()%>&page_type=employee";
var socket = new WebSocket(socketURI);
socket.onmessage=received;

function received(event)//HANDLE THE WEB SOCKET MESSAGE RECEIVED
{
	var rec_event=event.data;
	var resp=JSON.parse(rec_event);
	console.log(JSON.stringify(resp));
		if(resp["action"]==="CREATE TASK")
		{	
			var incharge_array=resp["task"]["incharge"];
			if(incharge_array.includes(<%=ud.getEid()%>))
			{
				tasks_incharge[resp["task"]["work_id"]]=resp["task"];
				all_tasks[resp["task"]["work_id"]]=resp["task"];
			}
			var assign_to_array=resp["task"]["assign_to"];
			if(assign_to_array.includes(<%=ud.getEid()%>))
			{
				all_tasks[resp["task"]["work_id"]]=resp["task"];
			}
				if(selected_view==="incharge")
				{
					
					var nxt_incharge_array=resp["task"]["incharge"];
					if(nxt_incharge_array.includes(<%=ud.getEid()%>))
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
								if("incharge" in resp["task"])
								{	
									tbl_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(resp["task"]["incharge"])));
								}
								else
								{
									tbl_row.insertCell(9).appendChild(document.createTextNode(" "));
								}	
								tbl_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(resp["task"]["assign_to"])));
								tbl_row.insertCell(11).appendChild(document.createTextNode(resp["task"]["type"]));
								var st_ch_btn=document.createElement('button');
								st_ch_btn.innerHTML="CHANGE STATUS";
								
								st_ch_btn.addEventListener('click',function(){showStChForm(this);});

								
								tbl_row.insertCell(12).appendChild(st_ch_btn);
								tbl_row.insertCell(13).appendChild(document.createTextNode(resp["task"]["status"]));
						create_table.appendChild(tbl_row);
						var firstNode = div_to_write.getElementsByTagName('table')[0];
						div_to_write.insertBefore(create_table,firstNode);

						 
					}	
					 
				}
				else if(selected_view==="all")
				{
					if(resp["task"]["work_id"] in all_tasks)
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
								if("incharge" in resp["task"])
								{	
									tbl_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(resp["task"]["incharge"])));
								}
								else
								{
									tbl_row.insertCell(9).appendChild(document.createTextNode(" "));
								}	
								tbl_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(resp["task"]["assign_to"])));
								tbl_row.insertCell(11).appendChild(document.createTextNode(resp["task"]["type"]));
								var st_ch_btn=document.createElement('button');
								st_ch_btn.innerHTML="CHANGE STATUS";
								
								st_ch_btn.addEventListener('click',function(){showStChForm(this);});

								
								tbl_row.insertCell(12).appendChild(st_ch_btn);
								tbl_row.insertCell(13).appendChild(document.createTextNode(resp["task"]["status"]));
						create_table.appendChild(tbl_row);
						var firstNode = div_to_write.getElementsByTagName('table')[0];
						div_to_write.insertBefore(create_table,firstNode);
						//tasks_details[resp["task"]["work_id"]]=resp["task"];
				  }
				}	
		}
		else if(resp["action"]==="CREATE SCRUMOFTASK")
		{
			var scot_id=resp["scrum"]["work_id"];
			var scot_tid=resp["scrum"]["task_of"];
			if(scot_tid in tasks_incharge)	
			{
				tasks_incharge[scot_tid]["task_scr"][scot_id]=resp["scrum"];
				
			}	
			var assign_to_arr=resp["scrum"]["assign_to"];
			if(assign_to_arr.includes(<%=ud.getEid()%>))
			{	
				all_tasks[scot_id]=resp["scrum"];
			}
			if(selected_view==="incharge")
			{
				if(scot_tid in tasks_incharge)
				{
					var scot_tab=document.getElementById(scot_tid);
					var fst_row=scot_tab.getElementsByTagName('tr')[0];
					var fst_row_index=fst_row.rowIndex;
					var scot_row=scot_tab.insertRow(fst_row_index+1);
					scot_row.id=resp["scrum"]["work_id"];
					scot_row.insertCell(0).appendChild(document.createTextNode(resp["scrum"]["work_id"]));
					scot_row.insertCell(1).appendChild(document.createTextNode(resp["scrum"]["workname"]));
					scot_row.insertCell(2).appendChild(document.createTextNode(resp["scrum"]["desp"]));
					scot_row.insertCell(3).appendChild(document.createTextNode(resp["scrum"]["module"]));
					scot_row.insertCell(4).appendChild(document.createTextNode(resp["scrum"]["project"]));
					scot_row.insertCell(5).appendChild(document.createTextNode(resp["scrum"]["dept"]));
					scot_row.insertCell(6).appendChild(document.createTextNode(resp["scrum"]["st_date"]));
					scot_row.insertCell(7).appendChild(document.createTextNode(resp["scrum"]["tg_date"]));
					scot_row.insertCell(8).appendChild(document.createTextNode(resp["scrum"]["remarks"]));
					if("incharge" in resp["scrum"])
					{	
						scot_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["incharge"])));
					}
					else
					{
						scot_row.insertCell(9).appendChild(document.createTextNode(" "));
					}	
					scot_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["assign_to"])));
					scot_row.insertCell(11).appendChild(document.createTextNode(resp["scrum"]["type"]));
					var st_ch_btn=document.createElement('button');
					st_ch_btn.innerHTML="CHANGE STATUS";
					
					st_ch_btn.addEventListener('click',function(){showStChForm(this);});

					
					scot_row.insertCell(12).appendChild(st_ch_btn);

					scot_row.insertCell(13).appendChild(document.createTextNode(resp["scrum"]["status"]));	

				}	
			}
			else if(selected_view==="all")
			{
				
				if(resp["scrum"]["work_id"] in all_tasks)
				{	
					var div_to_write=document.getElementById(resp["scrum"]["status"]);
					//creating a table
					var create_table=document.createElement("table");
							create_table.id=resp["scrum"]["work_id"];		                                                   		
							console.log(create_table.id);
					var tbl_row=document.createElement("tr");
					tbl_row.className = "task";
							tbl_row.insertCell(0).appendChild(document.createTextNode(resp["scrum"]["work_id"]));
							tbl_row.insertCell(1).appendChild(document.createTextNode(resp["scrum"]["workname"]));
							tbl_row.insertCell(2).appendChild(document.createTextNode(resp["scrum"]["desp"]));
							tbl_row.insertCell(3).appendChild(document.createTextNode(resp["scrum"]["module"]));
							tbl_row.insertCell(4).appendChild(document.createTextNode(resp["scrum"]["project"]));
							tbl_row.insertCell(5).appendChild(document.createTextNode(resp["scrum"]["dept"]));
							tbl_row.insertCell(6).appendChild(document.createTextNode(resp["scrum"]["st_date"]));
							tbl_row.insertCell(7).appendChild(document.createTextNode(resp["scrum"]["tg_date"]));
							tbl_row.insertCell(8).appendChild(document.createTextNode(resp["scrum"]["remarks"]));
							if("incharge" in resp["scrum"])
							{	
								tbl_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["incharge"])));
							}
							else
							{
								tbl_row.insertCell(9).appendChild(document.createTextNode(" "));
							}	
							tbl_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["assign_to"])));
							tbl_row.insertCell(11).appendChild(document.createTextNode(resp["scrum"]["type"]));
							var st_ch_btn=document.createElement('button');
							st_ch_btn.innerHTML="CHANGE STATUS";
							
							st_ch_btn.addEventListener('click',function(){showStChForm(this);});

							
							tbl_row.insertCell(12).appendChild(st_ch_btn);
							tbl_row.insertCell(13).appendChild(document.createTextNode(resp["scrum"]["status"]));

					create_table.appendChild(tbl_row);
					var firstNode = div_to_write.getElementsByTagName('table')[0];
					div_to_write.insertBefore(create_table,firstNode);
				}	
			}
		}
		else if(resp["action"]==="CREATE SCRUMOFSPRINT")
		{
			var scosp_id=resp["scrum"]["work_id"];
			var scosp_tid=resp["scrum"]["task_of"];
			var scosp_sp_id=resp["scrum"]["id_rel_to"];
			if(scosp_tid in tasks_incharge)
			{
				tasks_incharge[scosp_tid]["task_spr"][scosp_sp_id]["spr_scr"][scosp_id]=resp["scrum"];
				
			}
			else if(scosp_sp_id in sprints_incharge)
			{
				sprints_incharge[scosp_sp_id]["spr_scr"][scosp_id]=resp["scrum"];
			}
			var assign_to_arr=resp["scrum"]["assign_to"];
			if(assign_to_arr.includes(<%=ud.getEid()%>))
			{	
				all_tasks[scosp_id]=resp["scrum"];
				
			}
			
			if(selected_view==="incharge")
			{
				if(scosp_tid in tasks_incharge)
				{
					var scosp_sp_table=document.getElementById(scosp_tid);
					var scosp_sp_tr=document.getElementById(scosp_sp_id);
					var scosp_fst_row_index=scosp_sp_tr.rowIndex;
					var scosp_row=scosp_sp_table.insertRow(scosp_fst_row_index+1);
					scosp_row.id=resp["scrum"]["work_id"];
					scosp_row.insertCell(0).appendChild(document.createTextNode(resp["scrum"]["work_id"]));
					scosp_row.insertCell(1).appendChild(document.createTextNode(resp["scrum"]["workname"]));
					scosp_row.insertCell(2).appendChild(document.createTextNode(resp["scrum"]["desp"]));
					scosp_row.insertCell(3).appendChild(document.createTextNode(resp["scrum"]["module"]));
					scosp_row.insertCell(4).appendChild(document.createTextNode(resp["scrum"]["project"]));
					scosp_row.insertCell(5).appendChild(document.createTextNode(resp["scrum"]["dept"]));
					scosp_row.insertCell(6).appendChild(document.createTextNode(resp["scrum"]["st_date"]));
					scosp_row.insertCell(7).appendChild(document.createTextNode(resp["scrum"]["tg_date"]));
					scosp_row.insertCell(8).appendChild(document.createTextNode(resp["scrum"]["remarks"]));
					if("incharge" in resp["scrum"])
					{	
						scosp_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["incharge"])));
					}
					else
					{
						scosp_row.insertCell(9).appendChild(document.createTextNode(" "));
					}	
					scosp_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["assign_to"])));
					scosp_row.insertCell(11).appendChild(document.createTextNode(resp["scrum"]["type"]));
					var st_ch_btn=document.createElement('button');
					st_ch_btn.innerHTML="CHANGE STATUS";
					
					st_ch_btn.addEventListener('click',function(){showStChForm(this);});

					
					scosp_row.insertCell(12).appendChild(st_ch_btn);
					scosp_row.insertCell(13).appendChild(document.createTextNode(resp["scrum"]["status"]));


				}			
				else if(scosp_sp_id in sprints_incharge)
				{
					var scosp_tab=document.getElementById(scosp_sp_id);
					var scosp_fst_row=scosp_tab.getElementsByTagName('tr')[0];
					var scosp_fst_row_index=scosp_fst_row.rowIndex;
					var scosp_row=scosp_tab.insertRow(scosp_fst_row_index+1);
					scosp_row.id=resp["scrum"]["work_id"];
					scosp_row.insertCell(0).appendChild(document.createTextNode(resp["scrum"]["work_id"]));
					scosp_row.insertCell(1).appendChild(document.createTextNode(resp["scrum"]["workname"]));
					scosp_row.insertCell(2).appendChild(document.createTextNode(resp["scrum"]["desp"]));
					scosp_row.insertCell(3).appendChild(document.createTextNode(resp["scrum"]["module"]));
					scosp_row.insertCell(4).appendChild(document.createTextNode(resp["scrum"]["project"]));
					scosp_row.insertCell(5).appendChild(document.createTextNode(resp["scrum"]["dept"]));
					scosp_row.insertCell(6).appendChild(document.createTextNode(resp["scrum"]["st_date"]));
					scosp_row.insertCell(7).appendChild(document.createTextNode(resp["scrum"]["tg_date"]));
					scosp_row.insertCell(8).appendChild(document.createTextNode(resp["scrum"]["remarks"]));
					if("incharge" in resp["scrum"])
					{	
						scosp_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["incharge"])));
					}
					else
					{
						scosp_row.insertCell(9).appendChild(document.createTextNode(" "));
					}	
					scosp_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["assign_to"])));
					scosp_row.insertCell(11).appendChild(document.createTextNode(resp["scrum"]["type"]));
					var st_ch_btn=document.createElement('button');
					st_ch_btn.innerHTML="CHANGE STATUS";
					
					st_ch_btn.addEventListener('click',function(){showStChForm(this);});

					
					scosp_row.insertCell(12).appendChild(st_ch_btn);
					scosp_row.insertCell(13).appendChild(document.createTextNode(resp["scrum"]["status"]));

				}	
			}
			else if(selected_view==="all")
			{
				if(resp["scrum"]["work_id"] in all_tasks)
				{	
					
					var div_to_write=document.getElementById(resp["scrum"]["status"]);
					//creating a table
					var create_table=document.createElement("table");
							create_table.id=resp["scrum"]["work_id"];		                                                   		
							console.log(create_table.id);
					var tbl_row=document.createElement("tr");
					tbl_row.className = "task";
							tbl_row.insertCell(0).appendChild(document.createTextNode(resp["scrum"]["work_id"]));
							tbl_row.insertCell(1).appendChild(document.createTextNode(resp["scrum"]["workname"]));
							tbl_row.insertCell(2).appendChild(document.createTextNode(resp["scrum"]["desp"]));
							tbl_row.insertCell(3).appendChild(document.createTextNode(resp["scrum"]["module"]));
							tbl_row.insertCell(4).appendChild(document.createTextNode(resp["scrum"]["project"]));
							tbl_row.insertCell(5).appendChild(document.createTextNode(resp["scrum"]["dept"]));
							tbl_row.insertCell(6).appendChild(document.createTextNode(resp["scrum"]["st_date"]));
							tbl_row.insertCell(7).appendChild(document.createTextNode(resp["scrum"]["tg_date"]));
							tbl_row.insertCell(8).appendChild(document.createTextNode(resp["scrum"]["remarks"]));
							if("incharge" in resp["scrum"])
							{	
								tbl_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["incharge"])));
							}
							else
							{
								tbl_row.insertCell(9).appendChild(document.createTextNode(" "));
							}	
							tbl_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(resp["scrum"]["assign_to"])));
							tbl_row.insertCell(11).appendChild(document.createTextNode(resp["scrum"]["type"]));
							var st_ch_btn=document.createElement('button');
							st_ch_btn.innerHTML="CHANGE STATUS";
							
							st_ch_btn.addEventListener('click',function(){showStChForm(this);});

							
							tbl_row.insertCell(12).appendChild(st_ch_btn);
							tbl_row.insertCell(13).appendChild(document.createTextNode(resp["scrum"]["status"]));

					create_table.appendChild(tbl_row);
					var firstNode = div_to_write.getElementsByTagName('table')[0];
					div_to_write.insertBefore(create_table,firstNode);
				}	
			}
		}
		else if(resp["action"]==="CREATE SPRINT")
		{
			
			var sp_id=resp["sprint"]["work_id"];
			var sp_tid=resp["sprint"]["task_of"];
			if(sp_tid in tasks_incharge)
			{
				tasks_incharge[sp_tid]["task_spr"][sp_id]=resp["sprint"];
				
			}
			else
			{
				var spr_inch_arr=resp["sprint"]["incharge"];
				if(spr_inch_arr.includes(<%=ud.getEid()%>))
				{
					sprints_incharge[sp_id]=resp["sprint"];
					all_tasks[sp_id]=resp["sprint"];
				}
				
			}
			var spr_assign_arr=resp["sprint"]["assign_to"];
			if(spr_assign_arr.includes(<%=ud.getEid()%>))
			{
				all_tasks[sp_id]=resp["sprint"];
			}
			if(selected_view==="incharge")
			{
				if(sp_tid in tasks_incharge)
				{
					incharge();
					
				}
				else if(sp_id in sprints_incharge)
				{	
				sprint_incharge("cl");/////////////////////////////////////////////////////  
				}
			}
		
			else if(selected_view==="all")
			{
				if(sp_id in all_tasks)
				{	
					var div_to_write=document.getElementById(resp["sprint"]["status"]);
					//creating a table
					var create_table=document.createElement("table");
							create_table.id=resp["sprint"]["work_id"];		                                                   		
							console.log(create_table.id);
					var tbl_row=document.createElement("tr");
					tbl_row.className = "task";
							tbl_row.insertCell(0).appendChild(document.createTextNode(resp["sprint"]["work_id"]));
							tbl_row.insertCell(1).appendChild(document.createTextNode(resp["sprint"]["workname"]));
							tbl_row.insertCell(2).appendChild(document.createTextNode(resp["sprint"]["desp"]));
							tbl_row.insertCell(3).appendChild(document.createTextNode(resp["sprint"]["module"]));
							tbl_row.insertCell(4).appendChild(document.createTextNode(resp["sprint"]["project"]));
							tbl_row.insertCell(5).appendChild(document.createTextNode(resp["sprint"]["dept"]));
							tbl_row.insertCell(6).appendChild(document.createTextNode(resp["sprint"]["st_date"]));
							tbl_row.insertCell(7).appendChild(document.createTextNode(resp["sprint"]["tg_date"]));
							tbl_row.insertCell(8).appendChild(document.createTextNode(resp["sprint"]["remarks"]));
							if("incharge" in resp["sprint"])
							{	
								tbl_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(resp["sprint"]["incharge"])));
							}
							else
							{
								tbl_row.insertCell(9).appendChild(document.createTextNode(" "));
							}	
							tbl_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(resp["sprint"]["assign_to"])));
							tbl_row.insertCell(11).appendChild(document.createTextNode(resp["sprint"]["type"]));
							var st_ch_btn=document.createElement('button');
							st_ch_btn.innerHTML="CHANGE STATUS";
							
							st_ch_btn.addEventListener('click',function(){showStChForm(this);});

							
							tbl_row.insertCell(12).appendChild(st_ch_btn);
							tbl_row.insertCell(13).appendChild(document.createTextNode(resp["sprint"]["status"]));

					create_table.appendChild(tbl_row);
					var firstNode = div_to_write.getElementsByTagName('table')[0];
					div_to_write.insertBefore(create_table,firstNode);
				}
	
			}		
			
		}
		else if(resp["action"]==="STATUSCHANGE")
		{
			var w_id=resp["w_id"];
			var r_ch_st=resp["status"];
			if(w_id.startsWith("T="))
			{
				if(w_id in tasks_incharge)
				{
					tasks_incharge[w_id]["status"]=r_ch_st;
				}
			}
			else if(w_id.startsWith("S="))
			{
					var split=w_id.split("T=");
					var t_id="T="+split[1];
					var sprint_id=w_id;
					if(t_id in tasks_incharge)
					{
						tasks_incharge[t_id]["task_spr"][sprint_id]["status"]=r_ch_st;
					}
					else if(sprint_id in sprints_incharge)
					{
						sprints_incharge[sprint_id]["status"]=r_ch_st;
					}
					
					
			}
			else if(w_id.startsWith("SC="))
			{
				var split=w_id.split("T=");
				var t_id="T="+split[1];
				var scrum_id=w_id;
				if(t_id in tasks_incharge)
				{
					tasks_incharge[t_id]["task_scr"][scrum_id]["status"]=r_ch_st;
				}
			}
			else if(w_id.startsWith("SR="))
			{				
				var split_for_task=w_id.split("T=");
				var t_id="T="+split_for_task[1];
				var scrum_of_sprint_id=w_id;
				var split_for_sprint=w_id.split("S=");
				var spr_id="S="+split_for_sprint[1];
				if(t_id in tasks_incharge)
				{
					tasks_incharge[t_id]["task_spr"][spr_id]["spr_scr"][scrum_of_sprint_id]["status"]=r_ch_st;
				}
				else if(spr_id in sprints_incharge)
				{
					sprints_incharge[spr_id]["spr_scr"][scrum_of_sprint_id]["status"]=r_ch_st;
				}
			}
			if(w_id in all_tasks)
			{	
				all_tasks[w_id]["status"]=r_ch_st;
			}
			if(selected_view==="all")
			{
				all_tasks_create();
			}
			else
			{
				incharge();
			}
		}	

}		


function emptyDivs()
{
	var tables = document.getElementsByTagName("TABLE");
	for (var i=tables.length-1; i>=0;i-=1)
	   if (tables[i]) tables[i].parentNode.removeChild(tables[i]);
	var arr=document.getElementsByTagName("BR");
	for (var i=arr.length-1; i>=0;i-=1)
	{ if (arr[i]) arr[i].parentNode.removeChild(arr[i]);}

	}
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
	selected_view="incharge";
	console.log(selected_view);
	emptyDivs();
	
	for(key in tasks_incharge)
	{
		if(key!=0)
		{	
				var div_to_write=document.getElementById(tasks_incharge[key]["status"]);
				//creating a table
				var create_table=document.createElement("table");
						create_table.id=key;
						var st_ch_btn;
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
						tbl_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(tasks_incharge[key]["incharge"])));
						tbl_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(tasks_incharge[key]["assign_to"])));
						tbl_row.insertCell(11).appendChild(document.createTextNode(tasks_incharge[key]["type"]));
						st_ch_btn=document.createElement('button');
						st_ch_btn.innerHTML="CHANGE STATUS";
						st_ch_btn.addEventListener('click',function(){showStChForm(this);});
						tbl_row.insertCell(12).appendChild(st_ch_btn);
						tbl_row.insertCell(13).appendChild(document.createTextNode(tasks_incharge[key]["status"]));
						
				create_table.appendChild(tbl_row);		
				var tbl_row_index=tbl_row.rowIndex;
			var scr=tasks_incharge[key]["task_scr"];
						for(sid in scr)
						{
							let scr_row=create_table.insertRow(tbl_row_index+1);
									scr_row.id=sid
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
									scr_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(scr[sid]["assign_to"])));
									scr_row.insertCell(11).appendChild(document.createTextNode(scr[sid]["type"]+" of TASK :- "+tasks_incharge[key]["workname"]));
									st_ch_btn=document.createElement('button');
									st_ch_btn.innerHTML="CHANGE STATUS";
									st_ch_btn.addEventListener('click',function(){showStChForm(this);});
									scr_row.insertCell(12).appendChild(st_ch_btn);
									scr_row.insertCell(13).appendChild(document.createTextNode(scr[sid]["status"]));
						}
						
					var spr=tasks_incharge[key]["task_spr"];
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
									spr_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(spr[spr_id]["incharge"])));
									spr_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(spr[spr_id]["assign_to"])));
									spr_row.insertCell(11).appendChild(document.createTextNode(spr[spr_id]["type"]+"  of TASK :- "+tasks_incharge[key]["workname"]));
									st_ch_btn=document.createElement('button');
									st_ch_btn.innerHTML="CHANGE STATUS";
									st_ch_btn.addEventListener('click',function(){showStChForm(this);});
									spr_row.insertCell(12).appendChild(st_ch_btn);
									spr_row.insertCell(13).appendChild(document.createTextNode(spr[spr_id]["status"]));

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
										spr_sc_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(spr_sc[spr_sc_id]["assign_to"])));
										spr_sc_row.insertCell(11).appendChild(document.createTextNode(spr_sc[spr_sc_id]["type"]+" of Sprint:- "+spr[spr_id]["workname"]+" --OF TASK :-"+tasks_incharge[key]["workname"]));
										st_ch_btn=document.createElement('button');
										st_ch_btn.innerHTML="CHANGE STATUS";
										st_ch_btn.addEventListener('click',function(){showStChForm(this);});
										spr_sc_row.insertCell(12).appendChild(st_ch_btn);
										spr_sc_row.insertCell(13).appendChild(document.createTextNode(spr_sc[spr_sc_id]["status"]));
										
		
									}
					}			
					var firstNode = div_to_write.getElementsByTagName('table')[0];
					div_to_write.insertBefore(create_table,firstNode);
					var tab_br=document.createElement("br");
					div_to_write.insertBefore(tab_br,create_table.nextSibling);			

		}	
			
		
		
	}
	
		
	sprint_incharge("cl");
	
	
}
function sprint_incharge(clname)
{
				
					for(spr_id in sprints_incharge)
					{
						if(spr_id!=0)
						{	
							var div_to_write=document.getElementById(sprints_incharge[spr_id]["status"]);
							//creating a table
							var create_table=document.createElement("table");
									create_table.id=spr_id;
									//create_table.className=cl_name;
									console.log(create_table.id);
													
							var spr_row=document.createElement("tr");
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
									spr_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(sprints_incharge[spr_id]["incharge"])));
									spr_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(sprints_incharge[spr_id]["assign_to"])));
									spr_row.insertCell(11).appendChild(document.createTextNode(sprints_incharge[spr_id]["type"]));
									var st_ch_btn=document.createElement('button');
									st_ch_btn.innerHTML="CHANGE STATUS";
									st_ch_btn.addEventListener('click',function(){showStChForm(this);});
									spr_row.insertCell(12).appendChild(st_ch_btn);
									spr_row.insertCell(13).appendChild(document.createTextNode(sprints_incharge[spr_id]["status"]));
									
									create_table.appendChild(spr_row);	
									var spr_sc=sprints_incharge[spr_id]["spr_scr"];
									for(spr_sc_id in spr_sc)
									{
										var spr_sc_row=document.createElement("tr");
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
									spr_sc_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(spr_sc[spr_sc_id]["assign_to"])));
									spr_sc_row.insertCell(11).appendChild(document.createTextNode(spr_sc[spr_sc_id]["type"]));
									var st_ch_btn=document.createElement('button');
									st_ch_btn.innerHTML="CHANGE STATUS";
									st_ch_btn.addEventListener('click',function(){showStChForm(this);});
									spr_sc_row.insertCell(12).appendChild(st_ch_btn);
									spr_sc_row.insertCell(13).appendChild(document.createTextNode(spr_sc[spr_sc_id]["status"]));
									
									create_table.appendChild(spr_sc_row);				
				
									}
							
						div_to_write.appendChild(create_table);	
						var tab_br=document.createElement("br");
						div_to_write.appendChild(tab_br);
					}
				}
				
}
function all_tasks_create()
{
	selected_view="all";
	console.log(selected_view);
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
									
					var spr_row=document.createElement("tr");
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
					if("incharge" in all_tasks){spr_row.insertCell(9).appendChild(document.createTextNode(" "));}
					else{spr_row.insertCell(9).appendChild(document.createTextNode(eid_to_name(all_tasks[key]["incharge"])));}
					spr_row.insertCell(10).appendChild(document.createTextNode(eid_to_name(all_tasks[key]["assign_to"])));
					spr_row.insertCell(11).appendChild(document.createTextNode(all_tasks[key]["type"]));
					
					var st_ch_btn=document.createElement('button');
					st_ch_btn.innerHTML="CHANGE STATUS";
					st_ch_btn.addEventListener('click',function(){showStChForm(this);});
					spr_row.insertCell(12).appendChild(st_ch_btn);
					spr_row.insertCell(13).appendChild(document.createTextNode(all_tasks[key]["status"]));
					
					create_table.appendChild(spr_row);	
					var firstNode = div_to_write.getElementsByTagName('table')[0];
					div_to_write.insertBefore(create_table,firstNode);
					var tab_br=document.createElement("br");
					div_to_write.insertBefore(tab_br,create_table.nextSibling);			

				
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
<script>
function changeStatus()
{
	var formch=document.getElementById("changeStatus"); 
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