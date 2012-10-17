package com.gabrielozeas.unesp.bank;


import com.gabrielozeas.unesp.bank.communication.CommunicationException;
import com.gabrielozeas.unesp.bank.communication.CommunicationGateway;
import com.gabrielozeas.unesp.bank.communication.CommunicationGatewayFactory;
import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.permission.PermissionManager;
import com.gabrielozeas.unesp.bank.permission.PermissionManagerImpl;

public class TedAccountTransfer implements AccountTransfer {

	private PermissionManager permissionManager = new PermissionManagerImpl();
	
	public Transaction transfer(Transaction transaction) throws TransferException {
		Account creditAccount = transaction.getCreditAccount();

		permissionManager.authorizeTransfer(transaction);
		
		CommunicationGateway gateway = CommunicationGatewayFactory.getGateway(creditAccount.getBank());
		try {
			gateway.ted(transaction);
			return transaction;
		} catch (CommunicationException e) {
			throw new TransferException("Cannot transfer the doc", e);
		}
	}

}
