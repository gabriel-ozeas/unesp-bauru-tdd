package com.gabrielozeas.unesp.bank;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Account.Bank;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NoMoneyBabyException;

public class BradescoAccountTransferManagerTest {
	
	private AccountTransferManager transferManager;
	
	private Transaction transaction;
	
	private Account debtAcc;
	private Account creditAcc;
	
	@Before
	public void init() throws Exception {
		transferManager = new AccountTransferManager();
		
		debtAcc = new Account();
		debtAcc.setBank(Bank.BRADESCO);
		
		creditAcc = new Account();
		creditAcc.setBank(Bank.BRADESCO);
		
		transaction = new Transaction();
		transaction.setDebtAccount(debtAcc);
		transaction.setCreditAccount(creditAcc);
	}
	
	@Test
	public void shouldTransferTedWith3400Value() throws Exception {
		debtAcc.credit(new BigDecimal("3400"));
		creditAcc.setBank(Bank.ITAU);
		
		TedAccountTransfer ted = mock(TedAccountTransfer.class);
		transferManager.setTedAccountTransfer(ted);
		
		Transaction transaction = new Transaction(debtAcc, creditAcc, new BigDecimal("3400"));
		when(ted.transfer(null)).thenReturn(transaction);
		
		transferManager.transfer(transaction);
		verify(ted, times(1)).transfer(transaction);
	}
	
	@Test
	public void shouldTransferDocWith3200Value() throws Exception {
		debtAcc.credit(new BigDecimal("3200"));
		creditAcc.setBank(Bank.ITAU);
		
		final Transaction transaction = new Transaction(debtAcc, creditAcc, new BigDecimal("3200"));
		
		transferManager.setDocAccountTransfer(new DocAccountTransfer(){
			public Transaction transfer(Transaction transaction) throws TransferException {
				return transaction;
			}
		});
		
		Transaction returnedTransaction = transferManager.transfer(transaction);
		assertEquals(transaction, returnedTransaction);
	}
	
	@Test(expected=NoMoneyBabyException.class)
	public void shouldThrowExceptionIfDebtAccountHasNoMoney() throws Exception {
		debtAcc.setBalance(new BigDecimal("10"));
		transaction.setValue(new BigDecimal("20"));
		transferManager.transfer(transaction);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfDebitAccountIsntBradesco() throws Exception {
		debtAcc.setBank(Bank.ITAU);
		debtAcc.credit(new BigDecimal("20"));
		
		transferManager.transfer(transaction);
	}
	
	@Test
	public void shouldTransferToAccountsWithSucess() throws Exception {
		debtAcc.credit(new BigDecimal("20"));
		transaction.setValue(new BigDecimal("20"));
		transferManager.transfer(transaction);
		assertEquals(new BigDecimal("0"), debtAcc.getBalance());
		assertEquals(new BigDecimal("20"), creditAcc.getBalance());
	}
}
