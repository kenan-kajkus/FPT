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
                try{
                    Socket client = server.accept();
                    TCPThread tcpThread = new TCPThread(client);
                    tcpThread.start();
                }catch (IOException e2){
                    e2.printStackTrace();
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
class TCPThread extends Thread{
    Socket socket;
    public TCPThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream();){
            String name = in.readLine();
            String password = in.readLine();
            System.out.println(name+" "+password);
            if(password.equals("password")){

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
