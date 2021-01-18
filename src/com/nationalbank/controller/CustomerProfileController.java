package com.nationalbank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nationalbank.dao.CustomerDAO;
import com.nationalbank.dao.CustomerDAOImpl;
import com.nationalbank.entity.Customer;


public class CustomerProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//create reference objects
	static CustomerDAO customerDAO = null;
	static RequestDispatcher requestDispatcher = null;
	
	
	//invoke the customer DAOImpl() object inside constructor
	public CustomerProfileController() {
		customerDAO = new CustomerDAOImpl();
	}
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getCustomer(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getCustomer(request, response);
	}
	
	public static void getCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String action = request.getParameter("action");
		List<String> errors = new ArrayList<String>();
		
		String keyId;
	    String keySSNId;
		boolean flag = true;
		
		switch (action){
		
		//case for view profile opeartion
		case "viewProfile" :
			keyId = request.getParameter("id");
			keySSNId = "";
			Customer customer1 = customerDAO.getSingleCustomer(keyId,keySSNId);
			//if no customer found with given data redirect to empty customer profile
			if(customer1 == null) {
				requestDispatcher = request.getRequestDispatcher("/views/emptyCustomerProfile.jsp");
				requestDispatcher.forward(request, response);
			}else {
				request.setAttribute("customer", customer1);
				requestDispatcher = request.getRequestDispatcher("/views/customerProfile.jsp");
				requestDispatcher.forward(request, response);
			}
			
			break;
			
		//case for search customer operation
		case "search":
			keyId = request.getParameter("custId");
			keySSNId = request.getParameter("custSSNId");
			
			//validate the customer id and customer ssnid before processing
			if(keyId.isEmpty() && keySSNId.isEmpty()) {
				errors.add("Please fill the key id to search the element");
				flag = false;
			}else if(keyId.isEmpty() == false && keySSNId.isEmpty()) {
				if(keyId.length() != 9) {
					errors.add("Customer Id Should be 9 digit numeric value");
					flag = false;
				}else {
					Customer customer = customerDAO.getSingleCustomer(keyId,keySSNId);
					if(customer == null) {
						//if no customer found with given data redirect to empty customer profile
						requestDispatcher = request.getRequestDispatcher("/views/emptyCustomerProfile.jsp");
						requestDispatcher.forward(request, response);
					}else {
						request.setAttribute("customer", customer);
						requestDispatcher = request.getRequestDispatcher("/views/customerProfile.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			}else if(keySSNId.isEmpty() == false && keyId.isEmpty()) {
				if(keySSNId.length() != 9) {
					errors.add("Customer SSNId Should be 9 digit numeric value");
					flag = false;
				}else {
					Customer customer = customerDAO.getSingleCustomer(keyId,keySSNId);
					if(customer == null) {
						//System.out.println("Inside if");
						requestDispatcher = request.getRequestDispatcher("/views/emptyCustomerProfile.jsp");
						requestDispatcher.forward(request, response);
					}else {
						//System.out.println("Inside else");
						request.setAttribute("customer", customer);
						requestDispatcher = request.getRequestDispatcher("/views/customerProfile.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			}else {
				errors.add("Please fill only one Entry, either Customer Id or Customer SSNId");
				flag = false;
			}
			if(flag == false) {
				request.setAttribute("errors", errors);
				requestDispatcher = request.getRequestDispatcher("/views/deleteCustomer.jsp");
				requestDispatcher.forward(request, response);
				
			}
			break;
			

		}
		
	}
	
	//method to count digits in number
	static int countDigit(int n) 
    { 
        if (n == 0) 
            return 0; 
        return 1 + countDigit(n / 10); 
    }

}
