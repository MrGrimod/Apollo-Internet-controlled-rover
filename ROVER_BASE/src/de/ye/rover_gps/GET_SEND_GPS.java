package de.ye.rover_gps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GET_SEND_GPS implements Runnable{

	@Override
	public void run() {
		try {
			while(true){
				Thread.sleep(1000);
					//st_gps.sh
					ProcessBuilder pb = new ProcessBuilder("/home/pi/st_gps.sh");
					pb.redirectErrorStream(true);
					Process p = pb.start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
					BufferedReader reader_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					String s = null;
					String s_err = null;
					while((s = reader.readLine()) != null){
						System.out.println(s);
						THREAD_GPS.writer.println(s);
						THREAD_GPS.writer.flush();
						if(s==""){
							THREAD_GPS.writer.println("Traceback");
							THREAD_GPS.writer.flush();
						}
						
					}
					while((s_err = reader_err.readLine()) != null){
						//System.out.println(s);
					}
					reader.close();
					reader_err.close();
		
					}
		} catch (IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
