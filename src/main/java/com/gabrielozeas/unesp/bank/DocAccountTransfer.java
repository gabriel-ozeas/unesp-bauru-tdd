package com.gabrielozeas.unesp.bank;

import java.math.BigDecimal;

import com.gabrielozeas.unesp.bank.communication.CommunicationException;
import com.gabrielozeas.unesp.bank.communication.CommunicationGateway;
import com.gabrielozeas.unesp.bank.communication.CommunicationGatewayFactory;
import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.MaxTransactionAmountException;

public class DocAccountTransfer implements AccountTransfer {
	public static final BigDecimal LIMIT_TRANSACTION_AMOUNT = new BigDecimal("3200");
		
	public Transaction transfer(Transaction transaction) throws TransferException {
		Account debtAccount = transaction.getDebtAccount();
		Account creditAccount = transaction.getDebtAccount();
		
		BigDecimal value = transaction.getValue();
		
		if (LIMIT_TRANSACTION_AMOUNT.compareTo(value) < 0) {
			throw new MaxTransactionAmountException("Valor maximo permitido é " + LIMIT_TRANSACTION_AMOUNT);
		}
		debtAccount.debt(value);
		
		CommunicationGateway gateway = CommunicationGatewayFactory.getGateway(creditAccount.getBank());
		try {
			gateway.doc(transaction);
		} catch (CommunicationException e) {
			throw new TransferException("Cannot transfer the doc", e);
		}
		return null;
	}
}
