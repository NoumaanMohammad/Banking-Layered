package com.cg.evm.service;

import java.util.ArrayList;

import com.cg.evm.dao.TransactionDaoImpl;
import com.cg.evm.dto.Transaction;
import com.cg.evm.exception.InvalidAccountException;

public class TransactionServiceImpl implements TransactionService{

	@Override
	public int addTransactionDetails(Transaction trans) throws InvalidAccountException{
		TransactionDaoImpl dao=new TransactionDaoImpl();
		int id = dao.addTransactionDetails(trans);
		return id;
	}

	@Override
	public ArrayList<Transaction> getTransactionsList(int accNo) throws InvalidAccountException{
		TransactionDaoImpl dao=new TransactionDaoImpl();
		return dao.getTransactionsList(accNo);
	}

}
