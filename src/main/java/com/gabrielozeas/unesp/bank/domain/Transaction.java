package com.gabrielozeas.unesp.bank.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Transaction {
	private String transactionNumber;
	private Account debtAccount;
	private Account creditAccount;
	private BigDecimal value;
	private Date date = new Date();
	
	public Transaction(){
		generateTransactionNumber();
	}
	
	public Transaction(Account debtAccount, Account creditAccount, BigDecimal value) {
		generateTransactionNumber();
		this.debtAccount = debtAccount;
		this.creditAccount = creditAccount;
		this.value = value;
	}
	
	private void generateTransactionNumber() {
		transactionNumber = UUID.randomUUID().toString();
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public Account getDebtAccount() {
		return debtAccount;
	}

	public void setDebtAccount(Account debtAccount) {
		this.debtAccount = debtAccount;
	}

	public Account getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(Account creditAccount) {
		this.creditAccount = creditAccount;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
