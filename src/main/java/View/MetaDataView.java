package View;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MetaDataView extends VBox {
    Label title = new Label("Titel:");
    TextField titleText = new TextField();
    Label interpret = new Label("Interpret:");
    TextField interpretText = new TextField();
    Label album = new Label("Album:");
    TextField albumText = new TextField();
    public MetaDataView(){
        this.getChildren().addAll(title, titleText, interpret, interpretText, album, albumText);
    }
}
