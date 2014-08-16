package org.prep.action;

import org.prep.action.impl.AccelerateAction;
import org.prep.packet.PacketType;

/**
 * Models an action the RC car is required to complete.
 * @author Stephen Andrews
 */
public abstract class Action {

	/**
	 * Whether or not the action is currently running.
	 */
	private boolean running;
	
	/**
	 * Constructs an action.
	 */
	public Action() {
		running = false;
	}
	
	/**
	 * Checks if the action is running.
	 * @return <code>true</code> if the action is still running, <code>false</code> if not.
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Stops the action from running in the future.
	 */
	public void stop() {
		running = false;
	}
	
	/**
	 * Invokes the action causing it to run and perform its implemented action.
	 */
	public void fire() {
		running = true;
		while (running) {
			doAction();
		}
	}
	
	/**
	 * Gets the appropriate action based on the packet type.
	 * @param packetType The packet type.
	 */
	public static Action forPacketType(PacketType packetType) {
		switch(packetType) {
			case ACCELERATE:
				return new AccelerateAction();
			default:
				return null;
		}
	}
	
	/**
	 * Handles the action's behavior. Any class that extends this class should implement
	 * the behavior pertaining to the specific action.
	 */
	public abstract void doAction();
	
}
