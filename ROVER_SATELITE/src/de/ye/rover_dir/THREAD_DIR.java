package de.ye.rover_dir;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class THREAD_DIR implements Runnable {
	
	public static Socket client;
	
	public THREAD_DIR(Socket client) {
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
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._DIR");
			String s = null;
			
			while((s = reader.readLine()) != null){
					SERVER_DIR.sendToAllClients(s);
					System.out.println("DIR"+s);
			}
			System.out.println("STATUS CLIENT LISTENING READY._DIR");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._DIR");
		} catch (Exception e) {
		}
	}

}
