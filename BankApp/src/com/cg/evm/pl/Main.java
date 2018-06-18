package com.cg.evm.pl;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.evm.dto.Transaction;
import com.cg.evm.exception.InvalidAccountException;
import com.cg.evm.service.TransactionServiceImpl;

public class Main {

	static Logger lg=Logger.getLogger(Main.class);
	
	//method to perform Transaction.
	public static void performTransaction()
	{
		lg.info("starting transaction..");
		
		Scanner in=new Scanner(System.in);
		System.out.print("\nEnter Type : ");
		String type=in.next();
		//System.out.print("\nEnter trasaction Date:");
		Date date=new Date();
		System.out.print("\nEnter Account Number : ");
		int accNo=in.nextInt();
		Transaction tran=new Transaction();
		tran.setAccountNo(accNo);
		tran.setTrans_date(date);
		tran.setType(type);
		
		TransactionServiceImpl service=new TransactionServiceImpl();
		
		int id = 0;
		try {
			id = service.addTransactionDetails(tran);
			System.out.print("\nTransaction perforemed Id : "+id);
		} catch (InvalidAccountException e) {
			lg.error("Error while transaction : "+e);
			e.printStackTrace();
		}
		
		lg.info("transaction completed for "+accNo+" with trans id "+id);
		
	}
	
	
	//method to display All transaction for specific account number.
	public static void displayTransaction()
	{
		lg.info("Displaying Transaction ...");
		Scanner in=new Scanner(System.in);
		System.out.print("\nEnter account number : ");
		int accountNo=in.nextInt();
		TransactionServiceImpl service=new TransactionServiceImpl();
		
		List<Transaction> list = null;
		try {
			list = service.getTransactionsList(accountNo);
		} catch (InvalidAccountException e) {
			lg.error("Error while display "+e);
			e.printStackTrace();
		}
		
		if(list!=null&&list.size()==0)
		{
			lg.warn("No record found for "+accountNo);
			System.out.println("No Transaction Record found for you ...");
		}else{
			lg.debug("Displaying "+list);
			
			for(Transaction tran : list)
			{
				System.out.println("\n"+tran);
			}
		}
		
		lg.info("Display successfull.");
	}
	public static void main(String[] args) {
		
		//Configure log4J logger.
		PropertyConfigurator.configure("log4j.properties");
		
		//Display Menu and take choice from user.
		Scanner in=new Scanner(System.in);
		while(true){
			System.out.print("\n1. Accept transaction details from user and insert in DB."
					+"\n2. Display list of transactions for perticular Account .\n3.Exit");
			System.out.print("\nEnter your choice: ");
			int ch=in.nextInt();
			
			switch(ch)
			{
				case 1:
					performTransaction();
					break;
				case 2:
					displayTransaction();
					break;
				case 3:
					System.exit(0);
			}
		}
	}
}
