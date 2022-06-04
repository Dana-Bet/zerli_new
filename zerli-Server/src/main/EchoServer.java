package main;
// This file contains material supporting section 3.7 of the textbook:

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import Entities.Message;
import Entities.MyFile;
import Parsing.ParsingServer;
import controllers.ServerUIFController;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;


/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 */


public class EchoServer extends AbstractServer {
	// Class variables *************************************************
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	public static ServerUIFController serverUIFController;
	private Message resMessage;
	
	public EchoServer(int port) {
		super(port);
	}

	@Override
	
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		resMessage = ParsingServer.parsing(msg, client);
		try {
			client.sendToClient(resMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	
	public void handleMessageFromClientUpFile(Object msg, ConnectionToClient client) {
		int fileSize = ((MyFile) msg).getSize();
		
		File newFile = new File(((MyFile)msg).getFileName() + ".PDF");

		byte[] mybytearray = new byte[(int) newFile.length()];
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(newFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		try {
			bos.write(((MyFile)msg).getMybytearray(), 0, mybytearray.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Message received: " + ((MyFile)msg).getFileName() + " from " + client);
		System.out.println("length " + fileSize);
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *                is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on
		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class