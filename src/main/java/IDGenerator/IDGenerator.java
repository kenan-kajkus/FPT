package IDGenerator;

public class IDGenerator {
    private static long id = 0;
    public static long getNextID() throws IDOverFlowException {
        if(id>1000){
            throw new IDOverFlowException();
        }
        return id++;
    }
}
