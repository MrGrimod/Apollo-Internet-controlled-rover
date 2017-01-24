package de.ye.car_con_men;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import de.ye.car_con.THREAD_CON;
import de.ye.car_dir.THREAD_DIR;
import de.ye.car_eng.THREAD_ENG;
import de.ye.car_gps.THREAD_GPS;
import de.ye.car_led.THREAD_LED;
import de.ye.car_main.Main;

public class CONNECT implements Runnable {


    public static PrintWriter writer_led;
    public static BufferedReader reader_led;

    public static PrintWriter writer_dir;
    public static BufferedReader reader_dir;

    public static PrintWriter writer_eng;
    public static BufferedReader reader_eng;

    public static PrintWriter writer_sens_front;
    public static BufferedReader reader_sens_front;

    public static PrintWriter writer_modi;
    public static BufferedReader reader_modi;

    public static PrintWriter writer_con;
    public static BufferedReader reader_con;

    public static PrintWriter writer_track;
    public static BufferedReader reader_track;

    public static PrintWriter writer_gps;
    public static BufferedReader reader_gps;


    public static PrintWriter writer_ip;
    public static BufferedReader reader_ip;
    //Clients
    public static Socket client_led;
    public static  Socket client_eng;
    public static Socket client_dir;
    public static Socket client_ip;
    public static Socket client_gps;


    //TextView t = (TextView)  findViewById(R.id.TV_NC);

    @Override
    public void run() {
        try {
            System.out.println("Connecting");


            client_dir = new Socket();
            client_led = new Socket();
            client_eng = new Socket();
            client_ip = new Socket();
            client_gps = new Socket();
            
                client_led.connect(new InetSocketAddress(Main.SAT_IP, 8686), 100);
                client_dir.connect(new InetSocketAddress(Main.SAT_IP, 8787), 100);
                client_eng.connect(new InetSocketAddress(Main.SAT_IP, 8888), 100);
                client_ip.connect(new InetSocketAddress(Main.SAT_IP, 8989), 100);
                client_gps.connect(new InetSocketAddress(Main.SAT_IP, 9090), 100);



                if (client_led.isConnected()) {
                    System.out.println("Connected");

                    Thread LED = new Thread(new THREAD_LED(client_led));
                    LED.start();
                    
                    Thread DIR = new Thread(new THREAD_DIR(client_dir));
                    DIR.start();

                    Thread ENG = new Thread(new THREAD_ENG(client_eng));
                    ENG.start();

                    Thread IP = new Thread(new THREAD_CON(client_ip));
                    IP.start();

                    Thread GPS = new Thread(new THREAD_GPS(client_gps));
                    GPS.start();
                    // Thread CON_CECK = new Thread(new CON_CHECK());
    	   	    	// CON_CECK.start();
                    Main.STATUS = true;
                }

                //Thread IP_SAFE = new Thread(new THREAD_CON(client_ip));
                //IP_SAFE.start();
                
        }catch(IOException e){
                System.out.println("Konnte nicht verbinden");
                Main.STATUS = false;
            }
        }
    }