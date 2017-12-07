package model;


import IDGenerator.IDGenerator;
import IDGenerator.IDOverFlowException;

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
        IDGenerator.setIdDenetator(library);
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
        this.library = library;
    }
}