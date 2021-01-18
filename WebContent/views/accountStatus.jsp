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
<title>National Bank | Account Status</title>

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
        <button class="mdc-tab mdc-tab--active" onclick="window.location.href='#''" role="tab" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">      
            <span class="mdc-tab__text-label">View Account Status</span>
          </span>
          <span class="mdc-tab-indicator mdc-tab-indicator--active">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
         <button class="mdc-tab" role="tab" onclick="window.location.href='${pageContext.request.contextPath}/AddAccountController'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Create Account</span>
          </span>
          <span class="mdc-tab-indicator">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
        <button class="mdc-tab" role="tab" onclick="window.location.href='${pageContext.request.contextPath}/DeleteAccountController?action=default'" aria-selected="true" tabindex="0">
          <span class="mdc-tab__content">
            <span class="mdc-tab__text-label">Delete Account</span>
          </span>
          <span class="mdc-tab-indicator">
            <span class="mdc-tab-indicator__content mdc-tab-indicator__content--underline"></span>
          </span>
          <span class="mdc-tab__ripple"></span>
        </button>
        
        <button class="mdc-tab " role="tab" onclick="window.location.href='${pageContext.request.contextPath}/views/searchCustomerAccount.jsp'" aria-selected="true" tabindex="0">
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

<c:choose>
	    <c:when test="${message != null}">
	       
			  <div class="alert alert-info alert-dismissible fade show" role="alert">
				  <h3>${message}</h3>
				  <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/AccountStatusController'" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
			  </div>
			
	        <br />
	    </c:when>    
</c:choose>

<div class="mdc-card mdc-elevation--z7" style="width: 75rem; padding : 30px 50px; margin:20px auto;">


<div class = "form-group row">
<label for="numberOfRows" class="font-weight-bold text-secondary text-muted"> Select number of Rows </label>&nbsp;&nbsp;&nbsp;
	<select name="state" id="maxRows" class = "form-control" style = "width : 150px;">
		<option value = "50000">Show All</option>
		<option value = "1">1</option>
		<option value = "2">2</option>
		<option value = "3">3</option>
		<option value = "4">4</option>
		<option value = "5">5</option>
	</select>
</div>

<table class = "table table-hover" id = "accountsTable">
<thead >
	 <tr class = "text-center">
    <th>Customer ID</th>
    <th>Account Id</th>
    <th>Account Type</th>
    <th>Account Status</th>
    <th>message</th>
    <th>Last Updated</th>
    <th>Operation</th>
    
  </tr>
</thead>
 <tbody>
  <c:forEach items = "${accountList}" var = "account">
  	 <tr class = "text-center">
    <td>${account.custId}</td>
    <td>${account.accountId}</td>
    <td>${account.accountType}</td>
    <td>${account.status}</td>
    <td>${account.message}</td>
    <td>${account.lastUpdated}</td>
    <td>
    		<button class="mdc-button" onclick="window.location.href='${pageContext.request.contextPath}/AccountStatusController'">
			  <div class="mdc-button__ripple"></div>
			  <span class="mdc-button__label text-small">Refresh</span>
			  <i class="material-icons mdc-button__icon" aria-hidden="true">refresh</i>
			</button>

    </td>
  </tr>
  </c:forEach>
 </tbody>
</table>

<div class = "pagination-container">
	<nav>
		<ul class="pagination"></ul>
	</nav>
</div>

<script>
	var table = '#accountsTable'
	$('#maxRows').on('change',function(){
		$('.pagination').html('')
		var trnum = 0;
		var maxRows = parseInt($(this).val())
		var totalRows = $(table+' tbody tr').length
		$(table+' tr:gt(0)').each(function(){
			trnum++
			if(trnum > maxRows){
				$(this).hide()
			}
			if(trnum <= maxRows){
				$(this).show()
			}
		})
		if(totalRows > maxRows){
			var pagenum = Math.ceil(totalRows/maxRows)
			for(var i=1;i<=pagenum;){
				$('.pagination').append('<li class = "page-item" data-page="'+i+'">\<span class="page-link">'+ i++ +'<span class="sr-only">(current)</span></span>\</li>').show()
				
			}
		}
		$('.pagination li:first-child').addClass('active')
		$('.pagination li').on('click',function(){
			var pageNum = $(this).attr('data-page')
			var trIndex = 0
			$('.pagination li').removeClass('active')
			$(this).addClass('active')
			$(table+' tr:gt(0)').each(function(){
				trIndex++
				if(trIndex > (maxRows*pageNum) || trIndex <= ((maxRows*pageNum)-maxRows)){
					$(this).hide()
				}else{
					$(this).show()
				}
			})
		})
	})

</script>
</div>


</div>




</body>
</html>