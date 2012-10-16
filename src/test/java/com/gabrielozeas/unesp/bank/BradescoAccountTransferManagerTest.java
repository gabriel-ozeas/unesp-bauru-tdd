package com.gabrielozeas.unesp.bank;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.gabrielozeas.unesp.bank.Account.Bank;

public class BradescoAccountTransferManagerTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfDebitAccountIsntBradesco() throws Exception {
		BradescoAccountTransferManager transferManager = new BradescoAccountTransferManager();
		

		Account debtAcc = new Account();
		debtAcc.setBank(Bank.ITAU);
		debtAcc.credit(new BigDecimal("20"));
		
		Account creditAcc = new Account();
		creditAcc.setBank(Bank.BRADESCO);
		
		transferManager.transfer(debtAcc, creditAcc, new BigDecimal("20"));
	}
	
	@Test
	public void shouldTransferToAccountsWithSucess() throws Exception {
		BradescoAccountTransferManager transferManager = new BradescoAccountTransferManager();
		
		Account debtAcc = new Account();
		debtAcc.setBank(Bank.BRADESCO);
		debtAcc.credit(new BigDecimal("20"));
		
		Account creditAcc = new Account();
		creditAcc.setBank(Bank.BRADESCO);
		
		transferManager.transfer(debtAcc, creditAcc, new BigDecimal("20"));
		assertEquals(new BigDecimal("0"), debtAcc.getBalance());
		assertEquals(new BigDecimal("20"), creditAcc.getBalance());
	}
}
