package controller;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Model;
import view.MusicplayerView;
import view.View;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller{
    private File libFolder;
    private File[] files;
    private Model model;
    private View view;
    public void link(View view, Model model){
        this.view = view;
        this.model = model;
        this.view.bindData(model.getLibrary());
        addEventhandler();
    }
    private void addEventhandler(){
        view.setAddAll(() ->
            addLib());
        //MusicplayerEventhandler ---
        MusicplayerView tempMPView = view.getMusicPlayer();
        tempMPView.onPlay(() -> {
            //TODO implements function of PlayBtn ;
        });
        tempMPView.onPause(() -> {
            //TODO implements function of PauseBtn ;
        });
        tempMPView.onNextsong(() -> {
            //TODO implements function of nextSongBtn ;
        });
        tempMPView.onCommit(() -> {
            //TODO implements function of commitBtn ;
        });
        tempMPView.onToPlaylist(() -> {
            //TODO implements function of toPlaylistBtn ;
        });
        //---End MusicplayerEventhandler
    }

    private void addLib(){
        Stage stage = new Stage();
        DirectoryChooser libChooser = new DirectoryChooser();
        libChooser.setTitle("Choose lib folder");
        libFolder = libChooser.showDialog(stage) ;
        model.setLibrary(libFolder.listFiles());
        model.getLibrary().get(1);
    }
}