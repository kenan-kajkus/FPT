package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import udp.PlaytimeClient;
import view.ClientView;

public class MainClient extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientView view = new ClientView();
        Scene ClientScene = new Scene(view);
        primaryStage.setScene(ClientScene);
        primaryStage.show();
        PlaytimeClient pc = new PlaytimeClient();
        Thread client = new Thread(pc);
        client.start();
    }
}
