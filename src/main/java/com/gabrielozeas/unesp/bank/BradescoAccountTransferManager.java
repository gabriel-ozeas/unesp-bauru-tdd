package com.gabrielozeas.unesp.bank;

import java.math.BigDecimal;

import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.domain.Account.Bank;
import com.gabrielozeas.unesp.bank.exception.NoMoneyBabyException;

public class BradescoAccountTransferManager implements AccountTransfer {
	
	private BradescoDocAccountTransfer docAccountTransfer;
	private BradescoTedAccountTransfer tedAccountTransfer;
	
	public Transaction transfer(Account debtAccount, Account creditAccount, BigDecimal value) throws TransferException {
		if (!(Bank.BRADESCO.equals(debtAccount.getBank()))) {
			throw new IllegalArgumentException("Não é possível realizar debito de conta de outro banco.");
		}
		
		if (debtAccount.getBalance().compareTo(value) < 0) {
			throw new NoMoneyBabyException("Você precisa trabalhar mais");
		}
		
		if (Bank.BRADESCO.equals(creditAccount.getBank())) {
			debtAccount.debt(value);
			creditAccount.credit(value);
			
			return new Transaction(debtAccount, creditAccount, value);
		} else if (BradescoDocAccountTransfer.LIMIT_TRANSACTION_AMOUNT.compareTo(value) > -1){
			return docAccountTransfer.transfer(debtAccount, creditAccount, value);
		} else {
			return tedAccountTransfer.transfer(debtAccount, creditAccount, value);
		}
	}

	public BradescoDocAccountTransfer getDocAccountTransfer() {
		return docAccountTransfer;
	}

	public void setDocAccountTransfer(BradescoDocAccountTransfer docAccountTransfer) {
		this.docAccountTransfer = docAccountTransfer;
	}

	public BradescoTedAccountTransfer getTedAccountTransfer() {
		return tedAccountTransfer;
	}

	public void setTedAccountTransfer(BradescoTedAccountTransfer tedAccountTransfer) {
		this.tedAccountTransfer = tedAccountTransfer;
	}
	
	
	
	
}
