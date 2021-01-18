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
<title>National Bank | Add Customer</title>

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
	       				
	       					<button class="mdc-button mdc-button--raised mdc-elevation--z4 mr-4"  onclick="window.location.href='${pageContext.request.contextPath}/AccountStatusController'">
		       					<span class="mdc-button__label"> Manage Account Details</span>
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
        <button class="mdc-tab " onclick="window.location.href='${pageContext.request.contextPath}/CustomerStatusController'" role="tab" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">    
            <span class="mdc-tab__text-label">View Customer Status</span>
          </span>
          <span class="mdc-tab-indicator">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
         <button class="mdc-tab mdc-tab--active" role="tab" onclick="window.location.href='${pageContext.request.contextPath}/views/addCustomer.jsp'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">         
            <span class="mdc-tab__text-label">Add Customer</span>
          </span>
          <span class="mdc-tab-indicator mdc-tab-indicator--active">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
        <button class="mdc-tab" role="tab" onclick="window.location.href='${pageContext.request.contextPath}/views/searchForUpdate.jsp'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Edit Customer Details</span>
          </span>
          <span class="mdc-tab-indicator">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
        <button class="mdc-tab" role="tab" onclick="window.location.href='${pageContext.request.contextPath}/views/deleteCustomer.jsp'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">            
            <span class="mdc-tab__text-label">Delete Customer Customer</span>
          </span>
          <span class="mdc-tab-indicator">
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


<div class="mdc-card mdc-elevation--z7" style="width: 50rem; padding : 20px; margin : 10px auto;">

	<div class="mdc-tab-bar" role="tablist">
	    <div class="mdc-tab-scroller__scroll-area">
	      <div class="mdc-tab-scroller__scroll-content">
	        <button class="mdc-tab mdc-tab--active" role="tab" aria-selected="true" tabindex="0" disabled>
	          <span class="mdc-tab__content">
	            <span class="mdc-tab__text-label">Get The New Customer Details</span>
	          </span>
	          <span class="mdc-tab-indicator mdc-tab-indicator--active">
	            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
	          </span>
	          <span class="mdc-tab__ripple"></span>
	        </button>
	      </div>
	    </div>
	</div>
	
	 
	   <div class="alert alert-info" role="alert">
  		  <c:forEach items = "${errors}" var = "error">
  		  	<ul>
  		  		<li> ${error} </li>
  		  	</ul>
  		  </c:forEach>
	  </div>  
	
	
	
	<form action = "${pageContext.request.contextPath}/AddCustomerController" method = "POST">
	<div class="row">
		<div class= "col-md">
			<div class="form-group">
			    <label for="customerSSNId" class = "font-weight-bold text-secondary"><span class="text-danger">*</span> Customer SSN Id :.</label>
			    <input type="number" class="form-control" id="custSSNId" name = "custSSNId" min="1" max="999999999" value = "${customer.custSSNId}" placeholder="Enter Customer SSNId" required>
			</div>
		</div>
	</div>
	<div class="row">
		<div class= "col-md">
			<div class="form-group">
			    <label for="customerName" class = "font-weight-bold text-secondary"><span class="text-danger">*</span> Customer Name :.</label>
			    <input type="text" class="form-control" id="custName" name = "custName" value = "${customer.custName}" placeholder="Enter Customer Name" required>
			</div>
		</div>
		<div class= "col-md">
			<div class="form-group">
			    <label for="customerAge" class = "font-weight-bold text-secondary"><span class="text-danger">*</span> Customer Age :.</label>
			    <input type="number" class="form-control" id="custAge" name = "custAge" min="1" max="100" value = "${customer.custAge}" placeholder="Enter Customer Age" required>
			</div>
		</div>
	</div>
	<div class="row">
		<div class= "col-md">
			<div class="form-group">
			    <label for="customerAddress" class = "font-weight-bold text-secondary"><span class="text-danger">*</span> Customer Address :.</label>
			    <input type="text" class="form-control" id="custAddress" name = "custAddress"value = "${customer.custAddress}" placeholder="Enter Customer Address" required>
			</div>
		</div>
	</div>
	<div class="row">
		<div class= "col-md">
			<div class="form-group">
			    <label for="customerState" class = "font-weight-bold text-secondary"><span class="text-danger">*</span> Customer State :.</label>
			    <input type="text" class="form-control" id="custState" name = "custState" value = "${customer.custState}" placeholder="Enter Customer State" required>
			</div>
		</div>
		<div class= "col-md">
			<div class="form-group">
			    <label for="customerCity" class = "font-weight-bold text-secondary"><span class="text-danger">*</span> Customer City :.</label>
			    <input type="text" class="form-control" id="custCity" name = "custCity" value = "${customer.custCity}" placeholder="Enter customer City" required>
			</div>
		</div>
	</div>
	  
	  
	  <button type="submit" class="mdc-button mdc-button--raised mdc-elevation--z4"><span class="mdc-button__label"> Add Customer</span></button>
	  <button type="reset" class="mdc-button mdc-button--raised mdc-elevation--z4 ml-4"><span class="mdc-button__label"> Clear Form</span></button>
	  
	  <small class="form-text text-muted"><span class="text-danger">*</span> Fields are mandatory</small>
	</form>

</div>

</div>

</body>
</html>