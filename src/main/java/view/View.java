package view;

import interfaces.OnClick;
import interfaces.OnClickSong;
import interfaces.SerializableStrategy;
import interfaces.Song;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Playlist;
import strategies.BinaryStrategy;
import strategies.JdbcStrategy;
import strategies.OpenJpaStrategy;
import strategies.XmlStrategy;

/**
 * Main GUI class contains all other subparts to generate complete GUI
 *
 *
 *
*/
public class View extends BorderPane {
    private HBox saveLoad = new HBox();
    private ComboBox<SerializableStrategy> strategyChoise = new ComboBox<>();
    private Button load = new Button("Load");
    private Button save = new Button("Save");
    private ListView<Song> library = new ListView<>();
    private ListView<Song> playlist = new ListView<>();
    private VBox metaData = new VBox();
    private final Label album = new Label("Album:");
    private final Label interpret = new Label("Interpret:");
    private final Label title = new Label("Titel:");
    private final TextField titleText = new TextField();
    private final TextField interpretText = new TextField();
    private final TextField albumText = new TextField();

    private Button addAll = new Button("add All");
    private Button toPlaylist = new Button("Add to Playlist");
    private Button removeFromPlaylist = new Button("Remove form Playlist");
    private VBox musicplayer = new VBox();
    private HBox firstRow = new HBox();
    private Button play = new Button("PLAY");
    private Button pause = new Button("PAUSE");
    private Button nextsong = new Button("next Song");
    private Button commit = new Button("COMMIT");

    public View(){
        saveLoad.getChildren().addAll(strategyChoise,load,save);
        metaData.getChildren().addAll(title, titleText, interpret, interpretText, album, albumText);
        firstRow.getChildren().addAll(play, pause, nextsong, commit);
        musicplayer.getChildren().addAll(firstRow);
        setTop(saveLoad);
        setLeft(library);
        setCenter(playlist);
        setRight(new VBox(metaData,musicplayer,toPlaylist,removeFromPlaylist));
        setBottom(addAll);
        library.setCellFactory(e -> {
            ListCell<Song> cell = new ListCell<Song>() {
                @Override
                protected void updateItem(Song myObject, boolean b) {
                    super.updateItem(myObject, myObject == null || b);
                    if (myObject != null) {
                        setText(myObject.getTitle());
                    } else { // wichtig da sonst der text stehen bleibt!
                        setText("");
                    }
                }
            };
            return cell;
        });
        playlist.setCellFactory(e -> {
            ListCell<Song> cell = new ListCell<Song>() {
                @Override
                protected void updateItem(Song myObject, boolean b) {
                    super.updateItem(myObject, myObject == null || b);
                    if (myObject != null) {
                        setText(myObject.getTitle());
                    } else { // wichtig da sonst der text stehen bleibt!
                        setText("");
                    }
                }
            };
            return cell;
        });
    }
    public void bindData(Playlist library, Playlist playlist) {
        this.library.setItems(library);
        this.playlist.setItems(playlist);
        strategyChoise.getItems().addAll(
            new BinaryStrategy(),
                new XmlStrategy(),
                new JdbcStrategy(),
                new OpenJpaStrategy()
        );
    }
    public void showLibMeta(OnClick eh){
        library.setOnMouseClicked(e -> eh.doOnclick());
    }
    public void showPlayMeta(OnClick eh){
        playlist.setOnMouseClicked(e -> eh.doOnclick());
    }
    public void commit(OnClick eh){
        commit.setOnAction(e -> eh.doOnclick());
    }
    public void setAddAll(OnClick eh) {
        addAll.setOnAction(e -> {eh.doOnclick();});
    }
    public void onToPlaylist(OnClickSong eh){
        toPlaylist.setOnAction( song ->eh.doOnclick(library.getSelectionModel().getSelectedItem()));
    }
    public void removeFromPlaylist(OnClick eh){
        removeFromPlaylist.setOnAction(e -> eh.doOnclick());
    }
    public void play(OnClick eh){
        play.setOnAction(e -> eh.doOnclick());
    }
    public void pause(OnClick eh){
        pause.setOnAction(e -> eh.doOnclick());
    }
    public void next(OnClick eh){
        nextsong.setOnAction(e -> eh.doOnclick());
    }
    public void load(OnClick eh){
        load.setOnAction(e -> eh.doOnclick());
    }
    public void save(OnClick eh){
        save.setOnAction(e -> eh.doOnclick());
    }
    //***---->>> GETTER
    public ListView<Song> getLibrary() {
        return library;
    }

    public ListView<Song> getPlaylist() {
        return playlist;
    }

    public TextField getTitleText() {
        return titleText;
    }

    public TextField getAlbumText() {
        return albumText;
    }

    public TextField getInterpretText() {
        return interpretText;
    }

    public SerializableStrategy getStrategy(){
        return strategyChoise.getSelectionModel().getSelectedItem();
    }
    //GETTER <<<---***
    //***---->>> SETTER
    public void setMetaData(String title, String interpret, String album){
        titleText.setText(title);
        interpretText.setText(interpret);
        albumText.setText(album);
    }

    //SETTER <<<---***

    public void alertNoFiles() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Files selected");
        alert.setHeaderText(null);
        alert.setContentText("Please chose files");

        alert.showAndWait();
    }
    public void alertSaveFiles() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saving Problem");
        alert.setHeaderText(null);
        alert.setContentText("Your files are not save");

        alert.showAndWait();
    }
}
