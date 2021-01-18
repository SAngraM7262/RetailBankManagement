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
<title>Insert title here</title>

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

<script>

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
function dispNumber(){
	document.getElementById('NumTransactions').disabled = false; 
	document.getElementById('num').style.display = 'block';
	document.getElementById('NumTransactions').required=true;
	
	document.getElementById('StartDate').disabled = true; 
	document.getElementById('EndDate').disabled = true; 
	document.getElementById('StartDate').required=false;
	document.getElementById('EndDate').required=false;
	document.getElementById('dateP1').style.display = 'none';
	document.getElementById('dateP2').style.display = 'none';
	
}
function dispDates(){
	document.getElementById('NumTransactions').disabled = true;
	document.getElementById('num').style.display = 'none';
	document.getElementById('NumTransactions').required=false;
	
	document.getElementById('StartDate').disabled = false; 
	document.getElementById('EndDate').disabled = false; 
	document.getElementById('StartDate').required=true;
	document.getElementById('EndDate').required=true;
	document.getElementById('dateP1').style.display = 'block';
	document.getElementById('dateP2').style.display = 'block';
	
	
	
}
function defaultLoad()
{
	document.getElementById('num').style.display = 'none';
	document.getElementById('dateP1').style.display = 'none';
	document.getElementById('dateP2').style.display = 'none';

}

</script>
</head>
<body style="background-color : rgb(227,242,253);" onload="defaultLoad()">

<div class="mdc-card mdc-elevation--z7 header">
	       		<div class = "row">
	       			<div class = "col-md"><h2>National Bank </h2></div>
	       			<div class = "col-md" >
	       				<div style = "float : right;">
	       					<div class = "mr-4 text-muted" >Welcome!! <%= currentEmp.getEmpName() %></div>
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
        <button class="mdc-tab" onclick="window.location.href='${pageContext.request.contextPath}/AccSearch.jsp'" role="tab" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">    
            <span class="mdc-tab__text-label">Account Details</span>
          </span>
          <span class="mdc-tab-indicator ">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
         <button class="mdc-tab  mdc-tab--active" role="tab" onclick="window.location.href='${pageContext.request.contextPath}/accountStatement.jsp'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">         
            <span class="mdc-tab__text-label">Account Statement</span>
          </span>
          <span class="mdc-tab-indicator mdc-tab-indicator--active">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
      </div>
    </div>
  </div>
</div>
</nav>


<div class= "body">
<div class="mdc-card mdc-elevation--z7" style="width: 50rem; padding : 20px; margin : 10px auto;">

	<div class="mdc-tab-bar" role="tablist">
	    <div class="mdc-tab-scroller__scroll-area">
	      <div class="mdc-tab-scroller__scroll-content">
	        <button class="mdc-tab mdc-tab--active" role="tab" aria-selected="true" tabindex="0" disabled>
	          <span class="mdc-tab__content">
	            <span class="mdc-tab__text-label">Account Statement</span>
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
	 
	 	<form action = "${pageContext.request.contextPath}/PrintStatement" method = "POST">
		  <div class="form-group">
		    <label for="AccountId" class = "font-weight-bold text-secondary">Account Id :.</label>
		    <input type="number" class="form-control" name="accId" id="accId" min="-1" max="999999999" required onkeypress="return isNumberKey(event)" placeholder="Enter Account Id">
		    <small id="emailHelp" class="form-text text-muted">Enter 9 digit Account Id</small>
		  </div>
		  
		  <div class="form-group">
		  	<input onclick="dispNumber()" type="radio" id="TransactionNumber" name="printType" value="TransactionNumber" required  >
		    <label for="TransactionNumber" class = "font-weight-bold text-secondary">Last No of Transactions</label>
		  </div>
		  
		  <div class="form-group">
		  	<input onclick="dispDates()" type="radio" id="TransactionDate" name="printType" value="TransactionDate">
		    <label for="TransactionDate" class = "font-weight-bold text-secondary">Start-End Dates</label>
		  </div>
		  
		  <div class = "form-group" id="num">
		  	<label for="NumTransactions" class = "font-weight-bold text-secondary">Number of Transactions:</label>
			
			<select name="NumTransactions" class = "form-control" id="NumTransactions" disabled>
  					<option value="1">1</option>
  					<option value="2">2</option>
  					<option value="3">3</option>
  					<option value="4">4</option>
  					<option value="5">5</option>
  					<option value="6">6</option>
  					<option value="7">7</option>
  					<option value="8">8</option>
  					<option value="9">9</option>
  					<option value="10">10</option>
  					
			</select>
		  </div>
		  
		  <div id="dateP1" class = "form-group">
		  		<label for="StartDate" class = "font-weight-bold text-secondary">Start Date:</label>
				<input type="date" class = "form-control" name="StartDate" id="StartDate"  disabled >
		  </div>
		  
		  <div id="dateP2" class = "form-group">
		  		<label for="EndDate" class = "font-weight-bold text-secondary">EndDate:</label>
				<input type="date" class = "form-control" name="EndDate" id="EndDate"  disabled >
		  </div>
		  
		  <button type="submit" value="GET STATEMENT" class="mdc-button mdc-button--raised mdc-elevation--z4"><span class="mdc-button__label">Get Account Statement</span></button>
		</form>

</div>
</div>


</body>
</html>