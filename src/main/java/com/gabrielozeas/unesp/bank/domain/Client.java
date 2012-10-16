package com.gabrielozeas.unesp.bank.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client {
	private String name;
	private String cpf;
	
	private List<Account> accounts = new ArrayList<Account>();
	private Queue<Transaction> authorizations = new ConcurrentLinkedQueue<Transaction>();
	
	public Client(){}
	public Client(String name, String cpf) {
		super();
		this.name = name;
		this.cpf = cpf;
	}
	
	public void authorize(Transaction transaction) {
		authorizations.add(transaction);
	}
	
	public boolean isAuthorized(Transaction transaction) {
		return authorizations.contains(transaction);
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
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public Queue<Transaction> getAuthorizations() {
		return authorizations;
	}
	public void setAuthorizations(Queue<Transaction> authorizations) {
		this.authorizations = authorizations;
	}
}	
