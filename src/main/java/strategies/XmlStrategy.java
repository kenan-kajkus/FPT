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

    private BufferedInputStream bufferedXmlInputStream;
    private BufferedOutputStream bufferedXmlOutputStream;
    private XMLDecoder decoder;
    private XMLEncoder encoder;
    @Override
    public void openWritableLibrary() throws IOException {
        bufferedXmlOutputStream = new BufferedOutputStream(new FileOutputStream(libraryPath));
        encoder = new XMLEncoder(bufferedXmlOutputStream);
    }

    @Override
    public void openReadableLibrary() throws IOException {
        bufferedXmlInputStream = new BufferedInputStream(new FileInputStream(libraryPath));
        decoder = new XMLDecoder(bufferedXmlInputStream);
    }

    @Override
    public void openWritablePlaylist() throws IOException {
        bufferedXmlOutputStream = new BufferedOutputStream(new FileOutputStream(playlistPath));
        encoder = new XMLEncoder(bufferedXmlOutputStream);
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        bufferedXmlInputStream = new BufferedInputStream(new FileInputStream(playlistPath));
        decoder = new XMLDecoder(bufferedXmlInputStream);
    }

    @Override
    public void writeSong(Song s) throws IOException {
        //not needed can write playlist as whole
    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException {
        return null; // same as writeSong()
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
        encoder.writeObject(p);// works via getter/setter (add() and get())
    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        return (Playlist)decoder.readObject();
    }

    @Override
    public void closeWritableLibrary() {
        encoder.close();
        closeWritable();
    }

    @Override
    public void closeReadableLibrary() {
        decoder.close();
        closeReadable();
    }

    @Override
    public void closeWritablePlaylist() {
        encoder.close();
        closeWritable();
    }

    @Override
    public void closeReadablePlaylist() {
        decoder.close();
        closeReadable();
    }
    private void closeWritable(){
        try {
            bufferedXmlOutputStream.close();
        } catch (IOException e) {
            System.err.println("Problem saving to XML");
        }
    }

    private void closeReadable(){
        try {
            bufferedXmlInputStream.close();
        } catch (IOException e) {
            System.err.println("Problem reading from XML");
        }
    }
    @Override
    public String toString() {
        return XmlStrategy.class.getSimpleName();
    }
}
