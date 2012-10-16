package com.gabrielozeas.unesp.bank;

import java.math.BigDecimal;

import com.gabrielozeas.unesp.bank.communication.CommunicationException;
import com.gabrielozeas.unesp.bank.communication.CommunicationGateway;
import com.gabrielozeas.unesp.bank.communication.CommunicationGatewayFactory;
import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Transaction;

public class BradescoTedAccountTransfer implements AccountTransfer {

	private PermissionManager permissionManager;
	
	public Transaction transfer(Account debtAccount, Account creditAccount, BigDecimal value) throws TransferException {
		permissionManager.requestTed(debtAccount, value);
		
		CommunicationGateway gateway = CommunicationGatewayFactory.getGateway(creditAccount.getBank());
		Transaction transaction = new Transaction();
		transaction.setCreditAccount(debtAccount);
		transaction.setDebtAccount(debtAccount);
		
		try {
			gateway.ted(transaction);
			return transaction;
		} catch (CommunicationException e) {
			throw new TransferException("Cannot transfer the doc", e);
		}
	}

}
