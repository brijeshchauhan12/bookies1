package com.ma.bookies1.controller;
import com.ma.bookies1.entity.User;
import com.ma.bookies1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {


    @GetMapping

    public ResponseEntity<String> authenticatedUser() {

        return ResponseEntity.ok("For Admins Only");
    }

//    @GetMapping
//    @CrossOrigin(origins = "http://localhost:3000")
//    public ResponseEntity<List<User>> allUsers() {
//        List <User> users = userService.allUsers();
//
//        return ResponseEntity.ok(users);
//    }
}