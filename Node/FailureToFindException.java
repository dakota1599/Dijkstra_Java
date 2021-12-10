package Node;

public class FailureToFindException extends Exception {
    
    public FailureToFindException(){
        super("Unfortunately, there is not path between these two locations.");
    }

}
