package controller;

import interfaces.Song;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Model;
import view.MusicplayerView;
import view.View;
import java.io.File;

public class Controller{
    private File libFolder;
    private Model model;
    private View view;
    public void link(View view, Model model){
        this.view = view;
        this.model = model;
        this.view.bindData(this.model.getLibrary(),this.model.getPlaylist());
        addEventhandler();
    }
    private void addEventhandler(){
        view.setAddAll(() ->
            addLib());
        view.onToPlaylist((song) -> {
            addToPlaylist(song);
        });
        view.showPlayMeta(() ->{
            showMetaData(view.getPlaylist());
        });
        view.showLibMeta(() ->{
            showMetaData(view.getLibrary());
        });
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
        //---End MusicplayerEventhandler
    }

    private void addToPlaylist(Song s) {
        if(s!=null)
            model.getPlaylist().add(s);
    }

    private void addLib(){
        Stage stage = new Stage();
        DirectoryChooser libChooser = new DirectoryChooser();
        libChooser.setTitle("Choose lib folder");
        libFolder = libChooser.showDialog(stage) ;
        model.setLibrary(libFolder.listFiles());
    }

    private void showMetaData(ListView<Song> listView){
        Song tempSong = listView.getSelectionModel().getSelectedItem();
        view.setMetaData(tempSong.getTitle(),tempSong.getInterpret(),tempSong.getAlbum());
    }
}