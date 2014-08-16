package org.prep;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import org.prep.action.ActionQueue;
import org.prep.packet.PacketParser;

/**
 * The core of the server.
 * @author Stephen Andrews
 */
public class Server {
	
	/**
	 * Logger instance.
	 */
	private final Logger logger = Logger.getLogger(Server.class.getName());
	
	/**
	 * The port the server runs on.
	 */
	private final int PORT = 1337;
	
	/**
	 * The server socket.
	 */
	private ServerSocket serverSocket;
	
	/**
	 * The client socket.
	 */
	private static Socket client;
	
	/**
	 * An instance of the packet parser.
	 */
	private PacketParser packetParser;
	
	/**
	 * An instance of the action queue.
	 */
	private static ActionQueue actionQueue;
	
	/**
	 * The main method of the server.
	 * @param args The arguments.
	 */
	public static void main(String[] args) {
		new Server();
	}
	
	/**
	 * Constructs a server.
	 */
	public Server() {
		actionQueue = new ActionQueue();
		bindToPort();
		startLoop();
	}
	
	/**
	 * Binds the server to the specified port.
	 */
	public void bindToPort() {
		try {
			logger.info("Attempting to bind server to port: " + PORT);
			serverSocket = new ServerSocket(PORT);
			logger.info("Listening on port: " + PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts the server loop allowing connections and the processing of data.
	 */
	public void startLoop() {
		try {
			logger.info("Awaiting a connection...");
			while(true) {
				try {
					client = serverSocket.accept();
					packetParser = new PacketParser();
					packetParser.onReceive(packetParser.getIn().readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets the client socket.
	 * @return The client socket.
	 */
	public static Socket getClient() {
		return client;
	}
	
	/**
	 * Gets the action queue.
	 * @return The action queue.
	 */
	public static ActionQueue getActionQueue() {
		return actionQueue;
	}
}
