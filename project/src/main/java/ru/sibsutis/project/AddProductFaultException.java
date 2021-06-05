package ru.sibsutis.project;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AddProductFaultException extends RuntimeException{

    public AddProductFaultException(String message) {
        super(message);
    }
}
