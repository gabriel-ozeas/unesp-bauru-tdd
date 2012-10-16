package com.gabrielozeas.unesp.bank;

public class TransferException extends Exception {
	private static final long serialVersionUID = -1914549979793899972L;
	
	public TransferException() {}
	public TransferException(String msg) {super(msg);}
	public TransferException(String msg, Exception e) {
		super(msg, e);
	}
}
