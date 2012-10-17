package com.gabrielozeas.unesp.bank;


import com.gabrielozeas.unesp.bank.domain.Transaction;

public interface AccountTransfer {
	public Transaction transfer(Transaction transaction) throws TransferException; 
}
