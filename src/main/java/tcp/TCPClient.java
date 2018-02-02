package tcp;

import java.io.*;
import java.net.Socket;

public class TCPClient implements Runnable{
    Socket serverCon ;
    @Override
    public void run() {
        while (serverCon == null){
            try {
                serverCon = new Socket("localhost",5020);
            } catch (IOException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        try{
            DataOutputStream out = new DataOutputStream(serverCon.getOutputStream());
            BufferedReader in = new BufferedReader((new InputStreamReader(serverCon.getInputStream())));
            System.out.println("ready");
            out.writeBytes("name\n");
            out.writeBytes("password\n");
            out.flush();
            System.out.println("send");
            String servera = in.readLine();
            System.out.println(servera);
            try {
                out.close();
                in.close();
                serverCon.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
