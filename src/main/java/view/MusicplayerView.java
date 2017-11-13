package view;

import interfaces.OnClick;
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

    public void onPlay(OnClick eh){
        play.setOnAction(e -> eh.doOnclick());
    }
    public void onPause(OnClick eh){
        pause.setOnAction(e -> eh.doOnclick());
    }
    public void onNextsong(OnClick eh){
        nextsong.setOnAction(e -> eh.doOnclick());
    }
    public void onCommit(OnClick eh){
        commit.setOnAction(e -> eh.doOnclick());
    }
    public void onToPlaylist(OnClick eh){
        toPlaylist.setOnAction(e -> eh.doOnclick());
    }
}
