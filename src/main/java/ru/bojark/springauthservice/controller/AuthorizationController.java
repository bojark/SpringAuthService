package ru.bojark.springauthservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bojark.springauthservice.domain.User;
import ru.bojark.springauthservice.exception.InvalidCredentials;
import ru.bojark.springauthservice.exception.UnauthorizedUser;
import ru.bojark.springauthservice.misc.Authorities;
import ru.bojark.springauthservice.service.AuthorizationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }
    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String name,
                                            @RequestParam("password") String password) {
        return service.getAuthorities(name, password);

    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> invalidCredentialsHandler(UnauthorizedUser e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
