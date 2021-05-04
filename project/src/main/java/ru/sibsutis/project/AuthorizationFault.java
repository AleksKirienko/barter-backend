package ru.sibsutis.project;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorizationFault extends RuntimeException {
   public  AuthorizationFault() {
       System.out.println("Wrong email or password!");
   }
}
