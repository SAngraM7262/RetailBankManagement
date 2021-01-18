package com.nationalbank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nationalbank.dao.AccountDAO;
import com.nationalbank.dao.AccountDAOImpl;
import com.nationalbank.entity.Account;

public class AccountStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//create reference objects
	static AccountDAO accountDAO = null;
	static RequestDispatcher requestDispatcher = null; 
    
	//invoke the AccountDOAImpl() object inside the constructor
    public AccountStatusController() {
    	
        accountDAO = new AccountDAOImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		viewAccountStatus(request,response);
		
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	

	//method to get all account status
	public static void viewAccountStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				List<Account> accountList = accountDAO.getAllAccounts();
				
				//add customer list to request object
				request.setAttribute("accountList", accountList);
				
				//get the request dispatcher
				requestDispatcher = request.getRequestDispatcher("/views/accountStatus.jsp");
				
				//forward the request dispatcher
				requestDispatcher.forward(request, response);
		
	}

}
