package com.ntvspace.security.usecure.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HomeController {

//    @GetMapping
//    public String home() {
//        return "Welcome home";
//    }

    @GetMapping
    public Principal GetPrincipal(Principal principal) {
        return principal;
    }
}
