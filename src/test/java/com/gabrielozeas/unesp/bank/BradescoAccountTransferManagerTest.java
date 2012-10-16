package com.gabrielozeas.unesp.bank;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Account.Bank;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NoMoneyBabyException;

public class BradescoAccountTransferManagerTest {
	
	private BradescoAccountTransferManager transferManager;
	
	private Account debtAcc;
	private Account creditAcc;
	
	@Before
	public void init() throws Exception {
		transferManager = new BradescoAccountTransferManager();
		
		debtAcc = new Account();
		debtAcc.setBank(Bank.BRADESCO);
		
		creditAcc = new Account();
		creditAcc.setBank(Bank.BRADESCO);
	}
	
	@Test(expected=NoMoneyBabyException.class)
	public void shouldThrowExceptionIfDebtAccountHasNoMoney() throws Exception {
		transferManager.transfer(debtAcc, creditAcc, new BigDecimal("20"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfDebitAccountIsntBradesco() throws Exception {
		debtAcc.setBank(Bank.ITAU);
		debtAcc.credit(new BigDecimal("20"));
		
		transferManager.transfer(debtAcc, creditAcc, new BigDecimal("20"));
	}
	
	@Test
	public void shouldTransferToAccountsWithSucess() throws Exception {
		debtAcc.credit(new BigDecimal("20"));

		Transaction transaction = transferManager.transfer(debtAcc, creditAcc, new BigDecimal("20"));
		assertEquals(new BigDecimal("0"), debtAcc.getBalance());
		assertEquals(new BigDecimal("20"), creditAcc.getBalance());
		
		assertEquals(debtAcc, transaction.getDebtAccount());
		assertEquals(creditAcc, transaction.getCreditAccount());
		assertEquals(new BigDecimal("20"), transaction.getValue());
	}
}
