<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!--  <meta charset="utf-8"> -->
 
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
  <link rel="stylesheet" href="styling.css">  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://use.fontawesome.com/b0710fea57.js"></script>
  
  <script>
  $(document).ready(function(){
    $('#emptable').DataTable({
    	 "pageLength": 7
    });
   
   
});
  </script>

<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>Employees</title>
</head>

<body class="bg_container">
	<div class="container">
	<header>
		<h1 class="p-4 text-center text-white">Welcome to Employee Management Portal</h1>
	</header>
	
<div class="mb-4">
			<form class="form-inline" method="post" action="bulkupload.htm" enctype="multipart/form-data">
 				<div class="form-group mr-3">
   					<input class="btn btn-sm btn-outline-secondary" type="file" id="bulk" name="bulk" >
   					
 				</div>
 				 <button class="btn  btn-md btn-secondary ">Submit</button>
 				  <span class="text-center text-danger ml-4">${FAILURESTATUSMESSAGE}</span>
 				 <span class="text-center successmessage ml-4">${SUCCESSSTATUSMESSAGE}</span>
  </form>
 </div>
<table id="emptable" class="display" align="center">
<thead>
<tr >
<th  scope="col">EMPLOYEE ID</th>
<th  scope="col">NAME</th>
<th scope="col">DESIGNATION<br /></th>
<th  scope="col">LOCATION</th>
<th  scope="col">DELIVERY GROUP</th>
<th scope="col">TECHNOLOGY</th>
<th scope="col">UPDATE</th>
<th scope="col">DELETE</th>

</tr>
</thead>
<tbody>
<c:forEach var="i" items="${allemployee}">
<tr class="rows">
<td class="text-center">${i.empid}</td>
<td class="text-center">${i.name}</td>
<td class="text-center">${i.designation}</td>
<td class="text-center">${i.location}</td>
<td class="text-center">${i.deliveryGroup}</td>
<td class="text-center">${i.technology}</td>
<td class="text-center"><c:url var="updatepage" value="updatepage.htm">
<c:param name="employeeId" value="${i.empid}" />
</c:url> <a  href="${updatepage}"><i class="fa fa-pencil-square-o text-dark" aria-hidden="true"></i></a>
<td class="text-center"><c:url var="deletepage" value="deletepage.htm">
<c:param name="empid" value="${i.empid}" />
</c:url> <a style="color: #1e2571" href="${deletepage}"><i class="fa fa-trash text-dark" aria-hidden="true"></i></a>
</tr>

	

</c:forEach> 

</tbody>
</table>
<div class="text-center">
<a class="btn btn-primary text-light mr-4 " href="empform.jsp" role="button"><i class="fa fa-registered mr-2" aria-hidden="true"></i>Register</a>
<a class="btn btn-primary text-light mr-4" href="index.jsp" role="button"><i class="fa fa-home mr-2" aria-hidden="true"></i>Home</a>
</div>

<!--  div class="text-center text-warning">${STATUSMESSAGE}</div>-->
</div>

</body>
</html>