package com.nationalbank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nationalbank.dao.DAO;

public class DWTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String deposit=request.getParameter("deposit");
		String withdraw=request.getParameter("withdraw");
		String transfer=request.getParameter("transfer");
		HttpSession session=request.getSession();
		String accid=String.valueOf(session.getAttribute("accountId"));
		
		int currBal;
		try {
			currBal = DAO.getCurrBalance(accid);
			session.setAttribute("balance",currBal);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		//redirect to a different page(view)
		if(deposit!=null)
		{
			response.sendRedirect("Deposit.jsp");
		
		}
		else if(withdraw!=null)
		{
			response.sendRedirect("Withdraw.jsp");
			
		}
		else if(transfer!=null)
		{
			response.sendRedirect("Transfer.jsp");
		}
		
	
	}

}
