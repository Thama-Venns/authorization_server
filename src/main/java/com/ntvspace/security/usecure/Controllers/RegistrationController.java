package com.ntvspace.security.usecure.Controllers;

import com.ntvspace.security.usecure.Common.Data.Entities.User;
import com.ntvspace.security.usecure.Models.RegisterModel;
import com.ntvspace.security.usecure.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired private UserService _userService;

    @GetMapping
    public String get() {
        return "Register";
    }

    @PostMapping
    public ResponseEntity<?> UserRegistration(@Valid @RequestBody RegisterModel user) throws Exception {

        User crUser = _userService.UserCreateUser(user);

        return ResponseEntity.ok("User created");
    }

}
