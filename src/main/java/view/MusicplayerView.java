package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MusicplayerView extends VBox {
    private HBox firstRow = new HBox();
    private Button play = new Button("PLAY");
    private Button pause = new Button("PAUSE");
    private Button nextsong = new Button("next Song");
    private Button commit = new Button("COMMIT");
    private Button toPlaylist = new Button("Add to Playlist");
    public MusicplayerView(){
        firstRow.getChildren().addAll(play, pause, nextsong, commit);
        getChildren().addAll(firstRow,toPlaylist);

    }
}
