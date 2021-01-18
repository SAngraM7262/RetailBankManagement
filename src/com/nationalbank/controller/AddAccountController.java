package com.nationalbank.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nationalbank.dao.AccountDAO;
import com.nationalbank.dao.AccountDAOImpl;
import com.nationalbank.entity.Account;

public class AddAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//create reference objects
	static AccountDAO accountDAO = null;
	static RequestDispatcher requestDispatcher = null;
    
	//invoke the AccountDOAImpl() object inside the constructor
    public AddAccountController() {
    	
    	accountDAO = new AccountDAOImpl();
        
    }

    //get method to return customer ID List
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Integer> customerIDList = accountDAO.getCustomerIdList();
		if(customerIDList != null) {
			request.setAttribute("customerIdList", customerIDList);
			requestDispatcher = request.getRequestDispatcher("/views/addAccount.jsp");
			requestDispatcher.forward(request, response);
		}else {
			requestDispatcher = request.getRequestDispatcher("/views/noCustomer.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	//post method to add new account
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int custId = Integer.parseInt(request.getParameter("custId"));
		String accountType = request.getParameter("accountType");
		int depositAmmount = Integer.parseInt(request.getParameter("depositAmount"));
		List<String> errors = new ArrayList<String>();
		
		//check weather the customer is alredy present with same account type
		if(!(accountDAO.isCustomerPresent(custId,accountType))) {
			String status = accountDAO.getStatus(custId);
			String custName = accountDAO.getCustomerName(custId);
			Account account = new Account();
			account.setCustId(custId);
			account.setAccountType(accountType);
			account.setBalance(depositAmmount);
			account.setMessage("Account created SuccessFully");
			account.setStatus(status);
			account.setCustName(custName);
			Date dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

		    String lastUpdated = ft.format(dNow).toString();
		    account.setLastUpdated(lastUpdated);
			
			if(accountDAO.addAccount(account)) {
				request.setAttribute("message", "Addedded Successfully");
				AccountStatusController.viewAccountStatus(request, response);
			}
			
			
		}else {
			errors.add("Customer Account Already created");
			accountDAO.setMessage(custId,"Customer Account Already created");
			request.setAttribute("message", "Customer Account Already created");
			AccountStatusController.viewAccountStatus(request, response);
		}
	}

}
