package com.nationalbank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nationalbank.dao.DAO;



public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
		
		PrintWriter out = response.getWriter();
		int tra=Integer.parseInt(request.getParameter("tramount"));
		int sid=Integer.parseInt(request.getParameter("sid"));
		int tid=Integer.parseInt(request.getParameter("tid"));
		System.out.println(sid);
		System.out.println(tid);
		HttpSession session = request.getSession();
		
		
		try {
			if(sid!=tid)
			{
				try 
				{
					if(DAO.transfer(tra, sid,tid))
					{
						session.setAttribute("balance", DAO.getCurrBalance(String.valueOf(sid)));
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Amount transfer completed successfully');");
						 out.println("location='AccountInfo.jsp';");
						out.println("</script>");
						
						 
					}
					else
					{
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Transfer not allowed, please choose smaller amount');");
						 out.println("location='Transfer.jsp';");
						out.println("</script>");
						
					}
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
		        out.println("alert('Source and Destination Account Same.Try Again');");
		        out.println("location='Transfer.jsp';");
		        out.println("</script>");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	
	}

}
