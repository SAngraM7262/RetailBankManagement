/**
 * 
 */
package com.nationalbank.entity;


/**
 * @author SAngraM
 *
 */
public class Customer {
	
	//define the fields
	private int custId;
	private int custSSNId;
	private String custName;
	private int custAge;
	private String custAddress;
	private String custState;
	private String custCity;
	private String status;
	private String message;
	private String lastUpdated;
	
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	//generate getter and setter methods
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getCustSSNId() {
		return custSSNId;
	}
	public void setCustSSNId(int custSSNId) {
		this.custSSNId = custSSNId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getCustAge() {
		return custAge;
	}
	public void setCustAge(int custAge) {
		this.custAge = custAge;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custSSNId=" + custSSNId + ", custName=" + custName + ", custAge="
				+ custAge + ", custAddress=" + custAddress + ", custState=" + custState + ", custCity=" + custCity
				+ ", status=" + status + ", message=" + message + ", lastUpdated=" + lastUpdated + "]";
	}
	
	//generate to string method
	
	
	

}
