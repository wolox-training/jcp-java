package wolox.training.exceptions;

import org.springframework.http.HttpStatus;

public class BookIdMismatchException extends RuntimeException {

    public BookIdMismatchException() {
        super(String.valueOf(HttpStatus.BAD_REQUEST));
    }

}
