package com.nationalbank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nationalbank.dao.CustomerDAO;
import com.nationalbank.dao.CustomerDAOImpl;
import com.nationalbank.entity.Customer;


public class UpdateCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static CustomerDAO customerDAO = null;
	static RequestDispatcher requestDispatcher = null;
	
	public UpdateCustomerController() {
		customerDAO = new CustomerDAOImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getSingleCustomer(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		updateCustomer(request,response);
	}
	
	
	public static void getSingleCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String keyId = request.getParameter("id");
		String keySSNId = "";
		Customer customer = customerDAO.getSingleCustomer(keyId,keySSNId);
		request.setAttribute("customer", customer);
		requestDispatcher = request.getRequestDispatcher("/views/updateCustomer.jsp");
		requestDispatcher.forward(request, response);
		
		
	}
	public static void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int custId = Integer.parseInt(request.getParameter("custId"));
		int custSSNId = Integer.parseInt(request.getParameter("custSSNId"));
		String custName = request.getParameter("custName");
		Long custAge1 = Long.parseLong(request.getParameter("custAge"));;
		String custAddress = request.getParameter("custAddress");
		String custState = request.getParameter("custState");
		String custCity = request.getParameter("custCity");
		String message = "Customer Details updated";
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

	    String lastUpdated = ft.format(dNow).toString();
		
	    List<String> errors = new ArrayList<String>();
	    boolean flag = true;
	    
	    Customer customer = new Customer();
	    
	    if(custAge1 < 0) {
	    	errors.add("Customer Age should not be negative");
	    	customer.setCustAge(1);
	    	customer.setCustId(custId);
			customer.setCustSSNId(custSSNId);
			customer.setCustName(custName);
			customer.setCustAddress(custAddress);
			customer.setCustState(custState);
			customer.setCustCity(custCity);
			customer.setMessage(message);
			customer.setLastUpdated(lastUpdated);
	    	flag = false;
	    }
	    if(custAge1 >100) {
	    	errors.add("Customer Age should not be greater than 100");
	    	customer.setCustAge(1);
	    	customer.setCustId(custId);
			customer.setCustSSNId(custSSNId);
			customer.setCustName(custName);
			customer.setCustAddress(custAddress);
			customer.setCustState(custState);
			customer.setCustCity(custCity);
			customer.setMessage(message);
			customer.setLastUpdated(lastUpdated);
	    	flag = false;
	    }
	    if(countDigit(custAge1) > 3) {
	    	errors.add("Customer Age should not be greater than 3 digit numeric value");
	    	customer.setCustAge(1);
	    	customer.setCustId(custId);
			customer.setCustSSNId(custSSNId);
			customer.setCustName(custName);
			customer.setCustAddress(custAddress);
			customer.setCustState(custState);
			customer.setCustCity(custCity);
			customer.setMessage(message);
			customer.setLastUpdated(lastUpdated);
	    	flag = false;
	    }
	    if(custAge1 == 0) {
	    	errors.add("Customer Age should not be Zero");
	    	customer.setCustAge(1);
	    	customer.setCustId(custId);
			customer.setCustSSNId(custSSNId);
			customer.setCustName(custName);
			customer.setCustAddress(custAddress);
			customer.setCustState(custState);
			customer.setCustCity(custCity);
			customer.setMessage(message);
			customer.setLastUpdated(lastUpdated);
	    	flag = false;
	    }
	    
	    int custAge = custAge1.intValue();
	    
	    customer.setCustId(custId);
		customer.setCustSSNId(custSSNId);
		customer.setCustName(custName);
		customer.setCustAge(custAge);
		customer.setCustAddress(custAddress);
		customer.setCustState(custState);
		customer.setCustCity(custCity);
		customer.setMessage(message);
		customer.setLastUpdated(lastUpdated);
	    
		if(!(isStringOnlyAlphaSpaceDot(custName))) {
	    	errors.add("Customer name should contain alphabets only");
	    	customer.setCustName("");
	    	flag = false;
	    }
	    
	    
	    if(!(custAddress.matches("^[a-zA-Z0-9 ]+$"))){
	    	errors.add("Customer Address should contain alphanumerc values only");
	    	customer.setCustAddress("");
	    	flag = false;
	    }
	    if(!(isStringOnlyAlphabetSpace(custState))) {
	    	errors.add("Customer State should contain alphabets only");
	    	customer.setCustState("");
	    	flag = false;
	    }
	    if(!(isStringOnlyAlphabetSpace(custCity))) {
	    	errors.add("Customer City should contain alphabets only");
	    	customer.setCustCity("");
	    	flag = false;
	    }
	    
	    if(flag) {
			
			if(customerDAO.updateCustomer(customer)) {
				request.setAttribute("message", "Updated Successfully");
			}
			CustomerStatusController.viewCustomerStatus(request, response);;
	    }else {
	    	request.setAttribute("errors", errors);
	    	request.setAttribute("customer", customer);
	    	request.setAttribute("flag", flag);
	    	requestDispatcher = request.getRequestDispatcher("/views/updateCustomer.jsp");
	    	requestDispatcher.forward(request, response);
	    }
	    
	    
	    
		
		
		
		
	}

	public static boolean isStringOnlyAlphaSpaceDot(String str) 
	{ 
	    return ((!str.equals("")) 
	            && (str != null) 
	            && (str.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$"))); 
	}
	
	public static boolean isStringOnlyAlphabetSpace(String str) 
	{ 
		return ((!str.equals("")) 
	            && (str != null) 
	            && (str.matches("^[a-zA-Z]*$")));
	}
	static int countDigit(long n) 
    { 
        if (n == 0) 
            return 0; 
        return 1 + countDigit(n / 10); 
    } 
	
	
}
