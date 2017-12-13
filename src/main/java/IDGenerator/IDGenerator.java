package IDGenerator;


import model.Playlist;

public class IDGenerator {
    private static boolean idGeneratorInit = false;
    private static Playlist lib;

    public static long getNextID() throws IDOverFlowException, NotInitializedException {
        if(!idGeneratorInit){
            throw new NotInitializedException();
        }
        if(lib.size()>9999){
                throw new IDOverFlowException();
        }

        return lib.size();
    }
    public static void setIdGenerator(Playlist libTemp){
        idGeneratorInit = true;
        lib = libTemp;
    }
    private static class NotInitializedException extends RuntimeException{
        NotInitializedException(){
            super("IDGenerator not initialized");
        }
    }
}
