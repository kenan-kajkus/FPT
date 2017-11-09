package Main;

import View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public  static void main(String[] args){
        Application.launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View();
        Scene sc = new Scene(view);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}
