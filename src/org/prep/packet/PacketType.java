package org.prep.packet;

/**
 * Represents the variations of packets that can be received.
 * @author Stephen Andrews
 */
public enum PacketType {

	/**
	 * The accelerate packet.
	 */
	ACCELERATE(0),
	
	/**
	 * The reverse packet.
	 */
	REVERSE(1),
	
	/**
	 * Right turn packet.
	 */
	RIGHT_TURN(2),
	
	/**
	 * Left turn packet.
	 */
	LEFT_TURN(3),
	
	/**
	 * An unhandled packet.
	 */
	UNHANDLED(4);
	
	/**
	 * The opcode of the packet type.
	 */
	private int opcode;
	
	/**
	 * Constructs a packet type. Private constructor to prevent instantiation.
	 * @param opcode The opcode of the packet type.
	 */
	private PacketType(int opcode) {
		this.opcode = opcode;
	}
	
	/**
	 * Gets an integer representing the packet's opcode.
	 * @return An integer representing the packet's opcode.
	 */
	public int toInteger() {
		return opcode;
	}
	
	/**
	 * Gets a packet type for the specified opcode.
	 * @param opcode The opcode of the packet type.
	 * @return The packet type.
	 */
	public static PacketType getPacketType(int opcode) {
		return PacketType.values()[opcode];
	}
}
