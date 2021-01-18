package com.nationalbank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nationalbank.dao.DAO;
import com.nationalbank.entity.Account;



public class RedirectAccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String acc = req.getParameter("accounts");
		//System.out.println("value check:"+acc);
		int accid=Integer.parseInt(acc);
		PrintWriter out = res.getWriter();
		HttpSession session=req.getSession();
		session.setAttribute("accountId", accid);
		try 
		{
			Account ob = new Account();
			
			ob=DAO.checkAccount(accid);
			if(ob!=null)
				{
					
					session.setAttribute("customerName",ob.getCustName());
					session.setAttribute("customerId",ob.getCustId());
					session.setAttribute("accountId",ob.getAccountId());
					session.setAttribute("accountType",ob.getAccountType());
					session.setAttribute("balance", ob.getBalance());
					res.sendRedirect("AccountInfo.jsp");
					//RequestDispatcher rs = req.getRequestDispatcher("AccountInfo.jsp");
					//rs.include(req, res);
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
