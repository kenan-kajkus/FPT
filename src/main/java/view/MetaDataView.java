package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

class MetaDataView extends VBox {
    private final Label album = new Label("Album:");
    private final Label interpret = new Label("Interpret:");
    private final Label title = new Label("Titel:");
    private final TextField titleText = new TextField();
    private final TextField interpretText = new TextField();
    private final TextField albumText = new TextField();
    MetaDataView(){
        getChildren().addAll(title, titleText, interpret, interpretText, album, albumText);
    }
    void setMetaData(String title, String interpret, String album){
        titleText.setText(title);
        interpretText.setText(interpret);
        albumText.setText(album);
    }
}
