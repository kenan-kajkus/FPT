package udp;

import controller.Controller;
import javafx.util.Duration;

import java.io.IOException;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PlaytimeServer implements Runnable {
    private Controller controller;
    public PlaytimeServer(Controller controller){
        this.controller = controller;
    }

    @Override
    public void run() {
        //Socket zum erreichen
        try (DatagramSocket socket = new DatagramSocket(5000)){
            while(true){
                DatagramPacket packet = new DatagramPacket(new byte[50], 50);
                try {
                    socket.receive(packet);
                    System.out.println("received");
                    new UDPThread(socket,packet,controller).start();
                }catch (SocketTimeoutException e){
                    System.err.println("timeout");
                    continue;
                }
            }

    }catch (SocketException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}

class UDPThread extends Thread{
    private DatagramPacket packet;
    private DatagramSocket socket;
    private Controller controller;
    public UDPThread(DatagramSocket socket, DatagramPacket packet,Controller controller)throws SocketException{
        this.packet = packet;
        this.socket = socket;
        this.controller = controller;
    }
    public void run(){
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        int len = packet.getLength();
        byte[] data = packet.getData();
        String dataString = new String(data);
        System.out.println(port + " " + dataString);
        try(Scanner sc = new Scanner(dataString).useDelimiter(":")){
            String type = sc.findInLine("[a-zA-Z]+");
            sc.next();
            String cmd = sc.findInLine("[a-zA-Z]+");
            System.out.println(cmd);
            if(type.equals("cmd")|cmd.equals("time")){
                double d = controller.getTime().toMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                String time = dateFormat.format(new Date((long)d));
                byte[] timeArray = time.getBytes();
                packet = new DatagramPacket(timeArray,timeArray.length,address,port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
