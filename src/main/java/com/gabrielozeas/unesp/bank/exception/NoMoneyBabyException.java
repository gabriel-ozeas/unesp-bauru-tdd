package com.gabrielozeas.unesp.bank.exception;

import com.gabrielozeas.unesp.bank.TransferException;

public class NoMoneyBabyException extends TransferException {
	private static final long serialVersionUID = -913761885963835054L;
	
	public NoMoneyBabyException(){}
	public NoMoneyBabyException(String msg) {super(msg);}
}
