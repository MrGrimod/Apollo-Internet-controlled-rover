package de.ye.boat_client;

public class Main {
	//DIR=8787
	//ENG=8888
	//LED=8686
	public static String SATELITE = "134.255.234.216";
	
	public static void main(String[] args) {
		//
					GUI g = new GUI();
					g.createGUI();
					Thread t = new Thread(new JoyStick());
					t.start();
			 Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			      public void run() {
						//SSH.SSH("sudo screen -X -S BOAT quit");
			      }
			}));
	}
	
}
