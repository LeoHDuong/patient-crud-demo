package com.springboot.patientdemo.web;

import com.springboot.api.AuthApiDelegate;
import com.springboot.dto.LoginResponse;
import com.springboot.dto.LoginRequest;
import com.springboot.patientdemo.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthApiDelegateImpl implements AuthApiDelegate {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @SneakyThrows
    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);

        return ResponseEntity.ok(loginResponse);
    }

}
