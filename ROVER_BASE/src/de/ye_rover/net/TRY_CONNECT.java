package de.ye_rover.net;

import de.ye.rover_con_men.CONNECT;
import de.ye.rover_main.Main;

public class TRY_CONNECT implements Runnable{

	@Override
	public void run() {
		 System.out.println("TRY_CONNECT STARTED");
		while(true){
		 try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		 if(Main.STATUS==true){
			 break;
		 } else {
	    	 Thread con = new Thread(new CONNECT());
	    	 con.start();
		 }
	 }
	}

}
