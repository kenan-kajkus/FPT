package main;

import controller.Controller;
import model.Model;
import view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    private View view = new View();
    private Model model = new Model();
    private Controller vc = new Controller();

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene sc = new Scene(view);
        primaryStage.setScene(sc);
        primaryStage.show();
        vc.link(view,model);
    }
}
