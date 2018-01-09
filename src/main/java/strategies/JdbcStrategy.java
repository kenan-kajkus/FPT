package strategies;

import interfaces.Playlist;
import interfaces.SerializableStrategy;
import interfaces.Song;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.*;

public class JdbcStrategy implements SerializableStrategy {
    private String into;
    Connection con;
    DatabaseMetaData dmd;
    @Override
    public void openWritableLibrary() throws IOException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlite:music.db");
        }catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving Problem");
            alert.setHeaderText("No Database found!");
            alert.setContentText("Creating new database");
            alert.showAndWait();
        }
        try {
            PreparedStatement p = con.prepareStatement("SELECT name FROM sqlite_master WHERE type='table' and name='library';");
            p.executeQuery();
            ResultSet rs = p.getResultSet();
            rs.next();
            rs.close();
            p.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement q;
            q = con.prepareStatement("CREATE TABLE Library ( `ID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `Title` TEXT, `Artist` TEXT, `Album` TEXT, `Path` TEXT );");
            q.execute();
            q.close();
        }catch (SQLException e){
            System.out.println("DB Exist");
        }
    }

    @Override
    public void openReadableLibrary() throws IOException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlite:music.db");
        }catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Loading Problem");
            alert.setHeaderText(null);
            alert.setContentText("No Database found");

            alert.showAndWait();
        }
    }

    @Override
    public void openWritablePlaylist() throws IOException {

    }

    @Override
    public void openReadablePlaylist() throws IOException {

    }

    @Override
    public void writeSong(Song s) throws IOException {
        try{
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT OR REPLACE INTO "+into+" (id,title,Artist,album,path)"+
                    "VALUES (  ?, ?, ? ,?, ?)");
            preparedStatement.setLong(1,s.getId());
            preparedStatement.setString(2,s.getTitle());
            preparedStatement.setString(3,s.getInterpret());
            preparedStatement.setString(4,s.getAlbum());
            preparedStatement.setString(5,s.getPath());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Song readSong() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void writeLibrary(Playlist p) throws IOException {
        into = "Library";
        for(Song s : p){
            writeSong(s);
        }
    }

    @Override
    public Playlist readLibrary() throws IOException, ClassNotFoundException {
        model.Playlist lib = new model.Playlist();
        try  {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT ID,Title,Artist,Album,Path FROM Library");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                long id = Long.parseLong(resultSet.getString("ID"));
                String title = resultSet.getString("Title");
                String interpret = resultSet.getString("Artist");
                String album = resultSet.getString("Album");
                String path = resultSet.getString("Path");
                lib.add(new model.Song(id,title,interpret,album,path));
            }
            return lib;
        }catch (SQLException e) {
            e.printStackTrace();
        }return lib;
    }

    @Override
    public void writePlaylist(Playlist p) throws IOException {
        into = "Playlist";
        for(Song s : p){
            writeSong(s);
        }
    }

    @Override
    public Playlist readPlaylist() throws IOException, ClassNotFoundException {
        model.Playlist lib = new model.Playlist();
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            preparedStatement = con.prepareStatement("SELECT ID,Title,Artist,Album,Path FROM Playlist");
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e) {
            System.out.println("Mööp, No table Playlist");
        }
        try {
            while (resultSet!=null&&resultSet.next()) {
                long id = Long.parseLong(resultSet.getString("ID"));
                String title = resultSet.getString("Title");
                String interpret = resultSet.getString("Artist");
                String album = resultSet.getString("Album");
                String path = resultSet.getString("Path");
                lib.add(new model.Song(id, title, interpret, album, path));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lib;

    }

    @Override
    public void closeWritableLibrary() {

    }

    @Override
    public void closeReadableLibrary() {

    }

    @Override
    public void closeWritablePlaylist() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeReadablePlaylist() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return JdbcStrategy.class.getSimpleName();
    }
}
