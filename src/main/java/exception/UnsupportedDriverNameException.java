package exception;

import java.io.Serializable;

public class UnsupportedDriverNameException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = -1468109695483543145L;

    public UnsupportedDriverNameException() {
        super();
    }

    public UnsupportedDriverNameException(String message) {
        super(message);
    }

    public UnsupportedDriverNameException(String message, Throwable cause) {
        super(message, cause);
    }

}
