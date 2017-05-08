package de.ye_rover.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import de.ye.rover_con.THREAD_CON;

public class IpChecker implements Runnable{

    public static String getIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(500);
				//System.out.println(getIp());
				THREAD_CON.writer.println(getIp());
				THREAD_CON.writer.flush();
			} catch (InterruptedException e) {
			} catch (Exception e) {
			}
		}
	}
}