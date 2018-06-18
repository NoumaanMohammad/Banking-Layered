package com.cg.evm.test;

import java.beans.Transient;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.cg.evm.dao.TransactionDaoImpl;
import com.cg.evm.dto.Transaction;
import com.cg.evm.exception.InvalidAccountException;

public class TransactionDaoTest {

	
	@Test
	public void addTransactionTest() throws InvalidAccountException
	{
		TransactionDaoImpl dao=new TransactionDaoImpl();
		
		Transaction trans=new Transaction();
		trans.setAccountNo(10001);
		trans.setTrans_date(new java.util.Date());
		trans.setType("Deposit");
		
		int id;
	
			id = dao.addTransactionDetails(trans);

			List<Transaction> list=dao.getTransactionsList(10001);
			//System.out.println(" "+id+" "+list.get(0).getTransactionId()+" "+list.size());
			Assert.assertEquals(id,list.get(0).getTransactionId());
			
		
		
	}
}
