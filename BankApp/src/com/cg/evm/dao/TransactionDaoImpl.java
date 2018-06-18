package com.cg.evm.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import util.DatabaseConnection;

import com.cg.evm.dto.Transaction;
import com.cg.evm.exception.InvalidAccountException;


public class TransactionDaoImpl implements TransactionDao{

	Logger lg=Logger.getLogger(TransactionDaoImpl.class);
	 
	@Override
	public int addTransactionDetails(Transaction trans) throws InvalidAccountException {
		
		lg.debug("adding Transaction details...");
		
		Connection con=DatabaseConnection.getConnection();
		
		String sql="insert into transactions"
				+ " (transactionId, type, trans_date ,accountNo) "
				+ " values(transactionId_seq.nextval,?,?,?)";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1,trans.getType());
			ps.setDate(2,new java.sql.Date(trans.getTrans_date().getTime()));
			ps.setInt(3,trans.getAccountNo());
		
				int r= ps.executeUpdate();
				if(r==1){
						
						lg.info("insert query successfull for acc no "+trans.getAccountNo());
						
						Statement s=con.createStatement();
						String query="select transactionId_seq.currval from dual ";
						ResultSet rs= s.executeQuery(query);
						
						lg.debug("select transactionId_seq.currval successfull");
						
						if(rs.next())
						{
							int tid= rs.getInt(1);
							con.close();
							return tid;
						}else
							lg.warn("sequence has no currval");
				}
			
		}catch (SQLException e) {
			lg.fatal(e);
			throw new InvalidAccountException(e.getMessage());
		}
		
		lg.warn("returning zero for "+trans);
		return 0;
	}

	@Override
	public ArrayList<Transaction> getTransactionsList(int accNo) throws InvalidAccountException {
		
		lg.debug("Getting transaction list for "+accNo+" ...");
		
		ArrayList<Transaction> list= new ArrayList<Transaction>();
		
		String sql="select transactionid,type,trans_date,accountNo "
				+ " from transactions "
				+ "  where accountno=?";
		Connection con= DatabaseConnection.getConnection();
		
		
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, accNo);
			
			ResultSet rs= ps.executeQuery();
			lg.info("select query successfull");
			
			while(rs.next()){
				Transaction trans= new Transaction();
				int transactionId=rs.getInt("transactionid");
				String type=rs.getString("type");
				Date trans_date=rs.getDate("trans_date");
				
				trans.setAccountNo(accNo);
				trans.setType(type);
				trans.setTransactionId(transactionId);
				trans.setTrans_date(new java.util.Date(trans_date.getTime()));
				
				list.add(trans);
				lg.debug("record added "+trans);
		}
			
		} catch (SQLException e) {
			lg.fatal(e);
			throw new InvalidAccountException(e.getMessage());
		}
		
		lg.debug("Retuning lis "+list);
		return list;
		
	}

}
