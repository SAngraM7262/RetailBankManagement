package com.nationalbank.dao;

import java.util.List;

import com.nationalbank.entity.Account;

public interface AccountDAO {
	
	List<Account> getAllAccounts();
	
	List<Integer> getCustomerIdList();
	
	boolean isCustomerPresent(int custId,String accountType);
	
	String getStatus(int custId);
	
	boolean addAccount(Account account);
	
	boolean setMessage(int custId, String msg);
	
	String getCustomerName(int custId);
	
	Account getCustomerAccount(String custId,String accountId);
	
	Account getCustomerAccount(int accountId,String accountType);
	
	List<Integer> getAccountIdList();
	
	boolean deleteAccount(int accountId,String accountType);
	
	Account getAccountForPrint(int accountId);

}
