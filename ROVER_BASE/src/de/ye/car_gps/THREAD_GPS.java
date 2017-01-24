package de.ye.car_gps;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import de.ye.car_led.LED;
import de.ye.car_main.Main;

public class THREAD_GPS implements Runnable {
	
	public static Socket client;
	public static PrintWriter writer;
	public static BufferedReader reader;
	public THREAD_GPS(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			//Streams
			OutputStream out = client.getOutputStream();
			writer = new PrintWriter(out);
			
			InputStream in = client.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			//-------------
			System.out.println("STATUS CLIENT STREAM THREAD STARTED._LED");
			String s = null;
			
			Thread gsgps= new Thread(new GET_SEND_GPS());
			gsgps.start();
			
			while((s = reader.readLine()) != null){
				System.out.println(s);
			}
			System.out.println("STATUS CLIENT LISTENING READY._LED");
			writer.close();
			reader.close();
			client.close();
			System.out.println("STATUS CLIENT THREAD ENDED._LED");
			Main.STATUS=false;
		} catch (Exception e) {
			LED.LED_off_FINE();
		}
	}

}
