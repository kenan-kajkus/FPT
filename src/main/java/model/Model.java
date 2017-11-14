package model;

import org.apache.commons.lang.ObjectUtils;

import java.io.File;
import java.rmi.RemoteException;

public class Model{
    private Playlist library = new Playlist();
    private Playlist playlist;

    public Playlist getLibrary() {
        return library;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setLibrary(File[] file) {
        for (int i = 0; i < file.length; i++){
               System.out.println(file[i].toString());
               Song temp = new Song(file[i]);
               System.out.println("test1");
               try {
                   library.addSong(temp);
               }catch (RemoteException w){}
               System.out.println("test2");

        }
    }
}