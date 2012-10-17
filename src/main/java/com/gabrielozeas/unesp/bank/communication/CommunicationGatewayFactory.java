package com.gabrielozeas.unesp.bank.communication;

import com.gabrielozeas.unesp.bank.domain.Account.Bank;

public class CommunicationGatewayFactory {
	public static CommunicationGateway getGateway(Bank bank) {
		if (Bank.ITAU.equals(bank)) {
			return new ItauCommunicationGatewayImpl();
		} else {
			return null;
		}
		
	}
}
