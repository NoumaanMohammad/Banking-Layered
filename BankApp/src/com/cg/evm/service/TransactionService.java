package com.cg.evm.service;

import java.util.ArrayList;

import com.cg.evm.dto.Transaction;
import com.cg.evm.exception.InvalidAccountException;

public interface TransactionService {
	public int addTransactionDetails(Transaction trans) throws InvalidAccountException ;
	public ArrayList<Transaction> getTransactionsList(int accNo ) throws InvalidAccountException;
}
