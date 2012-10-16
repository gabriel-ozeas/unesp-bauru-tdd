package com.gabrielozeas.unesp.bank.permission;

import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NotAuthorizatedException;

public class PermissionManagerImpl implements PermissionManager {
	public boolean authorizeTransfer(Transaction transaction) throws NotAuthorizatedException {
		return false;
	}
}
