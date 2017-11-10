package Main;

import controller.Controller;
import model.Model;
import view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    View view = new View();
    Model model = new Model();
    Controller vc = new Controller();

    public static void main(String[] args){
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene sc = new Scene(view);
        primaryStage.setScene(sc);
        primaryStage.show();
        vc.link(view,model);
    }
}
