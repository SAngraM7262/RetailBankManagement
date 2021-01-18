package com.nationalbank.entity;

public class TransactionSet {

	private int accId;
    private int transactionId;
    private int tAmount ;
    
    private String accType;
    private String tDescription;
    private String transactionDate;
    private String sourceAccType ;
    private String targetAccType;
    
    public int getAcctId() {
        return this.accId;
    }
    
    public void setAcctId(final int accId) {
        this.accId = accId;
    }
    
    public int getTransactionId() {
        return this.transactionId;
    }
    
    public void setTransactionId(final int transactionId) {
        this.transactionId = transactionId;
    }
    public int getTAmount() {
        return this.tAmount;
    }
    
    public void setTAmount(final int tAmount) {
        this.tAmount= tAmount;
    }
    
    
    public String getAccType() {
        return this.accType;
    }
    
    public void setAccType(final String accType) {
        this.accType = accType;
    }
    public String getTransactionDate() {
        return this.transactionDate;
    }
    
    public void setTransactionDate(final String transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    public String getSourceAccType() {
        return this.sourceAccType;
    }
    
    public void setSourceAccType (final String sourceAccType ) {
        this.sourceAccType  = sourceAccType ;
    }
    public String getTargetAccType() {
        return this.targetAccType;
    }
    
    public void setTargetAccType (final String targetAccType ) {
        this.targetAccType  = targetAccType ;
    }
    
    public String getTDescription() {
        return this.tDescription;
    }
    
    public void setTDescription(final String tDescription) {
        this.tDescription = tDescription;
    }
}
