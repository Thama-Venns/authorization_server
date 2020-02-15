package com.ntvspace.security.usecure.Controllers;

import com.ntvspace.security.usecure.Models.UserDto;
import com.ntvspace.security.usecure.Services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Provides operations to manage users
 */

@RestController
@RequestMapping("/users")
@Api("Provides operations to manage users")
public class UsersController {

    @Autowired private UserService _userService;

    @GetMapping
    @ApiOperation("Retrieves a list of users")
    public ResponseEntity getUsers() {
        Collection<UserDto> users = _userService.GetAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping({"/userId"})
    @ApiOperation("Retrieves a single user by id")
    public ResponseEntity getUsers(@PathVariable long userId) {
        UserDto user = _userService.getById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    @ApiOperation("Deletes a user")
    public ResponseEntity<?> Delete(@RequestParam long userId) {
        _userService.DeleteUser(userId);
        return ResponseEntity.ok("User deleted");
    }
}
