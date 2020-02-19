package com.ntvspace.security.usecure.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("admin")
    public String Admin() {
        return "Admin resource";
    }

    @GetMapping("test")
    public String Test() {
        return " Marvitzo Test resource";
    }

    @GetMapping
    public ResponseEntity GetPrincipal(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}


