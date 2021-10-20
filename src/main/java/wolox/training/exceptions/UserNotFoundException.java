package wolox.training.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(String.valueOf(HttpStatus.NOT_FOUND));
    }

}
