package com.gabrielozeas.unesp.bank.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
	public enum Bank {
		BRADESCO, ITAU, SANTANDER, HSBC;
	}

	private Bank bank;
	private String agency;
	private String account;
	
	private Client owner;
	private List<Client> partners = new ArrayList<Client>();

	private BigDecimal balance = new BigDecimal("0");

	public Account debt(BigDecimal value) {
		balance = balance.subtract(value);
		return this;
	}

	public Account credit(BigDecimal value) {
		balance = balance.add(value);
		return this;
	}
	
	public Account addPartner(Client client) {
		partners.add(client);
		return this;
	}

	public Client getOwner() {
		return owner;
	}

	public Account setOwner(Client owner) {
		this.owner = owner;
		return this;
	}

	public List<Client> getPartners() {
		return partners;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
	
	public BigDecimal getBalance() {
		return balance;
	}
}
