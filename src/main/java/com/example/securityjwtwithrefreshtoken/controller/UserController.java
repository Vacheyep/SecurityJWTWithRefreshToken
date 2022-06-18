package com.example.securityjwtwithrefreshtoken.controller;

import com.example.securityjwtwithrefreshtoken.dto.UserDto;
import com.example.securityjwtwithrefreshtoken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDto> getByUsername(@RequestParam("username") String username) {
        return new ResponseEntity<>(
                userService.getUserByUsername(username),
                HttpStatus.OK
        );
    }

    @PostMapping({"/registerNewUser"})
    public UserDto registerNewUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

}
