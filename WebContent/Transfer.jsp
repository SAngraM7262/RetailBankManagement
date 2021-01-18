<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.nationalbank.dao.DAO" %>
<%@page import="com.nationalbank.entity.Account" %>
<%@page import="java.util.ArrayList"%>
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
<meta charset="UTF-8">
<title>National Bank | Transfer Money</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/material-components-web.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="${pageContext.request.contextPath}/js/material-components-web.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<script>
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}


</script>

</head>
<body style="background-color : rgb(227,242,253);">


<div class = "container">

<div class="mdc-card mdc-elevation--z7" style="width: 50rem; padding : 20px; margin : 10px auto;">

<div class="mdc-tab-bar" role="tablist">
    <div class="mdc-tab-scroller__scroll-area">
      <div class="mdc-tab-scroller__scroll-content">
        <button class="mdc-tab mdc-tab--active" role="tab" aria-selected="true" tabindex="0" disabled>
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Transfer Money</span>
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

<form action="${pageContext.request.contextPath}/TransferController" method="post">

<% int custId=(Integer)session.getAttribute("customerId");
ArrayList<Account> list=DAO.accounts(custId);
%>
	  <div class="row">
	    <div class="col-sm">
	      	  
	      	  <span class = "font-weight-bold text-secondary">Customer Id :.</span> <%=custId%> <br><hr>
			  
			  
			  <div class="form-group">
			    <label for="sid" class = "font-weight-bold text-secondary"><span class="text-danger">*</span> Choose Source Account :.</label>
				<select name="sid" id="sid" class = "form-control" required>
				<%
				for(Account a :list)
				{
				%>
				
				<option value=<%=a.getAccountId() %>><%=a.getAccountId() %> </option>
				<%} %>
				
				</select>
			  </div>
			  
			  <div class="form-group">
			    <label for="tid" class = "font-weight-bold text-secondary"><span class="text-danger">*</span> Choose Target Account :.</label>
				<select name="tid" id="tid" class = "form-control" required>
				<%
				for(Account a :list)
				{
				%>
				
				<option value=<%=a.getAccountId() %>><%=a.getAccountId() %> </option>
				<%} %>
				
				</select>
			  </div>
			  
			  <div class="form-group">
			    <label for="transferAmount" class = "font-weight-bold text-secondary">Transfer Amount :.</label>
			    <input class = "form-control" type="number" name="tramount" id="tramount"  required onkeypress="return isNumberKey(event)" placeholder="Enter Transfer Amount">
			  </div>
	    </div>
	  </div>
	  <div class="row" style="margin-top:20px;">
	  <div class="col-md">
	  			<button class="mdc-button mt-1" style = "margin-left:100px;"  onclick="window.location.href='${pageContext.request.contextPath}/AccountInfo.jsp'">
					  <div class="mdc-button__ripple"></div>
					  <i class="material-icons mdc-button__icon" aria-hidden="true">chevron_left</i>
					  <span class="mdc-button__label">Cancel Transaction</span>
					  
				</button>
				<span class="text-secondary">National Bank Customer Account Transaction</span>
	  </div>
	  <div class="col-md"><span class="text-secondary">
		
				<button type = "submit" value="Transfer" class="btn mdc-button mdc-button--raised mdc-elevation--z4" style = "margin-left:100px;">
				  Transfer Amount
			</button>
			
			
				 
			
		
		

	  </span></div>
	  </div>
</form>
	
</div>


</div>

<!--  -->

</body>
</html>