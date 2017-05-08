package de.ye_rover.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.ye.rover_eng.ENGINE;
import de.ye.rover_led.LED;
import de.ye.rover_main.Main;

public class PING_CHECK implements Runnable{

	@Override
	public void run() {
		while(true){
			try {Thread.sleep(100);} catch (InterruptedException e) {}try {
				if(InetAddress.getByName("google.com").isReachable(80)){
					LED.LED_on_ERR();
				} else {
					LED.LED_off_ERR();
				}
				if(Main.STATUS==false){
					LED.LED_off_FINE();
				} else {
					LED.LED_on_FINE();
				}
			} catch (UnknownHostException e) {} catch (IOException e) {}
			
		}
	}

}
