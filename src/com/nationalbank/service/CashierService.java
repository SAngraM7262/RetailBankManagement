package com.nationalbank.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nationalbank.dao.DAO;
import com.nationalbank.entity.Account;
import com.nationalbank.entity.TransactionSet;


public class CashierService {
	
	public static boolean customerIDpresent(String cid)throws SQLException, Exception
	{
		int cusid=Integer.parseInt(cid);
		boolean ret=DAO.check(cusid);
		return ret;
		
	}
	public static ArrayList<TransactionSet> accStatement(String accid,String value,String sdate,String edate,String ntr)
	{
		
		Account ob = new Account();
		if(accid.length()!=9)
		return null;
		
		int acc=Integer.parseInt(accid);
		
		
		
		try
		{
			ob=DAO.checkAccount(acc);
			
			if(ob!=null)
			{
				//get all transactions
				if(value.equalsIgnoreCase("TransactionNumber"))
				{
					int nTr=Integer.parseInt(ntr);
					ArrayList<TransactionSet> tList = DAO.nTransactions(nTr,acc);
					return tList;
					
				}
				else if(value.equalsIgnoreCase("TransactionDate"))
				{
					
						ArrayList<TransactionSet> tList = DAO.dateTransactions(sdate,edate,acc);
						
						return tList;
				}
				else
					return null;
			}
			else
			return null;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


}
