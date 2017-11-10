package model;

import interfaces.Song;
import javafx.collections.ModifiableObservableListBase;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

public class Playlist extends ModifiableObservableListBase<Song> implements interfaces.Playlist{
    ArrayList<Song> songs;
    @Override
    public boolean addSong(Song s) throws RemoteException {
        return false;
    }

    @Override
    public boolean deleteSong(Song s) throws RemoteException {
        return false;
    }

    @Override
    public boolean deleteSongByID(long id) throws RemoteException {
        return false;
    }

    @Override
    public void setList(ArrayList<Song> s) throws RemoteException {

    }

    @Override
    public ArrayList<Song> getList() throws RemoteException {
        return null;
    }

    @Override
    public void clearPlaylist() throws RemoteException {

    }

    @Override
    public int sizeOfPlaylist() throws RemoteException {
        return 0;
    }

    @Override
    public Song findSongByPath(String name) throws RemoteException {
        return null;
    }

    @Override
    public Song findSongByID(long id) throws RemoteException {
        return null;
    }

    @Override
    public Iterator<Song> iterator() {
        return null;
    }

    @Override
    public Song get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void doAdd(int index, Song element) {

    }

    @Override
    protected Song doSet(int index, Song element) {
        return null;
    }

    @Override
    protected Song doRemove(int index) {
        return null;
    }
}
