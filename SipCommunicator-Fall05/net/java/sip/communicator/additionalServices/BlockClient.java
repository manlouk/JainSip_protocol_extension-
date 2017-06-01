package net.java.sip.communicator.additionalServices;

import java.util.NoSuchElementException;

import net.java.sip.communicator.db.Blocking;

public class BlockClient {

	Blocking dbManager;
	
	public BlockClient() {
		dbManager = new Blocking();
	}
	
	public void enableBlock(){
		
	}
	public String getBlocks(String username) {
		return dbManager.getBlocks(username);
	}
	
	/**
	 * 
	 * @param fromUser
	 * @param toUser
	 * @throws NoSuchElementException in case no such user found
	 * @throws RuntimeException in case of a circle forwarding
	 */
	public void blockUser(String fromUser, String toUser) throws NoSuchElementException, RuntimeException{
		dbManager.blockUser(fromUser, toUser);
	}
	
	public void unblockUser(String fromUser, String toUser) {
		dbManager.unblockUser(fromUser, toUser);
	}
}
