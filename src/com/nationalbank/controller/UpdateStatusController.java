package com.nationalbank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nationalbank.dao.CustomerDAO;
import com.nationalbank.dao.CustomerDAOImpl;


public class UpdateStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static CustomerDAO customerDAO = null;
	RequestDispatcher requestDispatcher = null;
	
	public UpdateStatusController() {
		customerDAO = new CustomerDAOImpl();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updateCustomerStatus(request,response);
	}


	public static void updateCustomerStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int keyId = Integer.parseInt(request.getParameter("id"));
		String currentStatus = request.getParameter("currentStatus");
		
		if(customerDAO.updateStatus(keyId, currentStatus)) {
			if(currentStatus.equals("Active")) {
				String message = "Customer Inactivated Successfully";
				request.setAttribute("message", message);
				customerDAO.updateMessage(keyId, message);
			}else {
				String message = "Customer Activated Successfully";
				request.setAttribute("message", message);
				customerDAO.updateMessage(keyId, message);
			}
			
			CustomerStatusController.viewCustomerStatus(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
