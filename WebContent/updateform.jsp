<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h1 class="p-4 text-center text-white">Update Employee Details</h1>
	</header>

		<a class="btn btn-primary text-light mr-4" href="index.jsp" role="button"><i class="fa fa-home mr-2" aria-hidden="true"></i>Home</a>

		<form name="form1" action="modify.htm" class="updation_form"  method="POST">
			<div class="form-group form-inline">
				<label class="label " for="text">Employee ID</label>
			<input class="form-control ml-5 mb-3 w-50" type="text" name="empid" value="${emp.empid}" readonly />
			</div>
			
			<div class="form-group form-inline">
				<label class="label mr-5 pr-3" for="text">Name</label>
				<input class="form-control ml-5 mb-3 w-50" type="text" name="name" value="${emp.name}" />
			</div>
			
			<div class="form-group form-inline">
				<label class="label mr-1 pr-2" for="form-group ">Designation</label>
				<input class="form-control ml-5 mb-3 w-50" type="text" name="designation" value="${emp.designation }" />
			</div>
			
			<div class="form-group form-inline">
				<label class="label mr-2 pr-2" for="text">Technology</label>
				<input class="form-control ml-5 mb-3 w-50" type="text" name="technology" value="${emp.technology }" />
			</div>
			
			<div class="form-group form-inline">
				<label class="label pr-0" for="text">Delivery Group</label>
				<input class="form-control ml-4 pr-2 mb-3 w-50" type="text" name="deliveryGroup" value="${emp.deliveryGroup}" />
			</div>
			
			<div class="form-group form-inline">
				<label class="label mr-4 pr-2" for="text">Location</label>
				<input class="form-control ml-5 mb-3 w-50" type="text" name="location" value="${emp.location }" />
			</div>
	
		<button type="submit" class="btn btn-lg btn-primary" id="updatebutton" value="update"><i class="fa fa-pencil-square-o mr-2" aria-hidden="true"></i>Update</button>
			
 
  <div class="alert ">
   ${UPDATESTATUSMESSAGE}
  </div>
	

	

	</form>
</div>
</body>
</html>