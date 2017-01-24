package de.ye.rover_main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.ye.rover_auth.SERVER_ATH;
import de.ye.rover_dir.SERVER_DIR;
import de.ye.rover_eng.SERVER_ENG;
import de.ye.rover_gps.SERVER_GPS;
import de.ye.rover_led.SERVER_LED;
import de.ye.rover_serv_alive.SERVER_RASP_ALIVE;

public class Main {

	//DIR=8787
	//ENG=8888
	//LED=8686
	
	public static ExecutorService executor;
	 
	     public static void main(String args[]){
	 		 executor = Executors.newFixedThreadPool(30);

	 		 executor.execute(new SERVER_LED(8686));
	 		 
	 		 executor.execute(new SERVER_DIR(8787));
	 		 
	 		 executor.execute(new SERVER_ENG(8888));
	 		 
	 		 executor.execute(new SERVER_RASP_ALIVE(8989));
	 		 
	 		 executor.execute(new SERVER_GPS(9090));
	 		 
	 		 executor.execute(new SERVER_ATH(9191));
	 		 
	 		 
	 		 Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			      public void run() {
			    	  
			      }
			}));
	     }
}