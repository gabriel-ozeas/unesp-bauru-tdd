package com.gabrielozeas.unesp.bank.permission;

import java.math.BigDecimal;

import com.gabrielozeas.unesp.bank.domain.Client;
import com.gabrielozeas.unesp.bank.domain.Transaction;
import com.gabrielozeas.unesp.bank.exception.NotAuthorizatedException;

public class PermissionManagerImpl implements PermissionManager {

	public boolean authorizeTransfer(Transaction transaction) throws NotAuthorizatedException {
		if (new BigDecimal("10000").compareTo(transaction.getValue()) < 1) {
			for (Client partner:transaction.getDebtAccount().getPartners()) {
				if (!(partner.isAuthorized(transaction))) {
					throw new NotAuthorizatedException();
				}
			}
		}
		
		if (new BigDecimal("1000").compareTo(transaction.getValue()) < 1) {
			if (transaction.getDebtAccount().getOwner().isAuthorized(transaction)) {
				return true;
			} else {
				throw new NotAuthorizatedException();
			}
		} else {
			return true;
		}
	}
}
