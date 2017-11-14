package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.io.File;

public class Song implements interfaces.Song{
    private SimpleStringProperty path = new SimpleStringProperty();
    SimpleStringProperty title = new SimpleStringProperty();
    SimpleStringProperty album;
    SimpleStringProperty interpret;

    public Song(File path){
        this.path.set(path.toString());
        this.path.set(path.getName());
    }
    @Override
    public String getAlbum() {
        return null;
    }

    @Override
    public void setAlbum(String album) {

    }

    @Override
    public String getInterpret() {
        return null;
    }

    @Override
    public void setInterpret(String interpret) {

    }

    @Override
    public String getPath() {
        return path.getName();
    }

    @Override
    public void setPath(String path) {
        this.path.set(path);
    }

    @Override
    public String getTitle() {
        return title.getName();
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

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
}
