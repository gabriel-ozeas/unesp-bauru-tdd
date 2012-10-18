package com.gabrielozeas.unesp.bank.bdd.steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.mockito.internal.stubbing.answers.ThrowsException;

import com.gabrielozeas.unesp.bank.AccountTransferManager;
import com.gabrielozeas.unesp.bank.TedAccountTransfer;
import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Account.Bank;
import com.gabrielozeas.unesp.bank.domain.Client;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NotAuthorizatedException;

public class TransferSteps {
	private Account debtAccount;
	private Transaction transaction;
	private Throwable throwable;
	
	@Given("a account with balance of ${balance}")
	public void givenAAccountWithBalance(double balance) {
		debtAccount = new Account();
		debtAccount.setBank(Bank.BRADESCO);
		debtAccount.credit(new BigDecimal(balance));
	}
	
	@Given("owner $owner")
	public void givenOwner(String ownerName) {
		Client owner = new Client();
		owner.setName(ownerName);
		debtAccount.setOwner(owner);
	}
	
	@Given("partners $partnerNames")
	public void givenPartnersJoaoMaria(List<String> partnerNames) {
		for (String name : partnerNames) {
			Client partner = new Client();
			partner.setName(name);
			debtAccount.addPartner(partner);
		}
	}
	
	@When("owner request transfer of ${amount} to ${bank}")
	public void requestTransfer(double amount, String bank) {
		Account creditAccount = new Account();
		creditAccount.setBank(Bank.getBank(bank));
		  
		transaction = new Transaction(debtAccount, creditAccount, new BigDecimal(amount));
	}

	@When("a transfer happens")
	public void whenATransferHappens() throws Exception {
		AccountTransferManager transferManager = new AccountTransferManager();
		transferManager.setTedAccountTransfer(new TedAccountTransfer());
		try {
			transferManager.transfer(transaction);
		} catch (Throwable t) {
			throwable = t;
		}
	} 

	@When("owner authorize the transfer")
	public void whenOwnerAuthorizeTheTransfer() {
		debtAccount.getOwner().authorize(transaction);
	}

	@Then("the account has balance of ${amount}")
	public void thenTheAccountHasLess(double amount) {
		assertEquals(new BigDecimal(amount), debtAccount.getBalance());
	}

}
