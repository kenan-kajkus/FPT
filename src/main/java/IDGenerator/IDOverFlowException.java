package IDGenerator;

public class IDOverFlowException extends Exception {

    public IDOverFlowException() {
        super ("You can not add more items, please delete a song to add a new one.");
    }

}
