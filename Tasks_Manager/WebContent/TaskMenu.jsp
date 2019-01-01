<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=text], input[type=text], input[type=text] {
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
</head>
<body>


<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">project</button>

<div id="id01" class="modal">
  
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container">
      <label for="uname"><b>Project Id</b></label>
      <input type="text" placeholder="Project Id" name="Pid" required>
      
        <label for="uname"><b>Project Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="uname" required>
      
        <label for="uname"><b>Project Discription</b></label>
      <input type="text" placeholder="Enter Project Discription" name="uname" required>
      

     required>
        
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

<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">project</button>

<div id="id01" class="modal">
  
  <form class="modal-content animate" action="/action_page.php">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    
    </div>

    <div class="container">
      <label for="uname"><b>Project Id</b></label>
      <input type="text" placeholder="Project Id" name="Pid" required>
      
         <label for="uname"><b>Module Id</b></label>
      <input type="text" placeholder="Project Id" name="Pid" required> 
      
        <label for="uname"><b>Module Name</b></label>
      <input type="text" placeholder="Enter Project Name" name="uname" required>
      
        <label for="uname"><b>Module Discription</b></label>
      <input type="text" placeholder="Enter Project Discription" name="uname" required>
      

     required>
        
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

<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">project</button>

<div id="id01" class="modal">
  
  <form name="frm" id="createform" method="post" action="registerTask" >
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
					<td><input type="date" name="startDate" />
				</tr>
				<tr>
					<td>Target Date<font color="red" size=2>*</font> : </td> 
					<td><input type="date" name="targetDate" /></td>
				</tr>
			
				<tr>
					<td>Type:------->>>>>Task <font color="red" size=2></font>
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


<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>

</body>
</html>
