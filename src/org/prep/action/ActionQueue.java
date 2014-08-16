package org.prep.action;

import java.util.ArrayList;

/**
 * A queue of running actions for the server to perform.
 * @author Stephen Andrews
 */
public class ActionQueue {

	/**
	 * The action queue.
	 */
	private ArrayList<Action> queue;
	
	/**
	 * Constructs an action queue.
	 */
	public ActionQueue() {
		queue = new ArrayList<Action>();
	}
	
	/**
	 * Adds an action to the queue.
	 * @param action The action to be added to the queue.
	 */
	public void add(Action action) {
		queue.add(action);
		action.fire();
	}
	
	/**
	 * Removes an action from the queue.
	 * @param action The action to be removed.
	 */
	public void destroy(Action action) {
		action.stop();
		queue.remove(action);
	}
	
	/**
	 * Ciphers through each action in the queue and, if the action
	 * isn't currently running, executes it.
	 */
	public void startIdleActions() {
		for (Action action : queue) {
			if (!action.isRunning()) {
				action.fire();
			}
		} 
	}
	
}
