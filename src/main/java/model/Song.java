package model;

import IDGenerator.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import java.io.*;

public class Song implements interfaces.Song, Serializable, Externalizable{
    public long serialVersionUID = 1;
    private long id;
    private SimpleStringProperty path = new SimpleStringProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty album = new SimpleStringProperty();
    private SimpleStringProperty interpret = new SimpleStringProperty();
    public Song(){}
    public Song(File path)throws IDOverFlowException{
        this.id = IDGenerator.getNextID();
        this.path.set(path.toString());
        this.title.set(path.getName());
    }

    public Song(long id, String title, String interpret, String album, String path) {
        this.id = id;
        this.title.set(title);
        this.interpret.set(interpret);
        this.album.set(album);
        this.path.set(path);
    }

    @Override
    public String getAlbum() {
        return album.get() ;
    }

    @Override
    public void setAlbum(String album) {
        this.album.set(album);
    }

    @Override
    public String getInterpret() {
        return interpret.get();
    }

    @Override
    public void setInterpret(String interpret) {
        this.interpret.set(interpret);
    }

    @Override
    public String getPath() {
        return path.get();
    }

    @Override
    public void setPath(String path) {
        this.path.set(path);
    }

    @Override
    public String getTitle() {
        return title.get();
    }

    @Override
    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        //TODO maybe needs to check on IDOverFlow
        this.id = id;
    }

    @Override
    public ObservableValue<String> pathProperty() {
        return path;
    }

    @Override
    public ObservableValue<String> albumProperty() {
        return album;
    }

    @Override
    public ObservableValue<String> interpretProperty() {
        return interpret;
    }

    @Override
    public ObservableValue<String> titleProperty() {
        return title;
    }

    @Override
    public String toString(){
        return getTitle();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getPath());
        out.writeObject(getTitle());
        out.writeObject(getAlbum());
        out.writeObject(getInterpret());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
         path = new SimpleStringProperty((String) in.readObject());
         title = new SimpleStringProperty((String)in.readObject());
         album = new SimpleStringProperty((String) in.readObject());
         interpret = new SimpleStringProperty((String) in.readObject());
    }
}
