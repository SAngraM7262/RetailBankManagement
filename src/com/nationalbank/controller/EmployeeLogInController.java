package com.nationalbank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nationalbank.dao.EmployeeDAO;
import com.nationalbank.dao.EmployeeDAOImpl;
import com.nationalbank.entity.Employee;

public class EmployeeLogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//create reference variable
	static EmployeeDAO employeeDAO = null;
	static RequestDispatcher requestDispatcher = null;
	HttpSession session = null;

	//create constructor and initialize employeeDAO
    public EmployeeLogInController() {
    	
    	employeeDAO = new EmployeeDAOImpl();
    	

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session1 = request.getSession();
		Employee currentEmp = (Employee)session1.getAttribute("currentEmp");
    	if( currentEmp != null ){
    		//depending upon their designation redirect to related pages
    		switch(currentEmp.getEmpType()){
			case "customerExecutive" :
				response.sendRedirect("/views/customerExecutiveOperations.jsp");
				break;
			case "cashier" :
				response.sendRedirect("/views/cashierOperations.jsp");
				break;
			}
    	}
		
			Employee employee = new Employee();
			
			String empName = request.getParameter("empName");
			String password = request.getParameter("password");
			String message;
			
			employee = employeeDAO.getEmployee(empName, password);
			
			if(employee == null) {
				message = "No employee Record Found!! kindly enter valid credentials";
				request.setAttribute("message", message);
				requestDispatcher = request.getRequestDispatcher("logIn.jsp");
				requestDispatcher.forward(request, response);
			}else {
				message = "Log In successfull!!";
				session = request.getSession();
				session.setAttribute("currentEmp", employee);
				request.setAttribute("message", message);
				
				switch(employee.getEmpType()){
				case "customerExecutive" :
					requestDispatcher = request.getRequestDispatcher("/views/customerExecutiveOperations.jsp");
					requestDispatcher.forward(request, response);
					break;
				case "cashier" :
					requestDispatcher = request.getRequestDispatcher("/views/cashierOperations.jsp");
					requestDispatcher.forward(request, response);
					break;
				}
				
			}
			
	}

}
