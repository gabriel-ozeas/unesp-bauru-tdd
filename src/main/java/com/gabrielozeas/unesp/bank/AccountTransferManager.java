package com.gabrielozeas.unesp.bank;


import java.math.BigDecimal;

import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Account.Bank;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NoMoneyBabyException;

public class AccountTransferManager implements AccountTransfer {
	
	private DocAccountTransfer docAccountTransfer;
	private TedAccountTransfer tedAccountTransfer;
	
	public Transaction transfer(Transaction transaction) throws TransferException {
		Account debtAccount = transaction.getDebtAccount();
		Account creditAccount = transaction.getCreditAccount();
		
		BigDecimal value = transaction.getValue();
		
		if (!(Bank.BRADESCO.equals(debtAccount.getBank()))) {
			throw new IllegalArgumentException("Não é possível realizar debito de conta de outro banco.");
		}
		
		if (debtAccount.getBalance().compareTo(value) < 0) {
			throw new NoMoneyBabyException("Você precisa trabalhar mais");
		}
		
		if (Bank.BRADESCO.equals(creditAccount.getBank())) {
			debtAccount.debt(value);
			creditAccount.credit(value);
			
			return transaction;
		} else if (DocAccountTransfer.LIMIT_TRANSACTION_AMOUNT.compareTo(value) > -1){
			return docAccountTransfer.transfer(transaction);
		} else {
			return tedAccountTransfer.transfer(transaction);
		}
	}

	public DocAccountTransfer getDocAccountTransfer() {
		return docAccountTransfer;
	}

	public void setDocAccountTransfer(DocAccountTransfer docAccountTransfer) {
		this.docAccountTransfer = docAccountTransfer;
	}

	public TedAccountTransfer getTedAccountTransfer() {
		return tedAccountTransfer;
	}

	public void setTedAccountTransfer(TedAccountTransfer tedAccountTransfer) {
		this.tedAccountTransfer = tedAccountTransfer;
	}
}
