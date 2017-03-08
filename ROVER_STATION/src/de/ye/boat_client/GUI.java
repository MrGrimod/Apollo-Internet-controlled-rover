package de.ye.boat_client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.ye.boat_client_connections.CONNECT_DIR;
import de.ye.boat_client_connections.CONNECT_ENG;
import de.ye.boat_client_connections.CONNECT_GPS;
import de.ye.boat_client_connections.CONNECT_IP;
import de.ye.boat_client_connections.CONNECT_LED;


public class GUI {
	
    static JFrame clientFrame;
    static JPanel clientPanel;
    
    static JButton button_CONNECT;
    static JLabel JOY_X_AX;
    static JLabel JOY_Y_AX;
    static JTextField IP_TF;
    static JButton LIGHT;
    static JButton pl;
    static JButton cm;
    
	static PrintWriter writer_led;
	static BufferedReader reader_led;
	
	static PrintWriter writer_dir;
	static BufferedReader reader_dir;
	
	static PrintWriter writer_eng;
	static BufferedReader reader_eng;
	
	static PrintWriter writer_ip;
	static BufferedReader reader_ip;
	
	static PrintWriter writer_gps;
	static BufferedReader reader_gps;
	
	
	static boolean connected=false;
	

	static CONNECT_LED con_led = new CONNECT_LED();
	static CONNECT_DIR con_dir = new CONNECT_DIR();
	static CONNECT_ENG con_eng = new CONNECT_ENG();
	static CONNECT_IP con_ip = new CONNECT_IP();
	static CONNECT_GPS con_gps = new CONNECT_GPS();
	
	
	public void CONNECT(){
		 if(con_led.CONNECT_LED(Main.SATELITE) == true && con_dir.CONNECT_DIR(Main.SATELITE) == true && con_eng.CONNECT_ENG(Main.SATELITE) == true && con_gps.CONNECT_GPS(Main.SATELITE) == true && con_ip.CONNECT_IP(Main.SATELITE) == true){
				try {
				System.out.println("Connected");
				connected=true;
				//Clients
				Socket client_led = con_led.GET_CLIENT();
				Socket client_dir = con_dir.GET_CLIENT();
				Socket client_eng = con_eng.GET_CLIENT();
				Socket client_ip = con_ip.GET_CLIENT();
				Socket client_gps = con_gps.GET_CLIENT();
				
				//Streams LED
				OutputStream out_LED = client_led.getOutputStream();
				writer_led = new PrintWriter(out_LED);
				
				InputStream in_LED = client_led.getInputStream();
				reader_led = new BufferedReader(new InputStreamReader(in_LED));
				//-------------
				
				//Streams DIR
				OutputStream out_DIR = client_dir.getOutputStream();
				writer_dir = new PrintWriter(out_DIR);
				
				InputStream in_DIR = client_dir.getInputStream();
				reader_dir = new BufferedReader(new InputStreamReader(in_DIR));
				//-------------
				
				//Streams ENG
				OutputStream out_ENG = client_eng.getOutputStream();
				writer_eng = new PrintWriter(out_ENG);
				
				InputStream in_ENG = client_eng.getInputStream();
				reader_eng = new BufferedReader(new InputStreamReader(in_ENG));
				//-------------
				
				//Streams ENG
				OutputStream out_IP = client_ip.getOutputStream();
				writer_ip = new PrintWriter(out_IP);
				
				InputStream in_IP = client_ip.getInputStream();
				reader_ip = new BufferedReader(new InputStreamReader(in_IP));
				//-------------

				//Streams GPS
				OutputStream out_GPS = client_gps.getOutputStream();
				writer_gps = new PrintWriter(out_GPS);

				InputStream in_GPS = client_gps.getInputStream();
				reader_gps = new BufferedReader(new InputStreamReader(in_GPS));
				//-------------
				Thread gps = new Thread(new GPS());
				gps.start();
				} catch (IOException e) {
				}
			} else {
				System.out.println("NOT_CONNECTED");
				connected=false;
			}
	}
	
	
	 public void createGUI() {
		 
		 System.out.println("Start creating CLIENT_FRAME");
		
         clientFrame = new JFrame("ROVER_BASE");
         clientFrame.setSize(new Dimension(400,300));
         clientFrame.setVisible(true);
         clientPanel = new JPanel();
         
         button_CONNECT = new JButton("CONNECT");
         button_CONNECT.addActionListener(new ActionListener_CONNECT());
         
         cm = new JButton("POWER_0");
         cm.addActionListener(new ActionListener_CLEAN_MOTOR());

		
         LIGHT = new JButton("LIGHT_ON");
         LIGHT.addActionListener(new ActionListener_LIGHT());
         
         IP_TF=new JTextField(Main.SATELITE,15);
         
         JOY_X_AX= new JLabel("NO_JOYSTICK_X");
         JOY_Y_AX= new JLabel("NO_JOYSTICK_Y");
         
		 System.out.println("Created PANEL_CLIENT_FRAME");
		 
            
             clientPanel.add(button_CONNECT,BorderLayout.NORTH);
             clientPanel.add(IP_TF,BorderLayout.CENTER);
             clientPanel.add(JOY_X_AX);
             clientPanel.add(JOY_Y_AX);
             clientPanel.add(LIGHT);
             clientPanel.add(cm);


             clientFrame.getContentPane().add(BorderLayout.CENTER, clientPanel);
            
             clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             clientFrame.setVisible(true);
		 System.out.println("Created CLIENT_FRAME FINISHED");
		 Thread UPDATE_STICK = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while((true)){
					JOY_X_AX.setText(Integer.toString(JoyStick.X_AX));
					JOY_Y_AX.setText(Integer.toString(JoyStick.Y_AX));
					//System.out.println(JoyStick.Y_AX);
					//System.out.println(JoyStick.X_AX);
				}
			}
		});
		 UPDATE_STICK.start();
	 }
  
	 public static boolean isCONNECTED(){
		 return connected;
	 }
	 
	 

	 
	 
	 
	 
	 
	 	 public static void all_LED_OFF(){

				writer_led.println("ledoff_WARN");
				writer_led.flush();
				writer_led.println("ledoff_ERR");
				writer_led.flush();
				writer_led.println("ledoff_FINE");
				writer_led.flush();
	 	 }
	 
			public class ActionListener_CONNECT implements ActionListener {

				@Override
				public void actionPerformed(ActionEvent e) {
					Main.SATELITE=IP_TF.getText();
					CONNECT();
				}
				
			}
	 
			public class ActionListener_LIGHT implements ActionListener {

				boolean on=false;
				@Override
				public void actionPerformed(ActionEvent e) {
					if(on==false){
						on=true;
						System.out.println("AN");
						GUI.writer_led.println("ledon_WARN");
						GUI.writer_led.flush();
					} else {
						on=false;
						GUI.writer_led.println("ledoff_WARN");
						GUI.writer_led.flush();
						System.out.println("AUS");
					}
				}
				
			}
	 
			public class ActionListener_CLEAN_MOTOR implements ActionListener {

				boolean on=false;
				@Override
				public void actionPerformed(ActionEvent e) {
					if(on==false){
						on=true;
						cm.setText("POWER_1");
						System.out.println("POWER_1");
						GUI.writer_led.println("power_1");
						GUI.writer_led.flush();
					} else {
						on=false;
						cm.setText("POWER_0");
						GUI.writer_led.println("power_0");
						GUI.writer_led.flush();
						System.out.println("POWER_0");
					}
				}
				
			}
			
			public class ActionListener_pl implements ActionListener {

				boolean on=false;
				@Override
				public void actionPerformed(ActionEvent e) {
					GUI.writer_led.println("pic_load");
					GUI.writer_led.flush();
				}
				
			}
			
		public static class GPS implements Runnable{

			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
}
