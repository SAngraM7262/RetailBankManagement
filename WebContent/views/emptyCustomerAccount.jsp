<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@page import = "com.nationalbank.entity.Employee" %>
    <%
    	Employee currentEmp = (Employee)session.getAttribute("currentEmp");
    	if( currentEmp == null ){
    		response.sendRedirect("logIn.jsp");
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>>National Bank | No Account Found</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/material-components-web.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="${pageContext.request.contextPath}/js/material-components-web.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


</head>
<body style="background-color : rgb(227,242,253);">

<div class = "container">
	<div class="mdc-card mdc-elevation--z7 d-flex align-items-center" style="width: 50rem; padding : 20px; margin : 10px auto;">
		
		<div class = "row">
			<button class="mdc-button" onclick="javascript:history.go(-1)">
			  <div class="mdc-button__ripple"></div>
			  <i class="material-icons mdc-button__icon" aria-hidden="true">chevron_left</i>
			  <span class="mdc-button__label">Search Again</span>
			</button>
			<hr style="width:20px;">
			<button class="mdc-button" onclick="window.location.href='${pageContext.request.contextPath}/AccountStatusController'">
			  <div class="mdc-button__ripple"></div>
			  <span class="mdc-button__label">Get All Accounts</span>
			  <i class="material-icons mdc-button__icon" aria-hidden="true">chevron_right</i>
			</button>
		</div>
		    
		  
			<img src = "${pageContext.request.contextPath}/img/norecord.png" alt = "No Record Found" style = "height : 400px;width : 400px;" />
			<span class = "text-secondary text-muted text-*-center font-weight-bold" style = "line-height: 20px;"> No Customer Account Found With Given Details</span>
		
		
	</div>
</div>


</body>
</html>