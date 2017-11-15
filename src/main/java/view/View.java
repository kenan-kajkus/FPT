package view;

import interfaces.OnClick;
import interfaces.OnClickSong;
import interfaces.Song;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    private Button toPlaylist = new Button("Add to Playlist");

    public View(){
        setTop(saveLoad);
        setLeft(library);
        setCenter(playlist);
        setRight(new VBox(metaData,musicPlayer,toPlaylist));
        setBottom(addAll);
        library.setOnMouseClicked(e ->setMetaData());
        playlist.setOnMouseClicked(e -> setMetaData());
    }
    public void setAddAll(OnClick eh) {
        addAll.setOnAction(e -> {eh.doOnclick();});
    }
    public void onToPlaylist(OnClickSong eh){
        toPlaylist.setOnAction( song ->eh.doOnclick(library.getSelectionModel().getSelectedItem()));
    }
    public void bindData(Playlist library, Playlist playlist) {
        this.library.setItems(library);
        this.playlist.setItems(playlist);
    }
    public MusicplayerView getMusicPlayer() {
        return musicPlayer;
    }
    private void setMetaData(){
        Song tempSong = library.getSelectionModel().getSelectedItem();
        metaData.setMetaData(tempSong.getTitle(),tempSong.getInterpret(),tempSong.getAlbum());
    }

}
