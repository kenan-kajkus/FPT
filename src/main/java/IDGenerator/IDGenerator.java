package IDGenerator;


import model.Playlist;
import org.apache.openjpa.lib.meta.SourceTracker;

public class IDGenerator {
    private static boolean init = false;
    private static Playlist lib;

    public static long getNextID() throws IDOverFlowException, NotInitializedException {
        if(!init){
            throw new NotInitializedException();
        }
        if(lib.size()>9999){
                throw new IDOverFlowException();
        }

        return lib.size();
    }
    public static void setIDgenetator(Playlist libt){
        init = true;
        lib = libt;
    }
    private static class NotInitializedException extends RuntimeException{
        NotInitializedException(){
            super("IDGenerator not initialized");
        }
    }
}
