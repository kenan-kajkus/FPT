package strategies;

import interfaces.Playlist;
import interfaces.SerializableStrategy;
import interfaces.Song;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class XmlStrategy implements SerializableStrategy {
    private File libraryPath = new File("library.xml");
    private File playlistPath = new File("playlist.xml");

    private BufferedInputStream fis;
    private BufferedOutputStream fos;
    private XMLDecoder decoder;
    private XMLEncoder encoder;
    @Override
    public void openWritableLibrary() throws IOException {
        fos = new BufferedOutputStream(new FileOutputStream(libraryPath));
        encoder = new XMLEncoder(fos);
    }

    @Override
    public void openReadableLibrary() throws IOException {
        fis = new BufferedInputStream(new FileInputStream(libraryPath));
        decoder = new XMLDecoder(fis);
    }

    @Override
    public void openWritablePlaylist() throws IOException {
        fos = new BufferedOutputStream(new FileOutputStream(playlistPath));
        encoder = new XMLEncoder(fos);
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        fis = new BufferedInputStream(new FileInputStream(playlistPath));
        decoder = new XMLDecoder(fis);
    }

    @Override
    public void writeSong(Song s) throws IOException {

    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void writeLibrary(Playlist p) throws IOException {
        encoder.writeObject(p);

    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        return (Playlist) decoder.readObject();
    }

    @Override
    public void writePlaylist(Playlist p) throws IOException {
        encoder.writeObject(p);
    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        return (Playlist)decoder.readObject();
    }

    @Override
    public void closeWritableLibrary() {
        encoder.close();
        System.out.println("test");
    }

    @Override
    public void closeReadableLibrary() {
        decoder.close();

        System.out.println("test");
    }

    @Override
    public void closeWritablePlaylist() {
        encoder.close();
    }

    @Override
    public void closeReadablePlaylist() {
        decoder.close();
    }
    @Override
    public String toString() {
        return XmlStrategy.class.getSimpleName();
    }
}
