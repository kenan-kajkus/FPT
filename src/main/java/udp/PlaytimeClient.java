package udp;

import javafx.application.Platform;
import view.ClientView;

import java.io.IOException;
import java.net.*;

public class PlaytimeClient implements Runnable{
    private ClientView view;
    public PlaytimeClient(ClientView view){
        this.view = view;
    }
    @Override
    public void run() {
        // Eigene Adresse erstellen
        InetAddress ia = null;
		try {
            ia = InetAddress.getByName("localhost");
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
        // Socket für den Klienten anlegen
		try (DatagramSocket dSocket = new DatagramSocket(4000)) {
		    dSocket.setSoTimeout(2000);
            try {
                while (true) {
                    String command = "{\"cmd\":\"time\"}";

                    byte buffer[] = command.getBytes();

                    //Paket mit der Anfrage vorbereiten
                    DatagramPacket packet = new DatagramPacket(buffer,
                            buffer.length, ia, 5000);
                    // Paket versenden
                    dSocket.send(packet);

                    byte answer[] = new byte[10];
                    DatagramPacket paket = new DatagramPacket(answer,answer.length);

                    try {
                        dSocket.receive(paket); //erwartet Antwort vom TimeServer
                    } catch (SocketTimeoutException e){
                        continue;
                    }
                    System.out.println(new String(paket.getData(), 0, paket.getLength()));
                    String date = new String(paket.getData(), 0, paket.getLength());
                    Platform.runLater(()->view.setTime(date));
                    /*
                    byte answer[] = new byte[1024];
                    // Paket fÃ¼r die Antwort vorbereiten
                    packet = new DatagramPacket(answer, answer.length);
                    // Auf die Antwort warten
                    dSocket.receive(packet);

                    System.out.println(new String(packet.getData(),0,packet.getLength()));
                    */
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
		} catch (SocketException e1) {
        e1.printStackTrace();
    }

    }
}
