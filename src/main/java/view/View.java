package view;

import interfaces.OnClick;
import interfaces.Song;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Model;
import model.Playlist;

/**
 * Main GUI class contains all other subparts to generate complete GUI
 *
 *
 *
*/
public class View extends BorderPane {
    private SaveLoadView saveLoad = new SaveLoadView();
    private ListView<Song> library = new ListView<>();
    private ListView<Song> playlist = new ListView<>();
    private MetaDataView metaData = new MetaDataView();
    private MusicplayerView musicPlayer = new MusicplayerView();
    private Button addAll = new Button("add All");

    public View(){
        setTop(saveLoad);
        setLeft(library);
        setCenter(playlist);
        setRight(new VBox(metaData,musicPlayer));
        setBottom(addAll);
        library.setOnMouseClicked(e ->setMetaData());
        playlist.setOnMouseClicked(e -> setMetaData());
    }
    public void setAddAll(OnClick eh) {
        addAll.setOnAction(e -> {eh.doOnclick();});
    }
    public void bindData(Playlist playlist) {
        this.library.setItems(playlist);
    }
    public MusicplayerView getMusicPlayer() {
        return musicPlayer;
    }
    private void setMetaData(){
        Song tempSong = library.getSelectionModel().getSelectedItem();
        metaData.set(tempSong.getTitle(),tempSong.getInterpret(),tempSong.getAlbum());
    }

}
