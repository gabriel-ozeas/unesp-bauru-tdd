package com.gabrielozeas.unesp.bank.communication;

public class CommunicationException extends Exception {
	public CommunicationException() {}
	public CommunicationException(String msg) {super(msg);}
	public CommunicationException(String msg, Exception e) {
		super(msg, e);
	}
}
