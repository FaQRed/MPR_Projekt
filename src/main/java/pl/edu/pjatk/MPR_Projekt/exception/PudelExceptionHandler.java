package pl.edu.pjatk.MPR_Projekt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class PudelExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PudelNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(RuntimeException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidPudelDataException.class})
    public ResponseEntity<Object> handleInvalidPudelData(InvalidPudelDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
