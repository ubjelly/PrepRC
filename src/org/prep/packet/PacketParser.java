package org.prep.packet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.prep.Server;
import org.prep.action.Action;

/**
 * Parses packets sent from the client.
 * @author Stephen Andrews
 */
public class PacketParser implements PacketListener {

	/**
	 * Logger instance.
	 */
	private final Logger logger = Logger.getLogger(PacketParser.class.getName());
	
	/**
	 * The buffered reader for receiving data.
	 */
	private BufferedReader in;
	
	/**
	 * Constructs a packet parser.
	 */
	public PacketParser() {
		try {
			in = new BufferedReader(new InputStreamReader(Server.getClient().getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Decodes the packet's opcode to determine which action should be performed.
	 * @param opcode The opcode of the packet.
	 */
	private PacketType decodeAction(int opcode) {
		if (opcode > PacketType.values().length || opcode == PacketType.UNHANDLED.toInteger()) {
			logger.warning("Unhandled opcode: " + opcode);
			return PacketType.UNHANDLED;
		}
		
		PacketType packetType = PacketType.values()[opcode];
		return packetType;
	}
	
	/**
	 * Gets the buffered reader.
	 * @return The buffered reader.
	 */
	public BufferedReader getIn() {
		return in;
	}
	
	@Override
	public void onReceive(String packet) {
		int opcode = Integer.valueOf(packet);
		PacketType packetType = decodeAction(opcode);
		
		if (Action.forPacketType(packetType) != null) {
			Server.getActionQueue().add(Action.forPacketType(packetType));
		}
	}

}
