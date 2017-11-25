package IDGenerator;

import model.Playlist;
import model.Song;

public class IDGenerator {

    public long getNextID(Song song) throws IDOverFlowException {
        for (long id = 0; id < 9999; id++){
            if (Playlist.findSongByID(id) == null){
                return id;
            }
        } throw new IDOverFlowException();
    }
}
