package com.gabrielozeas.unesp.bank;

import java.math.BigDecimal;

import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Transaction;

public interface AccountTransfer {
	public Transaction transfer(Account debtAccount, Account creditAccount, BigDecimal value) throws TransferException; 
}
