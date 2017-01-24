package de.ye_boat.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.ye.car_eng.ENGINE;
import de.ye.car_led.LED;
import de.ye.car_main.Main;

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
