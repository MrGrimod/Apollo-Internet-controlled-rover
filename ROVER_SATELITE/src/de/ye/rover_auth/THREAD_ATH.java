package de.ye.rover_auth;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class THREAD_ATH implements Runnable {
	
	public static Socket client;
	
	public THREAD_ATH(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			//Streams
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			//-------------
			String s = null;
			
			while((s = reader.readLine()) != null){
					SERVER_ATH.sendToAllClients(s);
			}
			writer.close();
			reader.close();
			client.close();
		} catch (Exception e) {
		}
	}

}
