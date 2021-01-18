package com.nationalbank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nationalbank.dao.CustomerDAO;
import com.nationalbank.dao.CustomerDAOImpl;

public class DeleteCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//create reference variable
	static CustomerDAO customerDAO = null;
	static RequestDispatcher requestDispatcher = null;
	
	//create constructor and initialize customerDAO
	public DeleteCustomerController() {
		customerDAO = new CustomerDAOImpl();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteCustomer(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int keyId =  Integer.parseInt(request.getParameter("id"));
		
		if(customerDAO.deleteCustomer(keyId)) {
			request.setAttribute("message", "Deleted Successfully");
			CustomerStatusController.viewCustomerStatus(request, response);
		}
		
	}

}
