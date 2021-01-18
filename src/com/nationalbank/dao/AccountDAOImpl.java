package com.nationalbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nationalbank.entity.Account;
import com.nationalbank.entity.Customer;
import com.nationalbank.util.DBConnectionUtil;

public class AccountDAOImpl implements AccountDAO {
	
	Connection connection = null;
	
	Statement statement = null;
	
	ResultSet resultSet = null;
	
	PreparedStatement preparedStatement = null;

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accountList = null;
		Account account = null;
		
		try {
			accountList = new ArrayList<Account>();
			
			//select the sql query
			String sqlSelectQuery = "SELECT * FROM tbl_accounts";
			
			//get database connection
			connection = DBConnectionUtil.getConnection();
			
			//create sql Statement
			statement = connection.createStatement();
			
			//Execute the sql Query
			resultSet = statement.executeQuery(sqlSelectQuery);
			
			//process the result set
			while(resultSet.next()) {
				account = new Account();
				account.setCustId(resultSet.getInt("custId"));
				account.setAccountId(resultSet.getInt("accountId"));
				account.setAccountType(resultSet.getString("accountType"));
				account.setStatus(resultSet.getString("status"));
				account.setMessage(resultSet.getString("message"));
				account.setLastUpdated(resultSet.getString("lastUpdated"));
				
				//append temporary object to custList
				accountList.add(account);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return accountList;

	}

	@Override
	public List<Integer> getCustomerIdList() {
		List<Integer> customerIdList = null;
		try {
			
			customerIdList = new ArrayList<Integer>();
			String sqlSelectQuery = "SELECT custId FROM tbl_customer";
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlSelectQuery);
			
			while(resultSet.next()) {
				customerIdList.add(resultSet.getInt("custId"));
			}
			return customerIdList;
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isCustomerPresent(int custId, String accountType) {
		boolean flag = false;
		try {
			String sqlQuery = "SELECT * FROM tbl_accounts where custId = '"+ custId +"' AND accountType = '"+ accountType +"'";
			
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
			
			if(resultSet.next()) {
				flag = true;
			}else {
				flag = false;
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String getStatus(int custId) {
		String sqlQuery = "SELECT status FROM tbl_customer where custId = " + custId;
		
		try {
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
			if(resultSet.next()) {
				return resultSet.getString("status");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public boolean addAccount(Account account) {
		
		boolean flag = false;
		try {
			String insertSQLQuery = "INSERT INTO tbl_accounts (custID,accountType,status,lastUpdated,message,balance,custName) VALUES ('"+account.getCustId()+"','"+account.getAccountType()+"','"+account.getStatus()+"','"+account.getLastUpdated()+"','"+account.getMessage()+"','"+ account.getBalance()+"','"+ account.getCustName() +"')";
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
	public boolean setMessage(int custId, String msg) {
		boolean flag = false;
		try {
			String insertSQLQuery = "UPDATE tbl_accounts SET message = '"+ msg +"' WHERE custId = " + custId;
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
	public String getCustomerName(int custId) {
		String sqlQuery = "SELECT custName FROM tbl_customer where custId = " + custId;
		
		try {
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
			if(resultSet.next()) {
				return resultSet.getString("custName");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account getCustomerAccount(String custId, String accountId) {
		Account account = null;
		String sqlGetCustomerAccount = "";
		try {
			account = new Account();
			if(custId.isEmpty()==false) {
				int custId1 = Integer.parseInt(custId);
				sqlGetCustomerAccount = "SELECT * FROM tbl_accounts WHERE custId = " + custId1;
			}else {
				int accountId1 = Integer.parseInt(accountId);
				sqlGetCustomerAccount = "SELECT * FROM tbl_accounts WHERE accountId = " + accountId1;
			}
			
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlGetCustomerAccount);
			
			
			while(resultSet.next()) {
				account.setCustId(resultSet.getInt("custId"));
				account.setAccountId(resultSet.getInt("accountId"));
				account.setAccountType(resultSet.getString("accountType"));
				account.setStatus(resultSet.getString("status"));
				account.setLastUpdated(resultSet.getString("lastUpdated"));
				account.setMessage(resultSet.getString("message"));
				account.setBalance(resultSet.getInt("balance"));
				account.setCustName(resultSet.getString("custName"));
				return account;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public Account getCustomerAccount(int accountId, String accountType) {
		
		
		Account account = null;
		try {
			
			account = new Account();
			
			String sqlGetCustomerAccount = "SELECT * FROM tbl_accounts WHERE accountId = " + accountId + " AND accountType = '"+ accountType +"'";
			
			
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlGetCustomerAccount);
			
			
			while(resultSet.next()) {
				account.setCustId(resultSet.getInt("custId"));
				account.setAccountId(resultSet.getInt("accountId"));
				account.setAccountType(resultSet.getString("accountType"));
				account.setStatus(resultSet.getString("status"));
				account.setLastUpdated(resultSet.getString("lastUpdated"));
				account.setMessage(resultSet.getString("message"));
				account.setBalance(resultSet.getInt("balance"));
				account.setCustName(resultSet.getString("custName"));
				
				return account;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<Integer> getAccountIdList() {
		List<Integer> accountIdList = null;
		try {
			
			accountIdList = new ArrayList<Integer>();
			String sqlSelectQuery = "SELECT accountId FROM tbl_accounts";
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlSelectQuery);
			
			while(resultSet.next()) {
				accountIdList.add(resultSet.getInt("accountId"));
			}
			return accountIdList;
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteAccount(int accountId, String accountType) {
		boolean flag = false;
		
		try {
			String sqlDeleteQuery = "DELETE FROM tbl_accounts WHERE accountId = " + accountId + " AND accountType = '"+ accountType +"'";
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
	public Account getAccountForPrint(int accountId) {
		Account account = null;
		try {
			
			account = new Account();
			
			String sqlGetCustomerAccount = "SELECT * FROM tbl_accounts WHERE accountId = " + accountId ;
			
			
			connection = DBConnectionUtil.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlGetCustomerAccount);
			
			
			while(resultSet.next()) {
				account.setCustId(resultSet.getInt("custId"));
				account.setAccountId(resultSet.getInt("accountId"));
				account.setAccountType(resultSet.getString("accountType"));
				account.setStatus(resultSet.getString("status"));
				account.setLastUpdated(resultSet.getString("lastUpdated"));
				account.setMessage(resultSet.getString("message"));
				account.setBalance(resultSet.getInt("balance"));
				account.setCustName(resultSet.getString("custName"));
				
				return account;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	

}
