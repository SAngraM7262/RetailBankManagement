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
<title>National Bank | No Customer Present</title>

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
.navbar1{
margin: 50px auto 20px auto;
position : fixed;
top : 30px;
left : 0px;
z-index : 98;
width : 100%;
background : white;
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
	       				<div style = "float : right;">
	       					<div class = "mr-4 text-muted" >Welcome!! <%= currentEmp.getEmpName() %></div>
	       				
	       					<button class="mdc-button mdc-button--raised mdc-elevation--z4 mr-4"  onclick="window.location.href='${pageContext.request.contextPath}/CustomerStatusController'">
		       					<span class="mdc-button__label"> Manage Customer Information</span>
		       				</button>
		       				<button class="mdc-button mdc-button--raised mdc-elevation--z4 mr-4" style = "float : right;"  onclick="window.location.href='${pageContext.request.contextPath}/EmployeeLogOutController'">
		       					<span class="mdc-button__label"> Log Out</span>
		       				</button>
	       				</div>
	       				
	       			</div>
	       		</div>  
</div>

<nav class="navbar1">
<div class="mdc-tab-bar" role="tablist">
  <div class="mdc-tab-scroller">
    <div class="mdc-tab-scroller__scroll-area">
      <div class="mdc-tab-scroller__scroll-content">
        <button class="mdc-tab " onclick="window.location.href='#''" role="tab" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">      
            <span class="mdc-tab__text-label">View Account Status</span>
          </span>
          <span class="mdc-tab-indicator ">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
         <button class="mdc-tab mdc-tab--active" role="tab" onclick="window.location.href='${pageContext.request.contextPath}/AddAccountController'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Create Account</span>
          </span>
          <span class="mdc-tab-indicator mdc-tab-indicator--active">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
        <button class="mdc-tab" role="tab" onclick="window.location.href='#'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Delete Account</span>
          </span>
          <span class="mdc-tab-indicator">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
        <button class="mdc-tab " role="tab" onclick="window.location.href='#'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Search Account</span>
          </span>
          <span class="mdc-tab-indicator ">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
      </div>
    </div>
  </div>
</div>
</nav>
	
	
	
	
<div class = "body">
<div class="mdc-card mdc-elevation--z7 d-flex align-items-center" style="width: 50rem; padding : 20px; margin : 10px auto;"> 
			<img src = "${pageContext.request.contextPath}/img/norecord.png" alt = "No Record Found" style = "height : 400px;width : 400px;" />
			<span class = "text-secondary text-muted text-*-center font-weight-bold" style = "line-height: 20px;">
			No Customer Present !! Kindly Add Customer Details First
			</span>
	</div>
</div>
</div>



</body>
</html>