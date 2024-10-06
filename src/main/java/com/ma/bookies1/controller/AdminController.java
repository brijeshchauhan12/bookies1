package com.ma.bookies1.controller;
import com.ma.bookies1.dto.UserShow;
import com.ma.bookies1.entity.User;
import com.ma.bookies1.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping

    public ResponseEntity<String> authenticatedUser() {

        return ResponseEntity.ok("For Admins Only");
    }

    @GetMapping("/allUsers")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<?>> allUsers() {

        List <?> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/allBooks")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<?>> allBooks() {

        List <?> books = userService.allBooks();

        return ResponseEntity.ok(books);
    }


}