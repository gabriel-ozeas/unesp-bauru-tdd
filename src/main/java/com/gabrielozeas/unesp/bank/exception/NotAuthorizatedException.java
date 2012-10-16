package com.gabrielozeas.unesp.bank.exception;

import com.gabrielozeas.unesp.bank.TransferException;

public class NotAuthorizatedException extends TransferException {
	private static final long serialVersionUID = 2239537228012417413L;

	public NotAuthorizatedException() {}
	public NotAuthorizatedException(String msg) {super(msg);}
	public NotAuthorizatedException(String msg, Exception e) {
		super(msg, e);
	}
}
