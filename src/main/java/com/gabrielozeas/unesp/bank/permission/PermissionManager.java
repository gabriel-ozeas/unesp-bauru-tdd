package com.gabrielozeas.unesp.bank.permission;

import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NotAuthorizatedException;

public interface PermissionManager {

	/**
	 * Verifica o limite de credito pré-aprovado
	 * Verifica o limite do saldo
	 * Verifica se a conta é conjunta e se tem aprovação dos dois 
	 */
	public boolean authorizeTransfer(Transaction transaction) throws NotAuthorizatedException;
}
