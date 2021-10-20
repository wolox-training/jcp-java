package wolox.training.exceptions;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super(String.valueOf(HttpStatus.NOT_FOUND));
    }

}
