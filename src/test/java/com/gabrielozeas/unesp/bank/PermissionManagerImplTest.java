package com.gabrielozeas.unesp.bank;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.gabrielozeas.unesp.bank.domain.Account;
import com.gabrielozeas.unesp.bank.domain.Client;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NotAuthorizatedException;

public class PermissionManagerImplTest {
	
	@Test
	public void maisQue1000ComAutorizacaoDonoReturnTrue() throws Exception {
		Transaction transaction = new Transaction();
		transaction.setValue(new BigDecimal("1000"));
		
		Client owner = new Client();
		transaction.setDebtAccount(new Account().setOwner(owner));
		
		owner.authorize(transaction);
		
		PermissionManagerImpl permission = new PermissionManagerImpl();
		assertTrue(permission.authorizeTransfer(transaction));
	}
	
	@Test(expected=NotAuthorizatedException.class)
	public void maisQue1000SemAutorizacaoDonoLanceExcecao() throws Exception {
		Transaction transaction = new Transaction();
		transaction.setValue(new BigDecimal("1000"));
		
		Client owner = new Client();
		transaction.setDebtAccount(new Account().setOwner(owner));
		
		PermissionManagerImpl permission = new PermissionManagerImpl();
		permission.authorizeTransfer(transaction);
	}
	
	@Test
	public void smallValuesReturnTrue() throws Exception {
		Transaction transaction = new Transaction();
		transaction.setValue(new BigDecimal("10"));
		
		PermissionManagerImpl permission = new PermissionManagerImpl();
		assertTrue(permission.authorizeTransfer(transaction));
	}
}
