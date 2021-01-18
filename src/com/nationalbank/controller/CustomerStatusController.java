package com.nationalbank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nationalbank.dao.CustomerDAO;
import com.nationalbank.dao.CustomerDAOImpl;
import com.nationalbank.entity.Customer;

public class CustomerStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//create reference objects
	static CustomerDAO customerDAO = null;
	static RequestDispatcher requestDispatcher = null;
	
	
	//create constructor and initialize customerDAO
	public CustomerStatusController() {
		customerDAO = new CustomerDAOImpl();
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		viewCustomerStatus(request,response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	public static void viewCustomerStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//call The DAO method to get the list of customers
		List<Customer> custList = customerDAO.getAllCustomer();
		
		//add customer list to request object
		request.setAttribute("customerList", custList);
		
		//get the request dispatcher
		requestDispatcher = request.getRequestDispatcher("/views/customerStatus.jsp");
		
		//forward the request dispatcher
		requestDispatcher.forward(request, response);
		
	}

}
