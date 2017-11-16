package model;

import interfaces.Song;
import javafx.collections.ModifiableObservableListBase;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

public class Playlist extends ModifiableObservableListBase<Song> implements interfaces.Playlist{
    private ArrayList<Song> songs = new ArrayList<>();

    @Override
    public Song get(int index) {
        return songs.get(index);
    }

    @Override
    public int size() {
        return songs.size();
    }

    @Override
    protected void doAdd(int index, Song element) {
        songs.add(element);
    }

    @Override
    protected Song doSet(int index, Song element) {
        return songs.set(index, element);
    }

    @Override
    protected Song doRemove(int index) {
        return songs.remove(index);
    }

    @Override
    public boolean addSong(Song s) throws RemoteException {
        //TODO future use
        return false;
    }

    @Override
    public boolean deleteSong(Song s) throws RemoteException {
        //TODO future use
        return false;
    }
    @Override
    public boolean deleteSongByID(long id) throws RemoteException {
        //TODO future use
        return false;
    }

    @Override
    public void setList(ArrayList<Song> s) throws RemoteException {
        //TODO future use
    }

    @Override
    public ArrayList<Song> getList() throws RemoteException {
        //TODO future use
        return null;
    }

    @Override
    public void clearPlaylist() throws RemoteException {
        //TODO future use
    }

    @Override
    public int sizeOfPlaylist() throws RemoteException {
        //TODO future use
        return 0;
    }

    @Override
    public Song findSongByPath(String name) throws RemoteException {
        //TODO future use
        return null;
    }

    @Override
    public Song findSongByID(long id) throws RemoteException {
        //TODO future use
        return null;
    }
}
