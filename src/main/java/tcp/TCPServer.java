package tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer implements Runnable{
    ArrayList<String> names = new ArrayList<String>();
    @Override
    public void run() {
        try(ServerSocket server = new ServerSocket(5020)){
            while (true){
                try{
                    Socket client = server.accept();
                    TCPThread tcpThread = new TCPThread(client,names);
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
    ArrayList<String> names;
    public TCPThread(Socket socket,ArrayList names){
        this.socket = socket;
        this.names = names;
    }
    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());){
            System.out.println("thread created");
            String name = in.readLine();
            String password = in.readLine();
            System.out.println(name+" "+password);
            if(password.equals("password")){
                adder(name);
                outToClient.writeBytes(InetAddress.getLocalHost().getHostAddress()+"/"+name);
                outToClient.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private synchronized void adder(String name) {
        for(String n: names){
            if(n.equals(name))
                return;
        }
        names.add(name);
    }
}
