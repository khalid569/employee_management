<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Registration</title>
<link rel="stylesheet" href="styling.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
  <script src="https://use.fontawesome.com/b0710fea57.js"></script>
</head>

<body class="bg_container">
	<div class="container">

	<header>
		<h1 class="p-4 text-center text-white">Register New Employee</h1>
	</header>
	<a class="btn btn-primary text-light mr-4" href="index.jsp" role="button"><i class="fa fa-home mr-2" aria-hidden="true"></i>Home</a>
	<form name="form1" modelAttribute="employee" class=" registration_form" action="insert.htm" method="POST">
		<div class="form-group form-inline">
			<label class="label mr-5 pr-2" for="text">Name  </label>
			<input class="form-control ml-5 mb-3 w-50" type="text" name="name" placeholder="Enter Name" required/>
		</div>
		<div class="form-group form-inline">	
			<label class="label mr-1" for="text">Designation</label>
			<input class="form-control ml-5 mb-3 w-50" type="text" name="designation" placeholder="Enter Designation" required/>
		</div>
		<div class="form-group form-inline">	
			<label class="label mr-2 " for="text">Technology</label>
			<input class="form-control ml-5 mb-3 w-50" type="text" name="technology" placeholder="Enter Technology" required/>
		</div>
			<div class="form-group form-inline">
				<label  class="label" for="text ">Delivery Group</label>
				<input class="form-control ml-4 mb-3 w-50" type="text" name="deliveryGroup" placeholder="Enter Delivery Group" required />
			</div>
			<div class="form-group form-inline">
				<label  class="label mr-4 pr-2" for="text">Location</label>
				<select class="form-control ml-5 mb-3 w-50" id="loc" name="location">
					<option value="bangalore">Bangalore</option>
					<option value="pune">Pune</option>
					<option value="kolkata">Kolkata</option>
					<option value="chennai">Chennai</option>
					<option value="hyderabad">Hyderabad</option>
				</select>
			</div>
	<div class=" register_button">
	<button type="submit" class="btn btn-md btn-primary mr-5" id="submitbutton" value="Register">Submit</button>
	<input class="btn btn-md btn-primary ml-5" type="reset" value="Reset">	
	</div>				
	<div class="alert ">
     ${REGISTRATIONSTATUSMESSAGE}
    </div>

		
	</form>
</div>


</body>
</html>