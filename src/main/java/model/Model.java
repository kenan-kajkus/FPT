package model;


import java.io.File;

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
        for(File file: files){
            library.add(new Song(file));
        }
    }
}