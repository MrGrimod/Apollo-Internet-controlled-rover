package de.ye.rover_eng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class THREAD_ENG implements Runnable {
	
	public static Socket client;
	
	public THREAD_ENG(Socket client) {
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
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._ENG");
			String s = null;
			
			while((s = reader.readLine()) != null){
					SERVER_ENG.sendToAllClients(s);
					System.out.println("ENG"+s);
			}
			System.out.println("STATUS CLIENT LISTENING READY._ENG");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._ENG");
		} catch (Exception e) {
		}
	}

}
