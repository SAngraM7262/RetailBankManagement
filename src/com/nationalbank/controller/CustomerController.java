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

public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//create reference variable to customer DAO
		CustomerDAO customerDAO = null;
		RequestDispatcher requestDispatcher = null;
		
		//create constructor and initialize customerDAO
		public CustomerController() {
			customerDAO = new CustomerDAOImpl();
		}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetCustList(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddCustomer(request,response);
	}
	
	public void GetCustList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//call The DAO method to get the list of customers
				List<Customer> custList = customerDAO.getAllCustomer();
				
				//add customer list to request object
				request.setAttribute("customerList", custList);
				
				//get the request dispatcher
				requestDispatcher = request.getRequestDispatcher("/views/customerView.jsp");
				
				//forward the request dispatcher
				requestDispatcher.forward(request, response);
	}
	
	public void AddCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int custSSNId = Integer.parseInt(request.getParameter("custSSNId"));
		String custName = request.getParameter("custName");
		int custAge = Integer.parseInt(request.getParameter("custAge"));;
		String custAddress = request.getParameter("custAddress");
		String custState = request.getParameter("custState");
		String custCity = request.getParameter("custCity");
		
		Customer customer = new Customer();
		
		customer.setCustSSNId(custSSNId);
		customer.setCustName(custName);
		customer.setCustAge(custAge);
		customer.setCustAddress(custAddress);
		customer.setCustState(custState);
		customer.setCustCity(custCity);
		
		if(customerDAO.addCustomer(customer)) {
			request.setAttribute("message", "Addedded Successfully");
		}
		
		GetCustList(request, response);
		
	}

}
