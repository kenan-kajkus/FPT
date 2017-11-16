package controller;

import interfaces.Song;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Model;
import view.View;
import java.io.File;
import java.util.ArrayList;

public class Controller{
    private Media m;
    private int currentSong = 0;
    private MediaPlayer mp;
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
        view.removeFromPlaylist(() ->{
            model.getPlaylist().remove(view.getPlaylist().getSelectionModel().getSelectedItem());
        });
        view.commit(() ->{
            Song song = view.getLibrary().getSelectionModel().getSelectedItem();
            song.setInterpret(view.getInterpretText().getText());
            song.setAlbum(view.getAlbumText().getText());
            song.setTitle(view.getTitleText().getText());
        });
        view.play(() ->{
            if(m==null)
                m  = new Media(new File(model.getPlaylist().get(0).getPath()).toURI().toString());
            if(mp==null) {
                mp = new MediaPlayer(m);
                mp.setOnEndOfMedia(() -> newmp());
            }
            mp.play();
        });

        view.pause(() -> mp.pause());

        view.next(()->{
            mp.stop();
            if (++currentSong >= model.getPlaylist().size())
                currentSong = 0;
            mp = new MediaPlayer(new Media(new File(model.getPlaylist().get(currentSong).getPath()).toURI().toString()));
            mp.setOnEndOfMedia(() -> newmp());
            mp.play();
        });
    }


    private void newmp(){
        if (++currentSong >= model.getPlaylist().size())
            currentSong = 0;
        mp = new MediaPlayer(new Media(new File(model.getPlaylist().get(currentSong).getPath()).toURI().toString()));
        mp.play();
        mp.setOnEndOfMedia(()-> newmp());
        System.gc();
    }

    private void addToPlaylist(Song s) {
        if(s!=null)
            model.getPlaylist().add(s);
    }

    private void addLib(){
        File libFolder;
        Stage stage = new Stage();
        DirectoryChooser libChooser = new DirectoryChooser();
        libChooser.setTitle("Choose lib folder");
        //checks if no folder is chosen
        if((libFolder = libChooser.showDialog(stage))!=null) {
            File files[];
            files = chooseMp3(libFolder);
            if(files.length>0){
                model.setLibrary(files);
            }else{
                view.alertNoFiles();
            }
        }
    }

    private File[] chooseMp3(File folder){
        File[] files = folder.listFiles();
        ArrayList<File> mp3s = new ArrayList<>();
        for (File file: files) {
            if(file.toString().indexOf(".mp3",file.toString().length()-5)>0){
                mp3s.add(file);
            }
        }
        return mp3s.toArray(new File[0] );
    }

    private void showMetaData(ListView<Song> listView){
        Song tempSong = listView.getSelectionModel().getSelectedItem();
        if(tempSong == null)
            return;
        view.setMetaData(tempSong.getTitle(),tempSong.getInterpret(),tempSong.getAlbum());
    }
}