<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.nationalbank.entity.Account"%>
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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="ISO-8859-1">
<title>National Bank | Account Statement</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/material-components-web.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="${pageContext.request.contextPath}/js/material-components-web.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>




<script type="text/javascript">


function exportTableToExcel(tableID, filename = ''){
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    
    filename = filename?filename+'.xls':'TransactionStatement.xls';
    
    // Create download link element
    downloadLink = document.createElement("a");
    
    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
}
</script>

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

	<%
		String value = (String)request.getAttribute("value");
		String sdate = (String)request.getAttribute("sdate");
		String edate = (String)request.getAttribute("edate");
		String ntr = (String)request.getAttribute("ntr");
		

		
		
	%>


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

<div class = "body">
<div class="mdc-card mdc-elevation--z7" style="width: 75rem; padding : 30px 50px; margin:20px auto;">
<div id = "statement">
<div class = "row">
<div class = "col-md">
<h3>National Bank</h3>
<small class="text-muted">Account Statement</small>
</div>
<div class = "col-md">
<span style = "float : right;"><span class = "font-weight-bold text-secondary"> Customer Name :.</span> ${account.custName} </span><br>
<span style = "float : right;"><span class = "font-weight-bold text-secondary">Customer Id :.</span> ${account.custId} </span><br>
<span style = "float : right;"><span class = "font-weight-bold text-secondary">Account Id :.</span> ${account.accountId}</span><br>
<span style = "float : right;"><span class = "font-weight-bold text-secondary">Account Type :.</span> ${account.accountType}</span><br>
</div>
</div>

<table class = "table table-hover">
<thead>
	<tr>
    <th>Transaction ID</th>
    <th>Description</th>
    <th>Date (YYYY-MM-DD)</th>
    <th>Amount</th>
  	</tr>
</thead>
<tbody>
  <c:forEach var="list" items="${tList}" >
  
  <tr>
    <td >${list.getTransactionId()}</td>
    <td>${list.getTDescription()}</td>
    <td>${list.getTransactionDate()}</td>
    <td>${list.getTAmount()}</td>
  </tr>
  
  </c:forEach>
</tbody>
  
  

</table>
</div>

<p>
<input type="submit" class = "mdc-button mdc-button--raised mdc-elevation--z4 mr-4" value="Download as PDF" style = "float : right;"  id="btnPdf" onclick="Export()" />

<input type="submit" class = "mdc-button mdc-button--raised mdc-elevation--z4 mr-4" value="Download as Excel" style = "float : right;" name="download" onclick="exportTableToExcel('statement')" />
</p>
<button class="mdc-button mt-1" style = "margin-left:660px; margin-right:30px;"  onclick="window.location.href='${pageContext.request.contextPath}/AccountInfo.jsp'">
	<div class="mdc-button__ripple"></div>
	<i class="material-icons mdc-button__icon" aria-hidden="true">chevron_left</i>
	<span class="mdc-button__label">Back To Accounts</span>
					  
</button>

</div>


</div>



<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
    <script type="text/javascript">
        function Export() {
            html2canvas(document.getElementById('statement'), {
                onrendered: function (canvas) {
                    var data = canvas.toDataURL();
                    var docDefinition = {
                        content: [{
                            image: data,
                            width: 500
                        }]
                    };
                    pdfMake.createPdf(docDefinition).download("TransactionStatement.pdf");
                }
            });
        }
    </script>
</body>
</html>