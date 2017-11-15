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
            String fileString = file[i].toString();
            //checks if file is an mp3 therefore it looks up if last four chars of fileString equals ".mp3"
            if(fileString.indexOf(".mp3",fileString.length()-5)>0) {
                Song temp = new Song(file[i]);
                library.add(temp);
            }
        }
    }
}