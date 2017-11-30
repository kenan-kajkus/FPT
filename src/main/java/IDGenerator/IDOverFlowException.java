package IDGenerator;

public class IDOverFlowException extends Exception {

    public IDOverFlowException() {
        super ("You can not add more items. IDoverFlow reached");
    }

}
