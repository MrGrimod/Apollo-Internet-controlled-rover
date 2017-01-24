package de.ye.boat_client_connections;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import de.ye.boat_client.Main;

public class CONNECT_LED {
	 
	static Socket client;
	
	public boolean CONNECT_LED(String ip){
		try {
			client = new Socket();
			client.connect(new InetSocketAddress(ip,8686),100);
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		System.out.println("Client gestartet!_LED");
		return true;
	}
	public static Socket GET_CLIENT(){
		return client;
	}
	
}
