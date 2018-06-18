package com.cg.evm.dao;

import java.util.ArrayList;

import com.cg.evm.dto.Transaction;
import com.cg.evm.exception.InvalidAccountException;

public interface TransactionDao {

	public int addTransactionDetails(Transaction trans) throws InvalidAccountException;
	public ArrayList getTransactionsList(int accNo ) throws InvalidAccountException;
	
}
