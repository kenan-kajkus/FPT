package controller;

import interfaces.SerializableStrategy;
import interfaces.Song;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Model;
import model.Playlist;
import view.View;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller{
    private Media m;
    private int currentSong = 0;
    private MediaPlayer musicPlayer;
    private Model model;
    private View view;
    private Song metaSong;
    public void link(View view, Model model){
        this.view = view;
        this.model = model;
        this.view.bindData(this.model.getLibrary(),this.model.getPlaylist());
        addEventhandler();
    }
    private void addEventhandler(){
        view.setAddAll(this::addLib);
        view.onToPlaylist(this::addToPlaylist);
        view.showPlayMeta(() -> showMetaData(view.getPlaylist()));
        view.showLibMeta(() -> showMetaData(view.getLibrary()));
        view.removeFromPlaylist(this::removeFromPlaylist);
        view.commit(this::commit);
        view.play(this::play);
        view.pause(this::pause);
        view.next(this::next);
        view.load(this::load);
        view.save(this::save);
    }

    private void newmp(){
        if (++currentSong >= model.getPlaylist().size())
            currentSong = 0;
        musicPlayer = new MediaPlayer(new Media(new File(model.getPlaylist().get(currentSong).getPath()).toURI().toString()));
        musicPlayer.play();
        musicPlayer.setOnEndOfMedia(this::newmp);
        System.gc();
    }

    private void addToPlaylist(Song songToAdd) {
        if(songToAdd!=null)
            model.getPlaylist().add(songToAdd);
    }

    private void removeFromPlaylist(){
        model.getPlaylist().remove(view.getPlaylist().getSelectionModel().getSelectedItem());
    }

    private void commit(){
        metaSong.setInterpret(view.getInterpretText().getText());
        metaSong.setAlbum(view.getAlbumText().getText());
        metaSong.setTitle(view.getTitleText().getText());
        view.getLibrary().refresh();
        view.getPlaylist().refresh();
    }
    private void play(){
        if(m==null)
            m  = new Media(new File(model.getPlaylist().get(0).getPath()).toURI().toString());
        if(musicPlayer ==null) {
            musicPlayer = new MediaPlayer(m);
            musicPlayer.setOnEndOfMedia(this::newmp);
        }
        musicPlayer.play();
    }
    private void pause(){
        musicPlayer.pause();
    }
    private void next(){
        musicPlayer.stop();
        if (++currentSong >= model.getPlaylist().size())
            currentSong = 0;
        musicPlayer = new MediaPlayer(new Media(new File(model.getPlaylist().get(currentSong).getPath()).toURI().toString()));
        musicPlayer.setOnEndOfMedia(this::newmp);
        musicPlayer.play();
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
        if (files != null) {
            for (File file: files) {
                if(file.toString().indexOf(".mp3",file.toString().length()-5)>0){
                    mp3s.add(file);
                }
            }
        }
        return mp3s.toArray(new File[0] );
    }

    private void showMetaData(ListView<Song> listView){
        metaSong = listView.getSelectionModel().getSelectedItem();
        if(metaSong == null)
            return;
        view.setMetaData(metaSong.getTitle(),metaSong.getInterpret(),metaSong.getAlbum());
    }

    private void load(){
        SerializableStrategy strategy = view.getStrategy();
        try {
            strategy.openReadableLibrary();
            model.setLibrary((Playlist)strategy.readLibrary());
            strategy.closeReadableLibrary();
            strategy.openReadablePlaylist();
            model.setPlaylist((Playlist)strategy.readPlaylist());
            strategy.closeReadablePlaylist();
        }
        catch (IOException e){ }
        catch (ClassNotFoundException c){ }
    }
    private void save(){
        SerializableStrategy strategy = view.getStrategy();
        try {
            strategy.openWritableLibrary();
            strategy.writeLibrary(model.getLibrary());
            strategy.closeWritableLibrary();
            strategy.openWritablePlaylist();
            strategy.writePlaylist(model.getPlaylist());
            strategy.closeWritablePlaylist();
        }catch (IOException e){
            view.alertSaveFiles();
        }
    }
}