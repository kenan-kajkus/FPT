package controller;

import model.Model;
import view.MusicplayerView;
import view.View;

public class Controller{
    private Model model;
    private View view;
    public void link(View view, Model model){
        this.view = view;
        this.model = model;
        addEventhandler();
    }
    private void addEventhandler(){
        view.setAddAll(() -> {
            //TODO implements function of addAll  Button ;
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
        tempMPView.onToPlaylist(() -> {
            //TODO implements function of toPlaylistBtn ;
        });
        //---End MusicplayerEventhandler
    }
}
