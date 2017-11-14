package model;


import java.io.File;

public class Model{
    private Playlist library = new Playlist();
    private Playlist playlist;

    public Playlist getLibrary(){
        return library;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setLibrary(File[] file) {
        for (int i = 0; i < file.length; i++){
                Song temp = new Song(file[i]);
                library.add(temp);
            System.out.println(library.get(i).toString());
        }
    }
}