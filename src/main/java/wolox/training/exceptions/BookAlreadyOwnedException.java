package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyOwnedException extends ResponseStatusException {

    public BookAlreadyOwnedException(String errorMessage) {
        super(HttpStatus.NOT_FOUND, errorMessage);
    }

}
