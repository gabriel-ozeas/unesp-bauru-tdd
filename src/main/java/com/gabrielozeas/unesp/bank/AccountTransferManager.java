package com.gabrielozeas.unesp.bank;

import java.math.BigDecimal;

import com.gabrielozeas.unesp.bank.Account.Bank;

public class AccountTransferManager {

	public void transfer(Account debtAccount, Account creditAccount, BigDecimal value) throws NoMoneyBabyException {
		if (!(Bank.BRADESCO.equals(debtAccount))) {
			throw new IllegalArgumentException("Não é possível realizar debito de conta de outro banco.");
		}
		
		if (Bank.BRADESCO.equals(creditAccount)) {
			if (debtAccount.getBalance().compareTo(value) < 0) {
				throw new NoMoneyBabyException("Você precisa trabalhar mais");
			} else {
				debtAccount.debt(value);
				creditAccount.credit(value);
			}
		}
	}
}
