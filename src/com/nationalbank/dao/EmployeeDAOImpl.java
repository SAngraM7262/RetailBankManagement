package com.nationalbank.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nationalbank.entity.Employee;
import com.nationalbank.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection connection = null;
	
	Statement statement = null;
	
	ResultSet resultSet = null;

	@Override
	public Employee getEmployee(String empName, String password) {
		
		Employee employee = null;
		
		try {
			
			employee = new Employee();
			String sqlGetEmployeeQuery = "SELECT * FROM tbl_employee WHERE empName = '"+ empName +"' AND password = '" + password +"' ";
			
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlGetEmployeeQuery);
			
			while(resultSet.next()) {
				employee.setEmpId(resultSet.getInt("empId"));
				employee.setEmpName(resultSet.getString("empName"));
				employee.setEmpType(resultSet.getString("empType"));
				return employee;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	

}
