package hugo;
/**
 * Custom Exception class for REST Api calls.
 * @author Peter Wachira
 *
 */
public class RestApiException extends Exception {
    /**
     * 
     * @param message the exception message.
     */
    public RestApiException(String message) {
        super(message);
    }
}
