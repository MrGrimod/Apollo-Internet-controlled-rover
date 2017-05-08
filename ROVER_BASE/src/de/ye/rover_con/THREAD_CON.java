package de.ye.rover_con;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class THREAD_CON implements Runnable {
	
	public static Socket client;
	public static boolean ALIVE=false;
	public static boolean t=true;
	public static int IP;
	public static boolean end = false;
	public static String TRACK_IP;
	public static PrintWriter writer;
	public static OutputStream out;
	
	public THREAD_CON(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			//Streams
			 out = client.getOutputStream();
			 writer = new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			//-------------
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._CON");
			String s = null;
			
			while(t=true){
				
			}
			
			System.out.println("STATUS CLIENT LISTENING READY._CON");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._CON");
		} catch (Exception e) {
			
		}
	}


}
