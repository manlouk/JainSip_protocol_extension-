package net.java.sip.communicator.additionalServices;

import java.util.NoSuchElementException;

import net.java.sip.communicator.db.ForwardDB;

public class ForwardClient {

	ForwardDB dbManager;
	
	public ForwardClient() {
		dbManager = new ForwardDB();
	}
	
	public String getForwardRequest(String username) {
		return dbManager.getForwardRequest(username);
	}
	
	/**
	 * 
	 * @param fromUser
	 * @param toUser
	 * @throws NoSuchElementException in case no such user found
	 * @throws RuntimeException in case of a circle forwarding
	 */
	public void ForwardSet(String fromUser, String toUser) throws NoSuchElementException, RuntimeException{
		dbManager.ForwardSet(fromUser, toUser);
	}
	
	public void resetForward(String fromUser) {
		dbManager.ForwardReset(fromUser);
	}
}
