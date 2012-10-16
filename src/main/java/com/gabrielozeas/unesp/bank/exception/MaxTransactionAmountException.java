package com.gabrielozeas.unesp.bank.exception;

import com.gabrielozeas.unesp.bank.TransferException;

public class MaxTransactionAmountException extends TransferException {
private static final long serialVersionUID = -1914549979793899972L;
	
	public MaxTransactionAmountException() {}
	public MaxTransactionAmountException(String msg) {super(msg);}
}
