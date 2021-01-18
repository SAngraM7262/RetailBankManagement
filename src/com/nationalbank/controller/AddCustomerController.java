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

import com.nationalbank.dao.CustomerDAO;
import com.nationalbank.dao.CustomerDAOImpl;
import com.nationalbank.entity.Customer;


public class AddCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//create reference objects
	static CustomerDAO customerDAO = null;
	static RequestDispatcher requestDispatcher = null;
	
	//invoke the CustomerDOAImpl() object inside the constructor
	public AddCustomerController() {
		customerDAO = new CustomerDAOImpl();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		addCust(request, response);
	}
	
	//method to add new customer data
	public static void addCust(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int custSSNId = Integer.parseInt(request.getParameter("custSSNId")); 
		String custName = request.getParameter("custName");
		int custAge = Integer.parseInt(request.getParameter("custAge"));;
		String custAddress = request.getParameter("custAddress");
		String custState = request.getParameter("custState");
		String custCity = request.getParameter("custCity");
		String status = "Inactive";
		String message = "Customer created Successfully";
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

	    String lastUpdated = ft.format(dNow).toString();
	    
	    Customer customer = new Customer();
		
		customer.setCustSSNId(custSSNId);
		customer.setCustName(custName);
		customer.setCustAge(custAge);
		customer.setCustAddress(custAddress);
		customer.setCustState(custState);
		customer.setCustCity(custCity);
		customer.setStatus(status);
		customer.setMessage(message);
		customer.setLastUpdated(lastUpdated);
	    
	    //Do the validation before passing data to DAO
	    
	    List<String> errors = new ArrayList<String>();
	    boolean flag = true;
	    if(countDigit(custSSNId) < 9 || countDigit(custSSNId) >9) {
	    	errors.add("Customer SSNId should be 9 digit numeric value");
	    	customer.setCustSSNId(0);
	    	flag = false;
	    }
	    if(!(isStringOnlyAlphaSpaceDot(custName))) {
	    	errors.add("Customer name should contain alphabets only");
	    	customer.setCustName("");
	    	flag = false;
	    }
	    if(custAge < 0) {
	    	errors.add("Customer Age should not be negative");
	    	customer.setCustAge(1);
	    	flag = false;
	    }
	    if(countDigit(custAge) > 3) {
	    	errors.add("Customer Age should not be greater than 3 digit numeric value");
	    	customer.setCustAge(1);
	    	flag = false;
	    }
	    if(custAge == 0) {
	    	errors.add("Customer Age should not be Zero");
	    	customer.setCustAge(1);
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
	    
	    //if no error occur then call DAO method to add new customer
	    if(flag) {
			
			if(customerDAO.addCustomer(customer)) {
				request.setAttribute("message", "Addedded Successfully");
			}
			
			CustomerStatusController.viewCustomerStatus(request, response);
	    }else {
	    	request.setAttribute("errors", errors);
	    	request.setAttribute("customer", customer);
	    	request.setAttribute("flag", flag);
	    	requestDispatcher = request.getRequestDispatcher("/views/addCustomer.jsp");
	    	requestDispatcher.forward(request, response);
	    	
	    }
	   
		
		
		
	}
	

	//method to check weather string contains only alphabets, space and dot for naming convention
	public static boolean isStringOnlyAlphaSpaceDot(String str) 
	{ 
	    return ((!str.equals("")) 
	            && (str != null) 
	            && (str.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$"))); 
	}
	
	//method to check weather string contains only alphabets, space
	public static boolean isStringOnlyAlphabetSpace(String str) 
	{ 
		return ((!str.equals("")) 
	            && (str != null) 
	            && (str.matches("^[a-zA-Z]*$")));
	}
	
	//method to count the digits in number
	static int countDigit(int n) 
    { 
        if (n == 0) 
            return 0; 
        return 1 + countDigit(n / 10); 
    } 

}
