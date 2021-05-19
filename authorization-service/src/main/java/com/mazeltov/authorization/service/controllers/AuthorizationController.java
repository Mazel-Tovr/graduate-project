package com.mazeltov.authorization.service.controllers;

import com.mazeltov.authorization.service.service.*;
import com.mazeltov.common.dto.*;
import com.mazeltov.common.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorizationController {

    @Autowired
    private UserService userService;


    @PostMapping("${api.authorization-service.rout}")
    private ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {

        try {
            userService.creteUser(userDto);
            return new ResponseEntity<>("User successfully created", HttpStatus.OK);
        } catch (UserAlReadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("${api.authorization-service.rout}")
    private ResponseEntity<Object> editUser(@RequestBody UserDto userDto) {
        try {
            userService.editUser(userDto);
            return new ResponseEntity<>("User successfully edited", HttpStatus.OK);
        } catch (PasswordsAreNotTheSameExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("${api.authorization-service.admin.rout}")
    private ResponseEntity<Object> editUserAsAdmin(@RequestBody UserForAdminDto userDto) {
        try {
            userService.editUserAsAdmin(userDto);
            return new ResponseEntity<>("User successfully edited", HttpStatus.OK);
        } catch (PasswordsAreNotTheSameExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}
