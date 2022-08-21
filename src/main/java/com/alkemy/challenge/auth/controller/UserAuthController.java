package com.alkemy.challenge.auth.controller;

import com.alkemy.challenge.auth.dto.AuthRequest;
import com.alkemy.challenge.auth.dto.AuthResponse;
import com.alkemy.challenge.auth.dto.UserDto;
import com.alkemy.challenge.auth.service.JwtUtils;
import com.alkemy.challenge.auth.service.UserDetailsCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserAuthController {

    private final UserDetailsCustomService userDetailsCustomService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody UserDto dto){
        userDetailsCustomService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> singIn(@RequestBody AuthRequest authRequest) throws Exception{

        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        }catch (BadCredentialsException e){
            throw new Exception("Username or Password Incorrect", e);
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
