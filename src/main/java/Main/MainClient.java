package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tcp.TCPClient;
import udp.PlaytimeClient;
import view.ClientView;

public class MainClient extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientView view = new ClientView();
        PlaytimeClient pc = new PlaytimeClient(view);
        Thread client = new Thread(pc);
        client.start();
        TCPClient tcpClient = new TCPClient();
        Thread tcpThread = new Thread(tcpClient);
        tcpThread.start();
        Scene ClientScene = new Scene(view);
        primaryStage.setScene(ClientScene);
        primaryStage.show();
    }
}
