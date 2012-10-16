package com.gabrielozeas.unesp.bank;

import java.math.BigDecimal;

public interface AccountTransferManager {
	public void transfer(Account debtAccount, Account creditAccount, BigDecimal value) throws NoMoneyBabyException; 
}
