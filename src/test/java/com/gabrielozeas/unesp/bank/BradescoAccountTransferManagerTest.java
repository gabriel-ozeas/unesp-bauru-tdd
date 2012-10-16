package com.gabrielozeas.unesp.bank;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

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
	
	@Test
	public void shouldTransferTedWith3400Value() throws Exception {
		debtAcc.credit(new BigDecimal("3400"));
		creditAcc.setBank(Bank.ITAU);
		
		BradescoTedAccountTransfer ted = mock(BradescoTedAccountTransfer.class);
		transferManager.setTedAccountTransfer(ted);
		
		Transaction transaction = new Transaction(debtAcc, creditAcc, new BigDecimal("3400"));
		when(ted.transfer(any(Account.class), any(Account.class), eq(new BigDecimal("3400")))).thenReturn(transaction);
		
		Transaction returnedTransaction = transferManager.transfer(debtAcc, creditAcc, new BigDecimal("3400"));
		
		verify(ted, times(1)).transfer(debtAcc, creditAcc, new BigDecimal("3400"));
		assertEquals(transaction, returnedTransaction);
	}
	
	@Test
	public void shouldTransferDocWith3200Value() throws Exception {
		debtAcc.credit(new BigDecimal("3200"));
		creditAcc.setBank(Bank.ITAU);
		
		final Transaction transaction = new Transaction(debtAcc, creditAcc, new BigDecimal("3200"));
		
		transferManager.setDocAccountTransfer(new BradescoDocAccountTransfer(){
			public Transaction transfer(Account debtAccount, Account creditAccount, BigDecimal value) throws TransferException {
				return transaction;
			}
		});
		
		Transaction returnedTransaction = transferManager.transfer(debtAcc, creditAcc, new BigDecimal("3200"));
		assertEquals(transaction, returnedTransaction);
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
