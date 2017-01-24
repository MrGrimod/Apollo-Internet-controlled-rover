package de.ye.rover_serv_alive;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class THREAD_RASP_ALIVE implements Runnable {
	
	public static Socket client;
	
	public THREAD_RASP_ALIVE(Socket client) {
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
			//System.out.println("STATUS CLIENT STREAM THREAD STARTED._RASPI_ALIVE");
			String s = null;
			while((s = reader.readLine()) != null){
					SERVER_RASP_ALIVE.sendToAllClients(s);
					System.out.println(s);
			}
			//System.out.println("STATUS CLIENT LISTENING READY._RASPI_ALIVE");
			writer.close();
			reader.close();
			client.close();
			//System.out.println("STATUS CLIENT THREAD ENDED._RASPI_ALIVE");
		} catch (Exception e) {
		}
	}

}
