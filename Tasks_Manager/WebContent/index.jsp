<!DOCTYPE html>
<html lang="en">
<style>
body {font-family: Arial, Helvetica, sans-serif;
 background-image: url('images/pmt.jpg');
	background-size: cover;
 	
}

/* Full-width input fields */
input[type=text],input[type=submit], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border-radius:10%;
  box-sizing: border-box;
  text-align:center;
}

/* Set a style for all buttons */
button {
  background-color: white;
  color: black;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 1;
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
  width: 20%;
  opacity:1;
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

  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
	padding-top: 60px;
 	
}

/* Modal Content/Box */
.modal-content {
  background-color: #0A5757;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  opacity: 0.91;
  border-radius:50%;
 	height:50%;
  width: 50%; /* Could be more or less, depending on screen size */
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
</style>
<link rel="icon" href="images/project-management-tools.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="images/project-management-tools.ico" type="image/x-icon"/>	

<title>
LOGIN TO CRIS PMT
</title>
</head>
<body>

<div id="id01" class="modal">
  
  <form class="modal-content animate" action="login" method="post" >
    <div class="imgcontainer">
      <img src="images/cris.png" alt="CENTRE FOR RAILWAYS INFORMATION AND SYSTEMS" class="avatar">
    </div>

    <div class="container">
      <center><label for="username" style="color:#fefefe;"><b>EMPLOYEE - ID</b></label></center>
      <input type="text" placeholder="ENTER   EMPLOYEE - ID" name="username" required>
      <center><label for="password" style="color:white"><strong><b>Password</b></strong></label></center>
      <input type="password" placeholder="ENTER   PASSWORD" name="password" required>
        
      <input type="submit" style="color:black;font-weight:bolder;" value="LOGIN" />
      </div>

    </form>
</div>

</body>
</html>