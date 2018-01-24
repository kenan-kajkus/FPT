package udp;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

import java.io.IOException;
import java.net.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

public class PlaytimeServer implements Runnable {
    @Override
    public void run() {
    try (DatagramSocket socket = new DatagramSocket(5000);){
        while(true){
            DatagramPacket packet = new DatagramPacket(new byte[50], 50);
            System.out.println("Serverstart");
            socket.setSoTimeout(3000);
            try {
                socket.receive(packet);
            }catch (SocketTimeoutException e){
                System.err.println("timeout");
                continue;
            }
            System.out.println("empfangen");
            // Daten auslesen
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            int len = packet.getLength();
            byte[] data = packet.getData();
            String dataString = new String(data);
            System.out.println(port + " " + dataString);
            try(Scanner sc = new Scanner(dataString).useDelimiter(":")){
                String type = sc.findInLine("[a-zA-Z]+");
                sc.next();
                String cmd = sc.findInLine("[a-zA-Z]");
                if(type.equals("cmd")|cmd.equals("time")){
                    SimpleDateFormat time = new SimpleDateFormat("mm:ss");

                }
            }
        }

    }catch (SocketException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
