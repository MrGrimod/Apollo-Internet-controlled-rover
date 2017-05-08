package de.ye.rover_client;

import java.awt.event.KeyEvent;

import de.ye.joystick.JInputJoystick;
import net.java.games.input.Controller;

public class JoyStick implements Runnable{
	// Left controller joystick
	public static int X_AX;
	public static int Y_AX;
	@Override
	public void run() {
		JInputJoystick joystick = new JInputJoystick(Controller.Type.STICK);
		if( !joystick.isControllerConnected() ){
			   System.out.println("No controller found!");
			   // Do some stuff.
			}
		int throttle_inc;
		int throttle;
		int left_right;
		
		int final_throttle;
		int final_left_right;
		while(true){
			try {Thread.sleep(50);} catch (InterruptedException e) {}
			if(joystick.getControllerType() == Controller.Type.STICK){
				if(joystick.pollController()) {
					throttle_inc = joystick.getZAxisPercentage();
					throttle = joystick.getYAxisPercentage();
					
					left_right= joystick.getXAxisPercentage();
					
						if(throttle_inc>0&&throttle_inc<50){
							////////////MOTOR
							final_throttle=throttle;
							if(GUI.connected==true){
								GUI.writer_eng.println(Integer.toString(final_throttle));
								GUI.writer_eng.flush();
								//System.out.println("SEND");
							}
							//System.out.println(Integer.toString(final_throttle));
							Y_AX=final_throttle;
							////////////MOTOR
							////////////DIR
							final_left_right=left_right;
							if(GUI.connected==true){
							GUI.writer_dir.println(Integer.toString(final_left_right));
							GUI.writer_dir.flush();
							//System.out.println("SEND");
							}
							//System.out.println(Integer.toString(final_left_right));
							X_AX=final_left_right;
							////////////DIR
						} else if(throttle_inc>55&&throttle_inc<=100){
							////////////MOTOR
							final_throttle=throttle*5;
							if(GUI.connected==true){
							GUI.writer_eng.println(Integer.toString(final_throttle));
							GUI.writer_eng.flush();
							}
							//System.out.println(Integer.toString(final_throttle));
							Y_AX=final_throttle;
							////////////MOTOR
							////////////DIR
							final_left_right=left_right*5;
							if(GUI.connected==true){
							GUI.writer_dir.println(Integer.toString(final_left_right));
							GUI.writer_dir.flush();
							}
							//System.out.println(Integer.toString(final_left_right));
							X_AX=final_left_right;
							////////////DIR
						}
					}
			}
		}
	}

}
