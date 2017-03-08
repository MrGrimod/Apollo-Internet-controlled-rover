package de.ye.car_eng;

import de.ye.car_dir.THREAD_DIR;

public class ENG_CALC implements Runnable{
		
	public static int X_AX;
	public static int Y_AX;
	
	public static int LEFT_MOTOR=0;
	public static int RIGHT_MOTOR=0;
	
	public static String mode="0";
	
	
	@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(!(check_alive.no_internet==false || THREAD_ENG.has_connected==false||THREAD_DIR.s==null||THREAD_ENG.s==null)){
						X_AX=Integer.parseInt(THREAD_DIR.s);
						Y_AX=Integer.parseInt(THREAD_ENG.s);
						
						if(Y_AX<60&&Y_AX>40){
							ENGINE.FRONT_LEFT();
							ENGINE.FRONT_RIGHT();
							if(X_AX<60&&X_AX>40){
								LEFT_MOTOR=0;
								RIGHT_MOTOR=0;
							} else if(X_AX<40){
								//LEFT
								if(mode.equalsIgnoreCase("0")){
									RIGHT_MOTOR=180;
								} else if(mode.equalsIgnoreCase("1")){
									RIGHT_MOTOR=210;
								}
							} else if(X_AX>60){
								//Right
								if(mode.equalsIgnoreCase("0")){
									LEFT_MOTOR=180;
								} else if(mode.equalsIgnoreCase("1")){
									LEFT_MOTOR=210;
								}
							}
						} else if(Y_AX>60){
							if(mode.equalsIgnoreCase("0")){
								LEFT_MOTOR=120;
								RIGHT_MOTOR=120;
							} else if(mode.equalsIgnoreCase("1")){
								LEFT_MOTOR=150;
								RIGHT_MOTOR=150;
							}
							ENGINE.BACK_LEFT();
							ENGINE.BACK_RIGHT();
						} else if(Y_AX<40){
							if(mode.equalsIgnoreCase("0")){
								LEFT_MOTOR=120;
								RIGHT_MOTOR=120;
							} else if(mode.equalsIgnoreCase("1")){
								LEFT_MOTOR=150;
								RIGHT_MOTOR=150;
							}
							ENGINE.FRONT_LEFT();
							ENGINE.FRONT_RIGHT();
						}
						ENGINE.setSpeed_LEFT(LEFT_MOTOR);
						ENGINE.setSpeed_RIGHT(RIGHT_MOTOR);
					} else {
						ENGINE.setSpeed_LEFT(0);
						ENGINE.setSpeed_RIGHT(0);
					}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
