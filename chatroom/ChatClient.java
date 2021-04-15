package chatroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
//import java.net.ServerSocket;

/**
 * RABAAB DHINGRA
 */
public class ChatClient extends ChatWindow {

	// Inner class used for networking
	private Communicator comm;
    //change the name
    private String newName;
	// GUI Objects
	private JTextField serverTxt;
	private JTextField nameTxt;
	private JButton connectB;
	private JTextField messageTxt;
	private JButton sendB;
	//ServerSocket srv = null;

	boolean connected = false; //true if the connection is already established, saves from Hello    Server being called again and again.

    public ChatClient(){
		super();
		this.setTitle("Chat Client");
		printMsg("Chat Client Started.");

		// GUI elements at top of window
		// Need a Panel to store several buttons/text fields
		serverTxt = new JTextField("localhost");
		serverTxt.setColumns(15);
		nameTxt = new JTextField("Name");
		nameTxt.setColumns(10);
		connectB = new JButton("Connect");
		JPanel topPanel = new JPanel();
		topPanel.add(serverTxt);
		topPanel.add(nameTxt);
		topPanel.add(connectB);
		contentPane.add(topPanel, BorderLayout.NORTH);

		// GUI elements and panel at bottom of window
		messageTxt = new JTextField("");
		messageTxt.setColumns(40);
		sendB = new JButton("Send");
		JPanel botPanel = new JPanel();
		botPanel.add(messageTxt);
		botPanel.add(sendB);
		contentPane.add(botPanel, BorderLayout.SOUTH);

		// Resize window to fit all GUI components
		this.pack();

		// Setup the communicator so it will handle the connect button
		Communicator comm = new Communicator();
		connectB.addActionListener(comm);
		sendB.addActionListener(comm);

	}

	/** This inner class handles communication with the server. */
	class Communicator implements ActionListener, Runnable{
		private Socket socket;
		private PrintWriter writer;
		private BufferedReader reader;
		private int port = 2113;

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if(actionEvent.getActionCommand().compareTo("Connect") == 0) {
		        //Only go to connect if boolean connected is false, that is not when the name is changed
                String prev = nameTxt.getText();
                if(connected == false){
                     connect();
                     connected = true;
                     sendMsg(nameTxt.getText() + " has joined");
                }
                else if(!nameTxt.getText().equals(prev)){
                    writer.println("***Client name changed to " + nameTxt.getText() + "***");
                }
			}
            
			else if(actionEvent.getActionCommand().compareTo("Send") == 0) {
				sendMsg(messageTxt.getText());
                ChatClient.this.messageTxt.setText("");//clears the message field
			}
		}

		/** Connect to the remote server and setup input/output streams. */
		public void connect(){
			try {
				socket = new Socket(serverTxt.getText(), port);
				InetAddress serverIP = socket.getInetAddress();
				printMsg("Connection made to " + serverIP + " by " + nameTxt.getText());
				writer = new PrintWriter(socket.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //so that it doesn't print Hello Server over and over again
                
                if(connected == false){                    
                    sendMsg("Hello Server");
                }
                
                Thread ClientThread = new Thread(this);
                ClientThread.start();        
			}
			catch(IOException e) {
				printMsg("\nERROR:" + e.getLocalizedMessage() + "\n");
			}
		}
 
		/** Receive and display a message */
		public void readMsg() throws IOException {
			String s = reader.readLine();
			printMsg(s);
		}
		/** Send a string */
		public void sendMsg(String s){
            //check if the string contains "/name"
            if(s.contains("/name ")){
                newName = s.substring(s.indexOf(' ') + 1);
                nameTxt.setText(newName);
                printMsg("***Client name changed to " + newName + "***");
            }
            //read which client is sending the message
            else{
                writer.println(nameTxt.getText() + ": " + s);
            }
		}
        
        public void run(){
            //for the client to word it needs to be able to connect and send
            try{
                while(true){
                    readMsg();
                }
            }
            catch(IOException e) {
                    printMsg("\nERROR:" + e.getLocalizedMessage() + "\n");
            }
        }
	}


	public static void main(String args[]){
		new ChatClient();
	}

}
