package chatroom;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * RABAAB DHINGRA
 */

public class ChatServer extends ChatWindow {
    
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	private ClientHandler handler;
    
    ServerSocket srv = null;
	public ChatServer(){
		super();
		this.setTitle("Chat Server");
		this.setLocation(80,80);
		try {
			// Create a listening service for connections
			// at the designated port number.
			srv = new ServerSocket(2113);

			while (true) {
				// The method accept() blocks until a client connects.
				printMsg("Waiting for a connection");
				Socket socket = srv.accept();

				handler = new ClientHandler(socket);
               
				//create a thread when a new client is created
                Thread newThread = new Thread(handler);
                newThread.start();
                clients.add(handler);
                
                //after thread created not needed to handle connection
                //handler.handleConnection();
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/** This innter class handles communication to/from one client. */
	class ClientHandler implements Runnable{
		private PrintWriter writer;
		private BufferedReader reader;


		public ClientHandler(Socket socket) {
			try {
				InetAddress serverIP = socket.getInetAddress();
				printMsg("Connection made to " + serverIP);
				writer = new PrintWriter(socket.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			}
			catch (IOException e){
					printMsg("\nERROR:" + e.getLocalizedMessage() + "\n");
				}
		}
		public void handleConnection() {
			try {
				while(true) { 
                    String s = readMsg();
                    //to fix it keep printing null
                    if(s == null){
                        removeUser(this);
                        return;
                    }        
				}
			}
			catch (IOException e){
                printMsg("\nERROR:" + e.getLocalizedMessage() + "\n");
			}

		}
        /** Run for the runnable */
        public void run(){
            handleConnection();
        }

		/** Receive and display a message */
		public String readMsg() throws IOException {
			String s = reader.readLine();
			printMsg(s);
			sendMsg(s);
            
            return s;
		}
		/** Send a string */
		public void sendMsg(String s){
            for(int i = 0; i<clients.size(); i++){
               if(s == null){
                    clients.get(i).writer.println("a client has left the chat");
               } 
               else{
                    clients.get(i).writer.println(s);
               }
            }
		}
        //prevents it from sending null over and over when the user leaves the chat
        public void removeUser(ClientHandler client){
            for(int i = 0; i<clients.size(); i++){
                  clients.get(i);
                  if(client == clients.get(i)){
                    clients.remove(i);
                  }
            }
            printMsg("client left the chat");

        }

	}

	public static void main(String args[]){
		new ChatServer();
	}
}
