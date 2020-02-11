package com.example.obras.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.obras.message.request.LoginForm;
import com.example.obras.message.request.SignUpForm;
import com.example.obras.message.response.JwtResponse;
import com.example.obras.model.Account;
import com.example.obras.security.jwt.JwtProvider;
import com.example.obras.services.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
    	
        if(accountService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Account acc = new Account(signUpRequest.getName(), signUpRequest.getEmail(), 
        							encoder.encode(signUpRequest.getPassword()));
        
        //User user = new User(signUpRequest.getName(), signUpRequest.getEmail());
       
        accountService.addAccount(acc);
        //userService.addUser(user);
        return ResponseEntity.ok().body("User registered successfully!");
    }
}