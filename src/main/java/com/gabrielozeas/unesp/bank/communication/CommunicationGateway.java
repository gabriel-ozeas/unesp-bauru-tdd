package com.gabrielozeas.unesp.bank.communication;

import com.gabrielozeas.unesp.bank.domain.Transaction;

public interface CommunicationGateway {
	public void doc(Transaction transaction) throws CommunicationException;
	public void ted(Transaction transaction) throws CommunicationException;
}
