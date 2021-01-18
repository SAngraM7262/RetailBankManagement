package com.nationalbank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nationalbank.dao.DAO;
import com.nationalbank.entity.Account;
import com.nationalbank.service.CashierService;




public class CashierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//create referance objects
	static RequestDispatcher requestDispatcher = null;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String cid=req.getParameter("custId");
		String accid=req.getParameter("accId");
		res.setContentType("text/html");  
	    PrintWriter out = res.getWriter();
	    HttpSession session=req.getSession();
		
	    //validation for customer ID and Account ID
		if(accid.isEmpty() && cid.isEmpty())
		{
			out.println("<script type=\"text/javascript\">");
	        out.println("alert('Enter atleast one value');");
	        out.println("location='AccSearch.jsp';");
	        out.println("</script>");
	        
		}
		else if(cid.isEmpty()==false && accid.isEmpty())
		{
			if(cid.length()!=9)
			{
				out.println("<script type=\"text/javascript\">");
		        out.println("alert('Error! Customer Id is of 9 digits. Please Enter Correct Customer Id');");
		        out.println("location='AccSearch.jsp';");
		        out.println("</script>");
			}
			else 
			{
				
				try 
				{
				
						if(CashierService.customerIDpresent(cid))
						{
							session.setAttribute("customerId", cid);
							res.sendRedirect("CustomerAccounts.jsp");
							
						}
					
						else
						{
							out.println("<script type=\"text/javascript\">");
							out.println("alert('Error! Customer Id or SSN Id does not exist');");
							out.println("location='AccSearch.jsp';");
							out.println("</script>");
						}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		else if(accid.isEmpty()==false && cid.isEmpty()) //checking if only account value is entered
		{
			if(accid.length()!=9)//length of accountID should be 9
			{
				//displaying error for length not equal to 9
				out.println("<script type=\"text/javascript\">");
		        out.println("alert('Error! Account Id is of 9 digits. Please Enter Correct Account Id');");
		        out.println("location='AccSearch.jsp';");
		        out.println("</script>");
			}
			else 
			{
				try 
				{
					Account ob = new Account();
					int acc=Integer.parseInt(accid);
					ob=DAO.checkAccount(acc);
					if(ob!=null)
						{
						//System.out.println(ob.getCustName());
						
							session.setAttribute("custName",ob.getCustName());
							session.setAttribute("customerId",ob.getCustId());
							session.setAttribute("accountId",ob.getAccountId());
							session.setAttribute("accountType",ob.getAccountType());
							session.setAttribute("balance", ob.getBalance());
							res.sendRedirect("AccountInfo.jsp");
							
						}
					else
						{	
							out.println("<script type=\"text/javascript\">");
							out.println("alert('Error! Account Id does not exist');");
							out.println("location='AccSearch.jsp';");
							out.println("</script>");
						}
				}
				catch(Exception e) 
				{
					e.printStackTrace();
				}
				
			}
		}
		else
		{
			out.println("<script type=\"text/javascript\">");
	        out.println("alert('Error! Enter either Customer Id or Account Id');");
	        out.println("location='AccSearch.jsp';");
	        out.println("</script>");
		}
		
		out.close();  
		
		
		
	
	}

}
