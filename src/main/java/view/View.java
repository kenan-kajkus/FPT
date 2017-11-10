package view;

import interfaces.OnClick;
import interfaces.Song;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
    }
    public void setAddAll(OnClick eh) {
        addAll.setOnAction(e -> {eh.doOnclick();});
    }
}
