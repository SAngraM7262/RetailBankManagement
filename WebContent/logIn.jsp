<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page import = "com.nationalbank.entity.Employee" %>
    <%
    	Employee currentEmp = (Employee)session.getAttribute("currentEmp");
    	if( currentEmp != null ){
    		switch(currentEmp.getEmpType()){
			case "customerExecutive" :
				response.sendRedirect("views/customerExecutiveOperations.jsp");
				break;
			case "cashier" :
				response.sendRedirect("views/cashierOperations.jsp");
				break;
			}
    	}
    %>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>National Bank | Employee Login</title>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/material-components-web.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="${pageContext.request.contextPath}/js/material-components-web.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<style>
.header{
width: 85rem;
 padding : 15px 20px;
  margin : 0px auto; 
  position : fixed;
  left : 0px;
  top : 0px;
  z-index : 99;
}

.body{
position : relative;
top : 130px;

}
</style>

</head>
<body style="background-color : rgb(227,242,253);">

<div class="mdc-card mdc-elevation--z7 header">
	       		<div class = "row">
	       			<div class = "col-md"><h2>National Bank </h2></div>
	       			<div class = "col-md" >	
	       			</div>
	       		</div>  
</div>

<div class = "body">
<c:choose>
	    <c:when test="${message != null}">
	       
			  <div class="alert alert-info alert-dismissible fade show" role="alert">
				  <h3>${message}</h3>
				  <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/logIn.jsp'" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
			  </div>
			
	        <br />
	    </c:when>    
</c:choose>

	<div class="mdc-card mdc-elevation--z7" style="width: 50rem; padding : 20px; margin : 10px auto;">
		<form action = "${pageContext.request.contextPath}/EmployeeLogInController" method = "POST">
		
		<div class = "row">
		<div class = "col-md-3">
			<img src="${pageContext.request.contextPath}/img/empIcon.png" class="mt-3" height = "200px" width = "200px">
		</div>
		<div class = "col-md-9">
				<div class="form-group">
			    <label for="employeeName" class = "font-weight-bold text-secondary">Employee Name :.</label>
			    <input type="text" class="form-control" id="empName" name = "empName" placeholder="Enter Employee Name" required>
			  </div>
			  <div class="form-group">
			    <label for="password" class = "font-weight-bold text-secondary">Password :.</label>
			    <input type="password" class="form-control" id="password" name = "password" placeholder="Enter Password" required>
			  </div>
			  <button type="submit" name="submitbtn" class="mdc-button mdc-button--raised mdc-elevation--z4"><span class="mdc-button__label">Log In</span></button>
			  <small id="logInHelp" class="form-text text-muted"><a href="#" data-toggle="modal" data-target="#deleteModal">Need Help To Log In...</a></small>
			  
		
				
		</div>
		</div>
			  		
		
		
		</form>
	</div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Log In Help</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Please enter the valid Credentials.
        In case the loss of User Name and Password kindly contact to Bank Manager
      </div>
      <div class="modal-footer">
        <button type="button" class="mdc-button mdc-button--raised mdc-elevation--z4" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
 
</body>
</html>