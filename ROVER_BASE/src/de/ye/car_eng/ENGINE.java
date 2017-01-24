package de.ye.car_eng;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

public class ENGINE {
	
	public static int SPEED;
	public static boolean DR=true;
	
	public static GpioController gpio = GpioFactory.getInstance();
	static GpioPinDigitalOutput pin_ENG_1_RIGHT = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "ENG_DIR_1_RIGHT", PinState.HIGH);    
	static GpioPinDigitalOutput pin_ENG_2_RIGHT = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "ENG_DIR_2_RIGHT", PinState.LOW);   
	
	static GpioPinDigitalOutput pin_ENG_1_LEFT = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "ENG_DIR_1_LEFT", PinState.HIGH);    
	static GpioPinDigitalOutput pin_ENG_2_LEFT = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "ENG_DIR_2_LEFT", PinState.LOW);    
	
	
	
	public static void FRONT_RIGHT() throws InterruptedException{
		//System.out.println("FRONT");
            pin_ENG_1_RIGHT.high();
            pin_ENG_2_RIGHT.low();
	}
	
	public static void BACK_RIGHT() throws InterruptedException{
		//System.out.println("BACK");
        	pin_ENG_1_RIGHT.low();
            pin_ENG_2_RIGHT.high();
	}
	public static void setSpeed_RIGHT(int speed) throws InterruptedException{
		//System.out.println("Speed:"+speed);
		if(DR==true){
		SoftPwm.softPwmCreate(24, 0, 200);
		SoftPwm.softPwmWrite(24, speed);
		} else {
			SoftPwm.softPwmCreate(24, 0, 200);
			SoftPwm.softPwmWrite(24, 0);
		}
	}
	
	public static void FRONT_LEFT() throws InterruptedException{
		//System.out.println("FRONT");
			pin_ENG_1_LEFT.high();
            pin_ENG_2_LEFT.low();
	}
	
	public static void BACK_LEFT() throws InterruptedException{
		//System.out.println("BACK");
			pin_ENG_1_LEFT.low();
			pin_ENG_2_LEFT.high();
	}
	public static void setSpeed_LEFT(int speed) throws InterruptedException{
		//System.out.println("Speed:"+speed);
		if(DR==true){
		SoftPwm.softPwmCreate(23, 0, 200);
		SoftPwm.softPwmWrite(23, speed);
		} else {
			SoftPwm.softPwmCreate(23, 0, 200);
			SoftPwm.softPwmWrite(23, 0);
		}
	}

	public static void STOP(){
		DR=false;
	}

	public static void START(){
		DR=true;
	}
}

