package de.ye.car_main;

import de.ye.car_eng.check_alive;
import de.ye.car_led.LED;
import de.ye_boat.net.IpChecker;
import de.ye_boat.net.PING_CHECK;
import de.ye_boat.net.TRY_CONNECT;

public class Main {

	 	
	public static String SAT_IP= "134.255.234.216";
	public static boolean STATUS=false;
	     public static void main(String args[]){
	    	 LED.ALL_LED_OFF();
	 		 Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			      public void run() {
			        LED.ALL_LED_OFF();
			      }
			}));
	 		 
	    	 Thread TRY_CON = new Thread(new TRY_CONNECT());
	    	 TRY_CON.start();
	 		 
	    	 Thread PING_CHECK = new Thread(new PING_CHECK());
	    	 PING_CHECK.start();
	 		 
	    	 Thread IPd = new Thread(new IpChecker());
	    	 IPd.start();
	    	 
	    	 Thread check_con = new Thread(new check_alive());
	    	 check_con.start();
	     }
}