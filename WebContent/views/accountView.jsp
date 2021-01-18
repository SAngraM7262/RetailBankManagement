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
<title>National Bank | Account View</title>

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

<div class="mdc-card mdc-elevation--z7" style="width: 50rem; padding : 20px; margin : 10px auto;">

<div class="mdc-tab-bar" role="tablist">
    <div class="mdc-tab-scroller__scroll-area">
      <div class="mdc-tab-scroller__scroll-content">
        <button class="mdc-tab mdc-tab--active" role="tab" aria-selected="true" tabindex="0" disabled>
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Account View</span>
          </span>
          <span class="mdc-tab-indicator mdc-tab-indicator--active">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
      </div>
    </div>
</div>
<br>

	  <div class="row">
	    <div class="col-sm">
	      	<img class="card-img-top" style="height : 400px; width : 400px" src="${pageContext.request.contextPath}/img/custmer.png" alt="Card image cap">
	      	
	    </div>
	    <div class="col-sm">
	      	  <span class = "font-weight-bold text-secondary">Customer Id :.</span> ${account.custId} <br><hr>
			  <span class = "font-weight-bold text-secondary">Account Id :.</span> ${account.accountId}<br><hr>
			  <span class = "font-weight-bold text-secondary"> Customer Name :.</span> ${account.custName} <br><hr>
			  <span class = "font-weight-bold text-secondary">Account Status :.</span> ${account.status}<br><hr>
			  <span class = "font-weight-bold text-secondary">Account Type :.</span> ${account.accountType}<br><hr>
			  <span class = "font-weight-bold text-secondary">Balance Amount :.</span> ${account.balance}<br><hr>
	    </div>
	  </div>
	  <div class="row" style="margin-top:20px;">
	  <div class="col-md"><span class="text-secondary">National Bank Account View</span></div>
	  <div class="col-md"><span class="text-secondary">
    	
    	<button type="button" class="btn mdc-elevation--z4 ml-4 btn-danger" data-toggle="modal" data-target="#deleteModal">DELETE</button>
    	
    	<button class="mdc-button mt-1" style = "margin-left:113px;" onclick="window.location.href='${pageContext.request.contextPath}/AccountStatusController'">
			  <div class="mdc-button__ripple"></div>
			  <span class="mdc-button__label">Get All Accounts</span>
			  <i class="material-icons mdc-button__icon" aria-hidden="true">chevron_right</i>
		</button>
	  </span></div>  	
    	
	  </div>
	
</div>




<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete Confirmation</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are you sure you want to Remove the customer Account completely?
      </div>
      <div class="modal-footer">
        <button type="button" class="mdc-button mdc-button--raised mdc-elevation--z4" data-dismiss="modal">Close</button>
        <a href="${pageContext.request.contextPath}/DeleteAccountController?id=${account.accountId}&accountType=${account.accountType}&action=delete" class="btn btn-danger mdc-elevation--z4">CONFIRM DELETE</a>
      </div>
    </div>
  </div>
</div>

</div>

</body>
</html>