package strategies;

import interfaces.Playlist;
import interfaces.SerializableStrategy;
import interfaces.Song;

import java.io.*;

public class BinaryStrategy implements SerializableStrategy {
    private File libraryPath = new File("library.ser");
    private File playlistPath = new File("playlist.ser");

    private FileOutputStream fos = null;
    private ObjectOutputStream oos = null;
    private FileInputStream fis = null;
    private ObjectInputStream ois = null;
    @Override
    public void openWritableLibrary() throws IOException {
        fos = new FileOutputStream(libraryPath);
        oos = new ObjectOutputStream(fos);
    }

    @Override
    public void openReadableLibrary() throws IOException {
        fis = new FileInputStream(libraryPath);
        ois = new ObjectInputStream(fis);
    }

    @Override
    public void openWritablePlaylist() throws IOException {
        fos = new FileOutputStream(playlistPath);
        oos = new ObjectOutputStream(fos);
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        fis = new FileInputStream(playlistPath);
        ois = new ObjectInputStream(fis);
    }

    @Override
    public void writeSong(Song s) throws IOException {
            oos.writeObject(s); // write Object
    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException {
        Song readSong;
        try {
            readSong = (Song) ois.readObject();
        }catch(EOFException e){
            //end of file reached
            return null;
        }
        return readSong;
    }

    @Override
    public void writeLibrary(Playlist p) throws IOException {
        for(Song s:p){
            writeSong(s);
        }
    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        Song song;
        model.Playlist library = new model.Playlist();
        while ((song = readSong()) != null) {
            library.add(song);
        }
        return library;
    }

    @Override
    public void writePlaylist(Playlist p) throws IOException {
        for(Song s:p){
            writeSong(s);
        }
    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        Song song;
        model.Playlist library = new model.Playlist();
        while ((song = readSong()) != null) {
            library.add(song);
        }
        return library;
    }

    @Override
    public void closeWritableLibrary() {
        try {
            oos.flush();
            oos.close();
        }catch (Exception e){
            System.out.println("Problem Saving");
        }
    }

    @Override
    public void closeReadableLibrary() {
        try {
            ois.close();
            fis.close();
        }catch (IOException e){}
    }

    @Override
    public void closeWritablePlaylist() {
        try {
            oos.flush();
            oos.close();
        }catch (Exception e){
            System.out.println("Problem Saving");
        }
    }

    @Override
    public void closeReadablePlaylist() {
        try {
            oos.flush();
            oos.close();
        }catch (Exception e){
            System.out.println("Problem Saving");
        }
    }

    @Override
    public String toString() {
        return BinaryStrategy.class.getSimpleName();
    }
}
