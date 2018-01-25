package tcp;

import java.io.*;
import java.net.Socket;

public class TCPClient implements Runnable{
    @Override
    public void run() {
        try(Socket serverCon = new Socket("localhost",5020);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(serverCon.getOutputStream()));) {
            out.write("name");
            out.newLine();
            out.write("password");
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
