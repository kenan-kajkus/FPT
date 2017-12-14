package model;


import IDGenerator.IDGenerator;
import IDGenerator.IDOverFlowException;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Model{
    private Playlist library = new Playlist();
    private Playlist playlist = new Playlist();

    public Playlist getLibrary(){
        return library;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setLibrary(File[] files) {
        library.clear();
        IDGenerator.setIdGenerator(library);
        for(File file: files){
            try {
               library.add(new Song(file));
            }catch (IDOverFlowException e){
                System.err.println(e.getMessage());
                return;
            }
        }
    }
    public void setLibrary(Playlist library){
        this.library.clear();
        for( interfaces.Song s: library){
            this.library.add(s);
        }
    }

    public void setPlaylist(Playlist songs) {
        playlist.clear();
        for(interfaces.Song s : songs){
            try {
                playlist.add(library.findSongByID(s.getId()));
            }catch (RemoteException e){}
        }
    }
}