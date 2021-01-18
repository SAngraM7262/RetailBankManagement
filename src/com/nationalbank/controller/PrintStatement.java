package com.nationalbank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nationalbank.dao.AccountDAO;
import com.nationalbank.dao.AccountDAOImpl;
import com.nationalbank.entity.Account;
import com.nationalbank.entity.TransactionSet;
import com.nationalbank.service.CashierService;



public class PrintStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static AccountDAO accountDAO = null;
	
	public PrintStatement() {
		accountDAO = new AccountDAOImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String accid=req.getParameter("accId");
		String value=req.getParameter("printType");
		String sdate=req.getParameter("StartDate");
		String edate=req.getParameter("EndDate");
		String ntr=req.getParameter("NumTransactions");
		
		HttpSession session=req.getSession();
		int accountID = Integer.parseInt(accid);
		Account a = new Account();
		
		a = accountDAO.getAccountForPrint(accountID);
		session.setAttribute("account", a);
		
		
		PrintWriter out = res.getWriter();
		
		
		try 
		{
			
			ArrayList<TransactionSet> tList = CashierService.accStatement(accid,value,sdate,edate,ntr);
			
			if(tList!=null)
			{
				
					//redirect to transaction number statements
					
		            req.setAttribute("tList", tList);
		            req.setAttribute("value",value);
		            req.setAttribute("sdate",sdate);
		            req.setAttribute("edate",edate);
		            req.setAttribute("ntr",ntr);
		            
		            
		            RequestDispatcher dispatcher = req.getRequestDispatcher("TransactionPrintStatements.jsp");
		            dispatcher.forward(req, res);
		 
				
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
		        out.println("alert('Error! Account Id does not exist');");
		        out.println("location='accountStatement.jsp';");
		        out.println("</script>");
			}
			
		}
		catch(Exception e)
		{
			
		}
	
	}

}
