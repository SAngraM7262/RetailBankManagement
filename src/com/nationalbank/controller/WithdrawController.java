package com.nationalbank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nationalbank.dao.DAO;




public class WithdrawController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int accid=(Integer)session.getAttribute("accountId");
		String acc=String.valueOf(accid);
		PrintWriter out = response.getWriter();
		int wtAmount=Integer.parseInt(request.getParameter("wtamount"));
		try {
			if(DAO.withdraw(wtAmount,accid))
			{
				try {
					session.setAttribute("balance", DAO.getCurrBalance(acc));
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Amount withdrawn successfully');");
				 out.println("location='AccountInfo.jsp';");
				out.println("</script>");
				
				
				
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Error! Withdraw not allowed, please choose smaller amount');");
				 out.println("location='Withdraw.jsp';");
				out.println("</script>");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
