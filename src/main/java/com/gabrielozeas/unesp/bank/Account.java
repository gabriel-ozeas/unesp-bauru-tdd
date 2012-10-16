package com.gabrielozeas.unesp.bank;

import java.math.BigDecimal;

public class Account {
	public enum Bank {
		BRADESCO, ITAU, SANTANDER, HSBC;
	}

	private Bank bank;
	private String agency;
	private String account;
	private String name;
	private String cpf;

	private BigDecimal balance;

	public void debt(BigDecimal value) {
		balance = balance.subtract(value);
	}

	public void credit(BigDecimal value) {
		balance = balance.add(value);
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getBalance() {
		return balance;
	}
}
