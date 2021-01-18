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


public class DeleteAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//create reference variables
	static AccountDAO accountDAO = null;
	static RequestDispatcher requestDispatcher = null;
       
	//invoke the AccountDAOImpl() object inside constructor
    public DeleteAccountController() {
        accountDAO = new AccountDAOImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		//case for delete operation
		case "delete" : 
			int accountId = Integer.parseInt(request.getParameter("id"));
			String accountType = request.getParameter("accountType");
			if(accountDAO.deleteAccount(accountId,accountType)) {
				request.setAttribute("message", "Deleted Successfully");
				AccountStatusController.viewAccountStatus(request, response);
			}
			break;
			
		//default case for getting Account Id List
		case "default" :
			List<Integer> accountIDList = accountDAO.getAccountIdList();
			if(accountIDList != null) {
				request.setAttribute("accountIdList", accountIDList);
				requestDispatcher = request.getRequestDispatcher("/views/deleteAccount.jsp");
				requestDispatcher.forward(request, response);
			}else {
				requestDispatcher = request.getRequestDispatcher("/views/noCustomer.jsp");
				requestDispatcher.forward(request, response);
			}
			break;
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
