package wolox.training.exceptions;

import org.springframework.http.HttpStatus;

public class BookAlreadyOwnedException extends RuntimeException {

    public BookAlreadyOwnedException(String errorMessage) {
        super(errorMessage);
    }

}
