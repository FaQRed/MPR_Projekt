package pl.edu.pjatk.MPR_Projekt.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidPudelDataException extends RuntimeException {
    public InvalidPudelDataException(String message) {
        super(message);
    }
}
