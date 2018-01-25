package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable{
    @Override
    public void run() {
        try(ServerSocket server = new ServerSocket(5020)){
            while (true){
                try(Socket client = server.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    OutputStream out = client.getOutputStream()){
                    String a = in.readLine();
                    System.out.println(a);
                }catch (IOException e2){
                    e2.printStackTrace();
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
