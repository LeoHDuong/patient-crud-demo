package com.springboot.patientdemo.controller;

import com.springboot.patientdemo.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) throws Exception {
        String username = request.get("username");
        String password = request.get("password");

        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Generate JWT token
        String token = JwtTokenUtil.generateToken(userDetails.getUsername());

        // Create response
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}