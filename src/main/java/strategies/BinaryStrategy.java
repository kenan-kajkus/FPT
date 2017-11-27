package strategies;

import interfaces.Playlist;
import interfaces.SerializableStrategy;
import interfaces.Song;

import java.io.*;

public class BinaryStrategy implements SerializableStrategy {
    private File path = new File("binary.ser");
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    @Override
    public void openWritableLibrary() throws IOException {
        oos = new ObjectOutputStream(new FileOutputStream(path));
    }

    @Override
    public void openReadableLibrary() throws IOException {
        ois = new ObjectInputStream(new FileInputStream(path));
    }

    @Override
    public void openWritablePlaylist() throws IOException {

    }

    @Override
    public void openReadablePlaylist() throws IOException {

    }

    @Override
    public void writeSong(Song s) throws IOException {
            oos.writeObject(s); // write Object
            oos.flush();
    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void writeLibrary(Playlist p) throws IOException {
        oos.writeObject(p); // write Object
        oos.flush();
    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        return (Playlist)ois.readObject();
    }

    @Override
    public void writePlaylist(Playlist p) throws IOException {

    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void closeWritableLibrary() {
        try {
            oos.flush();
            oos.close();
        }catch (IOException e){}
    }

    @Override
    public void closeReadableLibrary() {
        try {
            ois.close();
        }catch (IOException e){}
    }

    @Override
    public void closeWritablePlaylist() {

    }

    @Override
    public void closeReadablePlaylist() {

    }

    @Override
    public String toString() {
        return BinaryStrategy.class.getSimpleName();
    }
}
