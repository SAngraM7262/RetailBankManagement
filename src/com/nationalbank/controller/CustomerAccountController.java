package com.nationalbank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nationalbank.dao.AccountDAO;
import com.nationalbank.dao.AccountDAOImpl;
import com.nationalbank.entity.Account;
import com.nationalbank.entity.Customer;

public class CustomerAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// create reference objects
	static AccountDAO accountDAO = null;
	static RequestDispatcher requestDispatcher = null;
       
   
	//invoke the AccountDAOImpl() object inside constructor
    public CustomerAccountController() {
    	accountDAO = new AccountDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getCustomerAccount(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	

	private void getCustomerAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		List<String> errors = new ArrayList<String>();
		String custId;
		String accountId;
		String accountType;
		boolean flag = true;
		
		switch (action){
		
		//get customer account for delete operation
		case "delete" :
			int dAccountId = Integer.parseInt(request.getParameter("accountId"));
			accountType = request.getParameter("accountType");
			Account account1 = accountDAO.getCustomerAccount(dAccountId,accountType);
			
			if(account1 == null) {
				//System.out.println("Inside if");
				requestDispatcher = request.getRequestDispatcher("/views/emptyCustomerAccount.jsp");
				requestDispatcher.forward(request, response);
			}else {
				//System.out.println("Inside else");
				request.setAttribute("account", account1);
				requestDispatcher = request.getRequestDispatcher("/views/accountView.jsp");
				requestDispatcher.forward(request, response);
			}
			
			
			break;
			
		//get customer account for search operation
		case "search":
			custId = request.getParameter("custId");
			accountId = request.getParameter("accountId");
			
			//validate the customer id and account id before processing
			if(custId.isEmpty() && accountId.isEmpty()) {
				errors.add("Please fill at list one field to search the element");
				flag = false;
			}else if(custId.isEmpty() == false && accountId.isEmpty()) {
				if(custId.length() != 9) {
					errors.add("Customer Id Should be 9 digit numeric value");
					flag = false;
				}else {
					Account account = accountDAO.getCustomerAccount(custId,accountId);
					if(account == null) {
						//System.out.println("Inside if");
						requestDispatcher = request.getRequestDispatcher("/views/emptyCustomerAccount.jsp");
						requestDispatcher.forward(request, response);
					}else {
						//System.out.println("Inside else");
						request.setAttribute("account", account);
						requestDispatcher = request.getRequestDispatcher("/views/accountView.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			}else if(accountId.isEmpty() == false && custId.isEmpty()) {
				if(accountId.length() != 9) {
					errors.add("Customer accountId Should be 9 digit numeric value");
					flag = false;
				}else {
					Account account = accountDAO.getCustomerAccount(custId,accountId);
					//in no customer found redirect to empty customer account
					if(account == null) {
						//System.out.println("Inside if");
						requestDispatcher = request.getRequestDispatcher("/views/emptyCustomerAccount.jsp");
						requestDispatcher.forward(request, response);
					}else {
						//System.out.println("Inside else");
						request.setAttribute("account", account);
						requestDispatcher = request.getRequestDispatcher("/views/accountView.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			}else {
				errors.add("Please fill only one Entry, either Customer Id or Account Id");
				flag = false;
			}
			//if any errors are there then redirect to same page with errors list
			if(flag == false) {
				request.setAttribute("errors", errors);
				requestDispatcher = request.getRequestDispatcher("/views/searchCustomerAccount.jsp");
				requestDispatcher.forward(request, response);
				
			}
				
			break;	

		}
		
		
		
		
	}
	
	static int countDigit(int n) 
    { 
        if (n == 0) 
            return 0; 
        return 1 + countDigit(n / 10); 
    }
		
	

}
