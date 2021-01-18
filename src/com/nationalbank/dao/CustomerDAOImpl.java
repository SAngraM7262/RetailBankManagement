package com.nationalbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nationalbank.entity.Customer;
import com.nationalbank.util.DBConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO {
	
	Connection connection = null;
	
	Statement statement = null;
	
	ResultSet resultSet = null;
	
	PreparedStatement preparedStatement = null;

	@Override
	public List<Customer> getAllCustomer() {
		//create a reference variable
		List<Customer> custList = null;
		Customer customer = null;
		
		try {
			custList = new ArrayList<Customer>();
			
			//select the sql query
			String sqlSelectQuery = "SELECT * FROM tbl_customer";
			
			//get database connection
			connection = DBConnectionUtil.getConnection();
			
			//create sql Statement
			statement = connection.createStatement();
			
			//Execute the sql Query
			resultSet = statement.executeQuery(sqlSelectQuery);
			
			//process the result set
			while(resultSet.next()) {
				customer = new Customer();
				customer.setCustId(resultSet.getInt("custId"));
				customer.setCustSSNId(resultSet.getInt("custSSNId"));
				customer.setCustName(resultSet.getString("custName"));
				customer.setCustAge(resultSet.getInt("custAge"));
				customer.setCustAddress(resultSet.getString("custAddress"));
				customer.setCustState(resultSet.getString("custState"));
				customer.setCustCity(resultSet.getString("custCity"));
				customer.setStatus(resultSet.getString("status"));
				customer.setMessage(resultSet.getString("message"));
				customer.setLastUpdated(resultSet.getString("lastUpdated"));
				
				//append temporary object to custList
				custList.add(customer);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return custList;
	}

	@Override
	public boolean addCustomer(Customer c) {
		boolean flag = false;
		try {
			String insertSQLQuery = "INSERT INTO tbl_customer (custSSNID,custName,custAge,custAddress,custState,custCity,status,lastUpdated,message) VALUES ('"+c.getCustSSNId()+"','"+c.getCustName()+"','"+c.getCustAge()+"','"+c.getCustAddress()+"','"+c.getCustState()+"','"+c.getCustCity()+"','"+c.getStatus()+"','"+c.getLastUpdated()+"','"+c.getMessage()+"')";
			connection = DBConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLQuery);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	

	@Override
	public Customer getSingleCustomer(String keyId,String keySSNId) {
		Customer customer = null;
		String sqlGetSingleCustomer = "";
		try {
			
			customer = new Customer();
			if(keyId.isEmpty()==false) {
				int custId = Integer.parseInt(keyId);
				sqlGetSingleCustomer = "SELECT * FROM tbl_customer WHERE custId = " + custId;
			}else {
				int custSSNId = Integer.parseInt(keySSNId);
				sqlGetSingleCustomer = "SELECT * FROM tbl_customer WHERE custSSNId = " + custSSNId;
			}
			
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlGetSingleCustomer);
			
			
			while(resultSet.next()) {
				customer.setCustId(resultSet.getInt("custId"));
				customer.setCustSSNId(resultSet.getInt("custSSNId"));
				customer.setCustName(resultSet.getString("custName"));
				customer.setCustAge(resultSet.getInt("custAge"));
				customer.setCustAddress(resultSet.getString("custAddress"));
				customer.setCustState(resultSet.getString("custState"));
				customer.setCustCity(resultSet.getString("custCity"));
				customer.setStatus(resultSet.getString("status"));
				customer.setMessage(resultSet.getString("message"));
				customer.setLastUpdated(resultSet.getString("lastUpdated"));
				return customer;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public boolean updateCustomer(Customer c) {
		
		boolean flag = false;
		
		try {
			String sqlUpdateQuery = "UPDATE tbl_customer SET custName = '"+c.getCustName()+"', custAge = '"+c.getCustAge()+"', custAddress = '"+c.getCustAddress()+"', custState = '"+c.getCustState()+"', custCity = '"+c.getCustCity()+"', message = '"+c.getMessage()+"', lastUpdated = '"+c.getLastUpdated()+"' WHERE custId = "+c.getCustId();
			connection = DBConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlUpdateQuery);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteCustomer(int keyId) {
		boolean flag = false;
		
		try {
			String sqlDeleteQuery = "DELETE FROM tbl_customer WHERE custId = " + keyId;
			connection = DBConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlDeleteQuery);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateStatus(int keyId, String currentStatus) {
		boolean flag = false;
		String sqlUpdateStatusQuery = "UPDATE tbl_customer SET status = 'Inactive' WHERE custId = "+keyId;
		
		try {
			System.out.println(currentStatus + " " + (currentStatus.equals("Active")));
			
			if(currentStatus.equals("Active")) {
				sqlUpdateStatusQuery = "UPDATE tbl_customer SET status = 'Inactive' WHERE custId = "+keyId;
			}else {
				sqlUpdateStatusQuery = "UPDATE tbl_customer SET status = 'Active' WHERE custId = "+keyId;
			}
			
			connection = DBConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlUpdateStatusQuery);
			preparedStatement.executeUpdate();
			flag = true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateMessage(int keyId, String message) {
		boolean flag = false;
		try {
			Date dNow = new Date( );
		      SimpleDateFormat ft = 
		      new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

		      String updatedOn = ft.format(dNow).toString();
			String sqlUpdateMessageQuery = "UPDATE tbl_customer SET message = '"+ message +"', lastUpdated = '"+ updatedOn +"' WHERE custId = "+keyId;
			connection = DBConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sqlUpdateMessageQuery);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

}
