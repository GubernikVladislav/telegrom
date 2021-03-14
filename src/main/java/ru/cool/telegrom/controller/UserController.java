package ru.cool.telegrom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cool.telegrom.model.RegistrationRequest;
import ru.cool.telegrom.service.UserService;

@RestController
@RequestMapping(path = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void registration(@RequestBody RegistrationRequest request) {
        userService.registration(request);
    }
}
