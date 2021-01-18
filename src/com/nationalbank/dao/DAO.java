package com.nationalbank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nationalbank.entity.Account;
import com.nationalbank.entity.TransactionSet;
import com.nationalbank.util.DateUtil;


public class DAO {
	

	public static boolean check(int cid)throws Exception
	{
		
		String userid = "root";
		String password = "root";
		String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
		Class.forName("com.mysql.jdbc.Driver");
		String query="select * from tbl_accounts where custId=? ;";
		Connection con = DriverManager.getConnection(connectionUrl,userid,password);
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1,cid);
		ResultSet rs=st.executeQuery();
		
		if(rs.next())
		{
			
			return true;
		}
		else
		{
			
			
			return false;
		}
			
		
		
		
	}
	
	public static Account checkAccount(int cid)throws Exception
	{
		String userid = "root";
		String password = "root";
		String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
		Class.forName("com.mysql.jdbc.Driver");
		String query="select * from tbl_accounts where  accountID=? ;";
		Connection con = DriverManager.getConnection(connectionUrl,userid,password);
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1,cid);
		
		ResultSet rs=st.executeQuery();
		if(rs.next())
		{
			Account ob = new Account();
			ob.setCustName(rs.getString("custName"));
			ob.setCustId(rs.getInt("custId"));
			ob.setAccountId(rs.getInt("accountID"));
			ob.setAccountType(rs.getString("accountType"));
			ob.setBalance(rs.getInt("balance"));
			return ob;
		}
		else
		return null;
	}
	public static ArrayList<TransactionSet> nTransactions(int ntr,int accid)throws Exception
	{
		String userid = "root";
		String password = "root";
		String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
		Class.forName("com.mysql.jdbc.Driver");
		String query="select * from transactions where accountID=? order by  transactionDate DESC LIMIT ?;";
		Connection con = DriverManager.getConnection(connectionUrl,userid,password);
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1,accid);
		st.setInt(2,ntr);
		ResultSet rs=st.executeQuery();
		ArrayList<TransactionSet> transactionList = new ArrayList<>();
		
		while(rs.next())
		{
			
			int tid=rs.getInt("transactionId");
			String des=rs.getString("tdescription");
			String date=rs.getString("transactionDate");
			int tamt=rs.getInt("tAmount");
			TransactionSet ob = new TransactionSet();
			ob.setTransactionId(tid);
			ob.setAcctId(accid);
			ob.setTDescription(des);
			ob.setTransactionDate(date);
			ob.setTAmount(tamt);
			transactionList.add(ob);
			
		}
		return transactionList;
	}
	
	public static ArrayList<TransactionSet> dateTransactions(String sdate,String edate,int accid)throws Exception
	{
		String userid = "root";
		String password = "root";
		String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
		java.sql.Date sd=DateUtil.conToDate(sdate, "yyyy-MM-dd");
		java.sql.Date ed=DateUtil.conToDate(edate, "yyyy-MM-dd");
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String query="select * from transactions where accountID=? and transactionDate between ? and ? order by  transactionDate DESC;";
		Connection con = DriverManager.getConnection(connectionUrl,userid,password);
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1,accid);
		
		st.setDate(2,sd);
		st.setDate(3,ed);
		
		
		ResultSet rs=st.executeQuery();
		ArrayList<TransactionSet> transactionList = new ArrayList<>();
		
		while(rs.next())
		{
			
			int tid=rs.getInt("transactionId");
			String des=rs.getString("tdescription");
			String date=rs.getString("transactionDate");
			int tamt=rs.getInt("tAmount");
			TransactionSet ob = new TransactionSet();
			ob.setTransactionId(tid);
			ob.setAcctId(accid);
			ob.setTDescription(des);
			ob.setTransactionDate(date);
			ob.setTAmount(tamt);
			transactionList.add(ob);
			
		}
		return transactionList;
	}
	public static int getCurrBalance(String accid)throws Exception
	{
		String userid = "root";
		String password = "root";
		String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
		
		int id=Integer.parseInt(accid);
		Class.forName("com.mysql.jdbc.Driver");
		
		String query="select balance from tbl_accounts where accountID=?;";
		Connection con = DriverManager.getConnection(connectionUrl,userid,password);
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1,id);
		ResultSet rs=st.executeQuery();
		if(rs.next())
		{
			return rs.getInt("balance");
		}
		return 0;
		
		
		
	}
	public static boolean deposit(int amt,int accid) throws Exception
	{
		String userid = "root";
		String password = "root";
		String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
		String id=String.valueOf(accid);
		String accType="";
		int balance=getCurrBalance(id);
		int newamt=balance+amt;
		Class.forName("com.mysql.jdbc.Driver");
		
		String query="update tbl_accounts set balance=? where accountID=?;";
		
		Connection con=DriverManager.getConnection(connectionUrl,userid,password);; 
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1, newamt);
		st.setInt(2, accid);
		int i=st.executeUpdate();
		st.close();
		
		String q1="select accountType from tbl_accounts where accountID=?;";
		st=con.prepareStatement(q1);
		st.setInt(1, accid);
		ResultSet rs=st.executeQuery();
		if(rs.next())
		{
			accType=rs.getString("accountType");
		}
		st.close();
		
		
		String q2="insert into transactions (accountID ,accountType ,tAmount,transactionDate ,sourceAccType,targetAccType ,tdescription) values(?,?,?,curdate(),?,?,?)";
		st=con.prepareStatement(q2);
		st.setInt(1, accid);
		st.setString(2,accType);
		st.setInt(3,amt);
		st.setString(4,accType);
		st.setString(5,accType);
		
		st.setString(6, "Deposit");
		st.executeUpdate(); 
		st.close();
		
		if(i!=0)
			return true;
		else
		
		return false;
	}
	
	
	public static boolean withdraw(int amt,int accid) throws Exception
	{
		String userid = "root";
		String password = "root";
		String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
		String id=String.valueOf(accid);
		String accType="";
		int balance=getCurrBalance(id);
		if(amt>balance)
			return false;
		else {
		int newamt=balance-amt;
		Class.forName("com.mysql.jdbc.Driver");
		
		String query="update tbl_accounts set balance=? where accountId=?;";
		
		Connection con=DriverManager.getConnection(connectionUrl,userid,password);; 
		PreparedStatement st=con.prepareStatement(query);
		st.setInt(1, newamt);
		st.setInt(2, accid);
		st.executeUpdate();
		st.close();
		
		String q1="select accountType from tbl_accounts where accountId=?;";
		st=con.prepareStatement(q1);
		st.setInt(1, accid);
		ResultSet rs=st.executeQuery();
		if(rs.next())
		{
			accType=rs.getString("accountType");
		}
		st.close();
		
		
		String q2="insert into transactions (accountID ,accountType ,tAmount,transactionDate ,sourceAccType,targetAccType ,tdescription) values(?,?,?,curdate(),?,?,?)";
		st=con.prepareStatement(q2);
		st.setInt(1, accid);
		st.setString(2,accType);
		st.setInt(3,amt);
		st.setString(4,accType);
		st.setString(5,accType);
		
		st.setString(6, "Withdraw");
		st.executeUpdate(); 
		st.close();
		
		return true;
		}
	}
	
	public static boolean transfer(int amt,int sid,int tid) throws Exception
	{
		String userid = "root";
		String password = "root";
		String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
		String id=String.valueOf(sid);
		String SaccType="";
		String TaccType="";
		int balance=getCurrBalance(id);
		if(amt>balance)
			return false;
		else
		{
			int newamt=balance-amt;
			Class.forName("com.mysql.jdbc.Driver");
			
			String query="update tbl_accounts set balance=? where accountId=?;";
			
			Connection con=DriverManager.getConnection(connectionUrl,userid,password);; 
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1, newamt);
			st.setInt(2, sid);
			st.executeUpdate();
			st.close();
			
			id=String.valueOf(tid);
			newamt=getCurrBalance(id)+amt;
			String query2="update tbl_accounts set balance=? where accountId=?;";
			
			st=con.prepareStatement(query2);
			st.setInt(1, newamt);
			st.setInt(2, tid);
			st.executeUpdate();
			st.close();
			
			//getting account types for both accounts
			String q1="select accountType from tbl_accounts where accountId=?;";
			st=con.prepareStatement(q1);
			st.setInt(1, sid);
			ResultSet rs=st.executeQuery();
			if(rs.next())
			{
				SaccType=rs.getString("accountType");
			}
			st.close();
			st=con.prepareStatement(q1);
			st.setInt(1, tid);
			ResultSet rs2=st.executeQuery();
			if(rs2.next())
			{
				TaccType=rs2.getString("accountType");
			}
			
			
			
			//add transaction to transactions table
			
			String q2="insert into transactions (accountID ,accountType ,tAmount,transactionDate ,sourceAccType,targetAccType ,tdescription) values(?,?,?,curdate(),?,?,?)";
			st=con.prepareStatement(q2);
			st.setInt(1, sid);
			st.setString(2,SaccType);
			st.setInt(3,amt);
			st.setString(4,SaccType);
			st.setString(5,TaccType);
		
			st.setString(6, "Transfer");
			st.executeUpdate(); 
			st.close();
			
			q2="insert into transactions (accountID ,accountType ,tAmount,transactionDate ,sourceAccType,targetAccType ,tdescription) values(?,?,?,curdate(),?,?,?)";
			st=con.prepareStatement(q2);
			st.setInt(1, tid);
			st.setString(2,TaccType);
			st.setInt(3,amt);
			st.setString(4,SaccType);
			st.setString(5,TaccType);
		
			st.setString(6, "Transfer");
			st.executeUpdate(); 
			st.close();
			return true;
			
			
			
			
			
			
		}
		
		
		
	}
		public static ArrayList<Account> accounts(int cid) throws SQLException, Exception
		{
			String userid = "root";
			String password = "root";
			String connectionUrl = "jdbc:mysql://localhost:3306/customerdirectory";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String query="select accountID  from tbl_accounts where custId=?;";
			
			Connection con=DriverManager.getConnection(connectionUrl,userid,password);; 
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1, cid);
			ResultSet rs=st.executeQuery();
			ArrayList<Account> transactionList = new ArrayList<>();
			
			while(rs.next())
			{
				
				int tid=rs.getInt("accountID");
				
				Account ob = new Account();
				
				ob.setAccountId(tid);
				
				transactionList.add(ob);
				
			}
			return transactionList;
			
		}
	


}
