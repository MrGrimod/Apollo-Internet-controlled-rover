package de.ye.car_eng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class THREAD_ENG implements Runnable {
	
	public static Socket client;
	public static String s;

	public static boolean has_connected;
	
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
			s = null;
			Thread ENG_CALC = new Thread(new ENG_CALC());
			ENG_CALC.start();
			while((s = reader.readLine()) != null){
				has_connected=true;
				System.out.println("GET_DATA");
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
