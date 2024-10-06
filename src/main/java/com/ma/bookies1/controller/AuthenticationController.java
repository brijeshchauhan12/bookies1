package com.ma.bookies1.controller;
import com.ma.bookies1.dto.LoginUserDto;
import com.ma.bookies1.dto.RegisterUserDto;
import com.ma.bookies1.entity.Role;
import com.ma.bookies1.entity.User;
import com.ma.bookies1.responses.LoginResponse;
import com.ma.bookies1.service.AuthenticationService;
import com.ma.bookies1.service.JwtService;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {

        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        Set<Role> userRoles = authenticatedUser.getRoles();
        String rolss="";
        for(Role role: userRoles){
            rolss+="*"+role.getName();
        }
        System.out.println(authenticatedUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime())
                .setRole(rolss);

        return ResponseEntity.ok(loginResponse);
    }
}