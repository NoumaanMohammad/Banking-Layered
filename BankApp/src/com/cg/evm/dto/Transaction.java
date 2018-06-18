package com.cg.evm.dto;

import java.util.Date;

public class Transaction {
	
	int transactionId;
	String type;
	Date trans_date;
	int accountNo;
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(Date trans_date) {
		this.trans_date = trans_date;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	
	public String toString()
	{
		return 	"\n---------------------------------------------"
				+"\nTransaction Id 		: "+this.transactionId
				+"\nTransaction Type	: "+this.getType()
				+"\nTransaction Date 	: "+this.getTrans_date()
				+"\nAccount Number 		: "+this.getAccountNo();
	}
}
