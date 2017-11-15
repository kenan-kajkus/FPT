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
    private Button removeFromPlaylist = new Button("Remove form Playlist");

    public View(){
        setTop(saveLoad);
        setLeft(library);
        setCenter(playlist);
        setRight(new VBox(metaData,musicPlayer,toPlaylist,removeFromPlaylist));
        setBottom(addAll);
    }
    public void bindData(Playlist library, Playlist playlist) {
        this.library.setItems(library);
        this.playlist.setItems(playlist);
    }

    public void showLibMeta(OnClick eh){
        library.setOnMouseClicked(e -> eh.doOnclick());
    }
    public void showPlayMeta(OnClick eh){
        playlist.setOnMouseClicked(e -> eh.doOnclick());
    }
    public void setAddAll(OnClick eh) {
        addAll.setOnAction(e -> {eh.doOnclick();});
    }
    public void onToPlaylist(OnClickSong eh){
        toPlaylist.setOnAction( song ->eh.doOnclick(library.getSelectionModel().getSelectedItem()));
    }
    //***---->>> GETTER
    public MusicplayerView getMusicPlayer() {
        return musicPlayer;
    }

    public ListView<Song> getLibrary() {
        return library;
    }

    public ListView<Song> getPlaylist() {
        return playlist;
    }
    //GETTER <<<---***
    //***---->>> SETTER
    public void setMetaData(String title, String interpret, String album){
        metaData.setMetaData(title,interpret,album);
    }

    //SETTER <<<---***

}
