package pl.edu.pjatk.MPR_Projekt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PudelNotFoundException extends RuntimeException {


    public PudelNotFoundException() {
        super("Pudel not found");
    }
}
