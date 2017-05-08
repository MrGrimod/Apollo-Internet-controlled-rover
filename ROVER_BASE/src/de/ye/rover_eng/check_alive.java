package de.ye.rover_eng;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import de.ye.rover_main.Main;

public class check_alive implements Runnable{

	public static boolean no_internet=true;
	
	@Override
	public void run() {
		
		
		int no_inet=0;
		
		while(true){
			try {
				Thread.sleep(450);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(has_Internet(Main.SAT_IP)){
				no_inet=0;
				no_internet=true;
				System.out.println("Internet!");
			} else {
				no_inet++;
				if(no_inet>1){
					no_internet=false;
					System.out.println("No internet!");
				}
			}
		}
		
	}
	
	
	public static boolean has_Internet(String site) {
	    Socket sock = new Socket();
	    InetSocketAddress addr = new InetSocketAddress(site,80);
	    try {
	        sock.connect(addr,300);
	        return true;
	    } catch (IOException e) {
	        return false;
	    } finally {
	        try {sock.close();}
	        catch (IOException e) {}
	    }
	}
	
}
