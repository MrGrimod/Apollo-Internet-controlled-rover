package de.ye.rover_client_connections;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import de.ye.rover_client.Main;

public class CONNECT_ENG {
	 
	static Socket client;
	
	public boolean CONNECT_ENG(String ip){
		try {
			client = new Socket();
			client.connect(new InetSocketAddress(ip,8888),100);
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		System.out.println("Client gestartet!_ENG");
		return true;
	}
	public static Socket GET_CLIENT(){
		return client;
	}
	
}
