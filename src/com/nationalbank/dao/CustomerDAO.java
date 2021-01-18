/**
 * 
 */
package com.nationalbank.dao;

import java.util.List;

import com.nationalbank.entity.Customer;

/**
 * @author SAngraM
 *
 */
public interface CustomerDAO {
	
	List<Customer> getAllCustomer();
	
	boolean addCustomer(Customer c);
	
	Customer getSingleCustomer(String keyId,String keySSNId);
	
	boolean updateCustomer(Customer c);
	
	boolean deleteCustomer(int keyId);
	
	boolean updateStatus(int keyId,String currentStatus);
	
	boolean updateMessage(int keyId,String message);

}
