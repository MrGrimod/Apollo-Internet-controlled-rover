package de.ye.rover_client_connections;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import de.ye.rover_client.Main;

public class CONNECT_GPS {
	 
	static Socket client;
	

	//Socket con;
	//con = new Socket();
	//con.connect(new InetSocketAddress(Main.SATELITE,8787),100);
	
	public boolean CONNECT_GPS(String ip){
		try {
			client = new Socket();
			client.connect(new InetSocketAddress(ip,9090),100);
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		System.out.println("Client gestartet!_DIR");
		return true;
	}

	public static Socket GET_CLIENT(){
		return client;
	}
	
}
