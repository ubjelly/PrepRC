package org.prep.packet;

/**
 * An interface used to listen for incoming packets.
 * @author Stephen Andrews
 */
public interface PacketListener {

	/**
	 * Handles the actions after receiving an incoming packet.
	 * @param packet The packet being received.
	 */
	public void onReceive(String packet);
	
}
