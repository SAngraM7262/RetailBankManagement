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
<title>National Bank | Edit Customer Details</title>

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


<div class="mdc-card mdc-elevation--z7" style="width: 70rem; padding : 20px; margin : 10px auto;">

<div class="mdc-tab-bar" role="tablist">
    <div class="mdc-tab-scroller__scroll-area">
      <div class="mdc-tab-scroller__scroll-content">
        <button class="mdc-tab mdc-tab--active" role="tab" aria-selected="true" tabindex="0" disabled>
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Edit Customer Details</span>
          </span>
          <span class="mdc-tab-indicator mdc-tab-indicator--active">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
      </div>
    </div>
</div>

  
 <c:choose>  
    <c:when test="${flag == false}">  
       	  <div class="alert alert-info" role="alert">
	  		  <c:forEach items = "${errors}" var = "error">
	  		  	<ul>
	  		  		<li> ${error} </li>
	  		  	</ul>
	  		  </c:forEach>
		  </div> 
    </c:when>  
    
    <c:otherwise>  
        
    </c:otherwise>  
</c:choose>

		  

 
<br>

<form action = "${pageContext.request.contextPath}/UpdateCustomerController"  method = "POST">

	  <div class="row">
	    <div class="col-sm">
	      	<img class="card-img-top" style="height : 400px; width : 400px" src="${pageContext.request.contextPath}/img/custmer.png" alt="Card image cap">
	      	
	    </div>
	    <div class="col-sm">
	      	  <span class = "font-weight-bold text-secondary">Customer Id :.</span> ${customer.custId}<input type = "hidden" class="form-control" name = "custId" value = "${customer.custId}"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <span class = "font-weight-bold text-secondary">Customer SSNId :.</span> ${customer.custSSNId}<input type = "hidden" class="form-control" name = "custSSNId" value = "${customer.custSSNId}"><br>
			  <span class = "font-weight-bold text-secondary"> Customer Name :.</span> <input type = "text" class="form-control" name = "custName" value = "${customer.custName}" > <br>
			  <span class = "font-weight-bold text-secondary">Customer Age :.</span> <input type = "text" class="form-control" name = "custAge" min="1" max="100" value = "${customer.custAge}"><br>
			  <span class = "font-weight-bold text-secondary">Customer Address :.</span> <input type = "text" class="form-control" name = "custAddress" value ="${customer.custAddress}" ><br>
			  <span class = "font-weight-bold text-secondary">Customer State :.</span> <input type = "text" class="form-control" name = "custState" value = "${customer.custState}"><br>
			  <span class = "font-weight-bold text-secondary">Customer City :.</span> <input type = "text" class="form-control" name = "custCity" value = "${customer.custCity}">
	    </div>
	  </div>
	  <div class="row" style="margin-top:20px;">
	  <div class="col-md"><span class="text-secondary">National Bank Employee</span></div>
	  <div class="col-md">
    	
    	<button type="submit" class="mdc-button mdc-button--raised mdc-elevation--z4 ml-4" data-toggle="modal" data-target="#deleteModal">Save Customer Details</button>
		<button type = "reset" class="mdc-button mdc-button--raised mdc-elevation--z4 ml-4" onclick="javascript:history.go(-1)" >Cancel&nbsp;&nbsp;<i class="material-icons mdc-button__icon" aria-hidden="true">chevron_right</i> </button>
		    	
    	
    	
	  </div>
	 
	  	
    	
	  </div>
	</form>
</div>





</div>


</body>
</html>