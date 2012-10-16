package com.gabrielozeas.unesp.bank;

public class NoMoneyBabyException extends Exception {
	private static final long serialVersionUID = -913761885963835054L;
	
	public NoMoneyBabyException(){}
	public NoMoneyBabyException(String msg) {super(msg);}
}
