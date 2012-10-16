package com.gabrielozeas.unesp.bank;

import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NotAuthorizatedException;
import com.gabrielozeas.unesp.bank.permission.PermissionManager;

public class PermissionManagerImpl implements PermissionManager {
	public boolean authorizeTransfer(Transaction transaction) throws NotAuthorizatedException {
		return false;
	}
}
