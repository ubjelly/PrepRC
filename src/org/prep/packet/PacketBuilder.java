package org.prep.packet;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.prep.Server;

/**
 * Builds packets to send to the client.
 * @author Stephen Andrews
 */
public class PacketBuilder {

	/**
	 * The output stream writer.
	 */
	private OutputStreamWriter out;
	
	/**
	 * Constructs a packet builder.
	 */
	public PacketBuilder() {
		try {
			out = new OutputStreamWriter(Server.getClient().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Builds a packet and sends it to the client.
	 * @param packet The type of packet to send.
	 */
	public void build(PacketType packet) {
		try {
			out.write("[" + packet.name() + "]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
