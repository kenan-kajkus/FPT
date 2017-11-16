package view;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

/**
 *This view part contains all elements to control saving and loading strategies
 */

 class SaveLoadView extends HBox {

    private ChoiceBox<String> strategyChoise = new ChoiceBox<>();
    private Button load = new Button("Load");
    private Button save = new Button("Save");

    SaveLoadView(){
        getChildren().addAll(strategyChoise, load, save);
    }
}
