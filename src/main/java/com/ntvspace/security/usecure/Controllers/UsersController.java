package com.ntvspace.security.usecure.Controllers;

import com.ntvspace.security.usecure.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired private UserService _userService;

    @GetMapping
    public String getUsers() {
        return "Hey there users";
    }

    @DeleteMapping
    public ResponseEntity<?> Delete(@RequestParam long userId) {
        _userService.DeleteUser(userId);
        return ResponseEntity.ok("User deleted");
    }
}
